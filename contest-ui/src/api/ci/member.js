import request from '@/utils/request'

// 查询队伍成员列表
export function listMember(query) {
  return request({
    url: '/ci/member/list',
    method: 'get',
    params: query
  })
}

// 查询队伍成员详细
export function getMember(id) {
  return request({
    url: '/ci/member/' + id,
    method: 'get'
  })
}

export function getMemberOptions(label) {
    return request({
        url: '/ci/member/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增队伍成员
export function addMember(data) {
  return request({
    url: '/ci/member',
    method: 'post',
    data: data
  })
}

// 修改队伍成员
export function updateMember(data) {
  return request({
    url: '/ci/member',
    method: 'put',
    data: data
  })
}

// 删除队伍成员
export function delMember(id) {
  return request({
    url: '/ci/member/' + id,
    method: 'delete'
  })
}

// 导出队伍成员
export function exportMember(query) {
  return request({
    url: '/ci/member/export',
    method: 'get',
    params: query
  })
}