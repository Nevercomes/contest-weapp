import http from '@/utils/request'

// 查询用户显示设置列表
export function listShowSetting(query) {
  return http.request({
    url: '/ci/showSetting/list',
    method: 'GET',
    params: query
  })
}

// 查询用户显示设置详细
export function getShowSetting(ownUserId) {
  return http.request({
    url: ownUserId ? '/ci/showSetting/' + ownUserId : '/ci/showSetting',
    method: 'GET'
  })
}

export function getShowSettingOptions(label) {
    return http.request({
        url: '/ci/showSetting/options',
        method: 'GET',
        params: {
          name: label || ''
        }
    })
}

// 新增用户显示设置
export function addShowSetting(data) {
  return http.request({
    url: '/ci/showSetting',
    method: 'POST',
    data: data
  })
}

// 修改用户显示设置
export function updateShowSetting(data) {
  return http.request({
    url: '/ci/showSetting',
    method: 'PUT',
    data: data
  })
}

// 删除用户显示设置
export function delShowSetting(ownUserId) {
  return http.request({
    url: '/ci/showSetting/' + ownUserId,
    method: 'DELETE'
  })
}

// 导出用户显示设置
export function exportShowSetting(query) {
  return http.request({
    url: '/ci/showSetting/export',
    method: 'GET',
    params: query
  })
}