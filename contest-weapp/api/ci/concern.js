import http from '@/utils/request.js'

// 查询关注竞赛列表
export function listConcernCp(query) {
  return http.request({
    url: '/ci/concernCp/list',
    method: 'GET',
    params: query
  })
}

// 修改关注竞赛
export function updateConcernCp(data) {
  return http.request({
    url: '/ci/concernCp',
    method: 'PUT',
    data: data
  })
}

// 查询关注竞赛详细
export function getConcernCp(id) {
  return http.request({
    url: '/ci/concernCp/' + id,
    method: 'GET'
  })
}

// 新增关注竞赛
export function addConcernCp(data) {
  return http.request({
    url: '/ci/concernCp',
    method: 'POST',
    data: data
  })
}

// 删除关注竞赛
export function delConcernCp(id) {
  return http.request({
    url: '/ci/concernCp/' + id,
    method: 'DELETE'
  })
}


// 查询关注用户详细
export function getConcernUser(id) {
  return http.request({
    url: '/ci/concernUser/' + id,
    method: 'get'
  })
}

export function getConcernUserOptions(label) {
    return http.request({
        url: '/ci/concernUser/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增关注用户
export function addConcernUser(data) {
  return http.request({
    url: '/ci/concernUser',
    method: 'post',
    data: data
  })
}

// 修改关注用户
export function updateConcernUser(data) {
  return http.request({
    url: '/ci/concernUser',
    method: 'put',
    data: data
  })
}

// 删除关注用户
export function delConcernUser(userId) {
  return http.request({
    url: '/ci/concernUser',
    method: 'delete',
	params: {
		userId: userId
	}
  })
}