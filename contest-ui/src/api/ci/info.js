import request from '@/utils/request'

// 查询竞赛信息模板列表
export function listInfo(query) {
  return request({
    url: '/ci/info/list',
    method: 'get',
    params: query
  })
}

// 查询竞赛信息模板详细
export function getInfo(id) {
  return request({
    url: '/ci/info/' + id,
    method: 'get'
  })
}

// 新增竞赛信息模板
export function addInfo(data) {
  return request({
    url: '/ci/info',
    method: 'post',
    data: data
  })
}

// 修改竞赛信息模板
export function updateInfo(data) {
  return request({
    url: '/ci/info',
    method: 'put',
    data: data
  })
}

// 删除竞赛信息模板
export function delInfo(id) {
  return request({
    url: '/ci/info/' + id,
    method: 'delete'
  })
}

// 导出竞赛信息模板
export function exportInfo(query) {
  return request({
    url: '/ci/info/export',
    method: 'get',
    params: query
  })
}