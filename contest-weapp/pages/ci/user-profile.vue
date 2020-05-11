<template>
	<view class="app-container">
		<van-cell-group>
			<van-cell title="头像" is-link @click="onChangeAvatarClick" center>
				<view slot class="cell-userinfo">
					<!-- 头像 -->
					<image v-if="avatar" class="cell-avatar" :src="avatar"></image>
					<view v-else class="cell-avatar">
						<open-data type="userAvatarUrl"></open-data>
					</view>
				</view>
			</van-cell>
			<van-cell title="昵称" :value="user.nickName" is-link url="#" center></van-cell>
		</van-cell-group>
		<!-- 是否认证 -->
		<van-cell-group v-if="user.identified == '1'">
			<van-cell title="姓名" :value="user.trueName" is-link :url="identifyUrl" center></van-cell>
			<van-cell title="学校" :value="user.schoolName" is-link :url="identifyUrl" center></van-cell>
			<van-cell title="专业" :value="user.major" is-link :url="identifyUrl" center></van-cell>
			<van-cell title="年级" :value="gradeFormat(user.grade)" is-link :url="identifyUrl" center></van-cell>
		</van-cell-group>
		<van-cell v-else title="认证状态" value="未认证" center></van-cell>
		<!-- 去认证按钮 -->
		<view v-if="user.identified != '1'" class="padding flex flex-direction">
			<button class="cu-btn bg-green margin-tb-sm lg" @click="goToIdentifyPage">去认证</button>
		</view>
	</view>
</template>

<script>
	import {
		getUserProfile
	} from '@/api/system/user.js'

	export default {
		name: 'UserProfile',
		data() {
			return {
				// 用户信息
				user: {},
				// 年级字典
				gradeOptions: {},
				// identify的url
				identifyUrl: '/pages/ci/user-identify-form'
			}
		},
		onLoad() {
			getUserProfile().then(res => {
				this.user = res.data
			})
			this.getDicts('user_grade_type').then(res => {
				this.gradeOptions = res.data
			})
		},
		onShow() {
			// 重新获取认证状态
			getUserProfile().then(res => {
				this.user = res.data
			})
		},
		methods: {
			goToIdentifyPage() {
				uni.navigateTo({
					url: 'user-identify-form'
				})
			},
			// 更换头像
			onChangeAvatarClick() {
				
			},
			// 翻译字典值
			gradeFormat(value, dict) {
				return this.selectDictLabel(value, this.gradeOptions)
			}
		}
	}
</script>

<style scoped lang="scss">
	.cell-avatar {
		width: 64px;
		height: 64px;
	}

	.cell-userinfo {
		display: flex;
		flex-direction: row;
	}
</style>
