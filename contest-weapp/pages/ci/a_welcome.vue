<!-- 欢迎页面，同时也是dispatch页面 -->
<template>
	<view class="app-container">

		<swiper @animationfinish="swiperChange" class="swiper round-dot" :indicator-dots="indicatorDots" :autoplay="autoplay"
		 :interval="interval" :duration="duration">
			<swiper-item item-id="competition">
				<view class="text-orange text-bold text-xxl text-center padding-tb-xl" style="height: 10vh;">竞赛服务</view>

				<view style="position: relative; height: 15vh">
					<view class="margin-sm basis-sm shadow bg-lightorange welcome_rec" :class="[swiperName=='competition'?'animation-slide-bottom':'']"
					 style="width: 140px; line-height: 4em; border-radius: 50%; position: absolute; bottom: 0; left: 10px;"
					 :data-class="'slide-bottom'" :style="[{animationDelay: 0.5 + 's'}]">竞赛信息</view>

					<view class="margin-sm basis-sm shadow bg-lightorange  welcome_rec" :class="[swiperName=='competition'?'animation-slide-bottom':'']"
					 style="width: 100px; line-height: 4em; border-radius: 50%; position: absolute; top: 0; right: 15px;" :data-class="'slide-bottom'"
					 :style="[{animationDelay: 0.5 + 's'}]">热榜推荐</view>

				</view>

				<image class="" :style="[{animationDelay: 0.3 + 's'}]" style="width: 100%; height: 45vh" src="../../static/welcome_competition.png"
				 mode="aspectFit"></image>

				<view style="position: relative; height: 15vh">
					<view class="margin-sm basis-sm shadow bg-lightorange  welcome_rec" :class="[swiperName=='competition'?'animation-slide-bottom':'']"
					 style="width: 100px; line-height: 3.5em; border-radius: 50%; position: absolute; bottom: 0; left: 15px;"
					 :data-class="'slide-bottom'" :style="[{animationDelay: 0.5 + 's'}]">一键关注</view>

					<view class="margin-sm basis-sm shadow bg-lightorange welcome_rec" :class="[swiperName=='competition'?'animation-slide-bottom':'']"
					 style="width: 140px; line-height: 4em; border-radius: 50%; position: absolute; top: 0; right: 15px;" :data-class="'slide-bottom'"
					 :style="[{animationDelay: 0.5 + 's'}]">即刻成队</view>

				</view>
			</swiper-item>

			<swiper-item item-id="team">
				<view class="text-orange text-bold text-xxl text-center padding-tb-xl" style="height: 10vh;">组队服务</view>

				<image class="margin-top" :style="[{animationDelay: 0.3 + 's'}]" style="width: 100%; height: 45vh" src="../../static/welcome_team.png"
				 mode="aspectFit"></image>

				<view style="position: relative; height: 30vh">
					<view class="margin-sm basis-sm shadow bg-lightorange welcome_rec round" :class="[swiperName=='team'?'animation-slide-bottom':'']"
					 style="width: 120px; line-height: 4em; position: absolute; left: 10px; top: 2em;" :data-class="'slide-bottom'"
					 :style="[{animationDelay: 0.5 + 's'}]">自主建队</view>

					<view class="margin-sm basis-sm shadow bg-lightorange welcome_rec round" :class="[swiperName=='team'?'animation-slide-bottom':'']"
					 style="width: 120px; line-height: 4em; position: absolute; right: 15px;" :data-class="'slide-bottom'" :style="[{animationDelay: 0.5 + 's'}]">招募队友</view>

					<view class="margin-sm basis-sm shadow bg-lightorange welcome_rec round" :class="[swiperName=='team'?'animation-slide-bottom':'']"
					 style="width: 120px; line-height: 4em; position: absolute; bottom: 0; left: 125px;" :data-class="'slide-bottom'"
					 :style="[{animationDelay: 0.5 + 's'}]">灵活加入</view>

				</view>
			</swiper-item>
			<swiper-item item-id="communication">
				<view class="text-orange text-bold text-xxl text-center padding-tb-xl" style="height: 10vh;">经验交流</view>

				<image class="margin-top" :style="[{animationDelay: 0.3 + 's'}]" style="width: 100%; height: 45vh" src="../../static/welcome_communication.png"
				 mode="aspectFit"></image>

				<view class="flex align-center justify-around padding-lr" style="position: relative; height: 30vh">

					<view class="margin-sm basis-sm shadow bg-lightorange welcome_rec round" :class="[swiperName=='communication'?'animation-slide-bottom':'']"
					 style="width: 120px; line-height: 4em;" :data-class="'slide-bottom'" :style="[{animationDelay: 0.5 + 's'}]">获取经验</view>

					<view class="margin-sm basis-sm shadow bg-lightorange welcome_rec round" :class="[swiperName=='communication'?'animation-slide-bottom':'']"
					 style="width: 120px; line-height: 4em;" :data-class="'slide-bottom'" :style="[{animationDelay: 0.5 + 's'}]">私信交流</view>

				</view>
			</swiper-item>

			<swiper-item item-id="rowing" style="background-color: #FFF;">
				<view class="text-orange text-bold text-center padding-tb" style="font-size: 64upx;">赛 舟</view>

				<view class="rowing-remark text-center text-lg padding-tb-xs margin-lr-xl magrin-tb">
					陪你乘风破浪，陪你驰骋赛场
				</view>

				<image class="margin-top" :style="[{animationDelay: 0.3 + 's'}]" style="width: 100%; height: 45vh" src="../../static/rowing.png"
				 mode="aspectFit"></image>

				<view style="position: relative; height: 30vh">
					<view class="padding-xl flex flex-direction" 
					:style="[{animationDelay: 0.5 + 's'}]"
					:class="[swiperName=='rowing'?'animation-fade':'']">
						<button class="cu-btn bg-orange margin-tb-sm lg shadow-blur round" @click="haveFun()">开启竞赛之旅</button>
					</view>
				</view>
			</swiper-item>

		</swiper>

	</view>

	<!-- <view class="padding flex flex-direction">
			<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" open-type="getUserInfo" @getuserinfo="getUserInfo">开启使用</button>
		</view> -->
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
				tabUrl: ['/pages/ci/home-index', '/pgaes/ci/user-index', '/pages/ci/competition-index', '/pages/ci/news-index'],
				animation: '',
				list: [{
						name: 'fade',
						color: 'red'
					},
					{
						name: 'scale-up',
						color: 'orange'
					},
					{
						name: 'scale-down',
						color: 'olive'
					},
					{
						name: 'slide-top',
						color: 'green'
					}, {
						name: 'slide-bottom',
						color: 'cyan'
					},
					{
						name: 'slide-left',
						color: 'blue'
					},
					{
						name: 'slide-right',
						color: 'purple'
					},
					{
						name: 'shake',
						color: 'mauve'
					}
				],
				toggleDelay: false,
				// swiper参数
				swiperName: 'competition',
				indicatorDots: true,
				autoplay: false,
				interval: 2000,
				duration: 500
			}
		},
		onLoad(options) {
			this.url = options.url ? decodeURIComponent(options.url) : '/pages/ci/home-index'
			uni.preloadPage({
				tabUrl: '/pages/ci/home-index'
			})
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
						if (self.tabUrl.indexOf(uri) != -1) {
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
			},
			haveFun() {
				let self = this
				let uri = self.url.split('?')[0]
				if (self.tabUrl.indexOf(uri) != -1) {
					uni.switchTab({
						url: self.url
					})
				} else {
					uni.redirectTo({
						url: self.url
					})
				}
			},
			Toggle(e) {
				var anmiaton = e.currentTarget.dataset.class;
				this.animation = anmiaton;
				setTimeout(() => {
					this.animation = '';
				}, 1000)
			},
			ToggleDelay() {
				this.toggleDelay = true;
				setTimeout(() => {
					this.toggleDelay = false
				}, 1000)
			},
			swiperChange(e) {
				this.swiperName = e.target.currentItemId
			}
		}
	}
</script>

<style>
	@import "../../colorui/animation.css";

	page {
		background-color: #FFF9F4;
	}

	.swiper {
		height: 95vh;
	}

	.welcome_rec {
		text-align: center;
		vertical-align: middle;
		font-weight: bold;
		font-size: 16px;
	}

	.bg-lightorange {
		background-color: #FFA95C;
		color: #FFF;
	}

	.rowing-remark {
		color: #FFD7B3;
		border-top: 1px solid #FFD7B3;
		border-bottom: 1px solid #FFD7B3;
	}
</style>
