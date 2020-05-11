<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">操作条</block>
		</cu-custom>
		<!-- 搜索框 -->
		<van-search :value="queryParams.keyword" placeholder="请输入搜索关键词" show-action @search="onSearch" />
		<!-- 热门搜索 -->
		<view class="popular-list-box">
			<view class="popular-item" v-for="(item, index) in popularList" :key="index" @click="onPopularClick(item.keyword)">
				<!-- 热门123 -->
				<view v-if="[0,1,2].indexOf(index) != -1" class='cu-tag bg-red'>{{index + 1}}</view>
				<!-- 后续 -->
				<view v-else class='cu-tag bg-orange'>{{index + 1}}</view>
				<view class="text-df">{{item.keyword}}</view>
			</view>
		</view>

		<!-- 搜索记录 -->
		<view>
			<view v-for="item in searchRecord" class='cu-tag' @click="onRecordClick(item.keyword)">{{item.keyword}}</view>
			<!-- 清空历史记录 -->
			<view @click="onClearClick">清空历史记录</view>
		</view>

	</view>
</template>

<script>
	import {
		mapGetters
	} from 'vuex'
	import {
		getPopularSearch
	} from '@/api/ci/search.js'

	export default {
		name: 'SearchCompetition',
		computed: {
			...mapGetters([
				'searchRecord'
			])
		},
		data() {
			return {
				// 查询参数
				queryParams: {
					keyword: undefined
				},
				// 热门搜索
				popularList: []
			}
		},
		onLoad() {
			// 获取热门搜索
			this.loadPopular()
		},
		methods: {
			//  搜索:保存记录，跳转
			onSearch(value) {
				this.$store.dispatch('Search', value)
				uni.navigateTo({
					url: 'search-result?keyword=' + value + '&type=all',
				})
			},
			// 点击热门搜索
			onPopularClick(value) {
				this.onSearch(value)
			},
			// 点击历史记搜索
			onRecordClick(value) {
				this.onSearch(value)
			},
			// 清空历史记录
			onClearClick() {
				this.$store.dispatch('ClearSearch').then(() => {
					this.msgInfo('清除搜索历史成功')
				})
			},
			loadPopular() {
				getPopularSearch().then(res => {
					this.popularList = res.rows
				})
			}
		}
	}
</script>

<style>
	.popular-list-box {}
	.popular-item {}
	
</style>
