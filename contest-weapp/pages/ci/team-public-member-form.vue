<template>
	<view class="app-container">
		<view class="cu-bar bg-white">
			<view class="action">
				<text class="cuIcon-title text-green"></text>
				<text>队员{{index}}招募信息</text>
			</view>
		</view>
		<form>
			<view class="cu-form-group">
				<view class="title">分工</view>
				<picker @change="pickerChange" :value="index" :range="workOptionsPicker">
					<input v-model="form.workLabel" name="workLabel" disabled placeholder="请选择分工" />
				</picker>
			</view>
			<view class="cu-form-group">
				<view class="title">能力期望</view>
				<view v-for="(item,index) in capabilityList" :key="index" class='cu-tag round' @click="onNotSelectClick(item)">{{item}}</view>
			</view>
		</form>
		
		<!-- 默认的能力选择label -->
		<view class="box bg-white padding-top-xs" style="height: 130upx;">
			<view v-for="(item,index) in capabilityLabelOptions" :key="index" class='cu-tag round' @click="onSelectClick(item)">{{item}}</view>
		</view>
		
		<view class="box">
			<view class="cu-bar btn-group">
				<button class="cu-btn bg-green shadow-blur round lg" @click="onConfirmClick">确定</button>
				<button class="cu-btn bg-green shadow-blur round lg" @click="onNextClick">继续添加</button>
			</view>
		</view>
		
	</view>
</template>

<script>
	
	import WxValidate from '@/utils/WxValidate.js'
	
	export default {
		name: 'TeamPublicMemberForm',
		data() {
			return {
				// 队员数据
				form: {},
				// 队员能力期望
				capabilityList: [],
				// 招募列表
				recruitList: [],
				// 招募人数
				recruitNumber: undefined,
				// 当前人数
				index: 1,
				// 分工选项
				workOptions: [],
				workOptionsPicker: [],
				// 默认能力期望标签
				capabilityOptions: [],
				capabilityLabelOptions: [],
				rules: {
					workLabel: {
						required: true
					}
				},
				messages: {
					workLabel: {
						required: '队员分工不能为空'
					}
				}
			}
		},
		onLoad(options) {
			this.recruitNumber = options.recruitNumber
			this.index = options.index
			if(this.index) this.index = Number(this.index)
			this.getDicts('team_work_type').then(res => {
				this.workOptions = res.data
				this.workOptionsPicker = res.data.map(item => item.dictLabel)
			})
			this.getDicts('team_capability_default').then(res => {
				this.capabilityOptions = res.data
				this.capabilityLabelOptions = res.data.map(item => item.dictLabel)
			})
		},
		methods: {
			reset() {
				this.form = {
					workId: undefined,
					workLabel: undefined,
				}
				this.capabilityList = []
				this.capabilityLabelOptions = this.capabilityOptions.map(item => item.dictLabel)
			},
			pickerChange(e) {
				const index = e.target.value
				this.form.workId = this.workOptions[index].dictValue
				this.form.workLabel = this.workOptions[index].dictLabel
				this.$forceUpdate()
			},
			onConfirmClick() {
				// 保存数据，返回页面
				this.saveRecruit()
				uni.navigateBack({
					delta: 1
				})
			},
			onNextClick() {
				// 判断是否可以再添加
				// 保存数据，清空表单
				if (this.index + 1 > this.recruitNumber) {
					this.msgInfo('已达到招募人数最大值')
				} else {
					this.saveRecruit()
					this.reset()
					this.index = this.index + 1
				}
			},
			onNotSelectClick(label) {
				const idx = this.capabilityList.indexOf(label)
				if(idx != -1) {
					this.capabilityList.splice(idx, 1)
					this.capabilityLabelOptions.push(label)
				}
			},
			// TODO Label的动效
			onSelectClick(label) {
				if(this.capabilityList.length >= 3) {
					this.msgInfo('不能再多啦')
					return
				}
				const idx = this.capabilityLabelOptions.indexOf(label)
				if(idx != -1) {
					this.capabilityLabelOptions.splice(idx, 1)
					this.capabilityList.push(label)
				}
			},
			saveRecruit() {
				this.form.capabilityList = this.capabilityList
				this.recruitList.push(this.form)
				const pages = getCurrentPages()
				if (pages.length >= 2) {
					const prePage = pages[pages.length - 2]
					const self = this
					let allList = prePage.$vm.recruitList
					allList = allList.concat(this.recruitList)
					prePage.setData({
						'recruitList': allList
					})
					// 这样子form可以绑定上数据，但是页面不会渲染, 所以前面添加一个setData
					prePage.$vm.recruitList = allList
				}
			}
		}
	}
</script>

<style>
</style>
