import http from '@/utils/request.js'


export function addAppendix() {

}

export function uploadAppendix(path) {
	return http.upload('/system/ossRecord/upload/appendix', {
		'filePath': path,
		'name': 'file'
	})
}
