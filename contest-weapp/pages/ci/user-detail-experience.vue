<template>
	<view class="app-container">
		<view @click="goToExpFormPage(item.id)" @longtap="onLongtap(item.id)" v-for="(item, index) in dataList" :key="index"
		 class="cu-timeline">
			<view class="cu-time">{{formatDate(item.time)}}</view>
			<view class="cu-item">
				<view class="content">
					<view class="solid-bottom text-lg">
						<text class="text-black">{{item.name}}</text>
					</view>
					<view class="margin-top">队内分工：{{item.work}}</view>
					<view class="margin-top">获得奖项：{{item.awards}}</view>
				</view>
			</view>
		</view>
		<nl-empty :show="empty"></nl-empty>
		<view v-if="!queryParams.userId" class="padding flex flex-direction">
			<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" @click="onAddClick">添加竞赛经历</button>
		</view>
	</view>
</template>

<script>
	import {
		listExperience,
		addExperience,
		delExperience
	} from '@/api/ci/experience.js'

	export default {
		name: 'UserDetailExperience',
		data() {
			return {
				// 加载状态
				loading: false,
				// 无数据
				empty: false,
				// 是否有更多数据
				hasMoreData: false,
				// 数据
				dataList: [],
				// 当前添加数据
				form: {},
				// 查询
				queryParams: {
					userId: undefined
				}
			}
		},
		watch: {
			dataList: function(newVal, oldVal) {
				this.empty = newVal == undefined || newVal.length == 0
			}
		},
		onLoad(options) {
			this.queryParams.userId = options.userId
		},
		onShow() {
			this.loadList()
		},
		// 列表数据刷新
		onPullDownRefresh() {
			this.queryParams.pageNum = 1
			this.dataList = []
			this.loadList()
		},
		methods: {
			loadList() {
				listExperience(this.queryParams).then(res => {
					this.dataList = res.rows
				})
			},
			// 长按
			onLongtap(id) {
				// 提示删除
				let self = this
				uni.showModal({
					title: '提示',
					content: '是否删除竞赛经历',
					success: function(result) {
						if (result.confirm) {
							delExperience(id).then(res => {
								if (res.code === 200) {
									self.msgSuccess('删除成功')
									self.loadList()
								} else {
									self.msgInfo(res.msg)
								}
							})
						}
					}
				})
			},
			// 添加竞赛经历
			onAddClick() {
				uni.navigateTo({
					url: 'user-detail-experience-form'
				})
			},
			goToExpFormPage(id) {
				uni.navigateTo({
					url: 'user-detail-experience-form?id=' + id
				})
			}
		}
	}
</script>

<style scoped lang="scss">
</style>
