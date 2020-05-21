<template>
	<view class="app-container">
		<form @submit="submitForm">
			<view class="cu-form-group margin-top">
				<view class="title">竞赛名称</view>
				<input v-model="form.name" name="name" placeholder="请输入竞赛名称" confirm-type="next"></input>
			</view>
			<view class="cu-form-group ">
				<view class="title">参加时间</view>
				<view class="input-picker" @click="show = true">
					<input v-model="form.time" name="time" disabled placeholder="请选择参加时间" />
				</view>
			</view>
			<view class="cu-form-group ">
				<view class="title">队内分工</view>
				<input v-model="form.work" name="work" placeholder="请输入队内分工" confirm-type="next"></input>
			</view>
			<view class="cu-form-group ">
				<view class="title">获得奖项</view>
				<input v-model="form.awards" name="awards" placeholder="请输入获得奖项" confirm-type="done"></input>
			</view>
			<view class="padding flex flex-direction">
				<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" form-type="submit">保 存</button>
			</view>
		</form>
		<!-- <van-calendar :show="show" :min-date="minDate" :max-date="maxDate" show-confirm="false" round="false" @confirm="onCalConfirm" @close="show = false" /> -->
		<van-popup :show="show" position="bottom">
			<van-datetime-picker type="date" :value="currentDate" :min-date="minDate" :max-date="maxDate" :formatter="formatter"
			 @confirm="onInput" @cancel="show = false" />
		</van-popup>

	</view>
</template>

<script>
	import WxValidate from '@/utils/WxValidate.js'
	import {
		getExperience,
		addExperience,
		updateExperience
	} from '@/api/ci/experience.js'

	export default {
		name: 'UserDetailExperienceForm',
		computed: {
			minDate: function() {
				// 今天的四年前
				const date = new Date()
				return date.getTime() - (1000 * 60 * 60 * 24 * 365 * 6)
			},
			maxDate: function() {
				// 今天
				let date = new Date()
				return date.getTime()
			}
		},
		data() {
			return {
				// 日历显示
				show: false,
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
				},
				currentDate: new Date().getTime(),
				formatter(type, value) {
					if (type === 'year') {
						return `${value}年`;
					} else if (type === 'month') {
						return `${value}月`;
					}
					return value;
				}
			}
		},
		onLoad(options) {
			if (options.id) {
				getExperience(options.id).then(res => {
					this.form = res.data
				})
			}
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
								this.back()
							} else {
								this.msgInfo(response.msg);
							}
						});
					} else {
						addExperience(this.form).then(response => {
							if (response.code === 200) {
								this.msgSuccess("新增成功");
								this.back()
							} else {
								this.msgInfo(response.msg);
							}
						});
					}
				}
			},
			validForm(params) {
				let wxValidate = new WxValidate(this.rules, this.messages)
				console.log(params)
				if (!wxValidate.checkForm(params)) {
					const error = wxValidate.errorList[0]
					this.msgInfo(error.msg)
					return false
				}
				return true
			},
			onCalConfirm(e) {
				this.show = false
				this.form.time = this.formatDate(e.detail)
			},
			onInput(e) {
				this.currentDate = e.detail
				this.form.time = this.formatDate(e.detail)
				this.show = false
			},
			formatDate(date) {
				date = new Date(date);
				return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
			}
		}
	}
</script>

<style>
</style>
