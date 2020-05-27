<template>
	<view class="app-container">
		队伍列表
	</view>
</template>

<script>
	
	import {
		listTeamInfo
	} from '@/api/ci/team.js'
	
	export default {
		name: 'TeamList',
		data() {
			return {
				// 加载状态
				loading: false,
				// 想要查询的类别
				teamType: undefined,
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
					cpId: undefined,
					status: undefined
				}
			}
		},
		onLoad(options) {
			this.queryParams.cpId = options.cpId
			this.teamType = options.type
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
				listTeamInfo(this.queryParams, this.teamType).then(res => {
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
