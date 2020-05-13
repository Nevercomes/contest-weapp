import http from '@/utils/request'

// 查询竞赛经历列表
export function listExperience(query) {
  return http.request({
    url: '/ci/experience/list',
    method: 'GET',
    params: query
  })
}

// 查询竞赛经历详细
export function getExperience(id) {
  return http.request({
    url: '/ci/experience/' + id,
    method: 'GET'
  })
}

export function getExperienceOptions(label) {
    return http.request({
        url: '/ci/experience/options',
        method: 'GET',
        params: {
          name: label || ''
        }
    })
}

// 新增竞赛经历
export function addExperience(data) {
  return http.request({
    url: '/ci/experience',
    method: 'POST',
    data: data
  })
}

// 修改竞赛经历
export function updateExperience(data) {
  return http.request({
    url: '/ci/experience',
    method: 'PUT',
    data: data
  })
}

// 删除竞赛经历
export function delExperience(id) {
  return http.request({
    url: '/ci/experience/' + id,
    method: 'DELETE'
  })
}

// 导出竞赛经历
export function exportExperience(query) {
  return http.request({
    url: '/ci/experience/export',
    method: 'GET',
    params: query
  })
}