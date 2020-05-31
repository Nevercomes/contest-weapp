import http from '@/utils/request.js'

// 查询队伍信息
export function getTeamInfo(id) {
	return http.request({
		url: '/ci/teamInfo/' + id,
		method: 'get'
	})
}

// 新增队伍信息
export function addTeamInfo(data) {
	return http.request({
		url: '/ci/teamInfo',
		method: 'POST',
		data: data
	})
}

// 修改队伍信息
export function updateTeamInfo(data) {
	return http.request({
		url: '/ci/teamInfo',
		method: 'PUT',
		data: data
	})
}

// 查询队伍信息列表
export function listTeamInfo(query, type) {
	return http.request({
		url: type ? '/ci/teamInfo/list/' + type : '/ci/teamInfo/list',
		method: 'GET',
		params: query
	})
}

// 上传队伍对象
export function uploadTeamAvatar(path) {
	return http.upload('/system/ossRecord/upload/teamAvatar', {
		'filePath': path,
		'name': 'file'
	})
}

// 判断是否可以申请加入
export function judgeCanJoin(teamId) {
	return http.request({
		url: '/ci/teamInfo/canJoin/' + teamId,
		method: 'get'
	})
}
