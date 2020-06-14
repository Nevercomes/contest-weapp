<template>
	<view class="app-container">
		<view class="cu-list menu">
			<view class="cu-item">
				<view class="content">
					<text class="cuIcon-attentionfavor text-green"></text>
					<text class="text-grey">校内浏览</text>
				</view>
				<view class="action">
					<switch @change="OnlySchool" :class="onlySchool?'checked':''" :checked="onlySchool?true:false"></switch>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'SysSettingIndex',
		data() {
			return {
				// 校内浏览
				onlySchool: false
			}
		},
		onLoad() {
			if (uni.getStorageSync('onlySchool')) {
				this.onlySchool = true
			} else {
				this.onlySchool = false
			}
		},
		methods: {
			OnlySchool(e) {
				this.onlySchool = e.detail.value
				uni.setStorage({
					key: 'onlySchool',
					data: e.detail.value
				})
			}
		}
	}
</script>

<style>
</style>
