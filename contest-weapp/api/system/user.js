import http from '@/utils/request.js'

export function identify(data) {
	return http.request({
		url: '/system/user/identify',
		method: 'POST',
		data: data
	})
}

export function listConcernUser(query) {
	return http.request({
		url: '/system/user/list/concern',
		method: 'get',
		params: query
	})
}

// 查询用户个人信息
export function getUserProfile(userId) {
  return http.request({
    url: userId ? '/system/user/profile/' + userId : '/system/user/profile',
    method: 'GET'
  })
}

// 更新用户个人信息
export function updateUserProfile(data) {
	return http.request({
		url: '/system/user/profile',
		method: 'put',
		data: data
	})
}

// 上传用户头像
export function uploadAvatar(path) {
	return http.upload('/system/user/profile/avatar', {
		'filePath': path,
		'name': 'avatarfile'
	})
}


