<template>
	<view class="app-container">
		<!-- 搜索框 -->
		<van-search style="position: fixed; width: 100%; top: 0; z-index: 1024;" :value="keyword" use-action-slot placeholder="请输入搜索关键词"
		 @search="onSearch">
		</van-search>

		<view class="margin-top-100">
			<van-tabs :active="tabActive" @change="tabChange" animated>
				<van-tab title="竞赛">
					<view v-for="(item,index) in compList" :key="index" @click="goToCompPage(item.id)" class="cu-card case no-card margin-top-sm"
					 style="position: relative;">
						<view v-if="!item.picUrl" class="cu-tag bg-blue" style="position: absolute; right: 0; top: 0;">{{levelFormat(item.basic.level)}}</view>
						<view class="cu-item shadow">
							<view v-if="item.picUrl" class="image">
								<image :src="item.picUrl" mode="scaleToFill"></image>
								<view class="cu-tag bg-blue">{{levelFormat(item.basic.level)}}</view>
							</view>
							<view class="cu-list padding">
								<view v-if="calSignDate(item.signEndTime)" class="">
									<text class="text-green margin-right">正在报名</text><text>离报名截止还有{{calSignDate(item.signEndTime)}}天</text>
								</view>
								<view v-else class="">
									<text class="text-grey">报名已结束</text>
								</view>
								<view class="text-bold text-lg padding-tb-xs">{{item.name}}</view>
								<view class=""><text class="margin-right">报名时间</text>
									{{formatDate(item.signBeginTime)}} —— {{formatDate(item.signEndTime)}}</view>
							</view>
						</view>
					</view>
					<nl-loading :loading="loading.comp"></nl-loading>
					<nl-empty :show="empty.comp"></nl-empty>
				</van-tab>
				
				<van-tab title="动态">
					<view class="cu-list">
						<view class="cu-item post-card padding-sm bg-white shadow-blur margin-top-sm" @click="goToPostPage(item.id)" v-for="(item,index) in postList"
						 :key="index">
							<view class="text-bold text-df padding-tb-xs">{{item.name}}</view>
							<view class="flex align-center">
								<image class="cu-avatar round sm margin-right" :src="item.createUser.avatar"></image>
								<text class="text-gray text-sm text-bold margin-right">{{item.createUser.nickName}}</text>
								<text class="text-grey text-sm">{{item.createUser.schoolName}}</text>
							</view>
							<view class="text-grey text-sm padding-tb-xs">{{item.cpName}}</view>
							<view class="flex padding-tb-xs" style="height: 4.5em; position: relative;">
								<view style="line-height: 1.5em; position: relative;" class="flex-sub text-cut-3 text-sm">{{item.summary}}</view>
								<view v-if="item.coverUrl">
									<image style="height: 4em; max-width: 170upx; margin-left: 10upx; border-radius: 5px" :src="item.coverUrl"
									 mode="aspectFit"></image>
								</view>
							</view>
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
					<view class="padding-sm margin-top-sm bg-white shadow-blur" v-for="(item,index) in timelineList" :key="index">
						<view class="">{{item.cpName}}</view>
						<view class="padding-tb-sm text-gray">{{item.content}}</view>
						<view class="text-gray text-sm">{{formatDate(item.time)}}</view>
					</view>
					<nl-loading :loading="loading.timeline"></nl-loading>
					<nl-empty :show="empty.timeline"></nl-empty>
				</van-tab>
				
				<van-tab title="队伍">
					<view class="cu-list menu-avatar">
						<view class="cu-item margin-top-sm" @click="goToTeamPage(item.id)" v-for="(item,index) in teamList" :key="index">
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
				<van-tab title="用户">
					<view class="cu-list menu-avatar">
						<view class="cu-item margin-top-sm" @click="goToUserPage(item.userId)" v-for="(item,index) in userList" :key="index">
							<image :src="item.avatar" class="cu-avatar round lg bg-white"></image>
							<view class="content flex-sub flex justify-between align-center">
								<view class="flex-direction align-start">
									<view class="flex">
										<view class="text-df margin-right-sm">{{item.nickName}}</view>
									</view>
									<view class="text-grey text-cut">{{item.trueName}}</view>
									<view class="text-grey text-cut text-sm">{{item.schoolName}}</view>
								</view>
							</view>
						</view>
					</view>
					<nl-loading :loading="loading.user"></nl-loading>
					<nl-empty :show="empty.user"></nl-empty>
				</van-tab>
			</van-tabs>
		</view>		
	</view>
