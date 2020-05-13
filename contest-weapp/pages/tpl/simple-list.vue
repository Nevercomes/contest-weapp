<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">竞赛经历</block>
		</cu-custom>
		<view @longtap="onLongtap(item.id)" v-for="(item, index) in dataList" :key="index" class="experience-card">
			{{item.name}}{{item.work}}{{item.awards}}{{item.time}}
		</view>
		<view class="padding flex flex-direction">
			<button class="cu-btn bg-green margin-tb-sm lg" @click="onAddClick">添加竞赛经历</button>
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
				// 是否有更多数据
				hasMoreData: false,
				// 数据
				dataList: [],
				// 当前添加数据
				form: {},
				// 查询
				queryParams: {
					pageNum: 1,
					pageSize: -1
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
				listExperience().then(res => {
					this.dataList = res.rows
				})
			},
			// 长按
			onLongtap(id) {
				// 提示删除
				uni.showModal({
					title: '提示',
					content: '是否删除竞赛经历',
					success: function() {
						delExperience(id).then(res => {
							if(res.code === 200) {
								this.msgSuccess('删除成功')
							} else {
								this.msgInfo(res.msg)
							}
						})
					}
				})
			},
			// 添加竞赛经历
			onAddClick() {
				
			}
		}
	}
</script>

<style scoped lang="scss">
</style>
