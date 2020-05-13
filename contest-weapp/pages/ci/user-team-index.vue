<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">我的队伍</block>
		</cu-custom>
		
		<van-cell-group>
			<van-cell title="我的队伍" is-link :url="cellUrl.team"></van-cell>
			<van-cell title="我的组队意愿" is-link :url="cellUrl.expect"></van-cell>
			<van-cell title="我的组队请求" is-link :url="cellUrl.apply"></van-cell>
			<van-cell title="我的邀请" is-link :url="cellUrl.invite"></van-cell>
		</van-cell-group>
	</view>
</template>

<script>
	export default {
		name: '',
		data() {
			return {
				cellUrl: {
					team: '/pages/ci/user-team-list',
					expect: '/pages/ci/user-team-expect',
					apply: '/pages/ci/user-team-apply',
					invite: '/pages/ci/user-team-invite'
				}
			}
		},
		onLoad() {

		},
		methods: {

		}
	}
</script>

<style>
</style>
