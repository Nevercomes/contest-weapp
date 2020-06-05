import http from '@/utils/request.js'

// 查询消息内容
export function listContent(query) {
	return http.request({
		url: '/ci/letter/list/content',
		method: 'get',
		params: query
	})
}

// 查询消息列表摘要
export function listDigest() {
	return http.request({
		url: '/ci/letter/list/digest',
		method: 'get'
	})
}

// 发送消息
export function insertMsg(data) {
	return http.request({
		url: '/ci/letter',
		method: 'post',
		data: data
	})
}

// 将消息设置为已读
export function readMsg(query) {
	return http.request({
		url: '/ci/letter/read',
		method: 'put',
		data: query
	})
}