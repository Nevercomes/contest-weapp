import http from '@/utils/request.js'

// 查询组队意愿列表
export function listExpect(query) {
  return http.request({
    url: '/ci/expect/list',
    method: 'GET',
    params: query
  })
}

// 新增组队意愿
export function addExpect(data) {
  return http.request({
    url: '/ci/expect',
    method: 'POST',
    data: data
  })
}

// 撤回意愿
export function undoExpect(id) {
	return http.request({
		url: '/ci/expect',
		method: 'put',
		data: {
			id: id,
			status: '3'
		}
	})
}

// 队伍创建成功之后展示可能的用户
export function listPossibleUser(query) {
	return http.request({
		url: '/ci/expect/possible',
		method: 'get',
		params: query
	})
}