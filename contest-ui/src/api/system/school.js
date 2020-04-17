import request from '@/utils/request'

// 查询学校信息列表
export function listSchool(query) {
  return request({
    url: '/system/school/list',
    method: 'get',
    params: query
  })
}

// 查询学校信息详细
export function getSchool(schoolId) {
  return request({
    url: '/system/school/' + schoolId,
    method: 'get'
  })
}

// 新增学校信息
export function addSchool(data) {
  return request({
    url: '/system/school',
    method: 'post',
    data: data
  })
}

// 修改学校信息
export function updateSchool(data) {
  return request({
    url: '/system/school',
    method: 'put',
    data: data
  })
}

// 删除学校信息
export function delSchool(schoolId) {
  return request({
    url: '/system/school/' + schoolId,
    method: 'delete'
  })
}

// 导出学校信息
export function exportSchool(query) {
  return request({
    url: '/system/school/export',
    method: 'get',
    params: query
  })
}