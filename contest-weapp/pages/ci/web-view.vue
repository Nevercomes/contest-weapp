<template>
	<web-view :src="webUrl"></web-view>
</template>

<script>
	export default {
		name: 'WebViewPage',
		data() {
			return {
				webUrl: 'https://mp.weixin.qq.com/s/JtN8bQDAc4_MHAKnroXaNQ'
			}
		},
		onLoad(options) {
			this.webUrl = decodeURIComponent(options.webUrl) || this.webUrl
			console.log(this.webUrl)
		}
	}
</script>

<style>
</style>
