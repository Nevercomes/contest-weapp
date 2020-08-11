<template>
	<view v-if="show" class="flex flex-direction justify-center align-center padding">
		<image style="width: 196upx; height: 128upx;" src="../static/no_data.png"></image>
		<view class="text-df text-gray margin-top margin-right-sm">{{msg}}</view>
	</view>
</template>

<script>
	export default {
		name: 'NlEmpty',
		props: {
			show: {
				type: Boolean,
				required: true,
				default: true
			},
			msg: {
				type: String,
				required: false,
				default: '空空如也'
			}
		}
	}
</script>

<style>
</style>
