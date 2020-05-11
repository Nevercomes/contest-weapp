<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="false">
			<!-- <block slot="backText">返回</block> -->
			<!-- <block slot="content">竞赛</block> -->
		</cu-custom>
		<!-- 与tab头相同的过度 最右边是一个search -->
		<view class="tab-head">
			<view>竞赛</view>
			<van-icon name="search-o" @click="goToSearchPage"></van-icon>
		</view>
		<view class="select-search">
			搜索过滤
		</view>
		<view class="list">
			<view v-for="(item,index) in dataList" :key="index" @click="onItemClick(item.id)">
				<view>{{item.name}}</view>
			</view>
		</view>
	</view>
</template>

<script>
	
	import {
		listPeriod
	} from '@/api/ci/period.js'
	
	export default {
		name: 'CompetitionIndex',
		data() {
			return {
				// 加载状态
				loading: false,
				// 是否有更多数据
				hasMoreData: false,
				// 查询参数
				queryParams: {
					pageNum: 1,
					pageSize: 30,
					classify: undefined,
					level: undefined,
					sortWay: undefined,
					schoolId: undefined
				},
				// 数据列表
				dataList: [],
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
				listPeriod(this.queryParams).then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
				})
			},
			goToSearchPage() {
				uni.navigateTo({
					url: 'search-competition'
				})
			},
			onItemClick(id) {
				uni.navigateTo({
					url: 'competition-info?id=' + id
				})
			},
		}
	}
</script>

<style>
</style>
