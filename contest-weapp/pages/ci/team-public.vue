<!-- 队伍创建、发布 -->
<template>
	<view class="app-container">
		<!-- 队伍创建 -->
		<!-- <view class="bg-white padding">
			<view class="cu-steps">
				<view class="cu-item" :class="index>step?'':'text-red'" v-for="(item,index) in stepList" :key="index">
					<text :class="'cuIcon-' + item.cuIcon"></text> {{item.name}}
				</view>
			</view>
		</view> -->

		<van-steps :steps="stepList" :active="step" />

		<!-- 步骤一，选择比赛，填写队伍需知 -->
		<form @submit="submitForm">
			<view class="cu-form-group margin-top">
				<view class="title">比赛</view>
				<view class="input-picker" @click="goToCompSelectPage">
					<input v-model="form.cpName" name="cpName" disabled placeholder="请选择比赛" />
				</view>
			</view>
			<view class="cu-form-group">
				<view class="title">名称</view>
				<input v-model="form.name" name="name" placeholder="请输入队伍名称" confirm-type="next">
			</view>
			<view class="cu-form-group ">
				<view class="title">介绍</view>
				<textarea v-model="form.intro" maxlength="50" placeholder="请输入队伍介绍" show-confirm-bar></textarea>
			</view>
			<view class="cu-form-group">
				<view class="title">招募人数</view>
				<input v-model="form.recruitNumber" name="recruitNumber" type="number" placeholder="请输入队伍招募人数" confirm-type="submit"></input>
			</view>
			<view class="padding flex flex-direction">
				<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" form-type="submit">下一步</button>
			</view>
		</form>
	</view>
</template>

<script>
	import WxValidate from '@/utils/WxValidate.js'

	import {
		addTeamInfo
	} from '@/api/ci/team.js'

	export default {
		name: 'TeamPublic',
		data() {
			return {
				// tab激活页
				active: 0,
				// 步骤条进度
				// stepList: [{
				// 	cuIcon: 'usefullfill',
				// 	name: '队伍信息'
				// }, {
				// 	cuIcon: 'radioboxfill',
				// 	name: '队员信息'
				// }, {
				// 	cuIcon: 'roundcheckfill',
				// 	name: '创建完成'
				// }],
				stepList: [{
						text: '队伍信息',
					},
					{
						text: '队员信息',
					},
					{
						text: '创建完成',
					},
				],
				form: {},
				rules1: {
					cpName: {
						required: true
					},
					name: {
						required: true,
						maxlengtn: 10
					},
					recruitNumber: {
						required: true
					},

				},
				messages1: {
					cpName: {
						required: '比赛不能为空'
					},
					name: {
						required: '队伍名不能为空',
						maxlengtn: '名字太长啦'
					},
					recruitNumber: {
						required: '招募人数不能为空',
					}
				},
				// 步骤进度
				step: 0,
				// 分工选项
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
			if (options.cpId) {
				this.form.cpId = options.cpId
				this.form.cpName = options.cpName
			}
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
					cpName: undefined,
					teamNumber: undefined,
					recruitNumber: undefined
				}
			},
			submitForm() {
				if (this.validForm(this.form)) {
					addTeamInfo(this.form).then(res => {
						this.msgInfo('队伍信息已添加')
						uni.navigateTo({
							url: 'team-public-member?teamId=' + res.data.id + '&recruitNumber=' +
								this.form.recruitNumber
						})
					})
				}
			},
			// 验证form1
			validForm(params) {
				let wxValidate = new WxValidate(this.rules1, this.messages1)
				if (!wxValidate.checkForm(params)) {
					const error = wxValidate.errorList[0]
					this.msgInfo(error.msg)
					return false
				}
				return true
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
