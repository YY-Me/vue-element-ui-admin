import {asyncRoutes, constantRoutes} from '@/router'
import Layout from '@/layout/index'
import path from "path";

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
const tempRoutesExample = [
    {
        path: '/example',
        component: Layout,
        redirect: '/example/test1',
        meta: {icon: 'dashboard', title: 'example'},
        children: [
            {
                path: 'test1',
                component: () => import('@/views/test/test1'),
                name: 'dashboard',
                meta: {title: 'example', icon: 'dashboard'}
            }
        ]
    }
]
//开发中可以临时将菜单写在这里，看tempRoutesExample，之后开发完后再把它们写入数据库
const tempRoutes = [{
    path: '/example',
    component: Layout,
    redirect: '/example/test1',
    meta: {icon: 'dashboard', title: 'example'},
    children: [
        {
            path: 'test1',
            component: () => import('@/views/test/test1'),
            name: 'dashboard',
            meta: {title: 'example', icon: 'dashboard'}
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
    addRoutes: []
}

const mutations = {
    SET_ROUTES: (state, routes) => {
        state.addRoutes = routes
        state.routes = constantRoutes.concat(routes)
    }
}

const actions = {
    generateRoutes({commit}, treePermission) {
        treePermission = treePermission || []
        return new Promise(resolve => {
            // 根据后端权限生成路由信息
            let accessedRoutes
            accessedRoutes = filterAsyncRoutes(treePermission)
            accessedRoutes = accessedRoutes.concat(tempRoutes)
            accessedRoutes = accessedRoutes.concat(asyncRoutes)
            // vuex里面存储动态的路由，以便于菜单显示
            commit('SET_ROUTES', accessedRoutes)
            resolve(accessedRoutes)
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
        if (item.type === 2) {
            //2表示是按钮
            return
        }
        let component = Layout
        if (item.pId !== 0) {
            component = componentList[item.name]
        }
        let tempRoute = {
            path: item.path,
            component: component,
            meta: {title: item.title, icon: item.icon}
        }
        if (item.pId === 0) {
            tempRoute.tId = item.tId
        }
        if (item.name === 'dashboard' && item.pId !== 0) {
            tempRoute.meta.affix = true
        }
        if (component === Layout && item.children && item.children.length > 0 && item.children[0]) {
            tempRoute.redirect = path.resolve(item.path, item.children[0].path)
        } else {
            if (item.name) {
                tempRoute.name = item.name
            }
        }
        if (item.children && item.children.length > 0) {
            let tempChild = filterAsyncRoutes(item.children)
            if (tempChild.length > 0) {
                tempRoute.children = tempChild
            }
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
