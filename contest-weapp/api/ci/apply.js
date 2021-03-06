import http from '@/utils/request'

// 查询入队申请列表
export function listApply(query) {
  return http.request({
    url: '/ci/apply/list',
    method: 'get',
    params: query
  })
}

// 查询自己发出的申请
export function listApplySend(query) {
	return http.request({
	  url: '/ci/apply/list/send',
	  method: 'get',
	  params: query
	})
}

// 查询收到的申请
export function listApplyReceive(query) {
	return http.request({
	  url: '/ci/apply/list/receive',
	  method: 'get',
	  params: query
	})
}

// 查询入队申请详细
export function getApply(id) {
  return http.request({
    url: '/ci/apply/' + id,
    method: 'get'
  })
}

export function getApplyOptions(label) {
    return http.request({
        url: '/ci/apply/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增入队申请
export function addApply(data) {
  return http.request({
    url: '/ci/apply',
    method: 'post',
    data: data
  })
}

// 修改入队申请
export function updateApply(data) {
  return http.request({
    url: '/ci/apply',
    method: 'put',
    data: data
  })
}

// 通过申请
export function passApply(id) {
	return http.request({
	  url: '/ci/apply/pass/' +  id,
	  method: 'put'
	})
}

// 拒绝申请
export function rejectApply(id) {
	return http.request({
	  url: '/ci/apply/reject/' +  id,
	  method: 'put'
	})
}

// 删除入队申请
export function delApply(id) {
  return http.request({
    url: '/ci/apply/' + id,
    method: 'delete'
  })
}

// 导出入队申请
export function exportApply(query) {
  return http.request({
    url: '/ci/apply/export',
    method: 'get',
    params: query
  })
}