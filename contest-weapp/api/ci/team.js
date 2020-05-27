import http from '@/utils/request.js'

// 新增队伍信息
export function addTeamInfo(data) {
	return http.request({
		url: '/ci/teamInfo',
		method: 'POST',
		data: data
	})
}

// 查询队伍信息
export function listTeamInfo(query, type) {
	return http.request({
		url: type ? '/ci/teamInfo/list/' + type : '/ci/teamInfo/list/',
		method: 'GET',
		params: query
	})
}
