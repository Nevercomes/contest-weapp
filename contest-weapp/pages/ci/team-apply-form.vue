<template>
	<view class="app-container">
		<form @submit="submitForm">
			<view class="cu-form-group margin-top">
				<view class="title">队伍</view>
				<input v-model="form.teamName" name="teamName" disabled placeholder="没有选择队伍" />
			</view>
			<view class="cu-form-group">
				<view class="title">期望分工</view>
				<picker @change="pickerChange" :value="index" :range="workOptionsPicker">
					<input v-model="form.workLabel" name="workLabel" disabled placeholder="请选择分工" />
				</picker>
			</view>
			<view class="form-group-capcity solid-top">
				<view class="title">个人能力</view>
				<view v-for="(item,index) in capabilityList" :key="index" class='cu-tag radius' @click="onNotSelectClick(item)">{{item}}</view>
			</view>
		
			<!-- 默认的能力选择label -->
			<view class="box bg-white padding solid-top">
				<view v-for="(item,index) in capabilityLabelOptions" :key="index" class='cu-tag round margin-xs' @click="onSelectClick(item)">{{item}}</view>
			</view>
		
			<view class="padding flex flex-direction">
				<button class="cu-btn bg-green margin-tb-sm lg  shadow-blur round" form-type="submit">发送加入申请</button>
			</view>
		</form>
		<nl-mask-loading :loading="loading"></nl-mask-loading>
	</view>
</template>

<script>
	
	import WxValidate from '@/utils/WxValidate.js'
	
	import {
		addApply
	} from '@/api/ci/apply.js'
	
	export default {
		name: 'TeamApplyForm',
		data() {
			return {
				// loading
				loading: false,
				// 数据
				form: {},
				rules: {
					teamName: {
						required: true
					},
					workLabel: {
						required: true
					}
				},
				messages: {
					teamName: {
						required: '队伍不能为空'
					},
					workLabel: {
						required: '期望分工不能为空'
					}
				},
				workOptions: [],
				workOptionsPicker: [],
				// 默认能力期望标签
				capabilityOptions: [],
				capabilityLabelOptions: [],
				// 个人能力
				capabilityList: [],
			}
		},
		onLoad(options) {
			this.form.teamId = options.teamId
			this.form.teamName = options.teamName
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
					teamId: undefined,
					teamNumber: undefined,
					work: undefined,
					capability: undefined
				}
			},
			submitForm() {
				if (this.validForm(this.form)) {
					// 发布加入信息
					this.loading = true
					this.form.capability = this.capabilityList.join(',')
					addApply(this.form).then(res => {
						this.loading = false
						uni.navigateTo({
							url: 'team-apply'
						})
					}).catch(() => {
						this.loading = false
					})
				}
			},
			// 验证加入意愿信息表单
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
			pickerChange(e) {
				const index = e.target.value
				this.form.work = this.workOptions[index].dictValue
				this.form.workLabel = this.workOptions[index].dictLabel
				this.$forceUpdate()
			},
			// 跳转到竞赛选取页面
			goToCompSelectPage() {
				uni.navigateTo({
					url: 'competition-select'
				})
			}
		}
	}
</script>

<style>
</style>
