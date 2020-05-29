<!-- 欢迎页面，同时也是dispatch页面 -->
<template>
	<view class="app-container">
		<view class="padding flex flex-direction">
			我是背景图片
		</view>
		<view class="padding flex flex-direction">
			<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" open-type="getUserInfo" @getuserinfo="getUserInfo">开启使用</button>
		</view>
	</view>
</template>

<script>
	import {
		updateUserProfile
	} from '@/api/system/user.js'

	export default {
		name: 'WelCome',
		data() {
			return {
				// 需要跳转的url
				url: undefined,
				// 四个tab页面
				tabUrl: ['/pages/ci/home-index', '/pgaes/ci/user-index', '/pages/ci/competition-index', '/pages/ci/news-index']
			}
		},
		onLoad(options) {
			this.url = options.url ? decodeURIComponent(options.url) : '/pages/ci/home-index'
		},
		methods: {
			getUserInfo() {
				let self = this
				uni.getUserInfo({
					success(res) {
						const userInfo = res.userInfo
						updateUserProfile({
							nickName: userInfo.nickName,
							avatar: userInfo.avatarUrl
						})
					},
					complete() {
						// 截取问号前的url
						let uri = self.url.split('?')[0]
						if(self.tabUrl.indexOf(uri) != -1) {
							uni.switchTab({
								url: self.url
							})
						} else {
							uni.redirectTo({
								url: self.url
							})
						}
					}
				})
			}
		}
	}
</script>

<style>
</style>
