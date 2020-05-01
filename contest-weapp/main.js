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

import {
	getDicts
} from './api/system/dict.js'

// 全局方法挂载
Vue.prototype.getDicts = getDicts

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
	...App,
	store,
})
app.$mount()
