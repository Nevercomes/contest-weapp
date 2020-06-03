<template>
	<view class="app-container bg-white">

		<view class="bg-white padding">
			<view class="cu-steps">
				<view class="cu-item" :class="index>step?'':'text-red'" v-for="(item,index) in stepList" :key="index">
					<text :class="'cuIcon-' + item.cuIcon"></text> {{item.name}}
				</view>
			</view>
		</view>
		
		<nl-success :icon="false" msg="队伍创建成功!"></nl-success>
		
		<view class="cu-list grid col-2 no-border margin">
			<view class="cu-item" v-for="(item,index) in expectList" :key="index" @click="goToUserShowPage(item.userId)">
				<image class="cu-avatar round xl bg-white " :src="item.createUser.avatar ? item.createUser.avatar : dfUserAvatar"></image>
				<text class="text-gray padding-tb-xs">{{item.createUser.nickName}}</text>
				<text class="text-gray text-sm">{{item.capability}}</text>
			</view>
		</view>
		
		<view  class="flex-sub text-center">
			<view class="solid-top text-df padding text-gray">
				<text v-if="expectList && expectList.length > 0">他们也在找队伍，试着邀请一下吧</text>
				<text v-else>好像没有人在寻找队伍呢</text>
			</view>
		</view>
		
		<view class="padding flex flex-direction">
			<!-- :disabled="!(expectList && expectList.length > 0)" -->
			<button  class="cu-btn bg-green margin-tb-sm lg shadow-blur round" @click="onChangeClick">换一批</button>
		</view>
	</view>
</template>

<script>
	import {
		listExpect
	} from '@/api/ci/expect.js'

	export default {
		name: 'TeamPublicSuccess',
		data() {
			return {
				// 步骤进度
				step: 2,
				// 步骤条进度
				stepList: [{
					cuIcon: 'usefullfill',
					name: '队伍信息'
				}, {
					cuIcon: 'radioboxfill',
					name: '队员信息'
				}, {
					cuIcon: 'roundcheckfill',
					name: '创建完成'
				}],
				// 想要加入队伍的用户列表
				dataList: [],
				expectList: [],
				// 是否有更多数据
				hasMoreData: false,
				// 查询参数
				queryParams: {
					pageNum: 1,
					pageSize: 5,
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
		methods: {
			loadList() {
				listExpect(this.queryParams).then(res => {
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
					this.expectList = this.getRandomArrayElements(this.dataList, 5)
				})
			},
			onChangeClick() {
				if (this.hasMoreData) {
					this.queryParams.pageNum = this.queryParams.pageNum + 1
					this.loadList()
				} else {
					this.expectList = this.getRandomArrayElements(this.dataList, 5)
				}
			},
			// 跳转到用户展示页面
			goToUserShowPage(userId) {
				uni.navigateTo({
					url: 'user-show-index?userId=' + userId + '&teamId=' + this.teamId
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
