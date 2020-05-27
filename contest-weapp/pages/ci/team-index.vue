<template>
	<view class="app-container">
		<van-cell-group custom-class="">
			<van-cell title="创建的队伍" is-link :url="cellUrl.myTeam"></van-cell>
			<van-cell title="加入的队伍" is-link :url="cellUrl.joinTeam"></van-cell>
			<van-cell title="组队邀请" is-link :url="cellUrl.invite"></van-cell>
			<van-cell title="入队申请" is-link :url="cellUrl.apply"></van-cell>
		</van-cell-group>
	</view>
</template>

<script>
	export default {
		"name": "TeamIndex",
		data() {
			return {
				cellUrl: {
					myTeam: '/pages/ci/team-list?type=create',
					joinTeam: '/pages/ci/team-list?type=join',
					invite: '/pages/ci/team-invite',
					apply: '/pages/ci/team-apply'
				}
			}
		},
		onLoad() {
			
		}
	}
</script>

<style>
</style>
