<template>
	<view class="app-container">
		
		<!-- 头像-基本信息 -->
		<van-cell-group>
			<van-cell is-link :url="cellUrl.profile" center>
				<view slot="title" class="cell-userinfo">
					<!-- 头像 -->
					<image v-if="avatar" class="cell-avatar" :src="avatar"></image>
					<view v-else class="cell-avatar">
						<open-data  type="userAvatarUrl"></open-data>
					</view>
					<!-- 昵称和积分信息 -->
					<view class="cell-userinfo__info">
						<view class="text-xl">
							<text v-if="nickName" class="text-black text-bold">{{nickName}}</text>
							<open-data v-else type="userNickName" class="text-black text-bold" ></open-data>
						</view>
						<view class="flex justify-start align-center">
							<text class="margin-right-xs">积分</text>
							<text v-if="points.value" class="margin-right-xs" >{{points.value}}</text>
							<button v-if="!todayChecked" class="cu-btn sm bg-green shadow-blur" @click.stop="onCheckClick">签到</button>
							<view v-else class="cu-tag round text-green">已签到</view>
						</view>
					</view>
				</view>
			</van-cell>
		</van-cell-group>
		<!-- 一类 -->
		<van-cell-group custom-class="margin-top:10px; margin-bottom: 10px">
			<van-cell title="我的主页" is-link :url="cellUrl.detail"></van-cell>
			<van-cell title="我的积分" is-link :url="cellUrl.points"></van-cell>
			<van-cell title="我的消息" is-link :url="cellUrl.message"></van-cell>
		</van-cell-group>
		
		<!-- 二类 -->
		<van-cell-group>
			<van-cell title="我的队伍" is-link :url="cellUrl.team"></van-cell>
			<van-cell title="我的期望" is-link :url="cellUrl.expect"></van-cell>
			<van-cell title="我的动态" is-link :url="cellUrl.news"></van-cell>
			<van-cell title="我的关注" is-link :url="cellUrl.concern"></van-cell>
			<van-cell title="我的收藏" is-link :url="cellUrl.collection"></van-cell>
		</van-cell-group>
		
		<!-- 三类 -->
		<van-cell-group custom-class="margin-top:10px; margin-bottom: 10px">
			<van-cell title="帮助与反馈" is-link :url="cellUrl.manual"></van-cell>
		</van-cell-group>

		<!-- <open-data type="userNickName"></open-data>
		<open-data type="userAvatarUrl"></open-data>
		<button open-type="getUserInfo" lang="zh_CN" @getuserinfo="onGotUserInfo"> 获取用户信息 </button>
		<nl-mask-loading :loading="loading" :lloadingMsg="'请求中'"></nl-mask-loading> -->
	</view>
</template>

<script>
	import NlMaskLoading from '../../nl_componet/nl-mask-loading.vue'
	import {
		mapGetters
	} from 'vuex'
	import {
		getUserPoints,
		todayCheck,
		isTodayCheck
	} from '@/api/ci/points.js'

	export default {
		components: {
			NlMaskLoading
		},
		computed: {
			...mapGetters([
				'name',
				'avatar',
				'nickName'
			])
		},
		data() {
			return {
				loading: true,
				// cell的跳转路径
				cellUrl: {
					profile: '/pages/ci/user-profile',
					detail: '/pages/ci/user-detail',
					points: '/pages/ci/points-index',
					message: '/pages/ci/message-index',
					team: '/pages/ci/team-index',
					expect: '/pages/ci/team-expect-list',
					news: '/pages/ci/user-news-index',
					concern: '/pages/ci/user-concern-index',
					collection: '/pages/ci/news-list?type=collection',
					manual: '/pages/ci/user-manual-index'
				},
				// 用户积分
				points: {},
				// 今日签到
				todayChecked: true
			}
		},
		onLoad() {
			this.$store.dispatch('GetInfo')
			getUserPoints().then(res => {
				this.points = res.data || {}
			})
			// 获取今日是否已经签到
			isTodayCheck().then(res => {
				this.todayChecked = res.data
			})
		},
		methods: {
			onCheckClick() {
				todayCheck().then(res => {
					this.msgInfo('签到+10 积分')
					this.points.value = this.points.value || 0
					this.points.value = Number(this.points.value) + 10
					this.todayChecked = true
				}).catch(error => {
					this.msgInfo(error.msg)
				})
			}
		}
	}
</script>

<style>
	.cell-avatar {
		width: 64px;
		height: 64px;
	}
	
	.cell-userinfo {
		display: flex;
		flex-direction: row;
	}
	
	.cell-userinfo__info {
		padding-left: 40upx;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}
	
</style>
