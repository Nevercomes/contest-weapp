<template>
	<view class="app-container">
		<van-tabs>
			<van-tab title="我发送的">
				<view @click="setAction(item, 'send')" v-for="(item, index) in sendList" :key="index" class="cu-timeline">
					<view class="cu-time">{{formatTime(item.createTime)}}</view>
					<view class="cu-item">
						<view class="content">
							<view class="solid-bottom text-lg">
								<text class="text-black">{{item.teamName}}</text>
							</view>
							<view class="margin-top">邀请状态：{{statusFormat(item.status)}}</view>
							<view class="margin-top">被邀请人：{{item.assUser.nickName}}</view>
						</view>
					</view>
				</view>
				<nl-empty v-if="sendList == undefined || sendList.length == 0" :show="true"></nl-empty>
			</van-tab>
			<van-tab title="我收到的">
				<view @click="setAction(item, 'receive')" v-for="(item, index) in receiveList" :key="index" class="cu-timeline">
					<view class="cu-time">{{formatTime(item.createTime)}}</view>
					<view class="cu-item">
						<view class="content">
							<view class="solid-bottom text-lg">
								<text class="text-black">{{item.teamName}}</text>
							</view>
							<view class="margin-top">邀请状态：{{statusFormat(item.status)}}</view>
						</view>
					</view>
				</view>
				<nl-empty v-if="receiveList == undefined || receiveList.length == 0" :show="true"></nl-empty>
			</van-tab>
		</van-tabs>

		<!-- 底部操作 -->
		<van-action-sheet @cancel="show = false" @close="show = false" @select="onActionSelect" :show="show" :actions="actions"
		 cancel-text="取消" />

	</view>
</template>

<script>
	
	import {
		listInviteSend,
		listInviteReceive,
		updateInvite,
		passInvite,
		rejectInvite
	} from '@/api/ci/invite.js'

	export default {
		name: 'TeamInvite',
		data() {
			return {
				// 加载状态
				loading: false,
				// 无数据
				empty: false,
				// 是否有更多数据
				hasMoreData: false,
				// 数据
				sendList: [],
				receiveList: [],
				// status字典
				statusOptions: [],
				// 当前添加数据
				form: {},
				// 查询
				queryParams: {
					pageNum: 1,
					pageSize: 10
				},
				// 底部操作
				show: false,
				actions: [],
				// 唤起当前action-sheet的item
				item: undefined
			}
		},
		onLoad() {
			this.loadList()
			this.getDicts('team_invite_status').then(res => {
				this.statusOptions = res.data
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
				listInviteSend().then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.sendList = this.sendList.concat(res.rows)
				})
				listInviteReceive().then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.receiveList = this.receiveList.concat(res.rows)
				})
			},
			setAction(item, type) {
				// 查看队伍
				let actions = []
				actions.push({
					'name': '查看队伍'
				})
				if (type == 'send') {
					actions.push({
						'name': '查看被邀请人'
					})
					if (item.status == '0') {
						actions.push({
							'name': '撤回邀请',
							'color': '#e54d42'
						})
					}
				} else if (type == 'receive') {
					if (item.status == '0') {
						actions.push({
							'name': '接受邀请',
							'color': '#39b54a'
						})
						actions.push({
							'name': '拒绝邀请',
							'color': '#e54d42'
						})
					}
				}
				this.actions = actions
				this.item = item
				this.show = true
			},
			onActionSelect(e) {
				const name = e.detail.name
				switch (name) {
					case '查看队伍':
						uni.navigateTo({
							url: 'team-info?id=' + this.item.teamId
						})
						break;
					case '撤回邀请':
						updateInvite({
							id: this.item.id,
							status: '3'
						}).then(res => {
							this.msgSuccess('撤回邀请成功')
							this.reload()
						})
						break;
					case '查看被邀请人':
						uni.navigateTo({
							url: 'user-show-index?userId=' + this.item.invitedUserId
						})
						break;
					case '同意邀请':
						passInvite(this.item.id).then(res => {
							this.msgSuccess('同意成功')
							this.reload()
						})
						break;
					case '拒绝邀请':
						rejectInvite(this.item.id).then(res => {
							this.msgSuccess('拒绝成功')
							this.reload()
						})
						break;
					default:
						break
				}
			},
			reload() {
				this.queryParams.pageNum = 1
				this.sendList = []
				this.receiveList = []
				this.loadList()
			},
			statusFormat(value, dict) {
				return this.selectDictLabel(this.statusOptions, value)
			}
		}
	}
</script>

<style scoped lang="scss">
</style>
