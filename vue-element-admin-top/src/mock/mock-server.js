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
      'id': 11,
      'title': '系统配置',
      'name': null,
      'permission': 'top:user:center',
      'type': 1,
      'path': '/top1',
      'icon': 'system-config',
      'sort': 2,
      'edit': false,
      'children': [],
      'pId': 0,
      'tId': 0,
      'mType': 2,
      'tName': null
    }, {
      'id': 12,
      'title': '业务系统',
      'name': null,
      'permission': 'top:biz:center',
      'type': 1,
      'path': '/top2',
      'icon': 'biz',
      'sort': 1,
      'edit': false,
      'children': [],
      'pId': 0,
      'tId': 0,
      'mType': 2,
      'tName': null
    }, {
      'id': 14,
      'title': '顶部一级',
      'name': null,
      'permission': 'top:test:1',
      'type': 1,
      'path': '/top3',
      'icon': 'el-icon-s-flag',
      'sort': 3,
      'edit': false,
      'children': [{
        'id': 15,
        'title': '测试1',
        'name': null,
        'permission': 'top:test:1:1',
        'type': 1,
        'path': '',
        'icon': 'el-icon-s-data',
        'sort': 0,
        'edit': false,
        'children': [],
        'pId': 14,
        'tId': 0,
        'mType': 2,
        'tName': null
      }, {
        'id': 16,
        'title': '测试2',
        'name': null,
        'permission': 'top:test:1:2',
        'type': 1,
        'path': '',
        'icon': 'el-icon-s-comment',
        'sort': 0,
        'edit': false,
        'children': [],
        'pId': 14,
        'tId': 0,
        'mType': 2,
        'tName': null
      }],
      'pId': 0,
      'tId': 0,
      'mType': 2,
      'tName': null
    }],
    menu: [{
      "id": 1,
      "title": "首页",
      "name": null,
      "permission": "index",
      "type": 1,
      "path": "/index",
      "icon": "dashboard",
      "sort": 1,
      "edit": false,
      "children": [{
        "id": 2,
        "title": "控制台",
        "name": "dashboard",
        "permission": "dashboard",
        "type": 1,
        "path": "dashboard",
        "icon": "dashboard",
        "sort": 0,
        "edit": false,
        "children": [],
        "pId": 1,
        "tId": 0,
        "mType": 1,
        "tName": null
      }],
      "pId": 0,
      "tId": 12,
      "mType": 1,
      "tName": null
    }, {
      "id": 3,
      "title": "系统用户",
      "name": null,
      "permission": "sys:user:index",
      "type": 1,
      "path": "/system",
      "icon": "system-user",
      "sort": 3,
      "edit": false,
      "children": [{
        "id": 4,
        "title": "用户管理",
        "name": "systemUser",
        "permission": "sys:user:list",
        "type": 1,
        "path": "user",
        "icon": "system-user",
        "sort": 0,
        "edit": false,
        "children": [],
        "pId": 3,
        "tId": 0,
        "mType": 1,
        "tName": null
      }],
      "pId": 0,
      "tId": 11,
      "mType": 1,
      "tName": null
    }, {
      "id": 5,
      "title": "权限中心",
      "name": null,
      "permission": "sys:permission:index",
      "type": 1,
      "path": "/permission",
      "icon": "permission",
      "sort": 4,
      "edit": false,
      "children": [{
        "id": 6,
        "title": "角色管理",
        "name": "systemRole",
        "permission": "sys:role",
        "type": 1,
        "path": "role",
        "icon": "role",
        "sort": 0,
        "edit": false,
        "children": [],
        "pId": 5,
        "tId": 0,
        "mType": 1,
        "tName": null
      }, {
        "id": 7,
        "title": "菜单管理",
        "name": "systemMenu",
        "permission": "sys:menu",
        "type": 1,
        "path": "menu",
        "icon": "menu",
        "sort": 0,
        "edit": false,
        "children": [],
        "pId": 5,
        "tId": 0,
        "mType": 1,
        "tName": null
      }],
      "pId": 0,
      "tId": 11,
      "mType": 1,
      "tName": null
    }, {
      "id": 8,
      "title": "租户管理",
      "name": null,
      "permission": "sys:tenant:index",
      "type": 1,
      "path": "/tenant",
      "icon": "tenant",
      "sort": 2,
      "edit": false,
      "children": [{
        "id": 9,
        "title": "租户管理",
        "name": "tenant",
        "permission": "sys:tenant:list",
        "type": 1,
        "path": "tenant",
        "icon": "tenant",
        "sort": 0,
        "edit": false,
        "children": [],
        "pId": 8,
        "tId": 0,
        "mType": 1,
        "tName": null
      }],
      "pId": 0,
      "tId": 12,
      "mType": 1,
      "tName": null
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
//Mock.mock('/dev-api/user/userInfo', 'get', userInfo)

//Mock.mock(RegExp('/dev-api/system/user.*'), 'get', systemUserList)

Mock.mock(RegExp('/dev-api/system/tenant.*'), 'get', systemTenantList)

//Mock.mock(RegExp('/dev-api/system/menu.*'), 'get', systemMenuList)

export default Mock
