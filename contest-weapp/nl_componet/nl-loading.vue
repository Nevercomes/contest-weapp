<template>
	<view class="bg-white flex-sub radius shadow-lg" v-if="loading">
		<image src="../static/loading-white.gif" mode="aspectFit" class="gif-white response" style="height:240upx"></image>
	</view>
</template>

<script>
	export default {
		name: "NlLoading",
		props: {
			loading: {
				required: true,
				type: Boolean,
				default: false
			}
		}
	}
</script>

<style>
	image[class*="gif-"] {
		border-radius: 6upx;
		display: block;
	}
</style>
