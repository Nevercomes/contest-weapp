// 目前未解决静默登录后再次静默请求原失败接口的问题

import store from '../store/index.js'
import {
	getToken
} from './auth.js'
import defaultConfig from '@/config/index.js'
import Dialog from '../wxcomponents/vant/dialog/dialog.js'

import Request from '@/js_sdk/luch-request/index.js'

const http = new Request()

// 注入全局配置,只会调用一次
http.setConfig((config) => {
	config.baseUrl = defaultConfig.REQ_API
	return config
})

// 设置对成功请求的resolve逻辑
http.validateStatus = (statusCode) => {
   return statusCode && statusCode >= 200 && statusCode < 300
}

// 配置请求拦截器,每次请求前都会调用,这里如果存在token则注入
http.interceptor.request((config, cancel) => {
	if (getToken()) {
		config.header['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token
	}
	return config
})

// 配置响应拦截器,每次请求前都会调用
http.interceptor.response((res) => {
	console.log(res)
	const code = res.data.code
	// 登录状态过期,调用微信进行静默登录
	if (code === 401) {
		console.log('调用静默登录')
		// 再重新请求接口
		store.dispatch('Login').then(loginRes => {
			// return request({
			// 	url: url,
			// 	method: method,
			// 	data: data,
			// 	mask: mask
			// })
		})
	} else if (code === 403) {
		// 实名信息未填写,跳转到信息页面
		Dialog.alert({
			title: '未实名',
			message: '未进行实名认证,请填写实名信息'
		}).then(() => {
			console.log('跳转到信息填写页面')
		})
		Promise.reject(res)
	} else if (code !== 200) {
		// toast显示错误信息
		uni.showToast({
			title: res.data.msg
		})
		Promise.reject(res)
	} else {
		return res.data
	}
})

export default http
