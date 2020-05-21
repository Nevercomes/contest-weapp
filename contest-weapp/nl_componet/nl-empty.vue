<template>
	<view v-if="show">无数据</view>
</template>

<script>
	export default {
		name: 'NlEmpty',
		props: {
			show: {
				type: Boolean,
				required: true,
				default: false
			}
		},
	}
</script>

<style>
</style>
