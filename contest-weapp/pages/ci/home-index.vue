<template>
	<view class="app-container">
		<!-- navbar -->
		<cu-custom bgColor="bg-gradual-pink" :isBack="false">
			<!-- <block slot="backText">返回</block> -->
			<!-- <block slot="content">操作条</block> -->
		</cu-custom>
		<!-- 搜索部分 -->
		<van-search :value="keywords" use-action-slot placeholder="请输入搜索关键词" @focus="goToSearchPage">
			<view style="display: flex; align-items: center;" slot="action" @tap="goToSettingPage">
				<van-icon size="24" name="setting-o"></van-icon>
			</view>
		</van-search>
		<!-- swipe部分 -->
		<swiper class="card-swiper" :class="dotStyle?'square-dot':'round-dot'" :indicator-dots="true" :circular="true"
		 :autoplay="true" interval="5000" duration="500" @change="cardSwiper" indicator-color="#8799a3"
		 indicator-active-color="#0081ff">
			<swiper-item v-for="(item,index) in swiperList" :key="index" :class="cardCur==index?'cur':''">
				<view class="swiper-item">
					<image :src="item.url" mode="aspectFill" v-if="item.type=='image'"></image>
					<video :src="item.url" autoplay loop muted :show-play-btn="false" :controls="false" objectFit="cover" v-if="item.type=='video'"></video>
				</view>
			</swiper-item>
		</swiper>
		<!-- 主界面操作组 -->
		<view class="button-group">
			<van-button type="primary" @click="goToTeamPage">组队</van-button>
			<van-button type="primary" @click="goToCompPage">竞赛</van-button>
			<van-button type="primary" @click="goToCollPage">收藏</van-button>
			<van-button type="primary" @click="goToBookPage">图书</van-button>
		</view>
	</view>
</template>

<script>
	export default {
		name: "HomeIndex",
		data() {
			return {
				// 搜索关键词
				keywords: undefined,

				// 轮播参数
				cardCur: 0,
				swiperList: [{
					id: 0,
					type: 'image',
					url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big84000.jpg'
				}, {
					id: 1,
					type: 'image',
					url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big37006.jpg',
				}, {
					id: 2,
					type: 'image',
					url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big39000.jpg'
				}, {
					id: 3,
					type: 'image',
					url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big10001.jpg'
				}, {
					id: 4,
					type: 'image',
					url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big25011.jpg'
				}, {
					id: 5,
					type: 'image',
					url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big21016.jpg'
				}, {
					id: 6,
					type: 'image',
					url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big99008.jpg'
				}],
				dotStyle: false,
				towerStart: 0,
				direction: ''
			}
		},
		onLoad() {

		},
		methods: {
			// 当搜索框聚焦的时候跳转到搜索界面
			goToSearchPage() {
				uni.navigateTo({
					url: 'search-index'
				})
			},
			// 取代搜索事件改为setting设置
			geToSettingPage() {

			},
			// 主页的发布组队
			goToTeamPage() {
				
			},
			// 竞赛页面
			goToCompPage() {
				
			},
			// 收藏页面
			goToCollPage() {
				
			},
			// 样式相关
			cardSwiper(e) {
				this.cardCur = e.detail.current
			},
		}
	}
</script>

<style>
	.button-group {
		display: flex;
		justify-content: space-between;
		padding: 0 20upx;
	}
</style>
