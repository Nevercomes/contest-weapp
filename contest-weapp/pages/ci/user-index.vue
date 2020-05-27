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
						<view class="solid-bottom text-xl">
							<text v-if="nickName" class="text-black text-bold">{{nickName}}</text>
							<open-data v-else type="userNickName" class="text-black text-bold" ></open-data>
						</view>
						<van-row gutter="10">
							<van-col>
								<!-- <van-icon name="points" /> -->
							积分</van-col>
							<van-col>10</van-col>
						</van-row>
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
			<van-cell title="我的动态" is-link></van-cell>
			<van-cell title="我的关注" is-link></van-cell>
			<van-cell title="我的收藏" is-link></van-cell>
		</van-cell-group>
		
		<!-- 三类 -->
		<van-cell-group custom-class="margin-top:10px; margin-bottom: 10px">
			<van-cell title="帮助与反馈" is-link></van-cell>
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
					team: '/pages/ci/team-index'
				}
			}
		},
		onLoad() {
			setTimeout(() => {
				this.loading = false
			}, 2000)
		},
		methods: {
			onGotUserInfo(res) {
				console.log(res)
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
