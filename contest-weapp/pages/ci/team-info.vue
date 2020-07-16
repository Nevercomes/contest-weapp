<template>
	<view class="app-container">
		<!-- 弹出action-sheet和分享 -->
		<view class="info-top-right">
			<text class="cuIcon-more margin-bottom text-xl" @click="onMoreClick"></text>
			<!-- <text class="cuIcon-share text-lg" @click="onShareClick"></text> -->
			<button open-type='share' class="cuIcon-share text-xl icon-btn"></button>
		</view>
		<!-- 基本信息 -->
		<view class="flex justify-center padding-tb-xl bg-white">
			<image @click="onChangeAvatarClick" class="info-avatar" :src="item.picUrl ? item.picUrl : dfTeamAvatar"></image>
			<view @click="goToTeamInfoForm(item.id)" class="margin-left flex flex-direction justify-around" style="max-width: 300upx;">
				<view class="flex">
					<view class="text-bold text-lg margin-right-sm">{{item.name}}</view>
					<text v-if="item.status == '0'" class="cu-tag radius text-green">组队中</text>
					<text v-else-if="item.status == '1'" class="cu-tag radius text-orange">已完成</text>
					<text v-else class="cu-tag radius">已解散</text>
				</view>
				<view class="text-gray text-sm text-cut">{{item.cpName}}</view>
				<view class="text-gray text-sm">{{item.intro}}</view>
			</view>
		</view>
		<!-- 队长 -->
		<view class="cu-bar bg-white">
			<view class="action">
				<text class="cuIcon-title text-green"></text>
				<text>队员信息</text>
			</view>
		</view>
		<view class="cu-list menu-avatar">
			<view class="cu-item" :class="modalName=='move-box-'+ index?'move-cur':''" @click="goToUserShowPage(item.userId)"
			 v-for="(item,index) in memberList" :key="index" @touchstart="ListTouchStart" @touchmove="ListTouchMove" @touchend="ListTouchEnd"
			 :data-target="'move-box-' + index">
				<image :src="item.assUser.avatar" class="cu-avatar round lg"></image>
				<view class="content">
					<view class="text-grey">
						<text class="text-cut">{{item.assUser.nickName}}</text>
						<text v-if="item.work" class="cu-tag round bg-orange sm">{{workFormat(item.work)}}</text>
						<text v-if="item.identity == '1'" class="cu-tag round bg-orange sm">队长</text>
					</view>
					<view class="text-gray flex text-cut">
						<text v-if="item.capacity">{{item.capacity}}</text>
						<text v-else>暂无能力描述</text>
					</view>
				</view>
				<view v-if="isCaptain() || isSelf(item.userId)" class="move">
					<view class="bg-green" @click.stop="goToMemberForm(item.id)">修改</view>
					<view v-if="isCaptain() && item.identity == '2'" class="bg-red" @click.stop="kickOut(item.userId)">踢出</view>
				</view>
			</view>
		</view>
		<!-- 如果队伍没满，可以申请加入 -->
		<view v-if="canJoin" class="padding flex flex-direction">
			<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" @click="onApplyClick">申请加入</button>
		</view>

		<!-- 底部操作 -->
		<van-action-sheet @cancel="show = false" @close="show = false" @select="onActionSelect" :show="show" :actions="actions"
		 cancel-text="取消" />

		<!-- 全局的遮罩层 已解散队伍 -->
		<view v-if="item.status == '2'">
			已解散
		</view>
		
		<kps-image-cutter @ok="onOk" @cancel="onCancel" :url="url" :fixed="true" :width="160" :height="160"></kps-image-cutter>

	</view>
</template>

