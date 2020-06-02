<template>
	<view class="app-container">
		<view class="cu-list menu-avatar">
			<view class="cu-item" :class="modalName=='move-box-'+ index?'move-cur':''" v-for="(item,index) in dataList" :key="index" @touchstart="ListTouchStart" @touchmove="ListTouchMove"
			 @touchend="ListTouchEnd" :data-target="'move-box-' + index">
				<!-- <image :src="dfExpectAvatar" class="cu-avatar round lg bg-white"></image> -->
				<view class="content flex-sub flex justify-between align-center">
					<view class="flex-direction align-start">
						<view class="flex">
							<view class="text-df margin-right-sm text-cut">{{item.cpName}}</view>
							<text v-if="item.status == '1'" class="cu-tag radius text-green">等待组队</text>
							<text v-else-if="item.status == '2'" class="cu-tag radius text-orange">已组队</text>
							<text v-else-if="item.status == '3'" class="cu-tag radius">已撤回</text>
						</view>
						<view class="text-grey text-cut">期望分工：{{workFormat(item.work)}}</view>
						<view class="text-grey text-cut text-sm">个人能力：{{item.capability == null ? '' : item.capability}}</view>
					</view>
				</view>
				<view v-if="item.status == '1'" class="move">
					<view class="bg-red" @click.stop="cancelExpect(item.id)">撤销</view>
				</view>
			</view>
		</view>
		<nl-empty v-if="dataList == undefined || dataList.length == 0" :show="true"></nl-empty>
	</view>
</template>

<script>
	import {
		listExpect,
		undoExpect
	} from '@/api/ci/expect.js'

	export default {
		name: 'TeamExpectList',
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
				},
				// 分工选项
				workOptions: [],
				// 滑动标识
				modalName: null,
				listTouchStart: 0,
				listTouchDirection: null,
			}
		},
		onLoad() {
			this.loadList()
			this.getDicts('team_work_type').then(res => {
				this.workOptions = res.data
			})
		},
		// 列表数据刷新
		onPullDownRefresh() {
			this.reLoad()
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
				listExpect(this.queryParams).then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
				})
			},
			reLoad() {
				this.queryParams.pageNum = 1
				this.dataList = []
				this.loadList()
			},
			cancelExpect(id) {
				undoExpect(id).then(res => {
					this.msgSuccess('撤销成功')
					this.reLoad()
				})
			},
			workFormat(value, dict) {
				return this.selectDictLabel(this.workOptions, value)
			},

			// ListTouch开始滚动
			ListTouchStart(e) {
				this.listTouchStart = e.touches[0].pageX
			},
			// ListTouch计算方向
			ListTouchMove(e) {
				this.listTouchDirection = e.touches[0].pageX - this.listTouchStart > 0 ? 'right' : 'left'
			},

			// ListTouch计算滚动
			ListTouchEnd(e) {
				if (this.listTouchDirection == 'left') {
					this.modalName = e.currentTarget.dataset.target
				} else {
					this.modalName = null
				}
				this.listTouchDirection = null
			},

		}
	}
</script>

<style scoped lang="scss">
	.cu-list.menu-avatar>.cu-item .content {
		position: absolute;
		left: 30upx;
	}

	.align-start {
		align-items: flex-start !important;
	}
</style>
