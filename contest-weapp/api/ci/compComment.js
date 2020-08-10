import http from '@/utils/request'

// 查询竞赛评论列表
export function listCompComment(query) {
  return http.request({
    url: '/ci/compComment/list',
    method: 'get',
    params: query
  })
}

// 查询竞赛评论详细
export function getCompComment(id) {
  return http.request({
    url: '/ci/compComment/' + id,
    method: 'get'
  })
}

export function getCompCommentOptions(label) {
    return http.request({
        url: '/ci/compComment/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增竞赛评论
export function addCompComment(data) {
  return http.request({
    url: '/ci/compComment',
    method: 'post',
    data: data
  })
}

// 修改竞赛评论
export function updateCompComment(data) {
  return http.request({
    url: '/ci/compComment',
    method: 'put',
    data: data
  })
}

// 删除竞赛评论
export function delCompComment(id) {
  return http.request({
    url: '/ci/compComment/' + id,
    method: 'delete'
  })
}

// 导出竞赛评论
export function exportCompComment(query) {
  return http.request({
    url: '/ci/compComment/export',
    method: 'get',
    params: query
  })
}