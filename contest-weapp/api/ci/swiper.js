import http from '@/utils/request'

// 查询首页推荐列表
export function listSwiper(query) {
  return http.request({
    url: '/ci/swiper/list',
    method: 'get',
    params: query
  })
}

// 查询首页推荐详细
export function getSwiper(id) {
  return http.request({
    url: '/ci/swiper/' + id,
    method: 'get'
  })
}

export function getSwiperOptions(label) {
    return http.request({
        url: '/ci/swiper/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增首页推荐
export function addSwiper(data) {
  return http.request({
    url: '/ci/swiper',
    method: 'post',
    data: data
  })
}

// 修改首页推荐
export function updateSwiper(data) {
  return http.request({
    url: '/ci/swiper',
    method: 'put',
    data: data
  })
}

// 删除首页推荐
export function delSwiper(id) {
  return http.request({
    url: '/ci/swiper/' + id,
    method: 'delete'
  })
}

// 导出首页推荐
export function exportSwiper(query) {
  return http.request({
    url: '/ci/swiper/export',
    method: 'get',
    params: query
  })
}