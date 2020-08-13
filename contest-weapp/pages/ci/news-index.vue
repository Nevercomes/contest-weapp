<template>
	<div class="app-container">
		<!-- 搜索部分 -->
		<!-- <van-search :value="keyword" use-action-slot placeholder="请输入搜索关键词" @focus="goToSearchPage">
			<view style="display: flex; align-items: center;" slot="action" @click="goToPublicPost">
				<van-icon size="24" name="add"></van-icon>
			</view>
		</van-search> -->
		<van-tabs :active="tabActive" @change="tabChange" animated>
			<van-tab title="经验">
				<view class="cu-list">
					<view class="cu-item post-card padding-sm bg-white shadow-blur margin-bottom-sm" @click="goPostPage(item.id)"
					 v-for="(item,index) in postList" :key="index">
						<view class="text-bold text-df padding-tb-xs">{{item.name}}</view>
						<!-- 作者信息 -->
						<view class="flex align-center">
							<image class="cu-avatar round sm margin-right" :src="item.createUser.avatar"></image>
							<text class="text-gray text-sm text-bold margin-right">{{item.createUser.nickName}}</text>
							<text class="text-grey text-sm">{{item.createUser.schoolName}}</text>
						</view>
						<!-- 竞赛 -->
						<view class="text-grey text-sm padding-tb-xs">{{item.cpName}}</view>
						<!-- 内容摘要 -->
						<view class="flex padding-tb-xs" style="max-height: 4.5em; position: relative;">
							<view style="line-height: 1.5em; position: relative;" class="flex-sub text-cut-3 text-sm">{{item.summary}}</view>
							<view v-if="item.coverUrl">
								<image style="height: 4em; max-width: 170upx; margin-left: 10upx; border-radius: 5px" :src="item.coverUrl" mode="aspectFit"></image>
							</view>
						</view>
						<!-- 浏览信息 -->
						<view class="padding-tb-xs">
							<text class="text-gray text-sm margin-right-xs">浏览 {{item.viewNum}}</text>
							<text class="text-gray text-sm margin-right-xs">点赞 {{item.likeNum}}</text>
							<text class="text-gray text-sm">收藏 {{item.collectNum}}</text>
						</view>
					</view>
				</view>
				<nl-loading :loading="loading.post"></nl-loading>
				<nl-empty :show="empty.post"></nl-empty>
			</van-tab>

			<van-tab title="资讯">
				<view class="padding-sm margin-bottom-sm bg-white shadow-blur" v-for="(item,index) in timelineList" :key="index">
					<view class="">{{item.cpName}}</view>
					<view class="padding-tb-sm text-gray">{{item.content}}</view>
					<view class="text-gray text-sm">{{formatDate(item.time)}}</view>
				</view>
				<nl-loading :loading="loading.timeline"></nl-loading>
				<nl-empty :show="empty.timeline"></nl-empty>
			</van-tab>

			<van-tab title="团队">
				<view class="cu-list menu-avatar">
					<view class="cu-item margin-bottom-sm" @click="goToTeamPage(item.id)" v-for="(item,index) in teamList" :key="index">
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
				<nl-loading :loading="loading.team"></nl-loading>
				<nl-empty :show="empty.team"></nl-empty>
			</van-tab>

			<van-tab title="等待组队">
				<view class="cu-list menu-avatar">
					<view class="cu-item margin-bottom-sm" @click="goToUserPage(item.createUser.userId)" v-for="(item,index) in expectList"
					 :key="index">
						<image :src="item.createUser.avatar" class="cu-avatar round lg bg-white"></image>
						<view class="content flex-sub flex justify-between align-center">
							<view class="flex-direction align-start">
								<view class="flex">
									<view class="text-df margin-right-sm text-cut" style="max-width: 430upx;">{{item.cpName}}</view>
									<text v-if="item.status == '1'" class="cu-tag radius text-green">等待组队</text>
									<text v-else-if="item.status == '2'" class="cu-tag radius text-orange">已组队</text>
									<text v-else-if="item.status == '3'" class="cu-tag radius">已撤回</text>
								</view>
								<view class="text-grey text-cut">期望分工：{{workFormat(item.work)}}</view>
								<view class="text-grey text-cut text-sm">个人能力：{{item.capability == null ? '' : item.capability}}</view>
							</view>
						</view>
					</view>
				</view>
				<nl-loading :loading="loading.expect"></nl-loading>
				<nl-empty :show="empty.expect"></nl-empty>
			</van-tab>
		</van-tabs>

	</div>
</template>

