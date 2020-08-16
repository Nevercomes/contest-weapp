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
			<view class="cu-item">
				<view class="content">
					<text class="cuIcon-pic text-green"></text>
					<text class="text-grey">少图省流</text>
				</view>
				<view class="action">
					<switch @change="LessPicture" :class="lessPicture?'checked':''" :checked="lessPicture?true:false"></switch>
				</view>
			</view>
			<view class="cu-item">
				<view class="content">
					<text class="cuIcon-message text-green"></text>
					<text class="text-grey">消息提醒</text>
				</view>
				<view class="action">
					<switch @change="MsgNotice" :class="msgNotice?'checked':''" :checked="msgNotice?true:false"></switch>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	
	import {
		updateMsgNotice,
		updateOnlySchool
	} from '@/api/system/userSetting.js'
	
	export default {
		name: 'SysSettingIndex',
		data() {
			return {
				// 校内浏览
				onlySchool: false,
				// 少图模式
				lessPicture: false,
				// 提醒消息
				msgNotice: true
			}
		},
		onLoad() {
			if (uni.getStorageSync('onlySchool')) {
				this.onlySchool = true
			} else {
				this.onlySchool = false
			}
			const notice = uni.getStorageSync('msgNotice')
			if (notice == 'false') this.msgNotice = false
		},
		methods: {
			OnlySchool(e) {
				let self = this
				this.onlySchool = e.detail.value
				if (e.detail.value) {
					uni.showModal({
						title: '提醒',
						content: '校内浏览模式下将只显示本校的队伍和用户',
						success: function(res) {
							if (res.confirm) {
								self.onlySchool = true
								uni.setStorage({
									key: 'onlySchool',
									data: e.detail.value
								})
								updateOnlySchool(self.onlySchool).then(response => {
									
								}).catch(() => {
									
								})
							} else if (res.cancel) {
								self.onlySchool = false
								self.$forceUpdate()
							}
						}
					})
				} else {
					this.onlySchool = e.detail.value
					uni.setStorage({
						key: 'onlySchool',
						data: e.detail.value
					})
					updateOnlySchool(this.onlySchool).then(res => {
						
					}).catch(() => {
						
					})
				}
			},
			LessPicture(e) {
				this.lessPicture = e.detail.value
				let self = this
				uni.showModal({
					title: '提醒',
					content: '少图浏览的省流量模式将在后续支持，感谢您的理解',
					showCancel: false,
					success: function() {
						self.lessPicture = false
					}
				})
			},
			MsgNotice(e) {
				let self = this
				const old = this.msgNotice
				this.msgNotice = e.detail.value
				if (!e.detail.value) {
					uni.showModal({
						title: '提醒',
						content: '关闭消息提醒后将无法再收到微信的提醒',
						success: function(res) {
							if (res.confirm) {
								self.msgNotice = false
								uni.setStorage({
									key: 'msgNotice',
									data: e.detail.value
								})
								updateMsgNotice(self.msgNotice).then(response => {
									
								}).catch(() => {
									
								})
							} else if (res.cancel) {
								self.msgNotice = true
							}

						}
					})
				} else {
					this.msgNotice = e.detail.value
					uni.setStorage({
						key: 'msgNotice',
						data: e.detail.value
					})
					updateMsgNotice(self.msgNotice).then(res => {
						
					}).catch(() => {
						
					})
				}
				
			}
		}
	}
</script>

<style>
</style>
