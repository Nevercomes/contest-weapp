<template>
	<view class="app-container">
		<!-- 展示信息 -->
		
		
		<view class="cu-item bg-white" style="margin-bottom: 120upx;">
			<!-- 封面图片 -->
			<image style="width: 100%; height: 200upx;" mode="center" :src="item.coverUrl"></image>
			<view class="padding-lr">
				<!-- 标题 -->
				<view class="padding-tb-sm text-lg text-bold">
					{{item.name}}
				</view>
				<view class="<padding-tb-sm></padding-tb-sm> text-sm text-grey">
					{{item.cpName}}
				</view>
				
				<!-- 浏览信息 -->
				<!-- <view class="padding-tb-xs">
					<text class="text-gray text-sm margin-right-xs">浏览 {{item.viewNum}}</text>
					<text class="text-gray text-sm margin-right-xs">点赞 {{item.likeNum}}</text>
					<text class="text-gray text-sm">收藏 {{item.collectNum}}</text>
				</view> -->
				
				<!-- 作者 -->
				<view class="flex padding-tb-xs" @click="goToUserShowPage(item.createUser.userId)">
					<image class="cu-avatar round lg margin-right" :src="item.createUser.avatar"></image>
					<view class="flex flex-direction justify-around align-start">
						<text class="text-gray text-sm text-bold margin-right">{{item.createUser.nickName}}</text>
						<text class="text-grey text-sm">{{item.createUser.schoolName}}</text>
					</view>
				</view>
				
				<view class="padding-tb-xs" style="line-height: 2em;">
					{{item.content}}
				</view>
				<!-- 资源下载 -->
				
			</view>
		</view>
		
		<!-- 底部操作条 -->
		<view class="cu-bar tabbar shadow foot bg-white">
			<view class="action" :class="liked?'text-orange':'text-gray'" @click="onLikeClick">
				<view :class="liked?'cuIcon-appreciatefill':'cuIcon-appreciate'"></view>
				<text v-if="liked">已赞 {{item.likeNum}}</text>
				<text v-else>点赞 {{item.likeNum}}</text>
			</view>
			<view class="action" :class="collected?'text-orange':'text-gray'" @click="onCollectClick">
				<view :class="collected?'cuIcon-favorfill':'cuIcon-favor'"></view>
				<text v-if="collected">已收藏 {{item.collectNum}}</text>
				<text v-else>收藏 {{item.collectNum}}</text>
			</view>
			<view class="action text-gray" @click="onTeamClick">
				<view class="cuIcon-friendfill"></view> 队伍
			</view>
			<view class="action text-gray" @click="onCommentClick">
				<view class="cuIcon-mark"></view> 评论
			</view>
		</view>
	</view>
</template>

<script>
	import {
		getPostInfo
	} from '@/api/ci/postInfo.js'
	import {
		getCollection,
		delCollection,
		listCollection,
		addCollection
	} from '@/api/ci/collection.js'
	import {
		getPostLIke,
		listPostLIke,
		delPostLIke,
		addPostLIke
	} from '@/api/ci/postLIke.js'

	export default {
		name: 'NewsInfo',
		data() {
			return {
				id: undefined,
				// 数据信息
				item: {},
				// 是否喜欢
				liked: false,
				likeRecord: {},
				// 是否收藏
				collected: false,
				collectRecord: {}
			}
		},
		onLoad(options) {
			this.id = options.id
			getPostInfo(this.id).then(res => {
				this.item = res.data
			})
			listPostLIke({
				postId: this.id
			}).then(res => {
				if(res.total > 0) {
					this.likeRecord = res.rows[0]
					this.liked = true
				} else {
					this.liked = false
				}
			})
			listCollection({
				postId: this.id
			}).then(res => {
				if(res.total > 0) {
					this.collectRecord = res.rows[0]
					this.collected = true
				} else {
					this.collected = false
				}
			})
		},
		methods: {
			// 设置关注
			onLikeClick() {
				if (this.liked) {
					delPostLIke(this.likeRecord.id).then(res => {
						this.msgInfo('已取消赞')
						this.liked = false
						this.item.likeNum = Number(this.item.likeNum) - 1
					})
				} else {
					addPostLIke({
						postId: this.item.id,
					}).then(res => {
						this.liked = true
						this.likeRecord = res.data
						this.msgInfo('点赞成功')
						this.item.likeNum = Number(this.item.likeNum) + 1
					})
				}
			},
			onCollectClick() {
				if (this.collected) {
					delCollection(this.collectRecord.id).then(res => {
						this.msgInfo('已取消收藏')
						this.collected = false
						this.item.collectNum = Number(this.item.collectNum) - 1
					})
				} else {
					addCollection({
						postId: this.item.id,
					}).then(res => {
						this.collected = true
						this.collectRecord = res.data
						this.msgInfo('收藏成功')
						this.item.collectNum = Number(this.item.collectNum) + 1
					})
				}
			},
			// 跳转到队伍界面
			onTeamClick() {
				uni.navigateTo({
					url: './team-list?cpId=' + this.item.cpId
				})
			},
			// 跳转到评论页面
			onCommentClick() {
				this.msgInfo('评论功能已关闭')
			},
			goToUserShowPage(userId) {
				uni.navigateTo({
					url: 'user-show-index?userId=' + userId
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	.cu-card .cu-item .image {
		margin: 0;
		height: 280upx;
		border-radius: 0;
	}
	
	.cu-card .cu-item .image image {
		height: 100%;
	}
	
	.margin-bottom-xxl {
		margin-bottom: 70upx;
	}
	
</style>
