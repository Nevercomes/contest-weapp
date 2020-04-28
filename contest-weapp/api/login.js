import {
	request
} from '../utils/request.js'

// 调用微信登录
export function login(code) {
	return request({
		url: '/wxLogin',
		method: 'get',
		data: {
			code: code
		}
	})
}

// 获取用户详细信息
export function getInfo() {
	return request({
		url: '/getInfo',
		method: 'get'
	})
}

// 退出方法
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