<script>
	import {
		listPostInfo
	} from '@/api/ci/postInfo.js'
	import {
		listExpectNews
	} from '@/api/ci/expect.js'
	import {
		listTeamInfo
	} from '@/api/ci/team.js'
	import {
		listTimeline
	} from '@/api/ci/timeline.js'

	export default {
		name: 'NewsIndex',
		data() {
			return {
				// 加载状态
				loading: {
					post: false,
					timeline: false,
					team: false,
					expect: false
				},
				// 无数据
				empty: {
					post: false,
					timeline: false,
					team: false,
					expect: false
				},
				tabActive: 0,
				// 是否有更多数据
				hasMoreData: {
					post: false,
					timeline: false,
					team: false,
					expect: false
				},
				// 搜索关键词
				keyword: undefined,
				// 查询参数				
				postQueryParams: {
					pageNum: 1,
					pageSize: 10
				},
				timelineQueryParams: {
					pageNum: 1,
					pageSize: 10
				},
				teamQueryParams: {
					pageNum: 1,
					pageSize: 10
				},
				expectQueryParams: {
					pageNum: 1,
					pageSize: 10
				},
				// 数据
				postList: [],
				timelineList: [],
				teamList: [],
				expectList: [],
				// 其他数据
				workOptions: []
			}
		},
		onLoad() {
			this.loadList()
			this.getDicts('team_work_type').then(res => {
				this.workOptions = res.data
			})
		},
		// 列表数据刷新
		onPullDownRefresh() {
			if (this.tabActive == 0) {
				this.postQueryParams.pageNum = 1
				this.postList = []
				this.loadPostList()
			} else if (this.tabActive == 1) {
				this.timelineList.pageNum = 1
				this.timelineList = []
				this.loadTimelineList()
			} else if (this.tabActive == 2) {
				this.teamQueryParams.pageNum = 1
				this.teamList = []
				this.loadTeamList()
			} else if (this.tabActive == 1) {
				this.expectQueryParams.pageNum = 1
				this.expectList = []
				this.loadExpectList()
			}
		},
		onReachBottom() {
			if (this.tabActive == 0) {
				if (this.hasMoreData.post) {
					this.postQueryParams.pageNum = this.postQueryParams.pageNum + 1
					this.loadPostList()
				}
			} else if (this.tabActive == 1) {
				if (this.hasMoreData.timeline) {
					this.timelineList.pageNum = this.postQueryParams.pageNum + 1
					this.loadTimelineList()
				}
			} else if (this.tabActive == 2) {
				if (this.hasMoreData.team) {
					this.teamQueryParams.pageNum = this.teamQueryParams.pageNum + 1
					this.loadTeamList()
				}
			} else if (this.tabActive == 1) {
				if (this.hasMoreData.expect) {
					this.expectQueryParams.pageNum = this.expectQueryParams.pageNum + 1
					this.loadExpectList()
				}
			}
		},
		methods: {
			loadList() {
				this.loadPostList()
				this.loadTimelineList()
				this.loadTeamList()
				this.loadExpectList()
			},
			loadPostList() {
				this.loading.post = true
				listPostInfo(this.postQueryParams).then(res => {
					this.loading.post = false
					// 计算是否有更多数据
					this.hasMoreData.post = this.hasMore(res.total, this.postQueryParams.pageNum, this.postQueryParams.pageSize)
					this.postList = this.postList.concat(res.rows)
					this.empty.post = !this.postList || this.postList.length == 0
				}).catch((error) => {
					// this.loading.post = false
				})
			},
			loadTimelineList() {
				this.loading.timeline = true
				listTimeline(this.timelineQueryParams).then(res => {
					this.loading.timeline = false
					// 计算是否有更多数据
					this.hasMoreData.timeline = this.hasMore(res.total, this.timelineQueryParams.pageNum, this.timelineQueryParams.pageSize)
					this.timelineList = this.timelineList.concat(res.rows)
					this.empty.timeline = !this.timelineList || this.timelineList.length == 0
				}).catch((error) => {
					// this.loading.post = false
				})
			},
			loadTeamList() {
				this.loading.team = true
				listTeamInfo(this.teamQueryParams).then(res => {
					this.loading.team = false
					// 计算是否有更多数据
					this.hasMoreData.team = this.hasMore(res.total, this.teamQueryParams.pageNum, this.teamQueryParams.pageSize)
					this.teamList = this.teamList.concat(res.rows)
					this.empty.team = !this.teamList || this.teamList.length == 0
				}).catch((error) => {
					// this.loading.team = false
				})
			},
			loadExpectList() {
				this.loading.expect = true
				listExpectNews(this.expectQueryParams).then(res => {
					this.loading.expect = false
					// 计算是否有更多数据
					this.hasMoreData.expect = this.hasMore(res.total, this.expectQueryParams.pageNum, this.expectQueryParams.pageSize)
					this.expectList = this.expectList.concat(res.rows)
					this.empty.expect = !this.expectList || this.expectList.length == 0
				}).catch((error) => {
					// this.loading.expect = false
				})
			},
			tabChange(e) {
				this.tabActive = e.detail.index
			},
			workFormat(value, dict) {
				return this.selectDictLabel(this.workOptions, value)
			},
			goToSearchPage() {
				uni.navigateTo({
					url: 'search-index?activeType=post'
				})
			},
			goPostPage(id) {
				uni.navigateTo({
					url: 'news-info?id=' + id
				})
			},
			goToTeamPage(id) {
				uni.navigateTo({
					url: 'team-info?id=' + id
				})
			},
			goToUserPage(id) {
				uni.navigateTo({
					url: 'user-show-index?userId=' + id
				})
			},
			// 取代搜索事件改为setting设置
			goToPublicPost() {
				// 动态发布功能已关闭
				this.msgInfo('发布暂时关闭')
				// uni.navigateTo({
				// 	url: 'news-public-form'
				// })
			}
		}
	}
</script>

<style scoped lang="scss">
	.align-start {
		align-items: flex-start !important;
	}
</style>
