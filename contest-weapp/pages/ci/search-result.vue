<template>
	<view class="app-container">
		<!-- 搜索框 -->
		<van-search style="position: fixed; width: 100%; top: 0; z-index: 1024;" :value="keyword" use-action-slot placeholder="请输入搜索关键词" @search="onSearch">
			
		</van-search>
		
		<!-- 竞赛结果 -->
		<view v-for="(item,index) in compList" :key="index" @click="goToCompPage(item.id)" class="cu-card case no-card margin-top-100">
			<view class="cu-item shadow">
				<view class="image">
					<image :src="item.picUrl" mode="scaleToFill"></image>
					<view class="cu-tag bg-blue">{{levelFormat(item.basic.level)}}</view>
				</view>
				<view class="cu-list padding">
					<view v-if="calSignDate(item.signEndTime)" class="">
						<text class="text-green margin-right">正在报名</text><text>离报名截止还有{{calSignDate(item.signEndTime)}}天</text>
					</view>
					<view v-else class="">
						<text class="text-grey">报名已结束</text>
					</view>
					<view class="text-bold text-lg padding-tb-xs">{{item.name}}</view>
					<view class=""><text class="margin-right">报名时间</text>
						{{formatDate(item.signBeginTime)}} —— {{formatDate(item.signEndTime)}}</view>
				</view>
			</view>
		</view>
		<!-- 动态结果 -->
		<view class="cu-list">
			<view class="cu-item post-card padding-sm bg-white shadow-blur margin-tb-sm" @click="goToPostPage(item.id)" v-for="(item,index) in postList"
			 :key="index">
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
				<view class="flex padding-tb-xs" style="height: 4.5em; position: relative;">
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
		search
	} from '@/api/ci/search.js'

	export default {
		name: 'SearchResult',
		data() {
			return {
				// 搜索关键字
				keyword: undefined,
				// 搜索类型
				type: undefined,
				// 搜索结果
				compList: [],
				postList: [],
				// 竞赛级别字典
				levelOptions: []
			}
		},
		onLoad(options) {
			this.keyword = decodeURIComponent(options.keyword)
			this.type = options.type
			this.loadList()
			this.getDicts('ci_competition_level').then(res => {
				this.levelOptions = res.data
			})
		},
		methods: {
			loadList() {
				search(this.keyword, this.type).then(res => {
					if (res.comp) {
						res.comp.forEach(item => {
							if (item.holder)
								item.holder = item.holder.replace('，', ',').split(',')
						})
						this.compList = res.comp
					}
					this.postList = res.post
				})
			},
			onSearch(e) {
				this.keyword = e.detail
				this.loadList()
			},
			goToCompPage(id) {
				uni.navigateTo({
					url: 'competition-info?id=' + id
				})
			},
			goToPostPage(id) {
				uni.navigateTo({
					url: 'news-info?id=' +id
				})
			},
			levelFormat(value, dict) {
				return this.selectDictLabel(this.levelOptions, value)
			},
			calSignDate(date) {
				try {
					const today = new Date()
					date = new Date(date)
					if (today.getTime() > date.getTime()) {
						return false
					}
					return Math.floor((date.getTime() - today.getTime()) / (24 * 3600 * 1000))
				} catch (e) {
					console.log(e)
					return false
				}
			},
			
		}
	}
</script>

<style scoped lang="scss">
	
	.margin-top-100 {
		margin-top: 100upx;
	}
	
	.cu-card .cu-item .image {
		margin: 0;
		// height: 280upx;
		height: 260upx;
		border-radius: 0;
	}
	
	.cu-card .cu-item .image image {
		height: 100%;
	}
</style>
