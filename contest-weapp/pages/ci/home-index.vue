<template>
	<view class="app-container">
		<!-- 搜索部分 -->
		<van-search :value="keyword" use-action-slot placeholder="请输入搜索关键词" @focus="goToSearchPage">
			<view style="display: flex; align-items: center;" slot="action" @click="goToSettingPage">
				<van-icon size="24" name="setting-o"></van-icon>
			</view>
		</van-search>
		<!-- 全屏样式 -->
		<swiper class="screen-swiper square-dot" :indicator-dots="true" :circular="true"
		 :autoplay="true" interval="5000" duration="500">
			<swiper-item v-for="(item,index) in swiperList" :key="index" @click="onSwiperClick(item)">
				<image :src="item.picUrl" mode="aspectFill"></image>
			</swiper-item>
		</swiper>
		<!-- Card样式 -->
		<!-- <swiper class="card-swiper round-dot" :indicator-dots="true" :circular="true" :autoplay="true" interval="5000"
		 duration="500" @change="cardSwiper" indicator-color="#8799a3" indicator-active-color="#0081ff">
			<swiper-item v-for="(item,index) in swiperList" :key="index" :class="cardCur==index?'cur':''" @click="onSwiperClick(item)">
				<view class="swiper-item">
					<image :src="item.picUrl" mode="aspectFill"></image>
				</view>
			</swiper-item>
		</swiper> -->
		<!-- 主界面操作组 -->
		<view class="cu-list grid col-4 no-border" style="padding-bottom: 0 !important;">
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
				<text class="text-lg text-bold">团队达人</text>
			</view>
		</view>
		<scroll-view scroll-x="true">
			<view class="flex padding-lr-sm padding-tb-sm">
				<view class="flex flex-direction align-center justify-center padding-lr-sm" v-for="(item,index) in famousList" :key="index" @click="onFamousItemClick(item)">
					<image class="cu-avatar round xl" :src="item.avatarUrl"></image>
					<text class="text-gray text-sm text-cut margin-top-xs">{{item.name}}</text>
					<text class="text-gray text-sm">{{item.desc}}</text>
				</view>
			</view>
		</scroll-view>

		<view class="cu-bar bg-white">
			<view class="action">
				<text class="cuIcon-title text-green"></text>
				<text class="text-lg text-bold">竞赛推荐</text>
			</view>
		</view>
		<view v-for="(item, index) in compList" :key="index" class="flex padding-lr padding-tb-sm bg-white" @click="goToCompInfoPage(item.cpId)">
			<view class="inline-image margin-right-sm">
				<image class="inline-image" :src="item.picUrl"></image>
			</view>
			<view class="flex flex-direction justify-around">
				<view class="text-df">{{item.cpName}}</view>
				<view>
					<text v-if="index<3" class='cu-tag margin-right-xs radius' v-for="(label,index) in item.classifyList" :key="index">{{label}}</text>
				</view>
			</view>
		</view>

		<view class="cu-bar bg-white">
			<view class="action">
				<text class="cuIcon-title text-green"></text>
				<text class="text-lg text-bold">动态推荐</text>
			</view>
		</view>
		<view class="cu-list padding-lr">
			<view class="cu-item post-card padding-tb-sm bg-white shadow-blur margin-bottom-sm" @click="goToPostPage(item.id)"
			 v-for="(item,index) in newsList" :key="index">
				<view class="text-bold text-df padding-tb-xs">{{item.name}}</view>
				<!-- 作者信息 -->
				<view class="flex align-center">
					<image class="cu-avatar round sm margin-right" :src="item.createUser.avatar"></image>
					<text class="text-gray text-sm text-bold margin-right">{{item.createUser.nickName}}</text>
					<text class="text-grey text-sm">{{item.createUser.schoolName}}</text>
				</view>
				<!-- 竞赛 -->
				<view class="text-grey text-sm padding-tb-xs">{{item.cpName}}</view>
				<!-- 内容摘要 -->
				<view class="flex padding-tb-xs" style="max-height: 4.5em; position: relative;">
					<view style="line-height: 1.5em; position: relative;" class="flex-sub text-cut-3 text-sm">{{item.summary}}</view>
					<view v-if="item.coverUrl">
						<image style="height: 4em; max-width: 170upx; margin-left: 10upx; border-radius: 5px" :src="item.coverUrl" mode="aspectFit"></image>
					</view>
				</view>
				<!-- 浏览信息 -->
				<view class="padding-tb-xs">
					<text class="text-gray text-sm margin-right-xs">浏览 {{item.viewNum}}</text>
					<text class="text-gray text-sm margin-right-xs">点赞 {{item.likeNum}}</text>
					<text class="text-gray text-sm">收藏 {{item.collectNum}}</text>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	import {
		getRecommendComp
	} from '@/api/ci/period.js'
	import {
		listSwiper
	} from '@/api/ci/swiper.js'
	import {
		listRecoComp
	} from '@/api/ci/recoComp.js'
	import {
		listPostInfo
	} from '@/api/ci/postInfo.js'
	import {
		listCollection
	} from '@/api/ci/collection.js'
	import {
		listIndexFamous
	} from '@/api/ci/indexFamous.js'

	export default {
		name: "HomeIndex",
		data() {
			return {
				ColorList: this.ColorList,
				
				// 搜索关键词
				keyword: undefined,

				// 收藏数目
				collectNum: 1,

				// 轮播参数
				cardCur: 0,
				swiperList: [],
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
					badge: this.collectNum,
					name: '收藏',
					url: 'news-list?type=collection'
				}, {
					cuIcon: 'presentfill',
					color: 'olive',
					badge: 0,
					name: '图书',
					url: undefined
				}],
				dotStyle: false,
				// 竞赛推荐list
				compList: [],
				// 动态推荐list
				newsList: [],
				// famous推荐List
				famousList: []

			}
		},
		onLoad() {
			// 安全性关系不大的不放在回调里面，可以匿名请求
			listRecoComp({
				pageNum: 1,
				pageSize: 10
			}).then(res => {
				this.compList = this.getRandomArrayElements(res.rows, 4)
				this.compList.forEach(item => {
					item.classifyList = item.classifyLabels ? item.classifyLabels.split(",") : []
				})
			})
			// 加载首页推荐
			listSwiper().then(res => {
				this.swiperList = res.rows
			})
			// 加载famous数据
			listIndexFamous({
				pageNum: 1,
				pageSize: 10,
				orderByColumn: 'id',
				isAsc: 'desc'
			}).then(res => {
				this.famousList = res.rows
				this.famousList = this.getRandomArrayElements(this.famousList, 12)
			})

			// 部分请求放在登录回调之后
			this.$store.dispatch('Login').then(loginRes => {
				// 跳转到欢迎页面 (去获取微信信息,根据小程序的规范不直接获取)
				if (loginRes.data.register) {
					uni.redirectTo({
						url: './a_welcome'
					})
				} else {
					listPostInfo({
						pageNum: 1,
						pageSize: 30
					}).then(res => {
						this.newsList = this.getRandomArrayElements(res.rows, 4)
					})
					listCollection().then(res => {
						if (res.total <= 1) {
							this.collectNum = 1
						} else {
							this.collectNum = res.total
						}
					})
				}
			})
		},
		methods: {
			onSwiperClick(item) {
				const type = item.type
				switch (type) {
					// 竞赛类型
					case '1':
						uni.navigateTo({
							url: 'competition-info?id=' + item.cpId
						})
						break;
						// 帖子类型
					case '2':
						uni.navigateTo({
							url: 'news-info?id=' + item.postId
						})
						break;
					case '3':
						uni.navigateTo({
							url: './web-view?webUrl=' + item.link
						})
						break;
					default:
						break;
				}
			},
			// 用户团队推荐点击
			onFamousItemClick(item) {
				if(item.type === 'user') {
					uni.navigateTo({
						url: 'user-show-index?userId=' + item.itemId
					})
				} else if(item.type === 'team') {
					uni.navigateTo({
						url: 'team-info?id=' + item.itemId
					})
				}
			},
			// 当搜索框聚焦的时候跳转到搜索界面
			goToSearchPage() {
				uni.navigateTo({
					url: 'search-index?activeType=comp'
				})
			},
			// 取代搜索事件改为setting设置
			goToSettingPage() {
				uni.navigateTo({
					url: 'sys-setting-index'
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
			goToPostPage(id) {
				uni.navigateTo({
					url: 'news-info?id=' + id
				})
			},
			// 样式相关
			cardSwiper(e) {
				this.cardCur = e.detail.current
			},
			getRandomArrayElements(arr, count) {
				var shuffled = arr.slice(0),
					i = arr.length,
					min = i >= count ? i - count : 0,
					temp, index;
				while (i-- > min) {
					index = Math.floor((i + 1) * Math.random());
					temp = shuffled[index];
					shuffled[index] = shuffled[i];
					shuffled[i] = temp;
				}
				return shuffled.slice(min);
			}

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
	
	.inline-image {
		width: 140upx;
		height: 140upx;
		border-radius: 14upx;
	}
	
</style>
