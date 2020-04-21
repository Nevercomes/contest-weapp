import request from '@/utils/request'

// 查询竞赛列表信息列表
export function listPeriod(query) {
  return request({
    url: '/ci/period/list',
    method: 'get',
    params: query
  })
}

// 查询竞赛列表信息详细
export function getPeriod(id) {
  return request({
    url: '/ci/period/' + id,
    method: 'get'
  })
}

// 新增竞赛列表信息
export function addPeriod(data) {
  return request({
    url: '/ci/period',
    method: 'post',
    data: data
  })
}

// 修改竞赛列表信息
export function updatePeriod(data) {
  return request({
    url: '/ci/period',
    method: 'put',
    data: data
  })
}

// 删除竞赛列表信息
export function delPeriod(id) {
  return request({
    url: '/ci/period/' + id,
    method: 'delete'
  })
}

// 导出竞赛列表信息
export function exportPeriod(query) {
  return request({
    url: '/ci/period/export',
    method: 'get',
    params: query
  })
}