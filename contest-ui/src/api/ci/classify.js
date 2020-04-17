import request from '@/utils/request'

// 查询竞赛分类列表
export function listClassify(query) {
  return request({
    url: '/ci/classify/list',
    method: 'get',
    params: query
  })
}

// 查询竞赛分类下拉树结构
export function treeselect() {
  return request({
    url: '/ci/classify/treeselect',
    method: 'get'
  })
}

// 根据竞赛ID查询分类下拉树结构
export function infoClassifyTreeselect(infoId) {
  return request({
    url: '/ci/classify/infoClassifyTreeselect/' + infoId,
    method: 'get'
  })
}

// 查询竞赛分类详细
export function getClassify(id) {
  return request({
    url: '/ci/classify/' + id,
    method: 'get'
  })
}

// 新增竞赛分类
export function addClassify(data) {
  return request({
    url: '/ci/classify',
    method: 'post',
    data: data
  })
}

// 修改竞赛分类
export function updateClassify(data) {
  return request({
    url: '/ci/classify',
    method: 'put',
    data: data
  })
}

// 删除竞赛分类
export function delClassify(id) {
  return request({
    url: '/ci/classify/' + id,
    method: 'delete'
  })
}

// 导出竞赛分类
export function exportClassify(query) {
  return request({
    url: '/ci/classify/export',
    method: 'get',
    params: query
  })
}
