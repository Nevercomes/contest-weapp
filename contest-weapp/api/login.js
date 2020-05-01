import http from '@/utils/request.js'

// 调用微信登录
export function login(code) {
	return http.request({
		url: '/wxLogin',
		method: 'GET',
		data: {
			code: code
		}
	})
}

// 获取用户详细信息
export function getInfo() {
	return http.request({
		url: '/getInfo',
		method: 'GET'
	})
}

// 退出方法
export function logout() {
  return http.request({
    url: '/logout',
    method: 'POST'
  })
}

