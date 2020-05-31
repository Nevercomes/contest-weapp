<template>
	<view class="app-container">
		<van-search :value="queryParams.name" placeholder="请输入竞赛名称" show-action @search="onSearch" @cancel="onCancel" />
		<van-cell-group>
			<van-cell v-for="item in dataList" :key="item.id" @click="onItemClick(item)" clickable :title="item.name" />
		</van-cell-group>
	</view>
</template>

<script>
	import {
		listPeriod
	} from '@/api/ci/period.js'

	export default {
		name: 'CompetitionPeriodSelect',
		data() {
			return {
				// 加载状态
				loading: false,
				// 是否有更多数据
				hasMoreData: false,
				// 查询参数
				queryParams: {
					pageNum: 1,
					pageSize: 30,
					name: undefined
				},
				// 数据列表
				dataList: [],
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
				this.loading = true
				listPeriod(this.queryParams).then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
				})
			},
			onSearch(e) {
				this.dataList = []
				this.queryParams.name = e.detail
				this.queryParams.pageNum = 1
				this.loadList()
			},
			onCancel() {
				// do nothing
			},
			onItemClick(row) {
				const pages = getCurrentPages()
				if (pages.length >= 2) {
					const prePage = pages[pages.length - 2]
					prePage.setData({
						'form.cpName': row.name
					})
					// 这样子form可以绑定上数据，但是页面不会渲染, 所以前面添加一个setData
					prePage.$vm.form.cpName = row.name
					prePage.$vm.form.cpId = row.id
					console.log(prePage)
				}
				uni.navigateBack({
					delta: 1
				})
			}
		}
	}
</script>

<style scoped lang="scss">

</style>
