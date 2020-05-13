import request from '@/utils/request'

// 查询用户显示设置列表
export function listShowSetting(query) {
  return request({
    url: '/ci/showSetting/list',
    method: 'get',
    params: query
  })
}

// 查询用户显示设置详细
export function getShowSetting(ownUserId) {
  return request({
    url: '/ci/showSetting/' + ownUserId,
    method: 'get'
  })
}

export function getShowSettingOptions(label) {
    return request({
        url: '/ci/showSetting/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增用户显示设置
export function addShowSetting(data) {
  return request({
    url: '/ci/showSetting',
    method: 'post',
    data: data
  })
}

// 修改用户显示设置
export function updateShowSetting(data) {
  return request({
    url: '/ci/showSetting',
    method: 'put',
    data: data
  })
}

// 删除用户显示设置
export function delShowSetting(ownUserId) {
  return request({
    url: '/ci/showSetting/' + ownUserId,
    method: 'delete'
  })
}

// 导出用户显示设置
export function exportShowSetting(query) {
  return request({
    url: '/ci/showSetting/export',
    method: 'get',
    params: query
  })
}