<template>
	<view class="app-container">
		<!-- 弹出action-sheet和分享 -->
		<view class="info-top-right">
			<text class="cuIcon-more margin-bottom text-lg" @click="onMoreClick"></text>
			<text class="cuIcon-share text-lg" @click="onShareClick"></text>
		</view>
		<!-- 基本信息 -->
		<view class="flex justify-center padding-tb-xl bg-white">
			<image class="info-avatar" :src="item.picUrl ? item.picUrl : dfTeamAvatar"></image>
			<view class="margin-left flex flex-direction justify-around" style="max-width: 240upx;">
				<view class="text-bold text-lg">{{item.name}}</view>
				<view class="text-gray text-sm text-cut">{{item.cpName}}</view>
				<view class="text-gray text-sm">{{item.intro}}</view>
			</view>
		</view>
		<!-- 队长 -->
		<view class="cu-bar bg-white">
			<view class="action">
				<text class="cuIcon-title text-green"></text>
				<text>队长信息</text>
			</view>
		</view>
		<view class="cu-list menu-avatar">
			<view class="cu-item " @click="goToUserShowPage(item.userId)" v-for="(item,index) in captainList" :key="index">
				<image :src="item.assUser.avatar" class="cu-avatar round lg"></image>
				<view class="content">
					<view class="text-grey">
						<text class="text-cut">{{item.assUser.nickName}}</text>
						<view class="cu-tag round bg-orange sm">{{workFormat(item.work)}}</view>
					</view>
					<view class="text-gray text-sm flex text-cut">
						{{item.capacity}}
					</view>
				</view>
			</view>
		</view>
		<!-- 队员 -->
		<view class="cu-bar bg-white">
			<view class="action">
				<text class="cuIcon-title text-green"></text>
				<text>队员信息</text>
			</view>
		</view>
		<view class="cu-list menu-avatar">
			<view class="cu-item" @click="goToUserShowPage(item.userId)" v-for="(item,index) in mList" :key="index">
				<image :src="item.assUser.avatar" class="cu-avatar round lg"></image>
				<view class="content">
					<view class="text-grey">
						<text class="text-cut">{{item.assUser.nickName}}</text>
						<view class="cu-tag round bg-orange sm">{{workFormat(item.work)}}</view>
					</view>
					<view class="text-gray text-sm flex text-cut">
						{{item.capacity}}
					</view>
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

	</view>
</template>

<script>
	import {
		mapGetters
	} from 'vuex'
	import {
		getTeamInfo,
		judgeCanJoin
	} from '@/api/ci/team.js'
	import {
		listMember
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
		data() {
			return {
				// 数据信息
				item: {},
				//  默认的队伍头像
				dfTeamAvatar: require('@/static/df_team_avatar.png'),
				// 成员列表
				memberList: [],
				// 队长
				captainList: [],
				// 队员
				mList: [],
				// 分工字典
				workOptions: [],
				// 是否有申请加入的button
				canJoin: false,
				// 底部action-sheet显示
				show: false,
				// action-sheet的操作列表
				actions: []
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
					this.captainList = res.rows.filter(item => {
						return item.indentity == '1'
					})
					this.mList = res.rows.filter(item => {
						return item.identity == '2'
					})
					this.setActions()
				})
			},
			goToUserShowPage(userId) {
				uni.navigateTo({
					url: 'user-show-index'
				})
			},
			// 展示action-sheet
			onMoreClick() {
				this.show = true
			},
			// TODO 弹出分享选项
			onShareClick() {
				this.msgInfo('TODO 分享')
			},
			// action-sheet操作
			onActionSelect(e) {
				console.log(e.detail)
			},
			// 申请加入队伍
			onApplyClick() {
				addApply({
					teamId: this.item.id
				}).then(res => {
					if (res.code == 200) {
						// 跳转到申请界面
						this.msgSuccess('申请加入成功')
					} else {
						this.msgInfo(res.msg)
					}
				})
			},
			setActions() {
				// 判断是否是队员
				this.actions.push({
					'name': '退出队伍',
					'disabled': this.isMember()
				})
				if (this.isCaptain()) {
					this.actions.push({
						'name': '解散队伍',
						'color': '#e54d42'
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
				this.memberList.forEach(item => {
					if (item.userId == this.userId && item.identity == '1') {
						return true
					}
				})
				return false
			},
			isMember() {
				this.memberList.forEach(item => {
					if (item.userId == this.userId) {
						return true
					}
				})
				return false
			},
			workFormat(value, dict) {
				return this.selectDictLabel(this.workOptions, value)
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
</style>
