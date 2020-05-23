<template>
	<view class="app-container">

		<view class="bg-white padding">
			<view class="cu-steps">
				<view class="cu-item" :class="index>step?'':'text-red'" v-for="(item,index) in stepList" :key="index">
					<text :class="'cuIcon-' + item.cuIcon"></text> {{item.name}}
				</view>
			</view>
		</view>

		<view class="cu-card article padding no-card" v-for="(item,index) in recruitList" :key="index">
			<view class="cu-item shadow">
				<view class="">队员分工：{{item.workLabel}}</view>
				<view class="margin-top">能力期望：
					<view v-for="(cap,capIndex) in item.capabilityList" :key="capIndex" class='cu-tag round'>{{cap}}</view>
				</view>
			</view>
		</view>

		<view class="cu-bar btn-group margin-top">
			<button class="cu-btn text-green line-green round shadow-blur" @click="onAddClick">添加队员</button>
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
		addTeamInfo
	} from '@/api/ci/team.js'

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
				stepList: [{
					cuIcon: 'usefullfill',
					name: '队伍信息'
				}, {
					cuIcon: 'radioboxfill',
					name: '队员信息'
				}, {
					cuIcon: 'roundcheckfill',
					name: '创建完成'
				}],
				// 表单数据
				form: {},
				// 招募队友列表
				recruitList: [],
				// 计划招募的人数
				recruitNumber: undefined,
				// 校验规则
				rules: {
					cpId: {
						required: true
					}
				},
				// 错误提示
				messages: {
					cpId: {
						required: '未选择竞赛'
					}
				},
			}
		},
		onLoad(options) {
			this.form.cpId = options.cpId
			this.form.teamNumber = options.teamNumber
			this.recruitNumber = options.recruitNumber
		},
		methods: {
			reset() {
				this.form = {
					
				}
			},
			submitForm(e) {
				if (this.validForm(e)) {
					// 提交队伍信息
					this.loading = true
					// 将能力期望转化成,连接
					this.form.expect = this.form.capabilityList.join(',')
					addTeamInfo(this.form).then(res => {
						this.loading = false
						// 跳转到成功页面,传入对应的竞赛id
						uni.navigateTo({
							url: 'team-public-success?compId=' + this.form.cpId
						})
					})
				}
			},
			validForm(params) {
				let wxValidate = new WxValidate(this.rules, this.messages)
				if (!wxValidate.checkForm(params)) {
					const error = wxValidate.errorList[0]
					uni.showToast({
						title: error
					})
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
