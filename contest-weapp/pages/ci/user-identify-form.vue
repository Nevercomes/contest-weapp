<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">实名认证</block>
		</cu-custom>
		<!-- 表单 -->

		<form @submit="submitForm">
			<view class="cu-form-group margin-top">
				<view class="title">姓名</view>
				<input v-model="form.trueName" name="trueName" placeholder="请输入真实姓名" confirm-type="next" />
			</view>
			<view class="cu-form-group">
				<view class="title">学校</view>
				<view class="input-picker" @click="goToSchoolList">
					<input v-model="form.schoolName" name="schoolName" disabled placeholder="请选择学校" />
				</view>
			</view>
			<view class="cu-form-group">
				<view class="title">专业</view>
				<input v-model="form.major" name="major" placeholder="请输入专业" confirm-type="next" />
			</view>
			<view class="cu-form-group">
				<view class="title">年级</view>
				<picker @change="pickerChange" :value="index" :range="gradeOptionsPicker">
					<input v-model="form.gradeLabel" name="gradeLabel" disabled placeholder="请选择年级" />
				</picker>
			</view>

			<view class="padding flex flex-direction">
				<button class="cu-btn bg-green margin-tb-sm lg" form-type="submit">提 交</button>
			</view>
		</form>

		<!-- 年级picker -->

		<nl-mask-loading :loading="loading" :loadingMsg="'提交中...'"></nl-mask-loading>

	</view>
</template>

<script>
	import NlMaskLoading from '@/nl_componet/nl-mask-loading.vue'
	import WxValidate from '@/utils/WxValidate.js'
	import {
		identify
	} from '@/api/system/user.js'

	export default {
		name: 'UserIdentifyForm',
		components: {
			NlMaskLoading
		},
		data() {
			return {
				// 表单提交加载
				loading: false,
				// 表单数据
				form: {},
				// 校验规则
				rules: {
					trueName: {
						required: true
					},
					schoolName: {
						required: true
					},
					major: {
						required: true
					},
					gradeLabel: {
						required: true
					}
				},
				// 错误提示
				messages: {
					trueName: {
						required: '真实姓名不能为空'
					},
					schoolName: {
						required: '学校不能为空'
					},
					major: {
						required: '专业不能为空'
					},
					gradeLabel: {
						required: '年级不能为空'
					}
				},
				// 年级选项
				gradeOptions: [],
				// 显示的年级选项
				gradeOptionsPicker: [],
				// 年级picker显示
				showPicker: false
			}
		},
		onLoad() {
			this.getDicts('sys_user_grade').then(res => {
				this.gradeOptions = res.data
				this.gradeOptionsPicker = res.data.map(item => item.dictLabel)
			})
		},
		methods: {
			reset() {
				this.form = {
					trueName: undefined,
					schoolId: undefined,
					major: undefined,
					grade: undefined,
					schoolName: undefined,
					gradeLabel: undefined
				}
			},
			pickerChange(e) {
				const index = e.target.value
				this.form.grade = this.gradeOptions[index].dictValue
				this.form.gradeLabel = this.gradeOptions[index].dictLabel
				this.$forceUpdate()
			},
			submitForm() {
				// if (this.validForm(this.form)) {
					// 调用提交方法
					this.loading = true
					identify(this.form).then(res => {
						this.loading = false
						this.msgSuccess('实名认证成功')
					}).catch(() => {
						this.loading = false
					})
				// }
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
			goToSchoolList() {
				uni.navigateTo({
					url: '/pages/ci/sys-school-list'
				})
			}
		}
	}
</script>

<style>
</style>
