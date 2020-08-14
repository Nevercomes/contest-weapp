<template>
	<view class="app-container">
		<!-- 展示信息 -->
		<view class="cu-item bg-white" style="margin-bottom: 120upx;">
			<!-- 封面图片 -->
			<image v-if="item.coverUrl" style="width: 100%; height: 200upx;" mode="center" :src="item.coverUrl"></image>
			<view class="padding-lr">
				<!-- 标题 -->
				<view class="padding-tb-sm text-lg text-bold">
					<text>{{item.name}}</text>
				</view>
				<view class="<padding-tb-sm></padding-tb-sm> text-sm text-grey">
					<text>{{item.cpName}}</text>
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
				
				<!-- 内容 -->
				<text selectable="true" space="emsp" class="padding-tb-xs" style="display: block; line-height: 2em;">
					{{item.content}}
				</text>
				
				<!-- 资源下载 -->
				<view v-if="id==1" class="flex align-center padding-tb-sm">
					<text class="cuIcon-title text-green margin-right"></text>
					<text class="text-df text-bold">附件下载</text>
				</view>
				<view v-if="id==1" class="flex padding-tb-sm align-center" @click="onFileItemClicked">
					<text class="cuIcon-down margin-lr-xs"></text>
					<text class="margin-right-sm text-blue" style="text-decoration: underline;">{{fileName}}</text>
					<text class="">{{attchHint}}</text>
				</view>
				<view v-if="fileText" class="message-content">
					{{fileText}}
				</view>
			</view>
		</view>
		
		<!-- 下载进度 -->
		<van-overlay :show="attchHint == '下载中...'" @click="onAbortClick">
		  <view class="wrapper">
			<van-circle :value="progress" :text="progress" />
		  </view>
		</van-overlay>
		

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
				<view class="cuIcon-discover"></view> 队伍
			</view>
			<!-- <view class="action text-gray" @click="onCommentClick">
				<view class="cuIcon-mark"></view> 评论
			</view> -->
			<view class="action text-gray" @click="onExpectClick">
				<view class="cuIcon-friend"></view> 期望
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
				collectRecord: {},
				
				// 附件下载伪代码部分
				fileUrl: 'https://dream-road-oss.oss-cn-hangzhou.aliyuncs.com/rowing_intro_doc.pdf',
				tempFilePath: null,
				attchHint: '点击下载',
				fileSaved: false,
				fileText: undefined,
				fileName: 'rowing(赛舟介绍文档).pdf',
				progress: 0,
				downTask: {}
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
				if (res.total > 0) {
					this.likeRecord = res.rows[0]
					this.liked = true
				} else {
					this.liked = false
				}
			})
			listCollection({
				postId: this.id
			}).then(res => {
				if (res.total > 0) {
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
			onExpectClick() {
				uni.navigateTo({
					url: './expect-list?cpId=' + this.item.cpId
				})
			},
			// 跳转到评论页面
			onCommentClick() {
				this.msgInfo('评论功能已关闭')
				// uni.navigateTo({
				// 	url:'news-comment?postId=' + this.id
				// })
			},
			goToUserShowPage(userId) {
				uni.navigateTo({
					url: 'user-show-index?userId=' + userId
				})
			},
			onFileItemClicked() {
				let that = this
				if (this.tempFilePath) {
					this.viewAttachFile()
					this.attchHint = '下载完成，点击查看'
				} else {
					that.attchHint = '下载中...'
					this.downTask = uni.downloadFile({
						url: that.fileUrl,
						success: (res) => {
							if (res.statusCode === 200) {
								if(that.progress == 100) {
									that.attchHint = '下载完成，点击查看'
									that.tempFilePath = res.tempFilePath
									console.log(that.tempFilePath)
									that.viewAttachFile()
								} else {
									this.attchHint = '已取消下载'
								}
							} else {
								that.attchHint = '下载失败，点击重试'
							}
						},
						fail: (res) => {
							that.attchHint = '下载失败，点击重试'
						}
					});
					this.downTask.onProgressUpdate((res) => {
						that.progress = res.progress
					})
				}
			},
			onAbortClick() {
				if(this.downTask) {
					this.downTask.abort()
				}
				this.attchHint = '已取消下载'
			},
			viewAttachFile() {
				// 分为图片和文档两类来进行查看
				// 1. 图片 图片不直接保存，用户可以长按选择保存
				let that = this
				if (this.isImage(this.tempFilePath)) {
					let urls = []
					urls.push(this.tempFilePath)
					uni.previewImage({
						urls: urls,
						fail: function(err) {
							that.msgInfo('图片预览失败')
						}
					})
				}
				// 2. 文档 文档直接保存，使用微信小程序的工具查看
				if (this.isDocument(this.tempFilePath)) {
					if (this.fileSaved) {
						this.viewDocument()
					} else {
						that.viewDocument()
						// uni.getSavedFileList({
						// 	success(res) {
						// 		res.fileList.forEach((value, key) => {
						// 			uni.removeSavedFile({
						// 				filePath: value.filePath
						// 			})
						// 		})
						// 	}
						// })
						// uni.saveFile({
						// 	tempFilePath: that.tempFilePath,
						// 	success: function(res) {
						// 		that.tempFilePath = res.savedFilePath
						// 		that.fileSaved = true
						// 		that.viewDocument()
						// 	}
						// })
					}
				}
				if (this.isTextFile(this.fileUrl)) {
					let fsm = uni.getFileSystemManager()
					fsm.readFile({
						filePath: this.tempFilePath,
						encoding: 'utf-8',
						success: function(res) {
							that.fileText = res.data
						}
					})
				}
			},
			viewDocument: function() {
				let that = this
				uni.openDocument({
					filePath: this.tempFilePath,
					fail: function(e) {
						that.msgInfo('文档预览失败')
					}
				})
			},
			isImage: function(filePath) {
				return this._isFileType("jpg,gif,bmp,png,jpeg,JPG,GIF,BMP,PNG,JPEG", filePath)
			},
			isDocument: function(filePath) {
				return this._isFileType("doc,xls,ppt,pdf,docx,xlsx,pptx,DOC,XLS,PPT,PDF,DOCX,XLSX,PPTX", filePath)
			},
			isTextFile: function(filePath) {
				let fileName = this._getFileName(filePath)
				if (fileName) {
					let suffixName = fileName.split(".")
					suffixName = suffixName[suffixName.length - 1]
					if (suffixName == 'txt')
						return true
				}
				return false
			},
			_getFileName(filePath) {
				if(!filePath) return
				// 匹配 / 和 '\'
				const re = /((\/+)|(\\+))/g
				let fileArray = filePath.replace(re, "#")
				fileArray = fileArray.split("#")
				let fileName = fileArray[fileArray.length - 1]
				return fileName
			},
			_isFileType(target, filePath) {
				if (!filePath) return
				// 匹配 / 和 '\'
				const re = /((\/+)|(\\+))/g
				let fileArray = filePath.replace(re, "#")
				fileArray = fileArray.split("#")
				let fileName = fileArray[fileArray.length - 1]
				let suffixName = fileName.split(".")
				suffixName = suffixName[suffixName.length - 1]
				return target.indexOf(suffixName) > -1
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

	.message-content {
		font-size: 34upx;
		opacity: 0.7;
		line-height: 68upx;
		text-overflow: ellipsis;
		word-wrap: break-word
	}
	
	.wrapper {
	  display: flex;
	  align-items: center;
	  justify-content: center;
	  height: 100%;
	}
	
</style>
