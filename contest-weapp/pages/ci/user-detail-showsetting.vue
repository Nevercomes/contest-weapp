<template>
	<view class="app-container">
		<form @submit="submitForm">
			<!-- 两个固定项 -->
			<checkbox-group>
				<view class="cu-form-group">
					<view class="title">学校</view>
					<checkbox disabled="true" class='round' :class="'checked'" :checked="true"></checkbox>
				</view>
				<view class="cu-form-group">
					<view class="title">专业</view>
					<checkbox disabled="true" class='round' :class="'checked'" :checked="true"></checkbox>
				</view>
				<view class="cu-form-group">
					<view class="title">姓名</view>
					<checkbox class='round' :class="showSetting.showName == '1'?'checked':''" :checked="showSetting.showName == '1'?true:false"
					 value="1" @click="showNameClick"></checkbox>
				</view>
				<view class="cu-form-group">
					<view class="title">个人简介</view>
					<checkbox class='round' :class="showSetting.showIntro == '1'?'checked':''" :checked="showSetting.showIntro == '1'?true:false"
					 value="1" @click="showIntroClick"></checkbox>
				</view>
				<view class="cu-form-group">
					<view class="title">竞赛经历</view>
					<checkbox class='round' :class="showSetting.showExperience == '1'?'checked':''" :checked="showSetting.showExperience == '1'?true:false"
					 value="1" @click="showExperienceClick"></checkbox>
				</view>
				<view class="cu-form-group">
					<view class="title">积分状态</view>
					<checkbox class='round' :class="showSetting.showPoints == '1'?'checked':''" :checked="showSetting.showPoints == '1'?true:false"
					 value="1" @click="showPointsClick"></checkbox>
				</view>
				<view class="cu-form-group">
					<view class="title">发布状态</view>
					<checkbox class='round' :class="showSetting.showNews == '1'?'checked':''" :checked="showSetting.showNews == '1'?true:false"
					 value="1" @click="showNewsClick"></checkbox>
				</view>
			</checkbox-group>

			<view class="padding flex flex-direction">
				<button class="cu-btn bg-green margin-tb-sm lg" form-type="submit">提 交</button>
			</view>
		</form>
	</view>
</template>

<script>
	import {
		mapGetters
	} from 'vuex'
	import {
		getShowSetting
	} from '@/api/ci/showSetting.js'

	export default {
		name: 'UserDetailShowsetting',
		computed: {
			...mapGetters([
				'showSetting'
			])
		},
		data() {
			return {
				setting: {}
			}
		},
		onLoad() {

		},
		methods: {
			showNameClick() {
				this.showSetting.showName = this.showSetting.showName == '1' ? '0' : '1'
			},
			showIntroClick() {
				this.showSetting.showIntro = this.showSetting.showIntro == '1' ? '0' : '1'
			},
			showExperienceClick() {
				this.showSetting.showExperience = this.showSetting.showExperience == '1' ? '0' : '1'
			},
			showPointsClick() {
				this.showSetting.showPoints = this.showSetting.showPoints == '1' ? '0' : '1'
			},
			showNewsClick() {
				this.showSetting.showNews = this.showSetting.showNews == '1' ? '0' : '1'
			},
			submitForm() {
				this.$store.dispatch('SetShowSetting',
					this.showSetting).then(res => {
					this.msgSuccess('修改成功')
					uni.navigateBack({
						delta: 1
					})
				})
			}
		}
	}
</script>

<style>
</style>
