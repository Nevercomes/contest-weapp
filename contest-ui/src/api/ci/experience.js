import request from '@/utils/request'

// 查询竞赛经历列表
export function listExperience(query) {
  return request({
    url: '/ci/experience/list',
    method: 'get',
    params: query
  })
}

// 查询竞赛经历详细
export function getExperience(id) {
  return request({
    url: '/ci/experience/' + id,
    method: 'get'
  })
}

export function getExperienceOptions(label) {
    return request({
        url: '/ci/experience/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增竞赛经历
export function addExperience(data) {
  return request({
    url: '/ci/experience',
    method: 'post',
    data: data
  })
}

// 修改竞赛经历
export function updateExperience(data) {
  return request({
    url: '/ci/experience',
    method: 'put',
    data: data
  })
}

// 删除竞赛经历
export function delExperience(id) {
  return request({
    url: '/ci/experience/' + id,
    method: 'delete'
  })
}

// 导出竞赛经历
export function exportExperience(query) {
  return request({
    url: '/ci/experience/export',
    method: 'get',
    params: query
  })
}