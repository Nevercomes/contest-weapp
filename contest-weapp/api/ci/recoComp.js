import http from '@/utils/request'

// 查询竞赛推荐列表
export function listRecoComp(query) {
  return http.request({
    url: '/ci/recoComp/list',
    method: 'get',
    params: query,
	anonymous: true
  })
}

// 查询竞赛推荐详细
export function getRecoComp(id) {
  return http.request({
    url: '/ci/recoComp/' + id,
    method: 'get'
  })
}

export function getRecoCompOptions(label) {
    return http.request({
        url: '/ci/recoComp/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增竞赛推荐
export function addRecoComp(data) {
  return http.request({
    url: '/ci/recoComp',
    method: 'post',
    data: data
  })
}

// 修改竞赛推荐
export function updateRecoComp(data) {
  return http.request({
    url: '/ci/recoComp',
    method: 'put',
    data: data
  })
}

// 删除竞赛推荐
export function delRecoComp(id) {
  return http.request({
    url: '/ci/recoComp/' + id,
    method: 'delete'
  })
}

// 导出竞赛推荐
export function exportRecoComp(query) {
  return http.request({
    url: '/ci/recoComp/export',
    method: 'get',
    params: query
  })
}