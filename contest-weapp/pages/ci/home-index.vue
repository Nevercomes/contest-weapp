<template>
	<view class="app-container">
		<!-- 搜索部分 -->
		<van-search :value="keyword" use-action-slot placeholder="请输入搜索关键词" @focus="goToSearchPage">
			<view style="display: flex; align-items: center;" slot="action" @tap="goToSettingPage">
				<van-icon size="24" name="setting-o"></van-icon>
			</view>
		</van-search>
		<!-- swipe部分 -->
		<swiper class="screen-swiper square-dot" :indicator-dots="true" :circular="true" :autoplay="true" interval="5000"
		 duration="500">
			<swiper-item v-for="(item,index) in swiperList" :key="index">
				<image :src="item.url" mode="aspectFill" v-if="item.type=='image'"></image>
				<video :src="item.url" autoplay loop muted :show-play-btn="false" :controls="false" objectFit="cover" v-if="item.type=='video'"></video>
			</swiper-item>
		</swiper>
		<!-- 主界面操作组 -->
		<view class="cu-list grid col-4 no-border">
			<view class="cu-item" v-for="(item,index) in cuIconList" :key="index" v-if="index<4" @click="goToPage(item.url)">
				<view :class="['cuIcon-' + item.cuIcon,'text-' + item.color]">
					<view class="cu-tag badge" v-if="item.badge!=0">
						<block v-if="item.badge!=1">{{item.badge>99?'99+':item.badge}}</block>
					</view>
				</view>
				<text>{{item.name}}</text>
			</view>
		</view>

		<view class="cu-bar bg-white">
			<view class="action">
				<text class="cuIcon-title text-green"></text>
				<text class="text-lg text-bold">竞赛推荐</text>
			</view>
		</view>
		<view class="content-container content-flex">
			<view v-for="(item, index) in compList" :key="index" class="item-2 margin-bottom" @click="goToCompInfoPage">
				<image style="width: 100%;  height: 300upx;" :src="item.picUrl"></image>
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
				cuIconList: [{
					cuIcon: 'discoverfill',
					color: 'purple',
					badge: 1,
					name: '建队',
					url: 'team-public'
				}, {
					cuIcon: 'friendaddfill',
					color: 'orange',
					badge: 0,
					name: '加入',
					url: 'team-public-expect'
				}, {
					cuIcon: 'newshotfill',
					color: 'yellow',
					badge: 22,
					name: '收藏',
					url: 'user-concern-index'
				}, {
					cuIcon: 'presentfill',
					color: 'olive',
					badge: 0,
					name: '图书',
					url: undefined
				}, {
					cuIcon: 'upstagefill',
					color: 'cyan',
					badge: 0,
					name: '排行榜'
				}, {
					cuIcon: 'clothesfill',
					color: 'blue',
					badge: 0,
					name: '皮肤'
				}, {
					cuIcon: 'discoverfill',
					color: 'purple',
					badge: 0,
					name: '发现'
				}, {
					cuIcon: 'questionfill',
					color: 'mauve',
					badge: 0,
					name: '帮助'
				}, {
					cuIcon: 'commandfill',
					color: 'purple',
					badge: 0,
					name: '问答'
				}, {
					cuIcon: 'brandfill',
					color: 'mauve',
					badge: 0,
					name: '版权'
				}],
				dotStyle: false,
				// 竞赛推荐list
				compList: [],
				// 动态推俄舰list
				newsList: []

			}
		},
		onLoad() {
			// 请求放在登录回调之后
			this.$store.dispatch('Login').then(loginRes => {
				// 跳转到欢迎页面去获取微信信息
				if (loginRes.data.needInfo) {
					uni.redirectTo({
						url: './a_welcome'
					})
				} else {
					getRecommendComp({
						pageNum: 1,
						pageSize: 30
					}).then(res => {
						this.compList = res.data
					})
				}
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
			goToPage(url) {
				if (url) {
					uni.navigateTo({
						url: url
					})
				} else {
					this.msgInfo('即将到来')
				}
			},
			goToCompInfoPage(id) {
				uni.navigateTo({
					url: 'competition-info?id=' + id
				})
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
	page {
		background-color: #FFFFFF;
	}

	.button-group {
		display: flex;
		justify-content: space-between;
		padding: 0 20upx;
	}
</style>
