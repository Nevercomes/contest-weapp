<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">操作条</block>
		</cu-custom>
		<form @submit="submitForm">
			<view class="cu-form-group margin-top">
				<view class="title">姓名</view>
				<input v-model="form.trueName" name="trueName" placeholder="请输入真实姓名" confirm-type="next"></input>
			</view>
			<view class="padding flex flex-direction">
				<button class="cu-btn bg-green margin-tb-sm lg" form-type="submit">提 交</button>
			</view>
		</form>
	</view>
</template>

<script>
	import WxValidate from '@/utils/WxValidate.js'

	export default {
		name: '',
		data() {
			return {
				// 表单数据
				form: {},
				// 校验规则
				rules: {
					trueName: {
						required: true
					}
				},
				// 错误提示
				messages: {
					trueName: {
						required: '真实姓名不能为空'
					}
				}
			}
		},
		onLoad() {

		},
		methods: {
			reset() {
				this.form = {

				}
			},
			submitForm(e) {
				if (this.validForm(e)) {
					// 调用提交方法
				}
			},
			validForm(params) {
				let wxValidate = new WxValidate(this.rules, this.messages)
				if (!wxValidate.checkForm(params)) {
					const error = this.WxValidate.errorList[0]
					uni.showToast({
						title: error
					})
					return false
				}
				return true
			}
		}
	}
</script>

<style>
</style>
