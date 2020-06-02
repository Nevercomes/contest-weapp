<template>
	<view class="app-container">
		<view class="cu-list menu-avatar">
			<view class="cu-item" @click="onItemClick(item.id)" v-for="(item,index) in dataList" :key="index">
				<image :src="item.picUrl ? item.picUrl : dfTeamAvatar" class="cu-avatar round lg bg-white"></image>
				<view class="content flex-sub flex justify-between align-center">
					<view class="flex-direction align-start">
						<view class="flex">
							<view class="text-df margin-right-sm">{{item.name}}</view>
							<text v-if="item.status == '0'" class="cu-tag radius text-green">组队中</text>
							<text v-else-if="item.status == '1'" class="cu-tag radius text-orange">已完成</text>
							<text v-else class="cu-tag radius">已解散</text>
						</view>
						<view class="text-grey text-cut">{{item.cpName}}</view>
						<view class="text-grey text-cut text-sm">{{item.intro}}</view>
					</view>
				</view>
			</view>
		</view>
		<nl-empty v-if="dataList == undefined || dataList.length == 0" :show="true"></nl-empty>
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
				},
				//  默认的队伍头像
				dfTeamAvatar: require('@/static/df_team_avatar.png')
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
				this.loading = true
				listTeamInfo(this.queryParams, this.teamType).then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
				})
			},
			onItemClick(id) {
				uni.navigateTo({
					url: 'team-info?id=' + id
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	.align-start {
		align-items: flex-start !important;
	}
</style>
