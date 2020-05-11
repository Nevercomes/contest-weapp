import http from '@/utils/request.js'

// 查询热门搜索,type表示竞赛 动态类别,没有这一参数则为全部
export function getPopularSearch(type) {
	return http.request({
		url: type ? '/search/popular' : '/search/popular/' + type,
		method: 'GET',
		params: {
			pgaeNum: 1,
			pageSize: 10
		}
	})
}

