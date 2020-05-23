<template>
	<van-cell-group>
		<van-cell title="积分账单" center is-link :url="cellUrl.actionList"></van-cell>
		<van-cell title="积分商城" center is-link :url="cellUrl.shop"></van-cell>
		<van-cell title="积分获取" center is-link :url="cellUrl.manual"></van-cell>
	</van-cell-group>
</template>

<script>
	export default {
		name: 'PointsIndex',
		data() {
			return {
				cellUrl: {
					actionList: '/pages/ci/points-action-list',
					shop: '/pages/ci/points-shop',
					manual: '/pages/ci/points-obtain-manual'
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
