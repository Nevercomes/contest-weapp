<template>
	<view class="flex-sub text-center">
		<view v-if="icon" class="solid-bottom text-xsl padding">
			<text class=" cuIcon-roundcheckfill text-green"></text>
		</view>
		<view class="solid-bottom text-xl padding">
			<text class="text-black text-bold">{{msg}}</text>
		</view>
	</view>
</template>

<script>
	export default {
		name: "NlSuccess",
		props: {
			icon: {
				type: Boolean,
				default: true
			},
			msg: {
				type: String,
				default: '操作成功'
			}
		}
	}
</script>

<style>
</style>
