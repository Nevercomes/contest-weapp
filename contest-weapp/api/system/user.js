import http from '@/utils/request.js'

export function identify(data) {
	return http.request({
		url: '/system/user/identify',
		method: 'POST',
		data: data
	})
}

// 查询用户个人信息
export function getUserProfile() {
  return http.request({
    url: '/system/user/profile',
    method: 'GET'
  })
}


// 查询用户的扩展信息
export function getUserExt() {
	return http.request({
	  url: '/system/user/ext',
	  method: 'GET'
	})
}

// 更新用户扩展信息
export function updateUserExt(data) {
	return http.request({
	  url: '/system/user/ext',
	  method: 'POST',
	  data: data
	})
}


