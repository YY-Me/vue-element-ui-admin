import request from '@/utils/request'
import Qs from 'qs'

const systemUserApi = {
  list: params => request({
    url: `system/user?${Qs.stringify(params)}`,
    method: 'get',
    data: { notLoading: true }
  }),
  update: data => request({
    url: `system/user`,
    method: 'put',
    data
  }),
  save: data => request({
    url: `system/user`,
    method: 'post',
    data
  }),
  remove: id => request({
    url: `system/user/${id}`,
    method: 'delete'
  }),
  status: status => request({
    url: `system/user/status/${status}`,
    method: 'put'
  })
}

export default systemUserApi
