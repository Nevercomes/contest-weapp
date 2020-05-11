<template>
	<view class="app-container">
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">竞赛详情</block>
		</cu-custom>
		<!-- 展示信息 -->
		<view class="info">
			<!-- 内容展开 -->
		</view>
		<!-- 底部操作条 -->
		<view class="bottom-oper-bar">
			<van-button v-if="!concerned" type="primary" @click="onConcernClick">关注</van-button>
			<van-button v-else type="primary" @click="onNotConcernClick">取消关注</van-button>
			<van-button type="primary" @click="onTeamClick">组队</van-button>
			<van-button type="primary" @click="onNewsClick">动态</van-button>
			<van-button type="primary" @click="onCommentClick">评论</van-button>
		</view>
	</view>
</template>

<script>
	import {
		getPeriod
	} from '@/api/ci/period.js'
	import {
		listConcernCp,
		updateConcernCp,
		getConcernCp,
		delConcernCp
	} from '@/api/ci/concern.js'

	export default {
		name: '',
		data() {
			return {
				id: undefined,
				// 竞赛信息
				period: {},
				// 关注信息
				concern: {},
				// 是否关注
				concerned: false
			}
		},
		onLoad(options) {
			this.id = options.id
			if (this.id) {
				getPeriod(id).then(res => {
					this.period = res.data
				})
				listConcernCp({
					cpId: this.id
				}).then(res => {
					if (res.total > 0) {
						this.concern = res.rows[0]
						this.concerned = true
					} else {
						this.concerned = false
					}
				})
			}
		},
		methods: {
			// 设置关注
			onConcernClick() {
				addConcernCp({
					cpId: this.period.id,
				}).then(res => {
					this.concerned = true
					this.concern = res.data
					this.msgInfo('添加关注成功')
				})
			},
			onNotConcernClick() {
				delConcernCp(this.concern.id).then(res => {
					this.msgInfo('取消关注成功')
					this.concerned = false
				})
			},
			// 跳转到关于该竞赛的队伍列表页面
			onTeamClick() {
				uni.navigateTo({
					url: 'team-list-competition?cpId=' + this.id
				})
			},
			// 跳转到关于该竞赛的动态页面
			onNewsClick() {
				uni.navigateTo({
					url: 'news-competition?cpId=' + this.id
				})
			},
			// 跳转到评论页面
			onCommentClick() {
				uni.navigateTo({
					url: 'competition-comment?cpId=' + this.id
				})
			}

		}
	}
</script>

<style>
</style>
