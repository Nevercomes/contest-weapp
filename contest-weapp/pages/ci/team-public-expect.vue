<template>
	<view class="app-container">
		<form @submit="submitAddForm">
			<view class="cu-form-group margin-top">
				<view class="title">比赛</view>
				<view class="input-picker" @click="goToCompSelectPage">
					<input v-model="form.cpName" name="cpName" disabled placeholder="请选择比赛" />
				</view>
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
				<button class="cu-btn bg-green margin-tb-sm lg  shadow-blur round" form-type="submit">发布组队期望信息</button>
			</view>
		</form>
		<nl-mask-loading :loading="loading"></nl-mask-loading>
	</view>
</template>

<script>
	import WxValidate from '@/utils/WxValidate.js'

	import {
		addExpect
	} from '@/api/ci/expect.js'

	export default {
		name: 'TeamPublicExpect',
		data() {
			return {
				// loading
				loading: false,
				// 数据
				form: {},
				rules2: {
					cpName: {
						required: true
					},
					workLabel: {
						required: true
					}
				},
				messages2: {
					cpName: {
						required: '比赛不能为空'
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
		onLoad() {
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
					cpId: undefined,
					cpName: undefined
				}
			},
			submitAddForm() {
				if (this.validAddForm(this.form)) {
					// 发布加入信息
					this.loading = true
					this.form.capability = this.capabilityList.join(',')
					addExpect(this.form).then(res => {
						this.loading = false
						uni.navigateTo({
							url: 'team-public-expect-success?cpId=' + this.form.cpId
						})
					})
				}
			},
			// 验证加入意愿信息表单
			validAddForm(params) {
				let wxValidate = new WxValidate(this.rules2, this.messages2)
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
