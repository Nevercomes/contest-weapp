import {
	login,
	getInfo,
	logout
} from '@/api/login'
import {
	getToken,
	setToken,
	removeToken
} from '@/utils/auth'
import config from '../../config/index.js'
import http from '@/utils/request.js'

const user = {
	state: {
		token: getToken(),
		userId: '',
		name: '',
		nickName: '',
		avatar: '',
		roles: [],
		permissions: []
	},

	mutations: {
		SET_TOKEN: (state, token) => {
			state.token = token
		},
		SET_USERID: (state, userId) => {
			state.userId = userId
		},
		SET_NAME: (state, name) => {
			state.name = name
		},
		SET_NICKNAME: (state, nickName) => {
			state.nickName = nickName
		},
		SET_AVATAR: (state, avatar) => {
			state.avatar = avatar
		},
		SET_ROLES: (state, roles) => {
			state.roles = roles
		},
		SET_PERMISSIONS: (state, permissions) => {
			state.permissions = permissions
		}
	},

	actions: {
		// 登录
		Login({
			commit
		}) {
			return new Promise((resolve, reject) => {
				uni.login({
					provider: 'weixin',
					success(wxRes) {
						// uni.request({
						// 	url: config.REQ_API + '/wxLogin',
						// 	method: 'GET',
						// 	data: {
						// 		code: wxRes.code
						// 	},
						// 	success: function(res) {
						// 		setToken(res.data.token)
						// 		commit('SET_TOKEN', res.data.token)
						// 		resolve(res.data)
						// 	},
						// 	fail: function() {
						// 		reject(error)
						// 	}
						// })
						// 登录前清空已有的token
						removeToken()
						commit('SET_TOKEN', '')
						login(wxRes.code).then(res => {
							setToken(res.data.token)
							commit('SET_TOKEN', res.data.token)
							resolve(res)
						}).catch(error => {
							reject(error)
						})
					},
					fail(wxError) {
						reject(wxError)
					}
				})
			})
		},

		// 获取用户信息
		GetInfo({
			commit
		}) {
			return new Promise((resolve, reject) => {
				getInfo().then(res => {
					const user = res.user
					const avatar = user.avatar
					if (res.roles && res.roles.length > 0) { // 验证返回的roles是否是一个非空数组
						commit('SET_ROLES', res.roles)
						commit('SET_PERMISSIONS', res.permissions)
					} else {
						commit('SET_ROLES', ['ROLE_DEFAULT'])
					}
					commit('SET_NAME', user.username)
					commit('SET_USERID', user.userId)
					commit('SET_NICKNAME', user.nickName)
					commit('SET_AVATAR', avatar)
					resolve(res)
				}).catch(error => {
					reject(error)
				})
			})
		},

		// 退出系统
		LogOut({
			commit,
			state
		}) {
			return new Promise((resolve, reject) => {
				logout().then(() => {
					commit('SET_TOKEN', '')
					commit('SET_ROLES', [])
					commit('SET_PERMISSIONS', [])
					removeToken()
					resolve()
				}).catch(error => {
					reject(error)
				})
			})
		},

		// 前端 登出
		FedLogOut({
			commit
		}) {
			return new Promise(resolve => {
				commit('SET_TOKEN', '')
				removeToken()
				resolve()
			})
		}
	}
}

export default user
