import request from '@/utils/request'
import Qs from 'qs'

const fileApi = {
    list: params => request({
        url: `system/file?${Qs.stringify(params)}`,
        method: 'get',
        data: {notLoading: true}
    }),
    createFolder: data => request({
        url: `system/file`,
        method: 'post',
        data
    }),
    deleteResource: data => request({
        url: `system/file`,
        method: 'delete',
        data
    }),
}

export default fileApi
