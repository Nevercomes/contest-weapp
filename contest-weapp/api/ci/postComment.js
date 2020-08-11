import http from '@/utils/request'

// 查询帖子评论列表
export function listComment(query) {
  return http.request({
    url: '/ci/comment/list',
    method: 'get',
    params: query
  })
}

// 查询帖子评论详细
export function getComment(id) {
  return http.request({
    url: '/ci/comment/' + id,
    method: 'get'
  })
}

export function getCommentOptions(label) {
    return http.request({
        url: '/ci/comment/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增帖子评论
export function addComment(data) {
  return http.request({
    url: '/ci/comment',
    method: 'post',
    data: data
  })
}

// 修改帖子评论
export function updateComment(data) {
  return http.request({
    url: '/ci/comment',
    method: 'put',
    data: data
  })
}

// 删除帖子评论
export function delComment(id) {
  return http.request({
    url: '/ci/comment/' + id,
    method: 'delete'
  })
}

// 导出帖子评论
export function exportComment(query) {
  return http.request({
    url: '/ci/comment/export',
    method: 'get',
    params: query
  })
}