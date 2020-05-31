<template>
	<view class="app-container">
		<form @submit="submitForm">
			<view class="cu-form-group margin-top">
				<textarea v-model="form.intro" maxlength="200" placeholder="请输入个人介绍" show-confirm-bar></textarea>
			</view>
			<view class="padding flex flex-direction">
				<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" form-type="submit">保 存</button>
			</view>
		</form>
	</view>
</template>

<script>
	import {
		getExt,
		addExt,
		updateExt
	} from '@/api/system/ext.js'

	export default {
		name: 'UserDetailIntro',
		data() {
			return {
				form: {}
			}
		},
		onLoad() {
			getExt().then(res => {
				if (res.data) {
					this.form = res.data
				}
			})
		},
		methods: {
			reset() {
				this.form = {
					userId: undefined,
					intro: undefined
				}
			},
			// textareaInput(e) {
			// 	this.form.intro = e.detail.value
			// },
			submitForm() {
				if (this.form.userId) {
					updateExt(this.form).then(res => {
						if (res.code === 200) {
							this.msgSuccess('修改成功')
							this.back()
						}
					})
				} else {
					addExt(this.form).then(res => {
						if (res.code === 200) {
							this.msgSuccess('修改成功')
							this.back()
						}
					})
				}
			}
		}
	}
</script>

<style>
</style>
