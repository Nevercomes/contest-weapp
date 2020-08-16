import http from '@/utils/request.js'

// 更新校内浏览模式状态
export function updateOnlySchool(onlySchool) {
	return http.request({
		url: '/sys/userSetting/onlySchool',
		method: 'PUT',
		params: {
			onlySchool: onlySchool
		}
	})
}

// 更新消息提醒状态
export function updateMsgNotice(msgNotice) {
	return http.request({
		url: '/sys/userSetting/msgNotice',
		method: 'PUT',
		params: {
			msgNotice: msgNotice
		}
	})
}