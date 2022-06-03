import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
import MemberPage from "@/views/member/MemberPage";
import LoginPage from "@/views/member/LoginPage";
import registerMemberPage from "@/views/member/RegisterMemberPage";
import ordinaryRegisterPage from "@/views/member/OrdinaryRegisterPage";
import IdSearchPage from "@/views/member/search/IdSearchPage";
import PwSearchPage from "@/views/member/search/PwSearchPage";
import ResultPage from "@/views/member/search/ResultPage";
import ChangePwPage from "@/views/member/search/ChangePwPage";

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
    path: '/login',
    name: 'LoginPage',
    component: LoginPage
  },
  {
    path: '/registerMember',
    name: 'registerMemberPage',
    component: registerMemberPage
  },
  {
    path: '/ordinaryRegister',
    name: 'ordinaryRegisterPage',
    component: ordinaryRegisterPage
  },
  {
    path: '/searchId',
    name: 'IdSearchPage',
    component: IdSearchPage
  },
  {
    path: '/searchPw',
    name: 'PwSearchPage',
    component: PwSearchPage
  },
  {
    path: '/search/result',
    name: 'ResultPage',
    component: ResultPage,
    props: true
  },
  {
    path: '/search/changePw',
    name: 'ChangePwPage',
    component: ChangePwPage,
    props: true
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
