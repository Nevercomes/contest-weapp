<template>
	<view class="app-container">
		<!-- 与tab头相同的过度 最右边是一个search -->
		<van-search :value="keywords" use-action-slot placeholder="请输入搜索关键词" @focus="goToSearchPage">

		</van-search>
		<!-- TODO 竞赛列表搜索过滤 -->
		<!-- <view class="select-search">
			搜索过滤
		</view> -->

		<view v-for="(item,index) in dataList" :key="index" @click="onItemClick(item.id)" class="cu-card case no-card">
			<view class="cu-item shadow">
				<view class="image">
					<image :src="item.picUrl" mode="widthFix"></image>
					<view class="cu-tag bg-blue">{{levelFormat(item.basic.level)}}</view>
				</view>
				<view class="cu-list menu-avatar">
					<view v-if="calSignDate(item.signEndTime)">
						<text class="">正在报名</text><text>离报名截止还有{{calSignDate(item.signEndTime)}}天</text>
					</view>
					<view v-else>
						<text>报名已结束</text>
					</view>
					<view class="text-bold text-lg">{{item.name}}</view>
					<view>报名时间 {{formatDate(item.signBeginTime)}} —— {{formatDate(item.signEndTime)}}</view>
					<view>
						<view>主办方</view>
						<view v-for="(holder,hIndex) in item.holder" :key="hIndex">
							{{holder}}
						</view>
					</view>
					<text class="cuIcon-attentionfill margin-lr-xs"></text> {{item.basic.viewNumber}}
					<text class="cuIcon-appreciatefill margin-lr-xs"></text> {{item.basic.concernNumber}}
				</view>
			</view>
		</view>

		<view class="list">
			<view>
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
				// 搜索关键词
				keywords: undefined,
				// 查询参数				
				queryParams: {
					pageNum: 1,
					pageSize: 10,
					classify: undefined,
					level: undefined,
					sortWay: undefined,
					schoolId: undefined
				},
				// 数据列表
				dataList: [],
				// 竞赛级别字典
				levelOptions: []
			}
		},
		onLoad() {
			this.loadList()
			this.getDicts('ci_competition_level').then(res => {
				this.levelOptions = res.data
			})
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
					res.rows.forEach(item => {
						if(item.holder)
							item.holder = item.holder.replace('，', ',').split(',')
					})
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
			parseHolder(str) {
				try {
					console.log(str)
					console.log(str.split(','))
					return str.replace('，', ',').split(',')
				} catch (e) {
					console.log(e)
					return []
				}
			},
			levelFormat(value, dict) {
				return this.selectDictLabel(this.levelOptions, value)
			}
		}
	}
</script>

<style>
</style>
