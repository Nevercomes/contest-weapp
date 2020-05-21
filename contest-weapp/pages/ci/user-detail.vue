<template>
	<view class="app-container">
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
