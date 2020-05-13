<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">个人简介</block>
		</cu-custom>
		<form @submit="submitForm">
			<view class="cu-form-group margin-top">
				<textarea v-model="form.intro" maxlength="200" @input="textareaAInput" placeholder="请输入个人介绍"></textarea>
			</view>
			<view class="padding flex flex-direction">
				<button class="cu-btn bg-green margin-tb-sm lg" form-type="submit">保 存</button>
			</view>
		</form>
	</view>
</template>

<script>
	
	import {
		getUserExt,
		updateUserExt
	} from '@/api/system/user.js'
	
	export default {
		name: '',
		data() {
			return {
				form: {}
			}
		},
		onLoad() {
			getUserExt().then(res => {
				this.form = res.data
			})
		},
		methods: {
			submitForm() {
				updateUserExt(this.form).then(res => {
					if(res.code === 200) {
						this.msgSuccess('修改成功')
						uni.navigateBack({
							delta: 1
						})
					}
				})
			}
		}
	}
</script>

<style>
</style>
