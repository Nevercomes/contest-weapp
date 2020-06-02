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
							<view class="margin-top">申请状态：{{statusFormat(item.status)}}</view>
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
							<view class="margin-top">申请状态：{{statusFormat(item.status)}}</view>
							<view class="margin-top">申请人：{{item.createUser.nickName}}</view>
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
		listApplySend,
		listApplyReceive,
		updateApply,
		passApply,
		rejectApply
	} from '@/api/ci/apply.js'

	export default {
		name: 'TeamExpect',
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
		watch: {
			dataList: function(newVal, oldVal) {
				this.empty = newVal == undefined || newVal.length == 0
			}
		},
		onLoad() {
			this.loadList()
			this.getDicts('team_apply_status').then(res => {
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
				listApplySend().then(res => {
					this.loading = false
					// 计算是否有更多数据
					this.hasMoreData = this.hasMore(res.total, this.queryParams.pageNum, this.queryParams.pageSize)
					this.sendList = this.sendList.concat(res.rows)
				})
				listApplyReceive().then(res => {
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
					if (item.status = '0') {
						actions.push({
							'name': '撤回申请',
							'color': '#e54d42'
						})
					}
				} else if (type == 'receive') {
					actions.push({
						'name': '查看申请人'
					})
					if (item.status = '0') {
						actions.push({
							'name': '通过申请',
							'color': '#39b54a'
						})
						actions.push({
							'name': '拒绝申请',
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
					case '撤回申请':
						updateApply({
							id: this.item.id,
							status: '3'
						}).then(res => {
							this.msgSuccess('撤回申请成功')
							this.reload()
						})
						break;
					case '查看申请人':
						uni.navigateTo({
							url: 'user-show-index?userId=' + this.item.createUser.userId
						})
						break;
					case '通过申请':
						passApply(this.item.id).then(res => {
							this.msgSuccess('通过成功')
							this.reload()
						})
						break;
					case '拒绝申请':
						rejectApply(this.item.id).then(res => {
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
				this.dataList = []
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
