<template>
	<view class="app-container bg-white">
		<nl-success :icon="true" msg="组队期望发布成功!"></nl-success>

		<!-- 该竞赛的队伍 -->
		<view class="cu-list grid col-2 no-border margin">
			<view class="cu-item" v-for="(item,index) in teamList" :key="index" @click="goToTeamShowPage(item.id)">
				<image class="cu-avatar round xl bg-white " :src="item.picUrl ? item.picUrl : dfTeamAvatar"></image>
				<text class="text-gray padding-tb-xs">{{item.name}}</text>
			</view>
		</view>

		<view class="flex-sub text-center">
			<view class="solid-bottom text-df padding text-gray">
				<text v-if="teamList && teamList.length > 0">这些队伍正在找队友，试着提交入队申请吧</text>
				<text v-else>好像这个比赛还没有什么队伍呢</text>
			</view>
		</view>
		<view class="box margin-tb">
			<view class="cu-bar btn-group">
				<button class="cu-btn bg-green shadow-blur round lg" @click="goToExpectPage">查看期望</button>
				<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" @click="onChangeClick">换一批</button>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		listTeamInfo
	} from '@/api/ci/team.js'

	export default {
		name: 'TeamPublicSuccess',
		data() {
			return {
				// 队伍列表
				dataList: [],
				teamList: [],
				// 是否有更多数据
				hasMoreData: true,
				// 查询参数
				queryParams: {
					pageNum: 1,
					pageSize: 10,
					cpId: undefined,
					status: '0'
				},
				//  默认的队伍头像
				dfTeamAvatar: require('@/static/df_team_avatar.png')
			}
		},
		onLoad(options) {
			this.queryParams.cpId = options.cpId
			this.loadList()
		},
		methods: {
			loadList() {
				if (this.hasMoreData) {
					listTeamInfo(this.queryParams).then(res => {
						this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
						this.dataList = this.dataList.concat(res.rows)
						this.teamList = this.getRandomArrayElements(this.dataList, 4)
					})
				}
			},
			onChangeClick() {
				if (this.hasMoreData) {
					this.queryParams.pageNum = this.queryParams.pageNum + 1
					this.loadList()
				} else {
					if (this.teamList.length < 4) {
						this.msgInfo('好像没有更多了')
					} else {
						this.teamList = this.getRandomArrayElements(this.dataList, 4)
					}
				}
			},
			// 跳转到用户展示页面
			goToTeamShowPage(id) {
				uni.navigateTo({
					url: 'team-info?id=' + id
				})
			},
			goToExpectPage() {
				uni.navigateTo({
					url: 'team-expect-list'
				})
			},
			// 从一个数组中随机取得n个元素
			getRandomArrayElements(arr, count) {
				var shuffled = arr.slice(0),
					i = arr.length,
					min = i >= count ? i - count : 0,
					temp, index;
				while (i-- > min) {
					index = Math.floor((i + 1) * Math.random());
					temp = shuffled[index];
					shuffled[index] = shuffled[i];
					shuffled[i] = temp;
				}
				return shuffled.slice(min);
			}
		}
	}
</script>

<style>
	.grid .cu-item {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
</style>
