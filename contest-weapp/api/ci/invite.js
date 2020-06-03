import http from '@/utils/request'

// 查询入队邀请列表
export function listInvite(query) {
  return http.request({
    url: '/ci/invite/list',
    method: 'get',
    params: query
  })
}

// 查询自己发出的邀请
export function listInviteSend(query) {
	return http.request({
	  url: '/ci/invite/list/send',
	  method: 'get',
	  params: query
	})
}

// 查询收到的邀请
export function listInviteReceive(query) {
	return http.request({
	  url: '/ci/invite/list/receive',
	  method: 'get',
	  params: query
	})
}

// 查询入队邀请详细
export function getInvite(id) {
  return http.request({
    url: '/ci/invite/' + id,
    method: 'get'
  })
}

export function getInviteOptions(label) {
    return http.request({
        url: '/ci/invite/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增入队邀请
export function addInvite(data) {
  return http.request({
    url: '/ci/invite',
    method: 'post',
    data: data
  })
}

// 修改入队邀请
export function updateInvite(data) {
  return http.request({
    url: '/ci/invite',
    method: 'put',
    data: data
  })
}

// 通过邀请
export function passApply(id) {
	return http.request({
	  url: '/ci/invite/pass/' +  id,
	  method: 'put'
	})
}

// 拒绝邀请
export function rejectApply(id) {
	return http.request({
	  url: '/ci/invite/reject/' +  id,
	  method: 'put'
	})
}

// 删除入队邀请
export function delInvite(id) {
  return http.request({
    url: '/ci/invite/' + id,
    method: 'delete'
  })
}

// 导出入队邀请
export function exportInvite(query) {
  return http.request({
    url: '/ci/invite/export',
    method: 'get',
    params: query
  })
}