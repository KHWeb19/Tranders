import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
import MemberPage from "@/views/member/MemberPage";
import ChattingPage from '@/views/chatting/ChattingPage'

// import LoginPage from "@/views/member/LoginPage";
// import registerMemberPage from "@/views/member/RegisterMemberPage";
// import ordinaryRegisterPage from "@/views/member/OrdinaryRegisterPage";

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
    path: '/chatting',
    name: 'ChattingPage',
    component: ChattingPage,
    props: {
      default: true
    }
  },

  // {
  //   path: '/login',
  //   name: 'LoginPage',
  //   component: LoginPage
  // },
  // {
  //   path: '/registerMember',
  //   name: 'registerMemberPage',
  //   component: registerMemberPage
  // },
  // {
  //   path: '/ordinaryRegister',
  //   name: 'ordinaryRegisterPage',
  //   component: ordinaryRegisterPage
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
