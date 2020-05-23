import Vue from 'vue'
import App from './App'
import store from 'store/index.js'


import basics from './pages/basics/home.vue'
Vue.component('basics', basics)

import components from './pages/component/home.vue'
Vue.component('components', components)

import plugin from './pages/plugin/home.vue'
Vue.component('plugin', plugin)

import cuCustom from './colorui/components/cu-custom.vue'
Vue.component('cu-custom', cuCustom)

import NlMaskLoading from './nl_componet/nl-mask-loading.vue'
Vue.component('nl-mask-loading', NlMaskLoading)

import NlEmpty from './nl_componet/nl-empty.vue'
Vue.component('nl-empty', NlEmpty)

import {
	getDicts
} from './api/system/dict.js'
import {
	selectDictLabel,
	formatDate,
	formatTime
} from './utils/index.js'

// 全局方法挂载
Vue.prototype.getDicts = getDicts
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.formatDate = formatDate
Vue.prototype.formatTime = formatTime

// 使用toast进行消息反馈
Vue.prototype.msgInfo = (msg) => {
	uni.showToast({
		title: msg,
		icon: 'none',
		position: 'bottom'
	})
}

// 使用toast进行成功消息反馈
Vue.prototype.msgSuccess = (msg) => {
	uni.showToast({
		title: msg,
		icon: 'success'
	})
}

// 延迟0.7s返回
Vue.prototype.back = () => {
	setTimeout(() => {
		uni.navigateBack({
			delta: 1
		})
	}, 700)
}

Vue.prototype.hasMore = (total, pageNum, pageSize = 10) => {
	try {
		if(total <= 0 || pageNum < 1) {
			return false
		} else {
			return total > pageNum * pageSize
		}
	} catch (e) {
		return false
	}
}

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
	...App,
	store,
})
app.$mount()
