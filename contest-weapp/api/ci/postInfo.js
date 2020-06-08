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

// 发布帖子
export function publicPost(postId) {
	return request({
		url: '/ci/postInfo/public/' + postId,
		method: 'put',
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

// 文章封面
export function uploadPostCover(path) {
	return http.upload('/system/ossRecord/upload/postCover', {
		'filePath': path,
		'name': 'file'
	})
}
	