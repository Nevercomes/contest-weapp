<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">操作条</block>
		</cu-custom>
		<view class="cell-userinfo">
			<!-- 头像 -->
			<image v-if="avatar" class="cell-avatar" :src="avatar"></image>
			<view v-else class="cell-avatar">
				<open-data  type="userAvatarUrl"></open-data>
			</view>
			<!-- 昵称和积分信息 -->
			<view class="cell-userinfo__info">
				<view class="solid-bottom text-xl">
					<text v-if="name" class="text-black text-bold">{{name}}</text>
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
		<van-cell-group>
			<van-cell title="个人简介" center is-link :url="cellUrl.intro"></van-cell>
			<van-cell title="竞赛经历" center is-link :url="cellUrl.experience"></van-cell>
			<van-cell title="显示设置" center is-link :url="cellUrl.showSetting"></van-cell>
		</van-cell-group>
	</view>
</template>

<script>
	export default {
		name: '',
		data() {
			return {
				cellUrl: {
					intro: '/pages/ci/user-detail-intro',
					experience: '/pages/ci/user-detail-experience',
					showSetting: '/pages/ci/user-detail-showsetting'
				}
			}
		},
		onLoad() {

		},
		methods: {

		}
	}
</script>

<style scoped lang="scss">
	
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
