<template>
	<view class="app-container">
		<van-cell-group>
			<van-cell title="头像" is-link @click="onChangeAvatarClick" center>
				<view slot="value" class="cell-userinfo">
					<image v-if="user.avatar" class="cell-avatar" :src="user.avatar"></image>
					<view v-else class="cell-avatar">
						<open-data type="userAvatarUrl"></open-data>
					</view>
				</view>
			</van-cell>
			<van-cell title="昵称" is-link :url="nickNameUrl + '?nickName=' + user.nickName" center>
				<view slot class="cell-userinfo">
					<view v-if="user.nickName">{{user.nickName}}</view>
					<open-data v-else type="userNickName" class="text-black text-bold"></open-data>
				</view>
			</van-cell>
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
			<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" @click="goToIdentifyPage">去认证</button>
		</view>
		<kps-image-cutter @ok="onOk" @cancel="onCancel" :url="url" :fixed="true" :width="160" :height="160"></kps-image-cutter>
	</view>
</template>

<script>
	import avatar from '@/components/yq-avatar/yq-avatar.vue'
	import kpsImageCutter from '@/components/ksp-image-cutter/ksp-image-cutter.vue'
	import {
		getUserProfile,
		uploadAvatar
	} from '@/api/system/user.js'


	export default {
		name: 'UserProfile',
		components: {
			avatar,
			kpsImageCutter
		},
		data() {
			return {
				// 用户信息
				user: {},
				// 年级字典
				gradeOptions: {},
				// identify的url
				identifyUrl: '/pages/ci/user-identify-form',
				// 更改昵称的url
				nickNameUrl: '/pages/ci/user-profile-nickname',
				// 图片裁剪工具的url
				url: ''
			}
		},
		onLoad() {
			getUserProfile().then(res => {
				this.user = res.data
			})
			this.getDicts('sys_user_grade').then(res => {
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
				let self = this
				uni.chooseImage({
					count: 1,
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: function(res) {
						self.url = res.tempFilePaths[0]
					}
				});
			},
			onOk(e) {
				uploadAvatar(e.path).then(response => {
					if (response.code === 200) {
						this.msgSuccess('头像上传成功')
						this.url = ''
						this.$store.commit('SET_AVATAR', response.imgUrl)
					} else {
						this.msgInfo(response.msg)
					}
				})
			},
			onCancel() {
				this.url = ''
			},
			onChangeNickNameClick() {

			},
			// 翻译字典值
			gradeFormat(value, dict) {
				return this.selectDictLabel(this.gradeOptions, value)
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
