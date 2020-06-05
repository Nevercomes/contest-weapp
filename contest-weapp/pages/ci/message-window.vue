<template>
	<view class="app-container">
		<view class="cu-chat">
			<view class="cu-info">
				赛舟提醒：聊天前请注意确认对方身份，保护信息隐私，谨防电信诈骗。
			</view>
			<block v-for="(item,index) in dataList" :key="index"> 
				<view v-if="item.sendUserId == userId" class="cu-item self">
					<view class="main">
						<view class="content bg-green shadow">
							<text>{{item.content}}</text>
						</view>
					</view>
					<image @click="goToUserShow(item.sendUserId)" class="cu-avatar radius" :src="item.sendUserAvatar" />
					<view class="date">{{formatMsgTime(item.createTime)}}</view>
				</view>
				<view v-else class="cu-item">
					<image @click="goToUserShow(item.sendUserId)" class="cu-avatar radius" :src="item.sendUserAvatar" />
					<view class="main">
						<view class="content shadow">
							<text>{{item.content}}</text>
						</view>
					</view>
					<view class="date">{{formatMsgTime(item.createTime)}}</view>
				</view>
			</block>
		</view>

		<view class="cu-bar foot input" :style="[{bottom:InputBottom+'px'}]">
			<view class="action">
				<text class="cuIcon-sound text-grey" @click="onVoiceClick"></text>
			</view>
			<input v-model="content" class="solid-bottom" :adjust-position="false" :focus="false" maxlength="300" cursor-spacing="10" @focus="InputFocus"
			 @blur="InputBlur"></input>
			 
			<view class="action">
				<text class="cuIcon-emojifill text-grey" @click="onEmojiClick"></text>
			</view>
			<button class="cu-btn bg-green shadow" @click="sendMessage">发送</button>
		</view>
	</view>
</template>

<script>
	import {
		mapGetters
	} from 'vuex'

	import {
		listContent,
		insertMsg,
		readMsg
	} from '@/api/ci/message.js'
	import {
		getUserProfile
	} from '@/api/system/user.js'

	export default {
		name: 'MessageWindow',
		computed: {
			...mapGetters([
				'userId'
			])
		},
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
					pageSize: 30,
					receiveUserId: undefined
				},
				// 编辑内容
				content: '',
				// 底部区域
				InputBottom: 0,
			}
		},
		onLoad(options) {
			this.queryParams.receiveUserId = options.userId
			this.loadList()
			getUserProfile(this.queryParams.receiveUserId).then(res => {
				uni.setNavigationBarTitle({
					title: res.data.nickName
				})
			})
			readMsg({
				sendUserId: this.queryParams.receiveUserId
			})
		},
		// 列表数据刷新
		onPullDownRefresh() {
			// this.queryParams.pageNum = 1
			// this.dataList = []
			// this.loadList()
			if (this.hasMoreData) {
				this.queryParams.pageNum = this.queryParams.pageNum + 1
				this.loadList()
			}
		},
		onReachBottom() {
			// if (this.hasMoreData) {
			// 	this.queryParams.pageNum = this.queryParams.pageNum + 1
			// 	this.loadList()
			// }
		},
		methods: {
			loadList() {
				this.loading = true
				listContent(this.queryParams).then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					res.rows.forEach(a => {
						this.dataList.unshift(a)
					})
					console.log(this.dataList)
					this.$forceUpdate()
				})
			},
			sendMessage() {
				console.log(this.content)
				insertMsg({
					content: this.content,
					receiveUserId: this.queryParams.receiveUserId
				}).then(res => {
					this.dataList.push(res.data)
					this.content = ''
					this.$forceUpdate()
				})
			},
			goToUserShow(userId) {
				uni.navigateTo({
					url: 'user-show-index?userId=' + userId
				})
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
			},
		}
	}
</script>

<style>
	page {
		padding-bottom: 100upx;
	}
</style>
