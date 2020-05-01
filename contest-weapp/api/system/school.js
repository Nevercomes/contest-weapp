import http from '@/utils/request.js'

// 查询学校信息列表
export function listSchool(query) {
  return http.request({
    url: '/system/school/list',
    method: 'GET',
    params: query
  })
}

// 查询学校信息选项
export function getSchoolOptions(label) {
  return http.request({
    url: '/system/school/options',
    method: 'GET',
    params: {
      schoolName: label || ''
    }
  })
}