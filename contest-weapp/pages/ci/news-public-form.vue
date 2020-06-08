<template>
	<view class="app-container">
		<form @submit="submitForm">
			<view class="cu-form-group">
				<view class="title">竞赛</view>
				<view class="input-picker" @click="goToCompSelectPage">
					<input v-model="form.cpName" name="cpName" disabled placeholder="请选择比赛" />
				</view>
			</view>
			<view class="cu-form-group">
				<view class="title">标题</view>
				<input v-model="form.name" name="name" placeholder="请输入标题" confirm-type="next">
			</view>

			<textarea class="post-content" v-model="form.content" maxlength="-1" placeholder="写下你想说的..." show-confirm-bar></textarea>

			<!-- 封面图片 -->
			<view class="cu-bar bg-white margin-top">
				<view class="action">
					文章封面
				</view>
			</view>
			<view class="cu-form-group">
				<view class="grid col-4 grid-square flex-sub">
					<view class="bg-img" @tap="ViewImage" :data-url="coverUrl">
					 <image :src="coverUrl" mode="aspectFill"></image>
						<view class="cu-tag bg-red" @tap.stop="DelImg" :data-index="index">
							<text class='cuIcon-close'></text>
						</view>
					</view>
					<view class="solids" @tap="ChooseImage" v-if="!coverUrl">
						<text class='cuIcon-cameraadd'></text>
					</view>
				</view>
			</view>
			
			<view class="cu-bar bg-white margin-top">
				<view class="action">
					上传附件（无附件则不上传）
				</view>
				<view class="action">
					{{appendixList.length}}/4
				</view>
			</view>
			<view class="cu-form-group">
				<!-- <view class="grid col-4 grid-square flex-sub">
					<view class="bg-img" v-for="(item,index) in appendixList" :key="index" @tap="ViewImage" :data-url="appendixList[index]">
					 <image :src="appendixList[index]" mode="aspectFill"></image>
						<view class="cu-tag bg-red" @tap.stop="DelImg" :data-index="index">
							<text class='cuIcon-close'></text>
						</view>
					</view>
					<view class="solids" @tap="ChooseImage" v-if="appendixList.length<4">
						<text class='cuIcon-cameraadd'></text>
					</view>
				</view> -->
			</view>

			<view class="padding flex flex-direction">
				<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" @click="onPublicClick">发 布</button>
			</view>
			<view class="padding flex flex-direction">
				<button class="cu-btn bg-green margin-tb-sm lg shadow-blur round" @click="submitForm">保存为草稿</button>
			</view>
		</form>

		<nl-mask-loading :loading="loading"></nl-mask-loading>
	</view>
</template>

<script>
	import WxValidate from '@/utils/WxValidate.js'

	import {
		addPostInfo,
		updatePostInfo,
		publicPost,
		uploadPostCover
	} from '@/api/ci/postInfo.js'
	import {
		uploadAppendix
	} from '@/api/ci/postAppendix.js'

	export default {
		name: 'NewsPublicForm',
		data() {
			return {
				// 表单数据
				form: {},
				// 封面图片
				coverUrl: '',
				// 校验规则
				rules: {
					cpName: {
						required: true
					},
					name: {
						required: true,
						maxlength: 16
					},
					content: {
						required: true
					}
				},
				// 错误提示
				messages: {
					cpName: {
						required: '竞赛不能为空'
					},
					name: {
						required: '标题不能为空',
						maxlength: '标题太长了'
					},
					content: {
						required: '内容不能为空'
					}
				}
			}
		},
		onLoad(options) {
			this.form.id = options.id
			if (this.form.id)
				this.getFormData()
		},
		methods: {
			reset() {
				this.form = {
					cpId: undefined,
					cpName: undefined,
					content: undefined
				}
			},
			getFormData() {
				getFunc(this.form.id).then(res => {
					this.form = res.data
					this.coverUrl = this.form.coverUrl
				})
			},
			onPublicClick() {
				if(this.form.id) {
					publicPost(this.form.id).then(res => {
						// 跳转到帖子界面
						this.msgSuccess('发布成功')
					})
				} else {
					addPostInfo(this.form).then(res => {
						this.loading = false
						publicPost(this.form.id).then(res => {
							// 跳转到帖子界面
							this.msgSuccess('发布成功')
						})
					}).catch(res => {
						this.loading = false
					})
				}
			},
			submitForm(e) {
				if (this.validForm(this.form)) {
					// 调用提交方法
					this.loading = true
					if (this.form.id) {
						updatePostInfo(this.form).then(res => {
							this.loading = false
							this.msgSuccess('修改成功')
							this.back()
						}).catch(res => {
							this.loading = false
						})
					} else {
						addPostInfo(this.form).then(res => {
							this.loading = false
							this.msgSuccess('已保存为草稿')
						}).catch(res => {
							this.loading = false
						})
					}
				}
			},
			validForm(params) {
				let wxValidate = new WxValidate(this.rules, this.messages)
				if (!wxValidate.checkForm(params)) {
					const error = wxValidate.errorList[0]
					uni.showToast({
						title: error.msg
					})
					return false
				}
				return true
			},
			ChooseImage() {
				let self = this
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: (res) => {
						self.coverUrl = res.tempFilePaths[0]
						// 提交到服务器
						uploadPostCover(self.coverUrl).then(res => {
							self.form.coverUrl = res.data
						})
					}
				});
			},
			ViewImage(e) {
				let imgList = []
				imgList.push(this.coverUrl)
				uni.previewImage({
					urls: this.imgList,
					current: e.currentTarget.dataset.url
				});
			},
			DelImg(e) {
				this.coverUrl = ''
				this.form.coverUrl = ''
			},
		}
	}
</script>

<style>
	.post-content {
		height: 400upx;
	}
</style>
