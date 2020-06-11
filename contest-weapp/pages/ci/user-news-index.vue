<template>
	<view class="app-container">
		<van-cell-group custom-class="">
			<van-cell title="发布内容" is-link :url="cellUrl.newsList"></van-cell>
			<van-cell title="我的草稿" is-link :url="cellUrl.draftList"></van-cell>
		</van-cell-group>
	</view>
</template>

<script>
	export default {
		"name": "UserNewsIndex",
		data() {
			return {
				cellUrl: {
					newsList: '/pages/ci/news-list?type=public',
					draftList: '/pages/ci/news-list?type=draft',
				}
			}
		},
		onLoad() {
			
		}
	}
</script>

<style>
</style>
