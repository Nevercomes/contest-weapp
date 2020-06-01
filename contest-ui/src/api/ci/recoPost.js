import request from '@/utils/request'

// 查询帖子推荐列表
export function listRecoPost(query) {
  return request({
    url: '/ci/recoPost/list',
    method: 'get',
    params: query
  })
}

// 查询帖子推荐详细
export function getRecoPost(id) {
  return request({
    url: '/ci/recoPost/' + id,
    method: 'get'
  })
}

export function getRecoPostOptions(label) {
    return request({
        url: '/ci/recoPost/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增帖子推荐
export function addRecoPost(data) {
  return request({
    url: '/ci/recoPost',
    method: 'post',
    data: data
  })
}

// 修改帖子推荐
export function updateRecoPost(data) {
  return request({
    url: '/ci/recoPost',
    method: 'put',
    data: data
  })
}

// 删除帖子推荐
export function delRecoPost(id) {
  return request({
    url: '/ci/recoPost/' + id,
    method: 'delete'
  })
}

// 导出帖子推荐
export function exportRecoPost(query) {
  return request({
    url: '/ci/recoPost/export',
    method: 'get',
    params: query
  })
}