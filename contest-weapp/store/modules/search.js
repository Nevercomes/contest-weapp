import {
	getPopularSearch
} from '@/api/ci/search.js'

const search = {
	state: {
		// 搜索记录
		recordList: uni.getStorageSync('recordList'),
		// 热门搜索
		popularList: []
	},
	mutations: {
		SET_RECORD: (state, recordList) => {
			state.recordList = recordList
		},
		SET_POPULAR: (state, popularList) => {
			state.popularList = popularList
		}
	},
	actions: {
		// 搜索
		Search({
			commit,
			state
		}, keyword) {
			return new Promise((resolve, reject) => {
				try {
					// 新的搜索关键词
					let list = state.recordList
					const idx = list.indexOf(keyword)
					if (idx != -1) {
						list.splice(idx, 1)
					} else if (list.length >= 10) {
						// 是否达到十个最大长度
						list.splice(9, list.length)
					}
					[keyword].concat(list);
					commit('SET_RECORD', list)
					uni.setStorageSync('recordList', list)
					resolve('success')
				} catch (e) {
					//TODO handle the exception
					reject()
				}
			})
		},
		// 清空历史记录
		ClearSearch({
			commit
		}) {
			return new Promise((resolve, reject) => {
				uni.clearStorageSync('recordList')
				commit('SET_RECORD', [])
				resolve('success')
			})
		},
		// 更新热门搜索
		UpdatePopular({
			commit
		}) {
			return new Promise((resolve, reject) => {
				getPopularSearch().then(res => {
					commit('SET_POPULAR', res.rows)
					resolve('success')
				}).catch(() => {
					reject()
				})
			})
		}
	}
}


export default search
