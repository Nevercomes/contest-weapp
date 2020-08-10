<template>
	<view class="app-container">
		<view class="cu-bar bg-white">
			<view class="action">
				<text class="cuIcon-title text-green"></text>
				<text class="text-lg text-bold">全部{{total}}条评论</text>
			</view>
		</view>

		<!-- 评论列表 -->
		<view class="cu-card dynamic margin-bottom-xxl" :class="'no-card'">
			<view class="cu-item shadow">
				<view class="cu-list menu-avatar comment solids-top">
					<view v-for="(item,index) in dataList" :key="index" class="cu-item">
						<image class="cu-avatar round" :src="item.createUser.avatar" @click="goToUserShowPage(item.createUser.userId)"></image>
						<view class="content">
							<view class="text-grey">{{item.createUser.nickName}} {{item.createUser.schoolName}}</view>
							<view class="text-gray text-content text-df">
								{{item.content}}
							</view>
							<view class="margin-top-sm flex justify-between">
								<view class="text-gray text-df">{{formatDate(item.createTime)}}</view>
								<view class="margin-right-sm">
									<text class="cuIcon-more" @click="onMoreClick(item)"></text>
								</view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>

		<!-- 评论输入 -->
		<view class="cu-bar foot input" :style="[{bottom:InputBottom+'px'}]">
			<view class="action">
				<text class="cuIcon-sound text-grey" @click="onVoiceClick"></text>
			</view>
			<input v-model="content" class="solid-bottom" :adjust-position="false" :focus="false" maxlength="300" cursor-spacing="10"
			 @focus="InputFocus" @blur="InputBlur"></input>

			<view class="action">
				<text class="cuIcon-emojifill text-grey" @click="onEmojiClick"></text>
			</view>
			<button class="cu-btn bg-green shadow" @click="publicComment">发布</button>
		</view>

		<!-- 举报弹窗 -->
		<van-action-sheet @cancel="show = false" @close="show = false" @select="onActionSelect" :show="show" :actions="actions"
		 cancel-text="取消" />

		<nl-loading v-if="loading" :loading="true"></nl-loading>
		<nl-empty v-if="empty" :show="true"></nl-empty>
	</view>
</template>

<script>
	import WxValidate from '@/utils/WxValidate.js'
	import {
		mapGetters
	} from 'vuex'
	import {
		listCompComment,
		addCompComment,
		delCompComment
	} from '@/api/ci/compComment.js'

	export default {
		name: 'CompetitionComment',
		computed: {
			...mapGetters([
				'userId'
			])
		},
		data() {
			return {
				// 加载状态
				loading: false,
				// 数据是否为空
				empty: false,
				// 是否出错
				error: false,
				// 是否有更多数据
				hasMoreData: false,
				// 数据
				dataList: [],
				// 全部评论数目
				total: 0,
				// 当前添加数据
				form: {},
				// 查询
				queryParams: {
					pageNum: 1,
					pageSize: 10.,
					cpId: undefined
				},
				rules: {
					content: {
						required: true,
						maxlength: 120
					}
				},
				// 错误提示
				messages: {
					content: {
						required: '评论内容不能为空',
						maxlength: '内容太长啦'
					}
				},
				// 编辑内容
				content: '',
				// 底部区域
				InputBottom: 0,
				// 举报弹窗
				show: false,
				// 底部弹窗内容
				actions: [],
				// 被选中操作的item
				selectedItem: {}
			}
		},
		onLoad(options) {
			this.queryParams.cpId = options.cpId
			this.form.cpId = options.cpId
			this.loadList()
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
				this.loading = true
				this.error = false
				listCompComment(this.queryParams).then(res => {
					this.loading = false
					this.total = res.total
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.dataList = this.dataList.concat(res.rows)
					this.empty = !this.dataList || this.dataList.length == 0
				}).catch(() => {
					this.loading = false
					this.error = true
				})
			},
			reload() {
				this.queryParams.pageNum = 1
				this.dataList = []
				this.loadList()
			},
			// 发布评论
			publicComment() {
				this.form.content = this.content
				this.content = ''
				if (this.validForm(this.form)) {
					addCompComment(this.form).then(res => {
						this.msgSuccess('评论发布成功')
						this.reload()
					}).catch(() => {
						this.msgInfo('评论发布失败')
					})
				}
			},
			// 前往用户主页
			goToUserShowPage(userId) {
				uni.navigateTo({
					url: 'user-show-index?userId=' + userId
				})
			},
			// 举报评论
			onMoreClick(item) {
				this.actions = []
				if (item.createUser.userId == this.userId) {
					this.actions.push({
						'name': '删除评论',
						'color': '#e54d42'
					})
				} else {
					this.actions.push({
						'name': '举报评论'
					})
				}
				this.selectedItem = item
				this.show = true
			},
			onActionSelect(e) {
				const name = e.detail.name
				let self = this
				if (name == '举报评论') {
					this.msgInfo("已举报" + item.createUser.nickName + "的评论")
				} else if (name == '删除评论') {
					delCompComment(this.selectedItem.id).then(res => {
						this.reload()
						this.msgSuccess('评论已删除')
					}).catch(() => {
						this.msgInfo('评论删除失败')
					})
				}
			},
			validForm(params) {
				let wxValidate = new WxValidate(this.rules, this.messages)
				if (!wxValidate.checkForm(params)) {
					const error = wxValidate.errorList[0]
					this.msgInfo(error.msg)
					return false
				}
				return true
			},
			InputFocus(e) {
				this.InputBottom = e.detail.height
			},
			InputBlur(e) {
				this.InputBottom = 0
			},
			onEmojiClick() {
				this.msgInfo('敬请期待')
			},
			onVoiceClick() {
				this.msgInfo('敬请期待')
			}
		}
	}
</script>

<style scoped lang="scss">
	.margin-bottom-xxl {
		margin-bottom: 100upx;
	}
</style>
