<template>
	<view class="app-container">
		<!-- 与tab头相同的过度 最右边是一个search -->
		<van-search :value="keyword" use-action-slot placeholder="请输入搜索关键词" @focus="goToSearchPage">

		</van-search>
		<!-- TODO 竞赛列表搜索过滤 -->
		<!-- <view class="select-search">
			搜索过滤
		</view> -->

		<view v-for="(item,index) in dataList" :key="index" @click="onItemClick(item.id)" class="cu-card case no-card margin-bottom">
			<view class="cu-item shadow">
				<view class="image">
					<image :src="item.picUrl" mode="aspectFill"></image>
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
					<view class="flex">
						<text class="margin-right" style="min-width: 50px;">主办单位</text>
						<view>
							<view v-for="(holder, hIndex) in item.holder" :key="hIndex">
								{{holder}}
							</view>
						</view>
					</view>
					<view class="text-right padding-tb-sm">
						<text class="cuIcon-attentionfill margin-lr-xs text-grey"></text>
						<text class="text-gray">浏览 {{item.basic.viewNumber}}</text>

						<text class="cuIcon-favorfill margin-lr-xs text-grey"></text>
						<text class="text-gray">关注 {{item.basic.concernNumber}}</text>
					</view>
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
				keyword: undefined,
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
						if (item.holder) {
							item.holder = item.holder.replace('，', ',').split(',')
							if (item.holder.length > 3) {
								item.holder = item.holder.slice(0, 3)
							}
						}
					})
					this.dataList = this.dataList.concat(res.rows)
				})
			},
			// 当搜索框聚焦的时候跳转到搜索界面
			goToSearchPage() {
				uni.navigateTo({
					url: 'search-index?type=comp'
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

<style scoped lang="scss">
	.cu-card .cu-item .image {
		margin: 0;
		height: 240upx;
		border-radius: 0;
	}

	.van-ellipsis {
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis
	}

	.van-multi-ellipsis--l2 {
		-webkit-line-clamp: 2
	}

	.van-multi-ellipsis--l2,
	.van-multi-ellipsis--l3 {
		display: -webkit-box;
		overflow: hidden;
		text-overflow: ellipsis;
		-webkit-box-orient: vertical
	}

	.van-multi-ellipsis--l3 {
		-webkit-line-clamp: 3
	}

	.fixed {
		position: fixed;
		width: 100%;
		top: 0;
		z-index: 1024;
	}
</style>
