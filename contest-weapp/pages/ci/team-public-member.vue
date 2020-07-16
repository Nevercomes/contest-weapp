<template>
	<view class="app-container">

		<!-- <view class="bg-white padding">
			<view class="cu-steps">
				<view class="cu-item" :class="index>step?'':'text-red'" v-for="(item,index) in stepList" :key="index">
					<text :class="'cuIcon-' + item.cuIcon"></text> {{item.name}}
				</view>
			</view>
		</view> -->
		
		<van-steps :steps="stepList" :active="step" />

		<view class="cu-card article padding no-card bg-white" v-for="(item,index) in recruitList" :key="index">
			<view class="cu-item shadow solid-bottom">
				<view class="">队员分工：{{item.workLabel}}</view>
				<view class="margin-top">能力期望：
					<view v-for="(cap,capIndex) in item.capabilityList" :key="capIndex" class='cu-tag round'>{{cap}}</view>
				</view>
			</view>
		</view>

		<view v-if="recruitList == undefined || recruitList.length == 0"  class="flex-sub text-center">
			<view class="solid-top text-df padding">
				<text class="text-gray">添加队友招募信息更容易找到心仪队友哦</text>
			</view>
		</view>

		<view class="cu-bar btn-group">
			<button class="cu-btn text-green line-green round shadow-blur" @click="onAddClick">添加招募信息</button>
		</view>

		<view class="padding flex flex-direction">
			<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" @click="submitForm">创建队伍</button>
		</view>
		<nl-mask-loading :loading="loading" :loadingMsg="'提交中...'"></nl-mask-loading>
	</view>
</template>

<script>
	import WxValidate from '@/utils/WxValidate.js'
	import NlMaskLoading from '@/nl_componet/nl-mask-loading.vue'
	import {
		addTeamInfo,
		getTeamInfo
	} from '@/api/ci/team.js'
	import {
		addRecruit
	} from '@/api/ci/recruit.js'

	export default {
		name: 'TeamPublicMember',
		components: {
			NlMaskLoading
		},
		data() {
			return {
				loading: false,
				// 步骤进度
				step: 1,
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
				
				// 表单数据
				form: {},
				// 招募队友列表
				recruitList: [],
				// 计划招募的人数
				recruitNumber: undefined,
				// 校验规则
				rules: {
					id: {
						required: true
					}
				},
				// 错误提示
				messages: {
					id: {
						required: '无队伍信息'
					}
				},
			}
		},
		onLoad(options) {
			this.form.id = options.teamId
			this.recruitNumber = options.recruitNumber
			this.getFormData()
		},
		methods: {
			reset() {
				this.form = {
					
				}
			},
			getFormData() {
				getTeamInfo(this.form.id).then(res => {
					this.form = res.data
				})
			},
			submitForm(e) {
				if (this.validForm(this.form)) {
					// 提交队伍信息
					this.loading = true
					// 将能力期望转化成,连接
					// addTeamInfo(this.form).then(res => {
					this.loading = false
					// 跳转到成功页面,传入对应的竞赛id
					const teamId = this.form.id
					this.recruitList.forEach(item => {
						let recruit = {
							teamId: teamId,
							work: item.workId,
							capacity: item.capabilityList.join(',')
						}
						addRecruit(recruit)
					})
					uni.navigateTo({
						url: 'team-public-success?cpId=' + this.form.cpId + '&teamId=' + this.form.id
					})
					// })
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
			onAddClick() {
				if (this.recruitList && this.recruitList.length < this.recruitNumber) {
					let index = this.recruitList.length + 1
					uni.navigateTo({
						url: './team-public-member-form?recruitNumber=' + this.recruitNumber + "&index=" + index
					})
				} else {
					this.msgInfo('期望人数不能大于招募数')
				}
			}
		}
	}
</script>

<style>
</style>
