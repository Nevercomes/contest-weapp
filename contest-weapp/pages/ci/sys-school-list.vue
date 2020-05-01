<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">选择学校</block>
		</cu-custom>
		<van-search :value="queryParams.schoolName" placeholder="请输入学校名称" show-action @search="onSearch" @cancel="onCancel" />
		<view v-for="item in dataList" :key="item.schoolId" @click="onItemClick(item)">
			{{item.schoolName}}
		</view>
	</view>
</template>

<script>
	import {
		listSchool
	} from '@/api/system/school.js'

	export default {
		name: '',
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
					schoolName: undefined
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
				listSchool(this.queryParams).then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
				})
			},
			onSearch(e) {
				console.log(e)
				this.dataList = []
				this.queryParams.schoolName = e.detail
				this.queryParams.pageNum = 1
				this.loadList()
			},
			onCancel() {
				// do nothing
			},
			onItemClick(row) {
				const pages = getCurrentPages()
				if(pages.length >= 2) {
					const prePage = pages[pages.length - 2]
					prePage.setData({
						'form.schoolName': row.schoolName
					})
					// 这样子form可以绑定上数据，但是页面不会渲染, 所以前面添加一个setData
					prePage.$vm.form.schoolName = row.schoolName
					prePage.$vm.form.schoolId = row.schoolId
					console.log(prePage)
				}
				uni.navigateBack({
					delta: 1
				})
			}
		}
	}
</script>

<style>
</style>
