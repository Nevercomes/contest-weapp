<template>
	<view class="app-container">
		<view class="user-expect-list">
			<!-- TODO 切换淡入淡出的动画 -->
			<view v-for="(item,index) in teamList" :key="index" @click="goToTeamShowPage">
				<view>{{item.name}}</view>
			</view>
			<van-button type="primary" @click="onChangeClick">换一批</van-button>
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
				// 想要加入队伍的用户列表
				dataList: [],
				teamList: [],
				// 是否有更多数据
				hasMoreData: false,
				// 查询参数
				queryParams: {
					pageNum: 1,
					pageSize: 5,
					cpId: undefined
				}
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
					})
				}
				this.teamList = this.getRandomArrayElements(this.dataList, 5)
			},
			onChangeClick() {
				if (this.hasMoreData) {
					this.queryParams.pageNum = this.queryParams.pageNum + 1
					this.loadList()
				} else {
					this.teamList = this.getRandomArrayElements(this.dataList, 5)
				}
			},
			// 跳转到用户展示页面
			goToTeamShowPage() {
				console.log('跳转到队伍展示界面')
			},
			// 从一个数组中随机取得n个元素
			getRandomArrayElements(arr, count) {
				if (!arr) return
				var shuffled = arr.slice(0),
					i = arr.length,
					min = i - count,
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
</style>
