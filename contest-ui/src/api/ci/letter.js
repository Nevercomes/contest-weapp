import request from '@/utils/request'

// 查询私信管理列表
export function listLetter(query) {
  return request({
    url: '/ci/letter/list',
    method: 'get',
    params: query
  })
}

// 查询私信管理详细
export function getLetter(id) {
  return request({
    url: '/ci/letter/' + id,
    method: 'get'
  })
}

export function getLetterOptions(label) {
    return request({
        url: '/ci/letter/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增私信管理
export function addLetter(data) {
  return request({
    url: '/ci/letter',
    method: 'post',
    data: data
  })
}

// 修改私信管理
export function updateLetter(data) {
  return request({
    url: '/ci/letter',
    method: 'put',
    data: data
  })
}

// 删除私信管理
export function delLetter(id) {
  return request({
    url: '/ci/letter/' + id,
    method: 'delete'
  })
}

// 导出私信管理
export function exportLetter(query) {
  return request({
    url: '/ci/letter/export',
    method: 'get',
    params: query
  })
}