</template>

<script>
	import {
		search
	} from '@/api/ci/search.js'

	export default {
		name: 'SearchResult',
		data() {
			return {
				// 加载状态
				loading: {
					comp: false,
					post: false,
					timeline: false,
					team: false,
					user: false
				},
				// 搜索关键字
				keyword: undefined,
				// 进入后优先显示哪一个
				activeType: 'comp',
				// 搜索结果
				compList: [],
				postList: [],
				timelineList: [],
				teamList: [],
				userList: [],
				// 竞赛级别字典
				levelOptions: [],
				empty: {
					comp: false,
					post: false,
					timeline: false,
					team: false,
					user: false
				},
				// 展示的搜索内容
				tabActive: 0,
				tabActiveMap: {
					'comp': 0,
					'post': 1,
					'timeline': 2,
					'team': 3,
					'user': 4					
				}
			}
		},
		onLoad(options) {
			this.keyword = decodeURIComponent(options.keyword)
			this.activeType = options.activeType
			this.tabActive = this.tabActiveMap[this.activeType]
			
			if (this.keyword) this.loadList()
			else this.msgInfo('关键词不能为空')
			
			this.getDicts('ci_competition_level').then(res => {
				this.levelOptions = res.data
			})
		},
		methods: {
			loadList() {
				this.searchComp()
				this.searchPost()
				this.searchTimeline()
				this.searchTeam()
				this.searchUser()
			},
			searchComp() {
				this.loading.comp = true
				search(this.keyword, 'comp').then(res => {
					this.loading.comp = false
					if (res.comp) {
						res.comp.forEach(item => {
							if (item.holder)
								item.holder = item.holder.replace('，', ',').split(',')
						})
						this.compList = res.comp
					}
					this.empty.comp = !res.comp || res.comp.length == 0
				}).catch(() => {
					this.loading.comp = false
				})
			},
			searchPost() {
				this.loading.post = true
				search(this.keyword, 'post').then(res => {
					this.loading.post = false
					this.postList = res.post
					this.empty.post = !res.post || res.post.length == 0
				}).catch(() => {
					this.loading.post = false
				})
			},
			searchTimeline() {
				this.loading.timeline = true
				search(this.keyword, 'timeline').then(res => {
					this.loading.timeline = false
					this.timelineList = res.timeline
					this.empty.timeline = !res.timeline || res.timeline.length == 0
				}).catch(() => {
					this.loading.timeline = false
				})
			},
			searchTeam() {
				this.loading.team = true
				search(this.keyword, 'team').then(res => {
					this.loading.team = false
					this.teamList = res.team
					this.empty.team = !res.team || res.team.length == 0
				}).catch(() => {
					this.loading.team = false
				})
			},
			searchUser() {
				this.loading.user = true
				search(this.keyword, 'user').then(res => {
					this.loading.user = false
					this.userList = res.user
					this.empty.user = !res.user || res.user.length == 0
				}).catch(() => {
					this.loading.user = false
				})
			},
			onSearch(e) {
				this.keyword = e.detail
				if (this.keyword) this.loadList()
				else this.msgInfo('关键词不能为空')
			},
			tabChange(e) {
				console.log(e)
			},
			goToCompPage(id) {
				uni.navigateTo({
					url: 'competition-info?id=' + id
				})
			},
			goToPostPage(id) {
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
			levelFormat(value, dict) {
				return this.selectDictLabel(this.levelOptions, value)
			},
			calSignDate(date) {
				try {
					const today = new Date()
					date = new Date(date)
					if (today.getTime() > date.getTime()) {
						return false
					}
					return Math.floor((date.getTime() - today.getTime()) / (24 * 3600 * 1000))
				} catch (e) {
					console.log(e)
					return false
				}
			},

		}
	}
</script>

<style scoped lang="scss">
	.margin-top-100 {
		margin-top: 100upx;
	}

	.cu-card .cu-item .image {
		margin: 0;
		// height: 280upx;
		height: 260upx;
		border-radius: 0;
	}

	.cu-card .cu-item .image image {
		height: 100%;
	}
	
	.align-start {
		align-items: flex-start !important;
	}
	
</style>
