<template>
	<view class="app-container">
		<view class="cu-bar bg-white">
			<view class="action">
				<text class="cuIcon-title text-green"></text>
				<text>队员-{{form.assUser.nickName}}-信息</text>
			</view>
		</view>
		<form @submit="submitForm">
			<view class="cu-form-group">
				<view class="title">分工</view>
				<picker @change="pickerChange" :value="index" :range="workOptionsPicker">
					<input v-model="form.workLabel" name="workLabel" disabled placeholder="请选择分工" />
				</picker>
			</view>
			<view class="cu-form-group">
				<view class="title">能力期望</view>
				<view v-for="(item,index) in capabilityList" :key="index" class='cu-tag round' @click="onNotSelectClick(item)">{{item}}</view>
			</view>
		</form>

		<!-- 默认的能力选择label -->
		<view class="box bg-white padding-top-xs" style="height: 130upx;">
			<view v-for="(item,index) in capabilityLabelOptions" :key="index" class='cu-tag round' @click="onSelectClick(item)">{{item}}</view>
		</view>

		<view class="padding flex flex-direction">
			<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" @click="submitForm">保 存</button>
		</view>
		
		<nl-mask-loading :loading="loading"></nl-mask-loading>

	</view>
</template>

<script>
	import WxValidate from '@/utils/WxValidate.js'

	import {
		getMember,
		updateMember
	} from '@/api/ci/member.js'

	export default {
		name: 'TeamMemberForm',
		data() {
			return {
				// loading
				loading: false,
				// 队员数据
				form: {},
				// 队员能力期望
				capabilityList: [],
				// 分工选项
				workOptions: [],
				workOptionsPicker: [],
				// 默认能力期望标签
				capabilityOptions: [],
				capabilityLabelOptions: [],
				rules: {
					workLabel: {
						required: true
					}
				},
				messages: {
					workLabel: {
						required: '队员分工不能为空'
					}
				}
			}
		},
		onLoad(options) {
			this.form.id = options.id
			this.getFormData()
			this.getDicts('team_work_type').then(res => {
				this.workOptions = res.data
				this.workOptionsPicker = res.data.map(item => item.dictLabel)
			})
			this.getDicts('team_capability_default').then(res => {
				this.capabilityOptions = res.data
				this.capabilityLabelOptions = res.data.map(item => item.dictLabel)
			})
		},
		methods: {
			reset() {
				this.form = {
					id: undefined,
					work: undefined,
					workLabel: undefined,
				}
				this.capabilityList = []
				this.capabilityLabelOptions = this.capabilityOptions.map(item => item.dictLabel)
			},
			getFormData() {
				getMember(this.form.id).then(res => {
					this.form = res.data
					this.form.workLabel = this.workFormat(this.form.work)
					this.$forceUpdate()
				})
			},
			pickerChange(e) {
				const index = e.target.value
				this.form.work = this.workOptions[index].dictValue
				this.form.workLabel = this.workOptions[index].dictLabel
				this.$forceUpdate()
			},
			submitForm() {
				// 保存数据，返回页面
				if (this.validForm(this.form)) {
					this.form.capacity = this.capabilityList.join(',')
					this.loading = true
					updateMember(this.form).then(res => {
						this.loading = false
						this.msgSuccess('修改成功')
						this.back()
					}).catch(() => {
						this.loading = false
					})
				}
			},
			validForm(params) {
				let wxValidate = new WxValidate(this.rules, this.messages)
				if (!wxValidate.checkForm(params)) {
					const error = wxValidate.errorList[0]
					this.msgInfo(error.msg)
					return false
				}
				return true
			},
			onNotSelectClick(label) {
				const idx = this.capabilityList.indexOf(label)
				if (idx != -1) {
					this.capabilityList.splice(idx, 1)
					this.capabilityLabelOptions.push(label)
				}
			},
			// TODO Label的动效
			onSelectClick(label) {
				if (this.capabilityList.length >= 3) {
					this.msgInfo('不能再多啦')
					return
				}
				const idx = this.capabilityLabelOptions.indexOf(label)
				if (idx != -1) {
					this.capabilityLabelOptions.splice(idx, 1)
					this.capabilityList.push(label)
				}
			},
			workFormat(value, dict) {
				return this.selectDictLabel(this.workOptions, value)
			}
		}
	}
</script>

<style>
</style>
