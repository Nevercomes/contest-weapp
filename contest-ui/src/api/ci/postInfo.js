import request from '@/utils/request'

// 查询帖子信息列表
export function listPostInfo(query) {
  return request({
    url: '/ci/postInfo/list',
    method: 'get',
    params: query
  })
}

// 查询帖子信息详细
export function getPostInfo(id) {
  return request({
    url: '/ci/postInfo/' + id,
    method: 'get'
  })
}

export function getPostInfoOptions(label) {
    return request({
        url: '/ci/postInfo/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增帖子信息
export function addPostInfo(data) {
  return request({
    url: '/ci/postInfo',
    method: 'post',
    data: data
  })
}

// 修改帖子信息
export function updatePostInfo(data) {
  return request({
    url: '/ci/postInfo',
    method: 'put',
    data: data
  })
}

// 删除帖子信息
export function delPostInfo(id) {
  return request({
    url: '/ci/postInfo/' + id,
    method: 'delete'
  })
}

// 导出帖子信息
export function exportPostInfo(query) {
  return request({
    url: '/ci/postInfo/export',
    method: 'get',
    params: query
  })
}