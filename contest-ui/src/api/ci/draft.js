import request from '@/utils/request'

// 查询帖子操作列表
export function listDraft(query) {
  return request({
    url: '/ci/draft/list',
    method: 'get',
    params: query
  })
}

// 查询帖子操作详细
export function getDraft(id) {
  return request({
    url: '/ci/draft/' + id,
    method: 'get'
  })
}

export function getDraftOptions(label) {
    return request({
        url: '/ci/draft/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增帖子操作
export function addDraft(data) {
  return request({
    url: '/ci/draft',
    method: 'post',
    data: data
  })
}

// 修改帖子操作
export function updateDraft(data) {
  return request({
    url: '/ci/draft',
    method: 'put',
    data: data
  })
}

// 删除帖子操作
export function delDraft(id) {
  return request({
    url: '/ci/draft/' + id,
    method: 'delete'
  })
}

// 导出帖子操作
export function exportDraft(query) {
  return request({
    url: '/ci/draft/export',
    method: 'get',
    params: query
  })
}