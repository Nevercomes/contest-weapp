<template>
	<view class="app-container bg-white">
		<!-- 搜索框 -->
		<van-search :value="queryParams.keyword" placeholder="请输入搜索关键词" show-action @search="onSearch" />
		<!-- 热门搜索 -->
		<view class="cu-bar">
			<view class="action">
				<!-- <text class="cuIcon-title text-green"></text> -->
				<text class="text-bold">热门搜索</text>
			</view>
		</view>
		<view class="box popular-list">
			<view class="popular-item" v-for="(item, index) in popularList" :key="index" @click="onPopularClick(item.keyword)">
				<!-- 热门123 -->
				<view v-if="[0,1,2].indexOf(index) != -1" class='cu-tag bg-red' style="width: 20px; height: 20px;">{{index + 1}}</view>
				<!-- 后续 -->
				<view v-else class='cu-tag bg-orange' style="width: 20px; height: 20px;">{{index + 1}}</view>
				<view class="text-df text-grey margin-left">{{item.keyword}}</view>
			</view>
		</view>

		<!-- 搜索记录 -->
		<view class="cu-bar">
			<view class="action">
				<!-- <text class="cuIcon-title text-green"></text> -->
				<text class="text-bold">搜索记录</text>
			</view>
		</view>
		<view class="padding-lr">
			<view v-for="(item,index) in recordList" :key="index" class='cu-tag round margin-xs' @click="onRecordClick(item.keyword)">{{item.keyword}}</view>
			<!-- 清空历史记录 -->
			<view class="padding flex flex-direction text-center margin-top">
				<view class="text-grey" @click="onClearClick">清空历史记录</view>
			</view>
		</view>

	</view>
</template>

<script>
	import {
		mapGetters
	} from 'vuex'
	import {
		listPopular
	} from '@/api/ci/popular.js'
	import {
		listSearchRecord,
		delAllSearchRecord
	} from '@/api/ci/searchRecord.js'

	export default {
		name: '',
		// computed: {
		// 	...mapGetters([
		// 		'searchRecord'
		// 	])
		// },
		data() {
			return {
				// 查询参数
				queryParams: {
					keyword: undefined,
					activeType: undefined
				},
				// 热门搜索
				popularList: [],
				// 搜索记录
				recordList: []
			}
		},
		onLoad(options) {
			if (['all', 'comp', 'post'].indexOf(options.activeType) != -1) {
				this.queryParams.activeType = options.activeType
			} else {
				this.queryParams.activeType = 'comp'
			}
			// 获取热门搜索
			this.loadPopular()
		},
		onShow() {
			// 获取历史搜索
			this.loadRecord()
		},
		methods: {
			//  搜索:保存记录，跳转
			doSearch() {
				// this.$store.dispatch('Search', value)
				uni.navigateTo({
					url: 'search-result?keyword=' + this.queryParams.keyword + '&activeType=' + this.queryParams.activeType,
				})
			},
			onSearch(e) {
				this.queryParams.keyword = e.detail
				this.doSearch()
			},
			// 点击热门搜索
			onPopularClick(value) {
				this.queryParams.keyword = value
				this.doSearch()
			},
			// 点击历史记搜索
			onRecordClick(value) {
				this.queryParams.keyword = value
				this.doSearch()
			},
			// 清空历史记录
			onClearClick() {
				let self = this
				uni.showModal({
					title: '操作确认',
					content: '删除所有的历史记录',
					success: function(res) {
						if (res.confirm) {
							// self.$store.dispatch('ClearSearch').then(() => {
							// 	self.msgInfo('清除搜索历史成功')
							// })
							delAllSearchRecord().then(res => {
								if (res.code == 200) {
									self.msgInfo('清除搜索历史成功')
									self.loadRecord()
								}
							})
						}
					}
				})

			},
			loadPopular() {
				let type = ''
				if (this.queryParams.type == 'comp') type = '1'
				else if (this.queryParams.type == 'post') type = '2'
				listPopular({
					type: type
				}).then(res => {
					this.popularList = res.rows
				})
			},
			loadRecord() {
				listSearchRecord().then(res => {
					this.recordList = res.rows
				})
			}
		}
	}
</script>

<style>
	.popular-list {
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
		justify-content: space-around;
	}

	.popular-list .popular-item {
		display: flex;
		width: 50%;
		padding: 30upx;
	}
</style>
