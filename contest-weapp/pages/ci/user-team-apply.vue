<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">竞赛经历</block>
		</cu-custom>
		<van-tabs :active="active">
			<van-tab title="我的申请">
				
			</van-tab>
			<van-tab title="收到的申请">
				
			</van-tab>
		</van-tabs>
	</view>
</template>

<script>
	import {
		listApply
	} from '@/api/ci/apply.js'

	export default {
		name: 'UserTeamApply',
		data() {
			return {
				// tab激活页
				active: 0,
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
				}
			}
		},
		onLoad() {
			this.loadList()
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
				listApply().then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
				})
			}
		}
	}
</script>

<style scoped lang="scss">
</style>
