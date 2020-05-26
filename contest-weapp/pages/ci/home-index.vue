<template>
	<view class="app-container">
		<!-- 搜索部分 -->
		<van-search :value="keyword" use-action-slot placeholder="请输入搜索关键词" @focus="goToSearchPage">
			<view style="display: flex; align-items: center;" slot="action" @tap="goToSettingPage">
				<van-icon size="24" name="setting-o"></van-icon>
			</view>
		</van-search>
		<!-- swipe部分 -->
		<swiper class="card-swiper" :class="dotStyle?'square-dot':'round-dot'" :indicator-dots="true" :circular="true"
		 :autoplay="true" interval="5000" duration="500" @change="cardSwiper" indicator-color="#8799a3"
		 indicator-active-color="#0081ff">
			<swiper-item v-for="(item,index) in swiperList" :key="index" :class="cardCur==index?'cur':''" @click="goToRecommendPage(item.url)">
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
		
		<view class="cu-bar bg-white margin-top">
			<view class="action">
				<text class="cuIcon-title text-green"></text>
				<text class="text-lg text-bold">竞赛推荐</text>
			</view>
		</view>
		<view class="content-container content-flex">
			<view v-for="(item, index) in compList" :key="index" class="item-2 margin-bottom" @click="goToCompInfoPage">
				<image style="width: 100%;  height: 300upx;" :src="item.picUrl" ></image>
				<view class="text-sm">{{item.name}}</view>
			</view>
		</view>
		
		<view class="cu-bar bg-white margin-top">
			<view class="action">
				<text class="cuIcon-title text-green"></text>
				<text class="text-lg text-bold">动态推荐</text>
			</view>
		</view>
		<view class="content-container content-flex">
			
		</view>
		
	</view>
</template>

<script>
	
	import {
		getRecommendComp
	} from '@/api/ci/period.js'
	
	export default {
		name: "HomeIndex",
		data() {
			return {
				// 搜索关键词
				keyword: undefined,

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
				// 竞赛推荐list
				compList: [],
				// 动态推俄舰list
				newsList: []
				
			}
		},
		onLoad() {
			getRecommendComp({
				pageNum: 1,
				pageSize: 30
			}).then(res => {
				this.compList = res.data
			})
		},
		methods: {
			// 当搜索框聚焦的时候跳转到搜索界面
			goToSearchPage() {
				uni.navigateTo({
					url: 'search-index?type=all'
				})
			},
			// 取代搜索事件改为setting设置
			geToSettingPage() {
				uni.navigateTo({
					url: 'view-mode-setting'
				})
			},
			// 主页的发布组队
			goToTeamPage() {
				uni.navigateTo({
					url: 'team-public'
				})
			},
			// 竞赛页面
			goToCompPage() {
				uni.navigateTo({
					url: 'competition-index'
				})
			},
			goToCompInfoPage(id) {
				uni.navigateTo({
					url: 'competition-info?id=' + id
				})
			},
			// 收藏页面
			goToCollPage() {
				uni.navigateTo({
					url: 'user-collection-index'
				})
			},
			// 图书界面
			goToBookPage() {
				this.msgInfo('即将到来')
			},
			goToRecommendPage(url) {
				this.msgInfo('跳转到指定页面')
				// uni.navigateTo({
				// 	url: url
				// })
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
