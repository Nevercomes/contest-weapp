<template>
	<view class="app-container">
		<form @submit="submitForm">
			<view class="cu-form-group margin-top">
				<view class="title">昵称</view>
				<input v-model="form.nickName" name="nickName" placeholder="请输入昵称" confirm-type="done" />
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
		updateUserProfile
	} from '@/api/system/user.js'
	
	export default {
		name: 'UserProfileNickname',
		data() {
			return {
				loading: false,
				form: {},
				rules: {
					nickName: {
						required: true
					},
				},
				// 错误提示
				messages: {
					nickName: {
						required: '昵称不能为空'
					},
				},
				// 原始的nickName
				nickName: ''
			}
		},
		onLoad(options) {
			this.nickName = options.nickName
			this.form.nickName = this.nickName
		},
		methods: {
			reset() {
				this.form = {
					nickName: this.nickName
				}
			},
			submitForm() {
				if (this.validForm(this.form)) {
					// 调用提交方法
					this.loading =  true
					updateUserProfile(this.form).then(res => {
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
		}
	}
</script>

<style>
</style>
