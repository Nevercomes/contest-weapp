<template>
	<view class="app-container">
		<!-- 与tab头相同的过度 最右边是一个search -->
		<van-search :value="keyword" use-action-slot placeholder="请输入搜索关键词" @focus="goToSearchPage">
			<view style="display: flex; align-items: center;" slot="action" @click="modalName = 'DrawerModalR'">
				<van-icon size="24" name="filter-o"></van-icon>
			</view>
		</van-search>

		<view v-for="(item,index) in dataList" :key="index" @click="onItemClick(item.id)" class="cu-card case no-card margin-bottom"
		 style="position: relative;">
			<view v-if="!item.picUrl" class="cu-tag bg-blue" style="position: absolute; right: 0; top: 0;">{{levelFormat(item.basic.level)}}</view>
			<view class="cu-item shadow">
				<view v-if="item.picUrl" class="image">
					<image :src="item.picUrl" mode="scaleToFill"></image>
					<view class="cu-tag bg-blue">{{levelFormat(item.basic.level)}}</view>
				</view>
				<view class="cu-list padding">
					<view class="flex justify-between align-center">
						<view v-if="calSignDate(item.signEndTime)" class="">
							<text class="text-green margin-right">正在报名</text><text>离报名截止还有{{calSignDate(item.signEndTime)}}天</text>
						</view>
						<view v-else class="">
							<text class="text-grey">报名已结束</text>
						</view>
					</view>
					<view class="text-bold text-lg padding-tb-xs">{{item.name}}</view>
					<view class=""><text class="margin-right nl-label">报名时间</text>
						{{formatDate(item.signBeginTime)}} —— {{formatDate(item.signEndTime)}}</view>
					<view class="flex">
						<text class="margin-right nl-label">主办单位</text>
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

		<!-- 竞赛筛选抽屉 -->
		<view class="cu-modal drawer-modal justify-end" style="text-align: left;" :class="modalName=='DrawerModalR'?'show':''"
		 @tap="hideModal">
			<view class="cu-dialog basis-lg" @tap.stop="" style="height: 100vh;">
				<scroll-view scroll-y="true" style="height: 100vh;">
					<!-- 分类栏 -->
					<view class="text-df padding text-gray">竞赛分类</view>
					<view class="padding-lr-sm">
						<button @click="queryParams.classify = undefined" :class="queryParams.classify==undefined?'bg-green':'line-green'"
						 class="cu-btn margin-xs">
							全部
						</button>
						<button @click="onClassifyClick(item.id)" :class="queryParams.classify==item.id?'bg-green':'line-green'"
						 class="cu-btn margin-xs" v-for="(item,index) in classifyList" :key="index">
							{{item.name}}
						</button>
					</view>
					<!-- 级别 -->
					<view class="text-df padding text-gray">竞赛级别</view>
					<view class="padding-lr-sm">
						<button @click="queryParams.level = undefined" :class="queryParams.level==undefined?'bg-green':'line-green'" class="cu-btn margin-xs">
							全部
						</button>
						<button @click="onLevelClick(item.dictValue)" :class="queryParams.level==item.dictValue?'bg-green':'line-green'"
						 class="cu-btn margin-xs" v-for="(item,index) in levelOptions" :key="index">
							{{item.dictLabel}}
						</button>
					</view>
					
					<view class="text-df padding text-gray">搜索优先</view>
					<view class="padding-lr-sm">
						<button @click="onSortWayClick(item.dictValue)" :class="queryParams.sortWay==item.dictValue?'bg-green':'line-green'"
						 class="cu-btn margin-xs" v-for="(item,index) in sortWayList" :key="index">
							{{item.dictLabel}}
						</button>
					</view>
					
					<view class="text-df padding text-gray flex justify-between align-center">
						<text class="text-grey">选定学校</text>
						<switch @change="OnlySchool" :class="queryParams.onlySchool?'checked':''" :checked="queryParams.onlySchool?true:false"></switch>
					</view>
					
					<view class="flex justify-around padding">
						<button class="cu-btn round line-green shadow" @click.stop="onClearClick">清空</button>
						<button class="cu-btn bg-green shadow round" @click.stop="onConfirmClick">确定</button>
					</view>
				</scroll-view>
			</view>
		</view>

	<nl-loading :loading="loading"></nl-loading>
	<nl-empty v-if="empty" :show="true"></nl-empty>

	</view>
</template>

<script>
	import {
		listPeriod
	} from '@/api/ci/period.js'
	import {
		listClassify
	} from '@/api/ci/classify.js'

	export default {
		name: 'CompetitionIndex',
		data() {
			return {
				// 加载状态
				loading: false,
				// 是否为空
				empty: false,
				// 是否有更多数据
				hasMoreData: false,
				// 搜索关键词
				keyword: undefined,
				// 查询参数				
				queryParams: {
					pageNum: 1,
					pageSize: 10,
					classify: undefined,
					sortWay: undefined,
					schoolId: undefined,
					signStatus: undefined,
					'level': undefined,
					onlySchool: false
				},
				// 数据列表
				dataList: [],
				// 竞赛级别字典
				levelOptions: [],
				// 显示右侧过滤抽屉
				modalName: null,
				// 分类目录
				classifyList: [],
				sortWayList: [{
						dictValue: undefined,
						dictLabel: '热度优先'
					},
					{
						dictValue: 'onlySign',
						dictLabel: '报名优先'
					}
				]
			}
		},
		onLoad() {
			this.loadList()
			this.getDicts('ci_competition_level').then(res => {
				this.levelOptions = res.data
			})
			listClassify({
				parentId: 0
			}).then(res => {
				this.classifyList = res.data
			})
		},
		// 列表数据刷新
		onPullDownRefresh() {
			this.reload()
		},
		onReachBottom() {
			if (this.hasMoreData) {
				this.queryParams.pageNum = this.queryParams.pageNum + 1
				this.loadList()
			}
		},
		methods: {
			reload() {
				this.queryParams.pageNum = 1
				this.dataList = []
				this.loadList()
			},
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
					this.empty = this.dataList.length == 0
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
			onClassifyClick(value) {
				this.queryParams.classify = value
			},
			onLevelClick(value) {
				this.queryParams.level = value
			},
			onSortWayClick(value) {
				this.queryParams.sortWay = value
			},
			OnlySchool(e) {
				this.queryParams.onlySchool = e.detail.value
			},
			onClearClick() {
				this.queryParams.classify = undefined
				this.queryParams.level = undefined
				this.queryParams.sortWay = undefined
				this.queryParams.schoolId = undefined
				this.queryParams.signStatus = undefined
				this.queryParams.onlySchool = false
			},
			onConfirmClick() {
				this.modalName = null
				this.reload()
			},
			hideModal() {
				this.modalName = null
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
		// height: 280upx;
		height: 260upx;
		border-radius: 0;
	}

	.cu-card .cu-item .image image {
		height: 100%;
	}

	.fixed {
		position: fixed;
		width: 100%;
		top: 0;
		z-index: 1024;
	}
</style>
