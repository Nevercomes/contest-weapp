<template>
	<view class="app-container">
		<view class="cu-list menu-avatar">
			<view @click="goToWindow(item.receiveUserId)" v-for="(item,index) in dataList" :key="index" class="cu-item">
				<image class="cu-avatar round lg" :src="item.receiveUserAvatar"></image>
				<view class="content">
					<view class="text-grey">{{item.receiveUserNickName}}</view>
					<view class="text-gray text-sm flex">
						<view class="text-cut">{{item.content}}</view>
					</view>
				</view>
				<view class="action">
					<view class="text-grey text-xs">{{formatMsgIndexTime(item.createTime)}}</view>
					<view v-if="item.notReadNum > 0" class="cu-tag round bg-red sm">{{item.notReadNum}}</view>
				</view>
			</view>
		</view>
		<nl-empty v-if="dataList == undefined || dataList.length == 0" :show="true"></nl-empty>
	</view>
</template>

<script>
	import {
		listDigest
	} from '@/api/ci/message.js'

	export default {
		name: 'MessageIndex',
		data() {
			return {
				// 加载状态
				loading: false,
				// 是否有更多数据
				hasMoreData: false,
				// 数据
				dataList: [],
				// 当前添加数据
				form: {},
				// 查询
				queryParams: {
					pageNum: 1,
					pageSize: 20
				}
			}
		},
		onLoad() {
			
		},
		onShow() {
			this.reload()
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
			loadList() {
				this.loading = true
				listDigest(this.queryParams).then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
				})
			},
			goToWindow(userId) {
				uni.navigateTo({
					url: 'message-window?userId=' + userId
				})
			},
			reload() {
				this.queryParams.pageNum = 1
				this.dataList = []
				this.loadList()
			}
		}
	}
</script>

<style scoped lang="scss">
</style>
