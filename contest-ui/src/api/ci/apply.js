import request from '@/utils/request'

// 查询入队申请列表
export function listApply(query) {
  return request({
    url: '/ci/apply/list',
    method: 'get',
    params: query
  })
}

// 查询入队申请详细
export function getApply(id) {
  return request({
    url: '/ci/apply/' + id,
    method: 'get'
  })
}

export function getApplyOptions(label) {
    return request({
        url: '/ci/apply/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增入队申请
export function addApply(data) {
  return request({
    url: '/ci/apply',
    method: 'post',
    data: data
  })
}

// 修改入队申请
export function updateApply(data) {
  return request({
    url: '/ci/apply',
    method: 'put',
    data: data
  })
}

// 删除入队申请
export function delApply(id) {
  return request({
    url: '/ci/apply/' + id,
    method: 'delete'
  })
}

// 导出入队申请
export function exportApply(query) {
  return request({
    url: '/ci/apply/export',
    method: 'get',
    params: query
  })
}