<script>
	
	import kpsImageCutter from '@/components/ksp-image-cutter/ksp-image-cutter.vue'
	
	import {
		mapGetters
	} from 'vuex'
	import {
		getTeamInfo,
		judgeCanJoin,
		updateTeamInfo,
		uploadTeamAvatar
	} from '@/api/ci/team.js'
	import {
		listMember,
		delMember
	} from '@/api/ci/member.js'
	import {
		addApply
	} from '@/api/ci/apply.js'

	export default {
		name: 'TeamInfo',
		computed: {
			...mapGetters([
				'userId'
			])
		},
		components: {
			kpsImageCutter
		},
		data() {
			return {
				// 数据信息
				item: {},
				// 队伍头像
				url: undefined,
				//  默认的队伍头像
				dfTeamAvatar: require('@/static/df_team_avatar.png'),
				// 成员列表
				memberList: [],
				// 分工字典
				workOptions: [],
				// 是否有申请加入的button
				canJoin: false,
				// 底部action-sheet显示
				show: false,
				// action-sheet的操作列表
				actions: [],
				// 滑动标识
				modalName: null,
				listTouchStart: 0,
				listTouchDirection: null,
			}
		},
		onLoad(options) {
			this.item.id = options.id
			this.getItem()
			this.loadMemberList()
			this.getCanJoin()
			this.getDicts('team_work_type').then(res => {
				this.workOptions = res.data
				this.workOptionsPicker = res.data.map(item => item.dictLabel)
			})
		},
		onShow() {
			this.getItem()
			this.loadMemberList()
		},
		methods: {
			getItem() {
				getTeamInfo(this.item.id).then(res => {
					this.item = res.data
				})
			},
			loadMemberList() {
				listMember({
					teamId: this.item.id
				}).then(res => {
					this.memberList = res.rows
					this.setActions()
				})
			},
			// 跳转到用户展示界面
			goToUserShowPage(userId) {
				uni.navigateTo({
					url: 'user-show-index?userId=' + userId
				})
			},
			// 跳转到member表单
			goToMemberForm(id) {
				uni.navigateTo({
					url: 'team-member-form?id=' + id
				})
			},
			// 修改队伍信息
			goToTeamInfoForm(id) {
				if (this.isCaptain()) {
					uni.navigateTo({
						url: 'team-info-form?id=' + id
					})
				}
			},
			// 展示action-sheet
			onMoreClick() {
				this.show = true
				console.log('click')
			},
			// TODO 弹出分享选项
			onShareAppMessage() {
				uni.showShareMenu({
					withShareTicket: true
				});
				let self = this
				let shareMsg = {
					title: self.item.cpName + '团队：' + self.item.name,
					path: '/pages/ci/team-info?id=' + self.item.id,
					// imageUrl: self.item.picUrl || self.dfTeamAvatar,
					// desc: self.item.cpName,
					// 因为微信的调整，将无法判断用户是否真的分享
					success: function(res) {
						console.log('分享成功', JSON.stringify(res))
						self.msgSuccess('分享成功')
					},
				}
				return shareMsg
			},
			// action-sheet操作
			onActionSelect(e) {
				const name = e.detail.name
				let self = this
				if (name == '解散队伍') {
					uni.showModal({
						title: '操作确认',
						content: '确定要解散队伍吗？',
						success(res) {
							if (res.confirm) {
								updateTeamInfo({
									id: self.item.id,
									status: '2'
								}).then(res => {
									self.getItem()
									self.msgSuccess('队伍解散成功')
								})
							}
						}
					})
				} else if (name == '退出队伍') {
					uni.showModal({
						title: '操作确认',
						content: '确定要退出队伍吗？',
						success(res) {
							if (res.confirm) {
								delMemberByTeamAndUser(self.userId, self.item.id).then(res => {
									self.msgSuccess('退出队伍成功')
									self.back()
								})
							}
						}
					})
				} else if(name == '完结组队') {
					let self = this
					uni.showModal({
						title: '操作确认',
						content: '完结组队后其他用户将无法申请加入',
						success(res) {
							if (res.confirm) {
								self.item.status = '1'
								updateTeamInfo(self.item).then(response => {
									self.msgSuccess('组队已完成')
								})
							}
						}
					})
				}
			},
			// 申请加入队伍
			onApplyClick() {
				uni.navigateTo({
					url: 'team-apply-form?teamId=' + this.item.id + '&teamName=' + this.item.name
				})
			},
			// 更换头像
			onChangeAvatarClick() {
				let self = this
				uni.chooseImage({
					count: 1,
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: function(res) {
						self.url = res.tempFilePaths[0]
					}
				});
			},
			onOk(e) {
				uploadTeamAvatar(e.path).then(res => {
					this.item.picUrl = res.data
					this.url = ''
					updateTeamInfo(this.item).then(res1 => {
						this.msgSuccess('头像上传成功')
					})
				})
			},
			onCancel() {
				this.url = ''
			},
			setActions() {
				this.actions = []
				// 判断是否是队员
				if (this.isCaptain()) {
					this.actions.push({
						'name': '完结组队'
					})
					this.actions.push({
						'name': '解散队伍',
						'color': '#e54d42'
					})
				} else if (this.isMember()) {
					this.actions.push({
						'name': '退出队伍',
					})
				}
			},
			// 其实这个方法也可以前端做，都试一下吧
			getCanJoin() {
				judgeCanJoin(this.item.id).then(res => {
					this.canJoin = res.data == 'yes'
				})
			},
			isCaptain() {
				// forEach无法使用break或者return跳出循环
				// some 是内部return true时跳出循环 也无法直接结束函数
				let flag = false
				this.memberList.some(item => {
					if (item.userId == this.userId && item.identity == '1') {
						flag = true
						return true
					}
				})
				return flag
			},
			isMember() {
				let flag = false
				this.memberList.some(item => {
					if (item.userId == this.userId) {
						flag = true
						return true
					}
				})
				return flag
			},
			isSelf(userId) {
				return userId == this.userId
			},
			workFormat(value, dict) {
				return this.selectDictLabel(this.workOptions, value)
			},

			// ListTouch开始滚动
			ListTouchStart(e) {
				this.listTouchStart = e.touches[0].pageX
			},
			// ListTouch计算方向
			ListTouchMove(e) {
				this.listTouchDirection = e.touches[0].pageX - this.listTouchStart > 0 ? 'right' : 'left'
			},

			// ListTouch计算滚动
			ListTouchEnd(e) {
				if (this.listTouchDirection == 'left') {
					this.modalName = e.currentTarget.dataset.target
				} else {
					this.modalName = null
				}
				this.listTouchDirection = null
			}
		}
	}
</script>

<style>
	
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
