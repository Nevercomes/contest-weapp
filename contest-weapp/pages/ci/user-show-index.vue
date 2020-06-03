<template>
	<view class="app-container">

		<view class="info-top-right">
			<text class="cuIcon-more margin-bottom text-lg" @click="show = true"></text>
			<button open-type='share' class="cuIcon-share text-lg icon-btn"></button>
		</view>

		<view class="flex flex-direction padding justify-center align-center bg-white">
			<image :src="user.avatar" class="cu-avatar round xl"></image>
			<view class="text-gray margin-sm">{{user.nickName}}</view>
			<view class="text-gray text-sm">
				<text>{{user.trueName ? user.trueName : ''}}</text>
				<text>{{user.schoolName ? user.schoolName : ''}}</text>
			</view>
			<!-- 个人介绍 -->
			<view v-if="showSetting.showIntro == '1'" class="text-center text-gray" style="max-width: 300upx;">
				{{userExt.intro ? userExt.intro : ''}}
			</view>
		</view>
		<!-- 竞赛经历 -->
		<view v-if="showSetting.showExperience == '1'" class="">
			<view class="cu-bar bg-white">
				<view class="action">
					<text class="cuIcon-title text-green"></text>
					<text class="">竞赛经历</text>
				</view>
			</view>
			<view v-for="(item, index) in expList" :key="index" class="cu-timeline">
				<view class="cu-time">{{formatDate(item.time)}}</view>
				<view class="cu-item">
					<view class="content">
						<view class="solid-bottom text-lg">
							<text class="text-black">{{item.name}}</text>
						</view>
						<view class="margin-top">队内分工：{{item.work}}</view>
						<view class="margin-top">获得奖项：{{item.awards}}</view>
					</view>
				</view>
			</view>
			<view v-if="expList==undefined || expList.length==0" class="text-gray text-center padding">
				用户暂无添加竞赛经历
			</view>
		</view>
		<view v-else class="text-gray text-center padding">
			用户设置了无法查看其竞赛经历
		</view>

		<view class="cu-modal" :class="modalName=='RadioModal'?'show':''" @tap="hideModal">
			<view class="cu-dialog" @tap.stop="">
				<radio-group class="block" @change="RadioChange">
					<view class="cu-list menu text-left">
						<view class="cu-item" v-for="(item,index) in teamList" :key="index">
							<label class="flex justify-between align-center flex-sub">
								<view class="flex-sub text-cut">{{item.name}}</view>
								<radio class="round" :class="radioIndex==index?'checked':''" :checked="radio==index" :value="index"></radio>
							</label>
						</view>
					</view>
				</radio-group>
				<view class="cu-bar bg-white">
					<view class="action margin-0 flex-sub  solid-left" @tap="sendInvite">确定</view>
				</view>
			</view>
		</view>

		<!-- 底部操作 -->
		<van-action-sheet @cancel="show = false" @close="show = false" @select="onActionSelect" :show="show" :actions="actions"
		 cancel-text="取消" />


	</view>
</template>

<script>
	import {
		mapGetters
	} from 'vuex'

	import {
		getUserProfile
	} from '@/api/system/user.js'
	import {
		getShowSetting
	} from '@/api/ci/showSetting.js'
	import {
		getExt
	} from '@/api/system/ext.js'
	import {
		listExperience
	} from '@/api/ci/experience.js'
	import {
		listTeamInfo
	} from '@/api/ci/team.js'
	import {
		addInvite
	} from '@/api/ci/invite.js'

	export default {
		name: 'UserShowIndex',
		computed: {
			...mapGetters([
				'userId'
			])
		},
		data() {
			return {
				parUserId: undefined,
				// 是否有特定的队伍
				teamId: undefined,
				// 用户信息数据
				user: {},
				// 用户扩展信息
				userExt: {},
				// 竞赛经历
				expList: [],
				// 显示设置
				showSetting: {},
				// action-sheet
				actions: [],
				show: false,
				// 队伍列表modal
				modalName: null,
				teamList: [],
				radioIndex: 0
			}
		},
		onLoad(options) {
			this.parUserId = options.userId || ''
			this.teamId = options.teamId
			this.getData()
		},
		methods: {
			getData() {
				getUserProfile(this.parUserId).then(res => {
					this.user = res.data
				})
				getShowSetting(this.parUserId).then(response => {
					let showSetting = response.data
					this.showSetting = showSetting
					// 显示intro
					if (showSetting.showIntro == '1') {
						getExt(this.parUserId).then(res => {
							this.userExt = res.data
						})
					}
					// 显示竞赛经历
					if (showSetting.showExperience == '1') {
						listExperience({}, this.parUserId).then(res => {
							this.expList = res.rows
						})
					}
					this.setActions(showSetting.showNews)
				})
				listTeamInfo({}, 'create').then(res => {
					this.teamList = res.rows
				})
			},
			RadioChange(e) {
				this.radioIndex = e.detail.value
			},
			sendInvite() {
				this.teamId = this.teamList[this.radioIndex].id
				addInvite({
					teamId: this.teamId,
					invitedUserId: this.parUserId
				}).then(res => {
					uni.navigateTo({
						url: 'team-invite'
					})
				})
			},
			setActions(showNews) {
				let actions = []
				actions.push({
					'name': '查看动态',
					'disabled': showNews == '0',
					'description': showNews == '0' ? '无法查看动态' : ''
				})
				actions.push({
					'name': '邀请组队',
					'disabled': this.parUserId == this.userId,
					'description': this.parUserId == this.userId ? '无法邀请自己' : ''
				})
				this.actions = actions
			},
			onActionSelect(e) {
				const name = e.detail.name
				switch (name) {
					case '查看动态':
						// TODO 跳转到动态界面
						break;
					case '邀请组队':
						if (this.teamId) {
							addInvite({
								teamId: this.teamId,
								invitedUserId: this.parUserId
							}).then(res => {
								uni.navigateTo({
									url: 'team-invite'
								})
							})
						} else {
							// 展示队伍列表
							if (this.teamList && this.teamList.length > 0) {
								this.modalName = 'RadioModal'
							} else {
								this.msgInfo('你还没有创建任何队伍')
							}
						}
						break;
					default:
						break;
				}
			},
			onShareAppMessage() {
				uni.showShareMenu({
					withShareTicket: true
				});
				let self = this
				let shareMsg = {
					title: '推荐用户：' + self.user.nickName,
					path: '/pages/ci/user-show-index?userId=' + self.parUserId,
					success: function(res) {
						console.log('分享成功', JSON.stringify(res))
						self.msgSuccess('分享成功')
					},
				}
				return shareMsg
			},
			hideModal(e) {
				this.modalName = null
			},
		}
	}
</script>

<style scoped lang="scss">
	.info-top-right {
		position: absolute;
		right: 20px;
		top: 10px;
		display: flex;
		flex-direction: column;
	}
	
	.info-avatar {
		width: 160upx;
		height: 160upx;
		border-radius: 50%;
	}
	
	.icon-btn {
		margin: 0 !important;
		padding: 0 !important;
		line-height: normal;
	}
	
	.icon-btn::after {
		content: '';
		border: none;
	}
</style>
