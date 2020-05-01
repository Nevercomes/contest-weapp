import store from '../store/index.js'
import {
	getToken
} from './auth.js'
import config from '../config/index.js'
import Dialog from '../wxcomponents/vant/dialog/dialog.js'

let param = {
	// 服务器地址
	baseURL: config.REQ_API,
	// 超时时间
	timeout: 10000,
	// 默认的请求头
	headers: {
		// 内容类型
		'Content-Type': 'application/json;charset=utf-8',
		// 请求携带token
		Authorization: getToken() ? 'Bearer ' + getToken() : ''
	},
	dataType: 'json'
}

export function request({
	url: url,
	method: method,
	data: data,
	mask: mask
}) {
	return new Promise((resolve, reject) => {
		console.log(param.headers.Authorization)
		if (mask) {
			uni.showLoading({
				title: '加载中'
			})
		}
		uni.request({
			// 地址
			url: param.baseURL + url,
			// 请求头
			headers: {
				// 内容类型
				'Content-Type': 'application/json;charset=utf-8',
				// 请求携带token
				Authorization: getToken() ? 'Bearer ' + getToken() : ''
			},
			// 超时时间
			timeout: param.timeout,
			// 希望的数据类型
			dataType: param.dataType,
			// 请求方法
			method: method.toUpperCase(),
			// 携带的数据
			data: data || null,
			// 成功回调
			success(res) {
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
						resolve('login')
					})
				} else if (code === 402) {
					// 实名信息未填写,跳转到信息页面
					Dialog.alert({
						title: '未实名',
						message: '未进行实名认证,请填写实名信息'
					}).then(() => {
						console.log('跳转到信息填写页面')
					})
					reject(res)
				} else if (code !== 200) {
					// toast显示错误信息
					uni.showToast({
						title: res.data.msg
					})
					reject(res)
				} else {
					resolve(res.data)
				}
			},
			// 失败回调
			fail(res) {
				console.error('err' + res)
				reject(res)
			},
			// 完成回调
			complete() {
				if (mask) uni.hideLoading()
			}
		})
	})
}
