const param = {
	// 开发api地址
	DEV_API: 'http://localhost:8090'

	// 生产环境api地址
	PROD_API: 'https://zhulu.nevercome.net'
}

export config = {
	// 当前请求的api
	REQ_API: param.DEV_API
}
