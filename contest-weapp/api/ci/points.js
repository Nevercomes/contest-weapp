import http from '@/utils/request'

// 查询积分记录列表
export function listPoints(query) {
  return http.request({
    url: '/ci/points/list',
    method: 'get',
    params: query
  })
}

// 查询积分记录详细
export function getPoints(id) {
  return http.request({
    url: '/ci/points/' + id,
    method: 'get'
  })
}

// 查询用户积分
export function getUserPoints() {
	return http.request({
		url: '/ci/points/user',
		method: 'get'
	})
}

export function getPointsOptions(label) {
    return http.request({
        url: '/ci/points/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增积分记录
export function addPoints(data) {
  return http.request({
    url: '/ci/points',
    method: 'post',
    data: data
  })
}

// 修改积分记录
export function updatePoints(data) {
  return http.request({
    url: '/ci/points',
    method: 'put',
    data: data
  })
}

// 删除积分记录
export function delPoints(id) {
  return request({
    url: '/ci/points/' + id,
    method: 'delete'
  })
}

// 导出积分记录
export function exportPoints(query) {
  return request({
    url: '/ci/points/export',
    method: 'get',
    params: query
  })
}