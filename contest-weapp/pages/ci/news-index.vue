<template>
	<div class="app-container">
		<!-- 搜索部分 -->
		<van-search :value="keyword" use-action-slot placeholder="请输入搜索关键词" @focus="goToSearchPage">
			<view style="display: flex; align-items: center;" slot="action" @click="goToPublicPost">
				<van-icon size="24" name="add"></van-icon>
			</view>
		</van-search>
		
		<!-- 这里是主要内容 -->
		<view class="cu-list">
			<view class="cu-item post-card padding-sm bg-white shadow-blur margin-tb-sm" @click="onItemClick(item.id)" v-for="(item,index) in dataList"
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
		
		<nl-loading :loading="loading"></nl-loading>
		<nl-empty v-if="empty" :show="true"></nl-empty>
		
	</div>
</template>

<script>
	
	import {
		listPostInfo
	} from '@/api/ci/postInfo.js'
	
	export default {
		name: 'NewsIndex',
		data() {
			return {
				// 加载状态
				loading: false,
				// 无数据
				empty: false,
				// 是否有更多数据
				hasMoreData: false,
				// 搜索关键词
				keyword: undefined,
				// 查询参数				
				queryParams: {
					pageNum: 1,
					pageSize: 10
				},
				// 数据
				dataList: []
			}
		},
		onLoad() {
			this.loadList()
		},
		// 列表数据刷新
		onPullDownRefresh() {
			this.queryParams.pageNum = 1
			this.dataList = []
			this.loadList()
		},
		onReachBottom() {
			if (this.hasMoreData) {
				this.queryParams.pageNum = this.queryParams.pageNum + 1
				this.loadList()
			}
		},
		methods: {
			loadList() {
				this.loading = true
				listPostInfo(this.queryParams).then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
					this.empty = this.dataList.length == 0
				})
			},
			goToSearchPage() {
				uni.navigateTo({
					url: 'search-index?type=all'
				})
			},
			// 取代搜索事件改为setting设置
			goToPublicPost() {
				// 动态发布功能已关闭
				// this.msgInfo('发布暂时关闭')
				uni.navigateTo({
					url: 'news-public-form'
				})
			},
			onItemClick(id) {
				uni.navigateTo({
					url: 'news-info?id=' + id
				})
			}
		}
	}
</script>

<style>
</style>
