<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">新增竞赛经历</block>
		</cu-custom>
		<form @submit="submitForm">
			<view class="cu-form-group margin-top">
				<view class="title">竞赛名称</view>
				<input v-model="form.name" name="name" placeholder="请输入竞赛名称" confirm-type="next"></input>
			</view>
			<view class="cu-form-group margin-top">
				<view class="title">参加时间</view>
				<input v-model="form.time" name="time" placeholder="请选择参加时间" confirm-type="next" disabled="true" @click="show = true"></input>
			</view>
			<view class="cu-form-group margin-top">
				<view class="title">队内分工</view>
				<input v-model="form.work" name="work" placeholder="请输入队内分工" confirm-type="next"></input>
			</view>
			<view class="cu-form-group margin-top">
				<view class="title">获得奖项</view>
				<input v-model="form.awards" name="awards" placeholder="请输入获得奖项" confirm-type="done"></input>
			</view>
			<view class="padding flex flex-direction">
				<button class="cu-btn bg-green margin-tb-sm lg" form-type="submit">添加竞赛经历</button>
			</view>
		</form>
		<van-calendar :show="show" round="false" position="right" @confirm="onCalConfirm" />
	</view>
</template>

<script>
	import WxValidate from '../utils/WxValidate.js'
	import {
		getExperience,
		addExperience,
		updateExperience
	} from '@/api/ci/experience.js'

	export default {
		name: '',
		data() {
			return {
				// 表单数据
				form: {},
				// 校验规则
				rules: {
					name: {
						required: true
					},
					time: {
						required: true
					},
					work: {
						required: true
					},
					awards: {
						required: true
					}
				},
				// 错误提示
				messages: {
					name: {
						required: '竞赛名称不能为空'
					},
					time: {
						required: '参加时间不能为空'
					},
					work: {
						required: '队内分工不能为空'
					},
					awards: {
						required: '获得奖项不能为空'
					}
				}
			}
		},
		onLoad(options) {
			this.form.id = options.id
		},
		methods: {
			reset() {
				this.form = {
					id: undefined,
					name: undefined,
					time: undefined,
					work: undefined,
					awards: undefined
				}
			},
			submitForm(e) {
				if (this.validForm(this.form)) {
					if (this.form.id != undefined) {
					  updateExperience(this.form).then(response => {
					    if (response.code === 200) {
					      this.msgSuccess("修改成功");
					      uni.navigateBack({
					      	delta: 1
					      })
					    } else {
					      this.msgInfo(response.msg);
					    }
					  });
					} else {
					  addExperience(this.form).then(response => {
					    if (response.code === 200) {
					      this.msgSuccess("新增成功");
					      uni.navigateBack({
					      	delta: 1
					      })
					    } else {
					      this.msgInfo(response.msg);
					    }
					  });
					}
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
			},
			onCalConfirm(e) {
				this.show = false
				this.form.time = this.formatDate(e.detail)
			},
			formatDate(date) {
				date = new Date(date);
				return `${date.getMonth() + 1}/${date.getDate()}`;
			}
		}
	}
</script>

<style>
</style>
