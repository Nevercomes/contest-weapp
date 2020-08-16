// TODO 目前未解决静默登录后再次静默请求原失败接口的问题

import store from '../store/index.js'
import {
	getToken,
	getTryLogin,
	addTryLogin,
	clearTryLogin
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
// 2020.7.16 改造一下,可以选择不带token,以实现某些匿名接口的调用
http.interceptor.request((config, cancel) => {
	if (!config.anonymous && getToken()) {
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
			let loginTimes = getTryLogin()
			if (loginTimes > 3) {
				uni.showToast({
					title: '登录失败,请重启再尝试',
					icon: 'none'
				})
				return Promise.reject(res.data)
			} else {
				addTryLogin()
				// 再重新请求接口
				// sun 20200528 如果登录请求也失败了话它将会一直调用这是很危险的
				// 至于一直fail的情况值得深究,请求是必须请求的,但是必须要对此进行计数
				store.dispatch('Login').then((_res) => {
					// return http.request({
					// 	url: res.config.url,
					// 	method: res.config.method,
					// 	data: res.config.data,
					// 	params: res.config.params
					// })
				})
			}
			return Promise.reject(res.data)
		} else if (code === 403) {
			// 实名信息未填写,跳转到信息页面
			console.debug('无接口权限')
			uni.showModal({
				title: '未进行实名认证',
				content: '点击确定前往填写认证信息',
				showCancel: false,
				success: function() {
					uni.navigateTo({
						url: '/pages/ci/user-identify-form'
					})
				}
			})
			// 调用处进入catch
			return Promise.reject(res.data)
		} else if (code != 200) {
			console.error(res)
			return Promise.reject(res.data)
		} else {
			return res.data
		}
	},
	// 返回的statusCode (不是data里面的code),不是200-300 则进入error
	(error) => {
		console.error(error)
		// 必须直接return error,不需要用Promise封装
		uni.showToast({
			title: '哎呀,请求发生了一点错误',
			icon: 'none'
		})
		return error
	}
)

export default http
