<template>
	<view class="app-container bg-white">

		<!-- <view class="bg-white padding">
			<view class="cu-steps">
				<view class="cu-item" :class="index>step?'':'text-red'" v-for="(item,index) in stepList" :key="index">
					<text :class="'cuIcon-' + item.cuIcon"></text> {{item.name}}
				</view>
			</view>
		</view> -->

		<van-steps :steps="stepList" :active="step" />

		<nl-success :icon="false" msg="队伍创建成功!"></nl-success>

		<view class="cu-list grid col-2 no-border margin">
			<view class="cu-item" v-for="(item,index) in expectList" :key="index" @click="goToUserShowPage(item.createUser.userId)">
				<image class="cu-avatar round xl bg-white " :src="item.createUser.avatar ? item.createUser.avatar : dfUserAvatar"></image>
				<text class="text-gray padding-tb-xs">{{item.createUser.nickName}}</text>
				<text class="text-gray text-sm">{{item.capability}}</text>
			</view>
		</view>

		<view class="flex-sub text-center">
			<view class="solid-top text-df padding text-gray">
				<text v-if="expectList && expectList.length > 0">他们也在找队伍，或者竞赛经验丰富哦~</text>
				<text v-else>好像没有人合适的人在找队伍呢</text>
			</view>
		</view>

		<!-- <view class="padding flex flex-direction">
			<button  class="cu-btn bg-green margin-tb-sm lg shadow-blur round" @click="onChangeClick">换一批</button>
		</view> -->
		<view class="box margin-tb">
			<view class="cu-bar btn-group">
				<button class="cu-btn bg-green shadow-blur round lg" @click="goToTeamPage">查看队伍</button>
				<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" @click="onChangeClick">换一批</button>
			</view>
		</view>
	</view>
</template>

<script>
	// 这里更换为冷启动方式，可能没有Expect
	import {
		listPossibleUser
	} from '@/api/ci/expect.js'

	export default {
		name: 'TeamPublicSuccess',
		data() {
			return {
				// 步骤进度
				step: 2,
				// 步骤条进度
				// stepList: [{
				// 	cuIcon: 'usefullfill',
				// 	name: '队伍信息'
				// }, {
				// 	cuIcon: 'radioboxfill',
				// 	name: '队员信息'
				// }, {
				// 	cuIcon: 'roundcheckfill',
				// 	name: '创建完成'
				// }],
				stepList: [{
						text: '队伍信息',
					},
					{
						text: '队员信息',
					},
					{
						text: '创建完成',
					},
				],

				// 想要加入队伍的用户列表
				dataList: [],
				expectList: [],
				// 是否有更多数据
				hasMoreData: false,
				// 查询参数
				queryParams: {
					pageNum: 1,
					pageSize: 30,
					cpId: undefined
				},
				// 队伍id
				teamId: undefined,
				dfUserAvatar: require('@/static/df_user_avatar.png')
			}
		},
		onLoad(options) {
			this.queryParams.cpId = options.cpId
			this.teamId = options.teamId
			this.loadList()
		},
		// onBackPress() {
		// 	const pages = getCurrentPages()
		// 	console.log(pages)
		// 	if (pages.length > 3) {
		// 		uni.navigateBack({
		// 			delta: 3
		// 		})
		// 	} else {
		// 		uni.navigateBack({
		// 			delta: 1
		// 		})
		// 	}
		// },
		methods: {
			loadList() {
				listPossibleUser(this.queryParams).then(res => {
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
					this.expectList = this.getRandomArrayElements(this.dataList, 4)
				})
			},
			onChangeClick() {
				// if (this.hasMoreData) {
				// 	this.queryParams.pageNum = this.queryParams.pageNum + 1
				// 	this.loadList()
				// } else {
				if (this.dataList.length < 5) {
					this.msgInfo('好像没有更多了')
				} else {
					this.expectList = this.getRandomArrayElements(this.dataList, 4)
				}
				// }
			},
			// 跳转到用户展示页面
			goToUserShowPage(userId) {
				uni.navigateTo({
					url: 'user-show-index?userId=' + userId + '&teamId=' + this.teamId
				})
			},
			goToTeamPage() {
				uni.navigateTo({
					url: 'team-info?id=' + this.teamId
				})
			},
			// 从一个数组中随机取得n个元素
			getRandomArrayElements(arr, count) {
				if (!arr) return
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

<style scoped lang="scss">
	.grid .cu-item {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
</style>
