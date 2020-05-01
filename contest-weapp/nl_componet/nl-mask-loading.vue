<template>
	<view class="cu-load load-modal" v-if="loading">
		<!-- <view class="cuIcon-emojifill text-orange"></view> -->
		<image src="/static/zhulu_logo.png" mode="aspectFit"></image>
		<view class="gray-text">{{loadingMsg}}</view>
	</view>
</template>

<script>
	export default {
		name: "NlMaskLoading",
		props: {
			loading: {
				required: true,
				type: Boolean,
				default: false
			},
			loadingMsg: {
				required: false,
				type: String,
				default: '加载中...'
			}
		}
	}
</script>

<style>
</style>
