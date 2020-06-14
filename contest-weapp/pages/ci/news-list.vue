<template>
	<view class="app-container">
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
				<view v-if="['collection', 'public'].indexOf(listType) != -1" class="padding-tb-xs">
					<text class="text-gray text-sm margin-right-xs">浏览 {{item.viewNum}}</text>
					<text class="text-gray text-sm margin-right-xs">点赞 {{item.likeNum}}</text>
					<text class="text-gray text-sm">收藏 {{item.collectNum}}</text>
				</view>
			</view>
		</view>
		<nl-empty v-if="dataList == undefined || dataList.length == 0" :show="true"></nl-empty>
	</view>
</template>

<script>
	import {
		listPostInfo
	} from '@/api/ci/postInfo.js'

	export default {
		name: 'UserNewsDraftList',
		data() {
			return {
				// 加载状态
				loading: false,
				// 是否有更多数据
				hasMoreData: false,
				// 数据
				dataList: [],
				// 展示的类型
				listType: [],
				// 当前添加数据
				form: {},
				// 查询
				queryParams: {
					pageNum: 1,
					pageSize: 10
				}
			}
		},
		onLoad(options) {
			this.listType = options.type
			switch (this.listType) {
				case 'draft':
					uni.setNavigationBarTitle({
						title: '我的草稿'
					})
					break;
				case 'public':
					uni.setNavigationBarTitle({
						title: '已发布动态'
					})
					break;
				case 'collection':
					uni.setNavigationBarTitle({
						title: '我的收藏'
					})
					break;
				default:
					break;
			}
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
				listPostInfo(this.queryParams, this.listType).then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
				})
			},
			onItemClick(id) {
				if (this.listType == 'draft') {
					uni.navigateTo({
						url: 'news-public-form?id=' + id
					})
				} else {
					uni.navigateTo({
						url: 'news-info?id=' + id
					})
				}
			}
		}
	}
</script>

<style scoped lang="scss">

</style>
