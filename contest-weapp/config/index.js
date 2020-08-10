const param = {
	// 开发api地址
	DEV_API: 'http://localhost:8090',

	// 测试环境api地址
	TEST_API_α: 'http://rowingtest.nevercome.net',
	
	// 测试环境api地址
	TEST_API_β: 'https://rowingtest.nevercome.net',

	// 生产环境api地址
	PROD_API: 'https://rowing.nevercome.net'
}

const config = {
	// 当前请求的api
	REQ_API: param.DEV_API
}

export default config
