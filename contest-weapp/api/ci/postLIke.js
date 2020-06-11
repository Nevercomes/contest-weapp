import http from '@/utils/request'

// 查询帖子点赞记录列表
export function listPostLIke(query) {
  return http.request({
    url: '/ci/postLIke/list',
    method: 'get',
    params: query
  })
}

// 查询帖子点赞记录详细
export function getPostLIke(id) {
  return http.request({
    url: '/ci/postLIke/' + id,
    method: 'get'
  })
}

export function getPostLIkeOptions(label) {
    return http.request({
        url: '/ci/postLIke/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增帖子点赞记录
export function addPostLIke(data) {
  return http.request({
    url: '/ci/postLIke',
    method: 'post',
    data: data
  })
}

// 修改帖子点赞记录
export function updatePostLIke(data) {
  return http.request({
    url: '/ci/postLIke',
    method: 'put',
    data: data
  })
}

// 删除帖子点赞记录
export function delPostLIke(id) {
  return http.request({
    url: '/ci/postLIke/' + id,
    method: 'delete'
  })
}

// 导出帖子点赞记录
export function exportPostLIke(query) {
  return http.request({
    url: '/ci/postLIke/export',
    method: 'get',
    params: query
  })
}