import request from '@/utils/request'

// 查询帖子浏览记录列表
export function listPostView(query) {
  return request({
    url: '/ci/postView/list',
    method: 'get',
    params: query
  })
}

// 查询帖子浏览记录详细
export function getPostView(id) {
  return request({
    url: '/ci/postView/' + id,
    method: 'get'
  })
}

export function getPostViewOptions(label) {
    return request({
        url: '/ci/postView/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增帖子浏览记录
export function addPostView(data) {
  return request({
    url: '/ci/postView',
    method: 'post',
    data: data
  })
}

// 修改帖子浏览记录
export function updatePostView(data) {
  return request({
    url: '/ci/postView',
    method: 'put',
    data: data
  })
}

// 删除帖子浏览记录
export function delPostView(id) {
  return request({
    url: '/ci/postView/' + id,
    method: 'delete'
  })
}

// 导出帖子浏览记录
export function exportPostView(query) {
  return request({
    url: '/ci/postView/export',
    method: 'get',
    params: query
  })
}