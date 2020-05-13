<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">我的组队意愿</block>
		</cu-custom>
		<view v-for="(item,index) in dataList" :key="index" @longtap="onItemLongtap(item.id)">
			{{item.name}}
		</view>

		<!-- 长按时展示的弹窗 -->
		<view class="cu-modal" :class="showModal?'show':''" @tap="hideModal">
			<view class="cu-dialog" @tap.stop="">
				<view class="cu-list menu">
					<view class="cu-item" @click="onCancelClick">
						<view class="padding-xl">
							撤销组队意愿
						</view>
					</view>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	import {
		listExpect,
		undoExpect
	} from '@/api/ci/expect.js'

	export default {
		name: 'UserTeamExpect',
		data() {
			return {
				// 长按显示
				showModal: false,
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
					pageSize: 10
				},
				// 当前操作的id
				expectId: undefined
			}
		},
		onLoad() {
			this.loadList()
		},
		// 列表数据刷新
		onPullDownRefresh() {
			this.refreshData()
		},
		onReachBottom() {
			if (this.hasMoreData) {
				this.queryParams.pageNum = this.queryParams.pageNum + 1
				this.loadList()
			}
		},
		methods: {
			refreshData() {
				this.queryParams.pageNum = 1
				this.dataList = []
				this.loadList()
			},
			loadList() {
				this.loading = true
				listExpect().then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
				})
			},
			onItemLongtap(id) {
				// 显示操作界面
				this.expectId = id
				this.showModal = true
			},
			onCancelClick() {
				undoExpect(this.expectId).then(res => {
					this.expectId = undefined
					if(res.code === 200) {
						this.refreshData()
					} else {
						this.msgInfo(res.msg)
					}
				})
			}
		}
	}
</script>

<style scoped lang="scss">
</style>
