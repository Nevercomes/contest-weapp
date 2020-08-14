<template>
	<view class="app-container">
		<view class="cu-list menu-avatar">
			<view class="cu-item" @click="goToUserPage(item.createUser.userId)" v-for="(item,index) in dataList"
			 :key="index">
				<image :src="item.createUser.avatar" class="cu-avatar round lg bg-white"></image>
				<view class="content flex-sub flex justify-between align-center">
					<view class="flex-direction align-start">
						<view class="flex">
							<view class="text-df margin-right-sm text-cut" style="max-width: 430upx;">{{item.cpName}}</view>
							<text v-if="item.status == '1'" class="cu-tag radius text-green">等待组队</text>
							<text v-else-if="item.status == '2'" class="cu-tag radius text-orange">已组队</text>
							<text v-else-if="item.status == '3'" class="cu-tag radius">已撤回</text>
						</view>
						<view class="text-grey text-cut">期望分工：{{workFormat(item.work)}}</view>
						<view class="text-grey text-cut text-sm">个人能力：{{item.capability == null ? '' : item.capability}}</view>
					</view>
				</view>
			</view>
		</view>
		<nl-loading :loading="loading"></nl-loading>
		<nl-empty :show="empty"></nl-empty>
	</view>
</template>

<script>
	import {
		listExpectNews
	} from '@/api/ci/expect.js'

	export default {
		name: 'ExpectList',
		data() {
			return {
				// 加载状态
				loading: false,
				// 数据为空
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
					pageSize: 10,
					cpId: undefined
				},
				// 其他数据
				workOptions: []
			}
		},
		onLoad(options) {
			this.queryParams.cpId = options.cpId
			this.loadList()
			this.getDicts('team_work_type').then(res => {
				this.workOptions = res.data
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
				listExpectNews(this.queryParams).then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
					this.empty = !this.dataList || this.dataList.length==0
				})
			},
			goToUserPage(userId) {
				uni.navigateTo({
					url: 'user-show-index?userId=' + userId
				})
			},
			workFormat(value, dict) {
				return this.selectDictLabel(this.workOptions, value)
			},
		}
	}
</script>

<style scoped lang="scss">
	.align-start {
		align-items: flex-start !important;
	}
</style>
