import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
import MemberPage from '@/views/member/MemberPage';

import ProductListPage from '@/views/productBoard/ProductListPage'
import ProductRegisterPage from '@/views/productBoard/ProductRegisterPage'
import ProductReadPage from '@/views/productBoard/ProductReadPage'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'HomePage',
    component: HomePage
  },
  {
    path: '/member',
    name: 'MemberPage',
    component: MemberPage
  },
  {
    path: '/productList',
    name: 'ProductListPage',
    component: ProductListPage
  },
  {
    path: '/productRegister',
    name: 'ProductRegisterPage',
    component: ProductRegisterPage
  },
  {
    path: '/productRead/:productNo',
    name: 'ProductReadPage',
    component: ProductReadPage
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router