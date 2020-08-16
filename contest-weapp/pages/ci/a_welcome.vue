<!-- 欢迎页面，同时也是dispatch页面 -->
<template>
	<view class="app-container">

		<swiper @animationfinish="swiperChange" class="swiper round-dot" :indicator-dots="indicatorDots"
		 :autoplay="autoplay" :interval="interval" :duration="duration">

			<swiper-item item-id="competition" class="bgcolor-green">
				<view class="screen-image">
					<image :src="imageList[0]" mode="aspectFit"></image>
				</view>
			</swiper-item>
			<swiper-item item-id="team" class="bgcolor-yellow">
				<view class="screen-image">
					<image :src="imageList[1]" mode="aspectFit"></image>
				</view>
			</swiper-item>
			<swiper-item item-id="community" class="bgcolor-blue">
				<view class="screen-image">
					<image :src="imageList[2]" mode="aspectFit"></image>
				</view>
			</swiper-item>

			<swiper-item item-id="rowing" class="bgcolor-white" style="background-color: #FFF; margin-top: 60px;">
				<view class="text-orange text-bold text-center padding-tb" style="font-size: 64upx;">赛 舟</view>

				<view class="rowing-remark text-center text-lg padding-tb-xs margin-lr-xl magrin-tb">
					陪你乘风破浪，陪你驰骋赛场
				</view>

				<image class="margin-top" :style="[{animationDelay: 0.1 + 's'}]" style="width: 100%; height: 45vh" src="../../static/rowing.png"
				 mode="aspectFit"></image>

				<view style="position: relative; height: 30vh">
					<view class="padding-xl flex flex-direction" :style="[{animationDelay: 0.1 + 's'}]" :class="[swiperName=='rowing'?'animation-fade':'hide']">
						<button class="cu-btn bg-orange margin-tb-sm lg shadow-blur round" @click="haveFun()">开启竞赛之旅</button>
					</view>
				</view>
			</swiper-item>

		</swiper>

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
				duration: 500,
				// 新的欢迎页面
				imageList: [
					'https://dream-road-oss.oss-cn-hangzhou.aliyuncs.com/1_welcome_competition.png',
					'https://dream-road-oss.oss-cn-hangzhou.aliyuncs.com/2_welcome_team.png',
					'https://dream-road-oss.oss-cn-hangzhou.aliyuncs.com/3_welcome_community.png'
				],
				bgColor: 'bgcolor-green',
				swiperBgColorMap: {
					'competition': 'bgcolor-green',
					'team': 'bgcolor-yellow',
					'community': 'bgcolor-blue',
					'rowing': 'bgcolor-white'
				}
			}
		},
		onLoad(options) {
			this.url = options.url ? decodeURIComponent(options.url) : '/pages/ci/home-index'
			uni.preloadPage({
				tabUrl: '/pages/ci/home-index'
			})
		},

		methods: {
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
				this.bgColor = this.swiperBgColorMap[this.swiperName]

			}
		}
	}
</script>

<style>
	@import "../../colorui/animation.css";

	page {
		/* background-color: #FFF9F4; */
		background-color: white;
	}

	.bgcolor-green {
		background-color: rgb(27, 133, 97);
	}

	.bgcolor-yellow {
		background-color: rgb(229, 200, 47);
	}

	.bgcolor-blue {
		background-color: rgb(115, 182, 230);
	}

	.bgcolor-white {
		background-color: #FFFFFF;
	}

	.swiper {
		height: 100vh;
	}
	
	.hide {
		display: none;
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

	.screen-image {
		display: flex;
		align-items: center;
	}

	.screen-image image {
		width: 100vw;
		height: 100vh;
	}
</style>
