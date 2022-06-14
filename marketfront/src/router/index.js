import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
// <<<<<<< main
// <<<<<<< main
// import MemberPage from '@/views/member/MemberPage';

// import ProductListPage from '@/views/productBoard/ProductListPage'
// import ProductRegisterPage from '@/views/productBoard/ProductRegisterPage'
// import ProductReadPage from '@/views/productBoard/ProductReadPage'

// =======
// import MemberPage from "@/views/member/MemberPage";
// import LoginPage from "@/views/member/LoginPage";
// import registerMemberPage from "@/views/member/RegisterMemberPage";
// import ordinaryRegisterPage from "@/views/member/OrdinaryRegisterPage";
// import IdSearchPage from "@/views/member/search/IdSearchPage";
// import PwSearchPage from "@/views/member/search/PwSearchPage";
// import ResultPage from "@/views/member/search/ResultPage";
// import ChangePwPage from "@/views/member/search/ChangePwPage";
// import myPage from "@/views/myPage/MyPage";
// >>>>>>> main
// =======
// import MemberPage from "@/views/member/MemberPage";

// import ChattingListPage from '@/views/chatting/ChattingListPage'
// import ChattingPage from '@/views/chatting/ChattingPage'
// import HomeTestPage from '@/views/chatting/HomeTestPage'
// import BoardTestPage from '@/views/chatting/BoardTestPage'
// import PythonTest from '@/views/chatting/PythonTest'
// >>>>>>> main

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
// <<<<<<< main
//     path: '/productList',
//     name: 'ProductListPage',
//     component: ProductListPage
//   },
//   {
//     path: '/productRegister',
//     name: 'ProductRegisterPage',
//     component: ProductRegisterPage
//   },
//   {
// <<<<<<< main
//     path: '/productRead/:productNo',
//     name: 'ProductReadPage',
//     component: ProductReadPage
//   },
// =======
//     path: '/ordinaryRegister',
//     name: 'ordinaryRegisterPage',
//     component: ordinaryRegisterPage
//   },
//   {
//     path: '/searchId',
//     name: 'IdSearchPage',
//     component: IdSearchPage
//   },
//   {
//     path: '/searchPw',
//     name: 'PwSearchPage',
//     component: PwSearchPage
//   },
//   {
//     path: '/search/result',
//     name: 'ResultPage',
//     component: ResultPage,
//     props: true
//   },
//   {
//     path: '/search/changePw',
//     name: 'ChangePwPage',
//     component: ChangePwPage,
//     props: true
//   },
//   {
//     path: '/myPage',
//     name: 'myPage',
//     component: myPage
//   }
// >>>>>>> main
// =======
//     path: '/chat',
//     name: 'ChattingListPage',
//     component: ChattingListPage,
//     props: {
//       default: true
//     }
//   },
//   {
//     path: '/chat/room/:roomNo',
//     name: 'ChattingPage',
//     component: ChattingPage,
//     props: {
//       default: true
//     }
//   },
//   {
//     path: '/homeTest',
//     name: 'HomeTestPage',
//     component: HomeTestPage,
//     props: {
//       default: true
//     }
//   },
//   {
//     path: '/boardTest',
//     name: 'BoardTestPage',
//     component: BoardTestPage,
//     props: {
//       default: true
//     }
//   },
//   {
//     path: '/python',
//     name: 'PythonTest',
//     component: PythonTest
//   },
// >>>>>>> main
]

const router = new VueRouter({
  mode: 'history',
  //base: process.env.BASE_URL,
  routes
})

// <<<<<<< main
// export default router
// =======


// export default router
// >>>>>>> main
