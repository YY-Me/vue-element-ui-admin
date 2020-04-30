import Mock from 'mockjs'

const response = {
  status: 200,
  message: '操作成功',
  data: {},
  count: 0
}
const login = {
  status: 200,
  message: 'ok',
  data: {
    access_token: 'dswe58ds7-jskw3gm1-qpidg54f-stkg51qw',
    refresh_token: 'dswe58ds7-jskw3gm1-qpidg54f-stkg51qw',
    expire_time: 7200
  }
}
const userInfo = {
  status: 200,
  message: 'ok',
  data: {
    name: 'admin',
    avatar: 'https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1246207470,1516116328&fm=111&gp=0.jpg',
    roles: ['admin']
  }
}
const systemUserList = {
  status: 200,
  message: 'ok',
  data: [
    {
      userName: 'wangwu',
      nickName: '王小虎',
      phone: '15565236541',
      roles: [{
        id: 1,
        name: '管理員'
      }],
      isEnable: true,
      createTime: '2020-11-23',
      updateTime: '2020-11-23'
    },
    {
      userName: 'zhangsan',
      nickName: '张三',
      phone: '70983928',
      roles: [{
        id: 1,
        name: '管理員'
      }],
      isEnable: true,
      createTime: '2019-12-11',
      updateTime: '2020-12-11'
    }, {
      userName: 'lisi',
      nickName: '里斯',
      phone: '18895623548',
      roles: [{
        id: 1,
        name: '管理員'
      }],
      isEnable: true,
      createTime: '2019-05-02',
      updateTime: '2019-05-02'
    }
  ]
}
Mock.mock('/dev-api/auth/login', 'post', login)
Mock.mock('/dev-api/user/userInfo', 'get', userInfo)
Mock.mock('/dev-api/auth/logout', 'post', response)

Mock.mock(RegExp('/dev-api/system/user.*'), 'get', systemUserList)

export default Mock
