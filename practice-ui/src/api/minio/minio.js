import request from '@/utils/request'

// 查询minio信息存储路径列表
export function listMinio(query) {
  return request({
    url: '/minio/minio/list',
    method: 'get',
    params: query
  })
}

// 查询minio信息存储路径详细
export function getMinio(id) {
  return request({
    url: '/minio/minio/' + id,
    method: 'get'
  })
}

// 新增minio信息存储路径
export function addMinio(data) {
  return request({
    url: '/minio/minio',
    method: 'post',
    data: data
  })
}

// 修改minio信息存储路径
export function updateMinio(data) {
  return request({
    url: '/minio/minio',
    method: 'put',
    data: data
  })
}

// 删除minio信息存储路径
export function delMinio(id) {
  return request({
    url: '/minio/minio/' + id,
    method: 'delete'
  })
}
