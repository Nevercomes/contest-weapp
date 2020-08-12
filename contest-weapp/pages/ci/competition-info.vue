<template>
	<view class="app-container">
		<!-- 展示信息 -->
		<view class="cu-card case no-card">
			<view class="cu-item shadow">
				<view class="image">
					<image :src="period.picUrl" mode="scaleToFill"></image>
					<view class="cu-tag bg-blue">{{levelFormat(period.basic.level)}}</view>
				</view>
				<view class="cu-list padding">
					<text v-if="index < 4" class='cu-tag margin-right-xs radius' v-for="(label,index) in period.classifyList" :key="index">{{label}}</text>
					<view v-if="calSignDate(period.signEndTime)" class="padding-tb-sm">
						<text class="text-green margin-right">正在报名</text><text>离报名截止还有{{calSignDate(period.signEndTime)}}天</text>
					</view>
					<view v-else class="padding-tb-sm">
						<text class="text-grey">报名已结束</text>
					</view>
					<text class="text-bold text-lg padding-tb-sm">{{period.name}}</text>
					<view class="padding-tb-sm"><text class="text-grey margin-right">官网链接</text>
						<text selectable="true" class="text-blue" style="text-decoration: underline; word-break: break-all;">{{period.details.officialLink || ''}}</text></view>
					<view class="padding-tb-sm"><text class="text-grey margin-right">报名时间</text>
						<text v-if="formatDate(period.signBeginTime)">{{formatDate(period.signBeginTime)}} —— {{formatDate(period.signEndTime)}}</text></view>
					<view class="padding-tb-sm">
						<view><text class="text-grey margin-right">主办单位</text>{{period.holder}}</view>
					</view>

					<view class="text-right">
						<text class="cuIcon-attentionfill margin-lr-xs text-grey"></text> <text class="text-gray">浏览 {{period.basic.viewNumber}}</text>
						<text class="cuIcon-favorfill margin-lr-xs text-grey"></text> <text class="text-gray"> 关注 {{period.basic.concernNumber}}</text>
					</view>
				</view>

				<!-- 加入资讯块的显示 -->
				<scroll-view scroll-x class="bg-white nav">
					<view class="flex text-center">
						<view class="cu-item flex-sub" :class="index==tabCur?'text-orange cur':''" v-for="(item,index) in tabList" :key="index"
						 @tap="tabSelect(index)" :data-id="index">
							<text :class="'cuIcon-' + item.icon" class="margin-right-xs"></text> {{item.label}}
						</view>
					</view>
				</scroll-view>
				<view class="tab-content padding margin-bottom-xxl">
					<view v-if="tabCur == 0">
						<rich-text :nodes="period.details.content"></rich-text>
					</view>
					<view v-if="tabCur == 1">
						<van-steps :steps="timelineSteps" :active="timelineActive" direction="vertical" active-color="#39b54a" @click="clipLink" />
						<nl-empty v-if="!timelineList || timelineList.length==0" :msg="'暂无资讯'"></nl-empty>
					</view>
				</view>
			</view>
		</view>
		<!-- 底部操作条 -->
		<view class="cu-bar tabbar shadow foot bg-white">
			<view class="action" :class="concerned?'text-orange':'text-gray'" @click="onConcernClick">
				<view :class="concerned?'cuIcon-favorfill':'cuIcon-favor'"></view>
				<text v-if="concerned">已关注</text>
				<text v-else>关注</text>
			</view>
			<view class="action text-gray" @click="onCommentClick">
				<view class="cuIcon-mark"></view> 评论
			</view>
			<view class="action text-gray add-action" @click="onPublicClick">
				<button class="cu-btn cuIcon-add bg-green shadow"></button> 建队
			</view>
			<view class="action text-gray" @click="onTeamClick">
				<view class="cuIcon-friendfill"></view> 队伍
			</view>
			<view class="action text-gray" @click="onNewsClick">
				<view class="cuIcon-community"></view> 动态
			</view>
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
		delConcernCp,
		addConcernCp
	} from '@/api/ci/concern.js'
	import {
		listTimeline
	} from '@/api/ci/timeline.js'

	export default {
		name: 'CompetitionInfo',
		data() {
			return {
				ColorList: this.ColorList,
				id: undefined,
				// 竞赛信息
				period: {},
				// 关注信息
				concern: {},
				// 是否关注
				concerned: false,
				// 竞赛级别字典
				levelOptions: [],
				// tab内容
				tabCur: 0,
				tabList: [{
					icon: 'tag',
					label: '信息简介'
				}, {
					icon: 'community',
					label: '竞赛资讯'
				}],
				// 竞赛资讯
				timelineList: [],
				timelineSteps: [],
				timelineActive: 0

			}
		},
		onLoad(options) {
			this.id = options.id
			this.getDicts('ci_competition_level').then(res => {
				this.levelOptions = res.data
			})
			if (this.id) {
				getPeriod(this.id).then(res => {
					this.period = res.data
					this.period.classifyList = res.data.classifyLabels ? res.data.classifyLabels.split(",") : []
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
				listTimeline({
					cpId: this.id
				}).then(res => {
					this.timelineList = res.rows
					this.timelineSteps = res.rows.map(item => {
						return {
							text: item.content,
							desc: this.formatDate(item.time)
						}
					})
				})
			}
		},
		methods: {
			// 切换竞赛简介和资讯tab
			tabSelect(index) {
				this.tabCur = index
			},
			// 复制文章链接
			clipLink(e) {
				console.log(e)
			},
			// 设置关注
			onConcernClick() {
				if (this.concerned) {
					delConcernCp(this.concern.id).then(res => {
						this.msgInfo('取消关注成功')
						this.concerned = false
					})
				} else {
					addConcernCp({
						cpId: this.period.id,
					}).then(res => {
						this.concerned = true
						this.concern = res.data
						this.msgInfo('添加关注成功')
					})
				}
			},
			// 跳转到关于该竞赛的队伍列表页面
			onPublicClick() {
				uni.navigateTo({
					url: './team-public?cpId=' + this.id + "&cpName=" + this.period.name
				})
			},
			onTeamClick() {
				uni.navigateTo({
					url: './team-list?cpId=' + this.id
				})
			},
			// 跳转到关于该竞赛的动态页面
			onNewsClick() {
				uni.switchTab({
					url: 'news-index'
				})
			},
			// 跳转到评论页面
			onCommentClick() {
				this.msgInfo('评论功能已关闭')
				// uni.navigateTo({
				// 	url: './competition-comment?cpId=' + this.id
				// })
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
			levelFormat(value, dict) {
				return this.selectDictLabel(this.levelOptions, value)
			},
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
		margin-bottom: 120upx;
	}
</style>
