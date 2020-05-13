import { asyncRoutes, constantRoutes } from '@/router'
import Layout from '@/layout/index'

/**
 * 前端的路由-和后端返回的进行判断，key规则：router的name
 * 具体可以查看 filterAsyncRoutes 里面的获取方法
 */
const componentList = {
  'dashboard': () => import('@/views/dashboard/index'),
  'systemUser': () => import('@/views/system/user/index'),
  'systemRole': () => import('@/views/system/role/index'),
  'systemMenu': () => import('@/views/system/menu/index'),
  'tenant': () => import('@/views/tenant/index')
}
const dynamicRoutes = [
  {
    path: '/',
    component: Layout,
    redirect: 'dashboard',
    meta: { icon: 'dashboard', title: '首页' },
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'dashboard',
        meta: { title: '首页', icon: 'dashboard', affix: true }
      }
    ]
  }]

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

const state = {
  routes: [],
  addRoutes: [],
  topRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  },
  SET_TOP_ROUTES: (state, topRoutes) => {
    state.topRoutes = topRoutes
  }
}

const actions = {
  generateRoutes({ commit }, treePermission) {
    treePermission = treePermission || []
    return new Promise(resolve => {
      //根据后端权限生成路由信息
      let accessedRoutes
      accessedRoutes = filterAsyncRoutes(treePermission)
      accessedRoutes = accessedRoutes.concat(asyncRoutes)
      //vuex里面存储动态的路由，以便于菜单显示
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  },
  generateTopRoutes({ commit }, treePermission) {
    treePermission = treePermission || []
    return new Promise(resolve => {
      commit('SET_TOP_ROUTES', treePermission)
      resolve(treePermission)
    })
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 */
export function filterAsyncRoutes(routes) {
  const res = []
  routes.forEach(item => {
    let component = Layout
    if (item.pId !== 0) {
      component = componentList[item.name]
    }
    let tempRoute = {
      path: item.path,
      component: component,
      meta: { title: item.title, icon: item.icon }
    }
    if (item.pId === 0) {
      tempRoute.topId = item.topId
    }
    if (item.name === 'dashboard' && item.pId !== 0) {
      tempRoute.meta.affix = true
    }
    if (component === Layout && item.children[0]) {
      tempRoute.redirect = item.children[0].path
    } else {
      if (item.name) {
        tempRoute.name = item.name
      }
    }
    if (item.children && item.children.length > 0) {
      tempRoute.children = filterAsyncRoutes(item.children)
    }
    res.push(tempRoute)
  })
  return res
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
