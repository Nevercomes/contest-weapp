<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">我的队伍</block>
		</cu-custom>
		<view v-for="(item,index) in dataList" :key="index" class="team-info__card" @click="goToTeamInfoPage(item.id)">
			{{item.name}}
		</view>
	</view>
</template>

<script>
	
	import {
		listTeamInfo
	} from '@/api/ci/team.js'
	
	export default {
		name: 'UserDetailExperience',
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
				this.loading = true
				listTeamInfo().then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
				})
			},
			goToTeamInfoPage(id) {
				uni.navigateTo({
					url: 'team-info?id=' + id
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	.team-info__card {
		
	}
</style>
