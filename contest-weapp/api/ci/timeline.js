import http from '@/utils/request'

// 查询竞赛资讯列表
export function listTimeline(query) {
  return http.request({
    url: '/ci/timeline/list',
    method: 'get',
    params: query
  })
}

// 查询竞赛资讯详细
export function getTimeline(id) {
  return http.request({
    url: '/ci/timeline/' + id,
    method: 'get'
  })
}

export function getTimelineOptions(label) {
    return http.request({
        url: '/ci/timeline/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增竞赛资讯
export function addTimeline(data) {
  return http.request({
    url: '/ci/timeline',
    method: 'post',
    data: data
  })
}

// 修改竞赛资讯
export function updateTimeline(data) {
  return http.request({
    url: '/ci/timeline',
    method: 'put',
    data: data
  })
}

// 删除竞赛资讯
export function delTimeline(id) {
  return http.request({
    url: '/ci/timeline/' + id,
    method: 'delete'
  })
}

// 导出竞赛资讯
export function exportTimeline(query) {
  return http.request({
    url: '/ci/timeline/export',
    method: 'get',
    params: query
  })
}