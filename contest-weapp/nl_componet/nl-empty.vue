<template>
	<view v-if="show" class="flex flex-direction justify-center align-center padding">
		<image style="width: 196upx; height: 128upx;" src="../static/no_data.png"></image>
		<view class="text-df text-gray margin-top margin-right-sm">空空如也</view>
	</view>
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
		watch: {
			show: {
				immediate: true,
				handler(newVal, oldVal) {
					this.show = newVal
				}
			}
		}
	}
</script>

<style>
</style>
