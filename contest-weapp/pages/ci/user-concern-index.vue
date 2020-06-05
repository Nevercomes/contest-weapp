<template>
	<view class="app-container">
		<van-tabs>
			<van-tab title="关注的竞赛">
				<view v-for="(item,index) in periodList" :key="index" @click="goToCompInfo(item.id)" class="cu-card case no-card margin-bottom">
					<view class="cu-item shadow">
						<view class="image">
							<image :src="item.picUrl" mode="scaleToFill"></image>
							<view class="cu-tag bg-blue">{{levelFormat(item.basic.level)}}</view>
						</view>
						<view class="cu-list padding">
							<view class="text-bold text-lg padding-tb-xs">{{item.name}}</view>
							<view class=""><text class="margin-right nl-label">报名时间</text>
								{{formatDate(item.signBeginTime)}} —— {{formatDate(item.signEndTime)}}</view>
							<view class="flex">
								<text class="margin-right nl-label">主办单位</text>
								<view>
									<view v-for="(holder, hIndex) in item.holder" :key="hIndex">
										{{holder}}
									</view>
								</view>
							</view>
						</view>
					</view>
				</view>
				<nl-empty v-if="periodList == undefined || periodList.length == 0" :show="true"></nl-empty>
			</van-tab>
			<van-tab title="关注的用户">
				<view class="cu-list menu-avatar">
					<view class="cu-item" :class="modalName=='move-box-'+ index?'move-cur':''" @click="goToUserShow(item.userId)" v-for="(item,index) in userList" :key="index" @touchstart="ListTouchStart"
					 @touchmove="ListTouchMove" @touchend="ListTouchEnd" :data-target="'move-box-' + index">
						<image :src="item.avatar" class="cu-avatar round lg bg-white"></image>
						<view class="content flex-sub flex justify-between align-center">
							<view class="flex-direction align-start">
								<view class="text-df margin-right-sm">{{item.nickName}}</view>
								<view class="text-grey text-cut">{{item.schoolName}},{{trueName}}</view>
							</view>
						</view>
						<view class="move">
							<view class="bg-red" @click.stop="cancelConcern(item.userId)">修改</view>
						</view>
					</view>
				</view>
			</van-tab>
		</van-tabs>
	</view>
</template>

<script>
	import {
		listConcernPeriod
	} from '@/api/ci/period.js'
	import {
		listConcernUser
	} from '@/api/system/user.js'
	import {
		delConcernUser
	} from '@/api/ci/concern.js'

	export default {
		name: 'UserConcernIndex',
		data() {
			return {
				// tab的激活
				active: 0,
				// 加载状态
				loading: false,
				// 是否有更多数据
				hasMoreData: false,
				// 数据
				periodList: [],
				userList: [],
				// 当前添加数据
				form: {},
				// 查询
				queryParams: {
					pageNum: 1,
					pageSize: 20
				},
				// 竞赛级别字典
				levelOptions: [],
				// 滑动标识
				modalName: null,
				listTouchStart: 0,
				listTouchDirection: null,
			}
		},
		onLoad() {
			this.loadList()
			this.getDicts('ci_competition_level').then(res => {
				this.levelOptions = res.data
			})
		},
		// 列表数据刷新
		onPullDownRefresh() {
			this.reload()
		},
		onReachBottom() {
			if (this.hasMoreData) {
				this.queryParams.pageNum = this.queryParams.pageNum + 1
				this.loadList()
			}
		},
		methods: {
			loadList() {
				listConcernPeriod().then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					res.rows.forEach(item => {
						if (item.holder) {
							item.holder = item.holder.replace('，', ',').split(',')
							if (item.holder.length > 3) {
								item.holder = item.holder.slice(0, 3)
							}
						}
					})
					this.periodList = this.periodList.concat(res.rows)
				})
				listConcernUser().then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.userList = this.userList.concat(res.rows)
				})
			},
			cancelConcern(userId) {
				delConcernUser(userId).then(res => {
					this.msgSuccess('取关成功')
					this.reload()
				})
			},
			goToCompInfo(id) {
				uni.navigateTo({
					url: 'competition-info?id=' + id
				})
			},
			goToUserShow(userId) {
				uni.navigateTo({
					url: 'user-show-index?userId=' + userId
				})
			},
			reload() {
				this.queryParams.pageNum = 1
				this.periodList = []
				this.userList = []
				this.loadList()
			},
			// ListTouch开始滚动
			ListTouchStart(e) {
				this.listTouchStart = e.touches[0].pageX
			},
			// ListTouch计算方向
			ListTouchMove(e) {
				this.listTouchDirection = e.touches[0].pageX - this.listTouchStart > 0 ? 'right' : 'left'
			},
			
			// ListTouch计算滚动
			ListTouchEnd(e) {
				if (this.listTouchDirection == 'left') {
					this.modalName = e.currentTarget.dataset.target
				} else {
					this.modalName = null
				}
				this.listTouchDirection = null
			}
		}
	}
</script>

<style scoped lang="scss">
	.cu-card .cu-item .image {
		margin: 0;
		// height: 280upx;
		height: 260upx;
		border-radius: 0;
	}

	.cu-card .cu-item .image image {
		height: 100%;
	}
</style>
