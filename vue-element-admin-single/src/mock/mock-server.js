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
    userName: 'admin',
    nickName: 'admin',
    avatar: 'https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1246207470,1516116328&fm=111&gp=0.jpg',
    roles: ['aaa'],
    permission: [],
    topMenu: [{
      id: 1,
      pId: 0,
      title: '业务系统',
      path: '/top1',
      icon: 'biz',
      mType: 1,
      children: []
    }, {
      id: 2,
      pId: 0,
      title: '用户中心',
      path: '/top2',
      icon: 'user-manage-all',
      mType: 1,
      children: []
    }, {
      id: 3,
      pId: 0,
      title: '系统配置',
      path: '/top3',
      icon: 'system-config',
      mType: 1,
      children: []
    }, {
      id: 3,
      pId: 0,
      title: '顶部一级',
      path: '/top4',
      icon: 'system-config',
      mType: 1,
      children: [{
        id: 5,
        pId: 3,
        title: '测试二级',
        name: 'topTenant',
        path: 'menu',
        icon: 'menu',
        mType: 1,
        children: []
      }]
    }],
    menu: [{
      id: 1,
      pId: 0,
      topId: 1,
      title: '首页',
      path: '/index',
      icon: 'dashboard',
      mType: 0,
      children: [{
        id: 11,
        pId: 1,
        title: '控制台',
        name: 'dashboard',
        path: 'console',
        icon: 'dashboard',
        mType: 0,
        children: []
      }]
    }, {
      id: 2,
      pId: 0,
      topId: 2,
      title: '系统用户',
      path: '/system',
      icon: 'system-user',
      mType: 0,
      children: [{
        id: 21,
        pId: 2,
        title: '系统用户',
        name: 'systemUser',
        path: 'user',
        icon: 'system-user',
        mType: 0,
        children: []
      }]
    }, {
      id: 3,
      pId: 0,
      topId: 3,
      title: '权限中心',
      path: '/permission',
      icon: 'permission',
      mType: 0,
      children: [{
        id: 31,
        pId: 3,
        title: '角色管理',
        name: 'systemRole',
        path: 'role',
        icon: 'role',
        mType: 0,
        children: []
      }, {
        id: 32,
        pId: 3,
        title: '菜单管理',
        name: 'systemMenu',
        path: 'menu',
        icon: 'menu',
        mType: 0,
        children: []
      }]
    }, {
      id: 4,
      pId: 0,
      topId: 2,
      title: '租户管理',
      path: '/tenant',
      icon: 'tenant',
      mType: 0,
      children: [{
        id: 41,
        pId: 4,
        title: '租户管理',
        name: 'tenant',
        path: 'tenant',
        icon: 'tenant',
        mType: 0,
        children: []
      }]
    }]
  }
}
const systemUserList = {
  status: 200,
  message: 'ok',
  data: [
    {
      id: 1,
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
      id: 2,
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
    }
  ]
}

const systemTenantList = {
  status: 200,
  message: 'ok',
  data: [
    {
      id: 1,
      name: '租户A',
      appId: '213u3i2132134',
      appSecret: '8d7b11ec9be0e59a36b52f32366c09cb',
      qps: 12,
      requested: 465,
      dayLimit: 10000,
      totalRequested: 465,
      totalLimit: 0,
      isEnable: true
    },
    {
      id: 2,
      name: '租户B',
      appId: '5464237234',
      appSecret: '449441eb5e72dca9c42a12f3924ea3a2',
      qps: 20,
      requested: 3432,
      dayLimit: 10000,
      totalRequested: 3432,
      totalLimit: 0,
      isEnable: true
    }
  ]
}

const systemMenuList = {
  status: 200,
  message: 'ok',
  data: [
    {
      id: 1,
      pId: 0,
      title: '首页',
      path: '/index',
      icon: 'dashboard',
      type: 1,
      mType: 1,
      sort: 0,
      permission: 'index',
      edit: false,
      children: [{
        id: 11,
        pId: 1,
        title: '控制台',
        name: 'console',
        path: 'console',
        icon: 'dashboard',
        type: 1,
        mType: 1,
        permission: 'dashboard',
        edit: false,
        sort: 0,
        children: []
      }]
    }, {
      id: 2,
      pId: 0,
      title: '系统用户',
      permission: 'sys:user:index',
      path: '/system',
      icon: 'system-user',
      type: 1,
      mType: 1,
      sort: 0,
      edit: false,
      children: [{
        id: 21,
        pId: 2,
        title: '用户管理',
        permission: 'sys:user:list',
        path: 'user',
        icon: 'system-user',
        name: 'systemUser',
        type: 1,
        mType: 1,
        edit: false,
        sort: 0,
        children: []
      }]
    }, {
      id: 3,
      pId: 0,
      title: '权限中心',
      path: '/permission',
      icon: 'permission',
      permission: 'sys:permission:index',
      type: 1,
      mType: 1,
      sort: 0,
      edit: false,
      children: [{
        id: 31,
        pId: 3,
        title: '角色管理',
        permission: 'sys:role',
        name: 'systemRole',
        path: 'role',
        icon: 'role',
        type: 1,
        mType: 1,
        edit: false,
        sort: 0,
        children: []
      }, {
        id: 32,
        pId: 3,
        title: '菜单管理',
        permission: 'sys:menu',
        name: 'systemMenu',
        path: 'menu',
        icon: 'menu',
        type: 1,
        mType: 1,
        edit: false,
        sort: 0,
        children: []
      }]
    }, {
      id: 4,
      pId: 0,
      title: '租户管理',
      permission: 'sys:tenant:index',
      path: '/tenant',
      icon: 'tenant',
      type: 1,
      mType: 1,
      sort: 0,
      edit: false,
      children: [{
        id: 41,
        pId: 4,
        title: '租户管理',
        permission: 'sys:tenant:list',
        name: 'tenant',
        path: 'tenant',
        icon: 'tenant',
        type: 1,
        mType: 1,
        edit: false,
        sort: 0,
        children: []
      }]
    }
  ]
}
//Mock.mock('/dev-api/auth/login', 'post', login)
//Mock.mock('/dev-api/auth/logout', 'post', response)
Mock.mock('/dev-api/user/userInfo', 'get', userInfo)

Mock.mock(RegExp('/dev-api/system/user.*'), 'get', systemUserList)

Mock.mock(RegExp('/dev-api/system/tenant.*'), 'get', systemTenantList)

Mock.mock(RegExp('/dev-api/system/menu.*'), 'get', systemMenuList)

export default Mock
