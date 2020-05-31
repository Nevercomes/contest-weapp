<template>
	<view class="app-container">
		<form @submit="submitForm">
			<view class="cu-form-group margin-top">
				<view class="title">比赛</view>
				<!-- <view class="input-picker" @click="goToCompSelectPage"> -->
				<input v-model="form.cpName" name="cpName" disabled placeholder="请选择比赛" />
				<!-- </view> -->
			</view>
			<view class="cu-form-group ">
				<view class="title">名称</view>
				<input v-model="form.name" name="name" type="number" placeholder="请输入队伍名称" confirm-type="next">
			</view>
			<view class="cu-form-group ">
				<view class="title">介绍</view>
				<textarea v-model="form.intro" maxlength="50" placeholder="请输入队伍介绍" show-confirm-bar></textarea>
			</view>

			<view class="padding flex flex-direction">
				<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" form-type="submit">保 存</button>
			</view>
		</form>
	</view>
</template>

<script>
	import WxValidate from '@/utils/WxValidate.js'

	import {
		getTeamInfo,
		updateTeamInfo
	} from '@/api/ci/team.js'

	export default {
		name: 'TeamInfoForm',
		data() {
			return {
				loading: false,
				// 表单数据
				form: {},
				// 校验规则
				rules: {
					cpName: {
						required: true
					},
					name: {
						required: true
					}
				},
				// 错误提示
				messages: {
					cpName: {
						required: '竞赛不能为空'
					},
					name: {
						required: '队伍名称不能为空'
					}
				}
			}
		},
		onLoad(options) {
			this.form.id = options.id
			this.getFormData()
		},
		methods: {
			reset() {
				this.form = {
					cpId: undefined,
					cpName: undefined,
					intro: undefined
				}
			},
			getFormData() {
				getTeamInfo(this.form.id).then(res => {
					this.form = res.data
				})
			},
			submitForm() {
				if (this.validForm(this.form)) {
					this.loading = true
					updateTeamInfo(this.form).then(res => {
						this.loading = false
						this.msgSuccess('修改成功')
						this.back()
					}).catch(res => {
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
			}
		}
	}
</script>

<style>
</style>
