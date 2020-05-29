const TokenKey = 'Zhulu-Token'
const tryLogin = 'try-login-times'

export function getToken() {
	return uni.getStorageSync(TokenKey)
}

export function setToken(token) {
	return uni.setStorageSync(TokenKey, token)
}

export function removeToken() {
	return uni.removeStorageSync(TokenKey)
}

export function getTryLogin() {
	return uni.getStorageSync(tryLogin)
}

export function addTryLogin() {
	let loginTimes = uni.getStorageSync(tryLogin)
	if (loginTimes) {
		uni.setStorageSync(Number(tryLogin) + 1)
	} else {
		uni.setStorageSync(tryLogin, 1)
	}
}

export function clearTryLogin() {
	return uni.removeStorageSync(tryLogin)
}
