import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
import MemberPage from "@/views/member/MemberPage";

import ChattingListPage from '@/views/chatting/ChattingListPage'
import ChattingPage from '@/views/chatting/ChattingPage'
import HomeTestPage from '@/views/chatting/HomeTestPage'
import BoardTestPage from '@/views/chatting/BoardTestPage'
import PythonTest from '@/views/chatting/PythonTest'

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
    path: '/chat',
    name: 'ChattingListPage',
    component: ChattingListPage,
    props: {
      default: true
    }
  },
  {
    path: '/chat/room/:roomNo',
    name: 'ChattingPage',
    component: ChattingPage,
    props: {
      default: true
    }
  },
  {
    path: '/homeTest',
    name: 'HomeTestPage',
    component: HomeTestPage,
    props: {
      default: true
    }
  },
  {
    path: '/boardTest',
    name: 'BoardTestPage',
    component: BoardTestPage,
    props: {
      default: true
    }
  },
  {
    path: '/python',
    name: 'PythonTest',
    component: PythonTest
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
