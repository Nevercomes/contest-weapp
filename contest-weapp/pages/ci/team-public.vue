<!-- 队伍创建、发布 -->
<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">队伍</block>
		</cu-custom>
		<!-- 队伍创建 -->
		<van-tabs :active="active" bind:change="onChange">
			<van-tab title="创建队伍">
				<view class="bg-white padding">
					<view class="cu-steps">
						<view class="cu-item" :class="index>step?'':'text-red'" v-for="(item,index) in stepList" :key="index">
							<text :class="'cuIcon-' + item.cuIcon"></text> {{item.name}}
						</view>
					</view>
				</view>
				<!-- 步骤一，选择比赛，填写队伍需知 -->
				<form @submit="submitForm">
					<view class="cu-form-group margin-top">
						<view class="title">比赛</view>
						<view class="input-picker" @click="goToCompSelectPage">
							<input v-model="form.compName" name="compName" disabled placeholder="请选择比赛" />
						</view>
					</view>
					<view class="cu-form-group margin-top">
						<view class="title">名称</view>
						<input v-model="form.name" name="name" type="number" placeholder="请输入队伍名称" confirm-type="next">
					</view>
					<view class="cu-form-group">
						<view class="title">队伍人数</view>
						<input v-model="form.teamNumber" name="teamNumber" type="number" placeholder="请输入队伍总人数" confirm-type="next"></input>
					</view>
					<view class="cu-form-group">
						<view class="title">招募人数</view>
						<input v-model="form.recruitNumber" name="recruitNumber" type="number" placeholder="请输入队伍招募人数" confirm-type="submit"></input>
					</view>
					<view class="padding flex flex-direction">
						<button class="cu-btn bg-green margin-tb-sm lg" form-type="submit">下一步</button>
					</view>
				</form>
				<view></view>
			</van-tab>
			<van-tab title="发布加入">
				<form @submit="submitAddForm">
					<view class="cu-form-group margin-top">
						<view class="title">比赛</view>
						<view class="input-picker" @click="goToCompSelectPage">
							<input v-model="form.compName" name="compName" disabled placeholder="请选择比赛" />
						</view>
					</view>
					<view class="cu-form-group">
						<view class="title">期望分工</view>
						<picker @change="pickerChange" :value="index" :range="workOptionsPicker">
							<input v-model="form.workLabel" name="workLabel" disabled placeholder="请选择分工" />
						</picker>
					</view>
					<view class="cu-form-group">
						<view class="title">个人能力</view>
						<view v-for="(item,index) in capabilityList" :key="index" class='cu-tag radius' @click="onNotSelectClick(item)">{{item}}</view>
					</view>
					<view class="padding flex flex-direction">
						<button class="cu-btn bg-green margin-tb-sm lg" form-type="submit">发布加入信息</button>
					</view>
				</form>
			</van-tab>
		</van-tabs>
	</view>
</template>

<script>
	import WxValidate from '@/utils/WxValidate.js'
	
	import {
		addExpect
	} from '@/api/ci/expect.js'

	export default {
		name: 'TeamPublic',
		data() {
			return {
				// tab激活页
				active: 0,
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
				form: {},
				rules2: {
					compName: {
						required: true
					},
					workLabel: {
						required: true
					}
				},
				messages2: {
					compName: {
						required: '比赛不能为空'
					},
					workLabel: {
						required: '期望分工不能为空'
					}
				},
				rules1: {
					compName: {
						required: true
					},
					teamNumber: {
						required: true
					},
					recruitNumber: {
						required: true
					},
					name: {
						required: true,
						maxlengtn: 10
					}
				},
				messages1: {
					compName: {
						required: '比赛不能为空'
					},
					teamNumber: {
						required: '队伍人数不能为空'
					},
					recruitNumber: {
						required: '招募人数不能为空',
						maxlengtn: '名字太长啦'
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
		onLoad() {
			
		},
		methods: {
			reset() {
				this.form = {
					cpId: undefined,
					compName: undefined,
					teamNumber: undefined,
					recruitNumber: undefined
				}
			},
			submitForm() {
				if (this.validForm(this.form)) {
					this.step = 1
				}
			},
			submitAddForm() {
				if(this.validAddForm(this.form)) {
					// 发布加入信息
					addExpect(this.form).then(res => {
						uni.navigateTo({
							url: 'team-public-expect-success?cpId=' + this.form.cpId
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
			// 验证加入意愿信息表单
			validAddForm() {
				let wxValidate = new WxValidate(this.rules2, this.messages2)
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
					url: 'competition-select?cpId=' + this.form.cpId + "&teamNumber=" + this.form.teamNumber + "&recruitNumber=" +
						this.form.recruitNumber
				})
			}
		}
	}
</script>

<style>
</style>
