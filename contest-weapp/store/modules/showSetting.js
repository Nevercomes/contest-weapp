import {
	getShowSetting,
	updateShowSetting
} from '@/api/ci/showSetting.js'

const storeKey = 'user_show_setting'

const showSetting = {
	state: {
		// 显示设置
		show: uni.getStorageSync(storeKey)
	},
	mutations: {
		SET_SHOWSETTING: (state, showSetting) => {
			state.showSetting = showSetting
		}
	},
	actions: {
		// 搜索
		SetShowSetting({
			commit,
			state
		}, showSetting) {
			return new Promise((resolve, reject) => {
				updateShowSetting(showSetting).then(res => {
					if(res.code === 200) {
						commit('SET_SHOWSETTING', showSetting)
						uni.setStorageSync(storeKey, showSetting)
						resolve(res)
					} else {
						reject(res)
					}
				})
			})
		},
		getShowSetting({
			commit
		}) {
			return new Promise((resolve, reject) => {
				getShowSetting().then(res => {
					commit('SET_SHOWSETTING', showSetting)
					uni.setStorageSync(storeKey, showSetting)
					resolve(res)
				})
			})
		}
	}
}


export default showSetting
