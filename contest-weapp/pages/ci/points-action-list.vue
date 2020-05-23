<template>
	<view class="app-container">
		<view v-for="(item, index) in dataList" :key="index" class="cu-timeline">
			<view class="cu-time">{{formatTime(item.createTime)}}</view>
			<view class="cu-item">
				<view class="content">
					<view class="solid-bottom text-lg">
						<text class="text-black">{{item.value}}分</text>
					</view>
					<view class="margin-top">积分来源：{{changeTypeFormat(item.changeType)}}</view>
				</view>
			</view>
		</view>
		<nl-empty :show="empty"></nl-empty>
	</view>
</template>

<script>
	import {
		listPoints
	} from '@/api/ci/points.js'

	export default {
		name: 'UserDetailExperience',
		data() {
			return {
				// 加载状态
				loading: false,
				// empty
				empty: false,
				// 是否有更多数据
				hasMoreData: false,
				// 数据
				dataList: [],
				// 当前添加数据
				form: {},
				// 查询
				queryParams: {
					pageNum: 1,
					pageSize: 10
				},
				// 变动原因类型
				changeTypeOptions: []
			}
		},
		watch: {
			dataList: function(newVal, oldVal) {
				this.empty = newVal == undefined || newVal.length == 0
			}
		},
		onLoad() {
			this.loadList()
			this.getDicts('points_change_type').then(res => {
				this.changeTypeOptions = res.data
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
				listPoints(this.queryParams).then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
				})
			},
			changeTypeFormat(value, dict) {
				return this.selectDictLabel(this.changeTypeOptions, value)
			}
		}
	}
</script>

<style scoped lang="scss">
</style>
