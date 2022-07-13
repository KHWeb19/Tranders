import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '@/views/HomePage.vue'

import ProductListPage from '@/views/productBoard/ProductListPage'
import ProductRegisterPage from '@/views/productBoard/ProductRegisterPage'
import ProductReadPage from '@/views/productBoard/ProductReadPage'
import ProductModifyPage from '@/views/productBoard/ProductModifyPage'
import ProductSearchListPage from '@/views/productBoard/ProductSearchListPage'
import MyPurchaseHistory from '@/views/myPage/MyPurchaseHistory'
import MyLikesPage from '@/views/myPage/MyLikesPage'

import CommunityBoardListPage from '@/views/communityBoard/CommunityBoardListPage.vue'
import CommunityBoardRegisterPage from '@/views/communityBoard/CommunityBoardRegisterPage.vue'
import CommunityBoardReadPage from '@/views/communityBoard/CommunityBoardReadPage.vue'
import CommunityBoardModifyPage from '@/views/communityBoard/CommunityBoardModifyPage.vue'
import SearchListPage from '@/views/communityBoard/SearchListPage.vue'

import MemberPage from "@/views/member/MemberPage";
import LoginPage from "@/views/member/LoginPage";
import RegisterMemberPage from "@/views/member/RegisterMemberPage";
import ordinaryRegisterPage from "@/views/member/OrdinaryRegisterPage";
import IdSearchPage from "@/views/member/search/IdSearchPage";
import PwSearchPage from "@/views/member/search/PwSearchPage";
import ResultPage from "@/views/member/search/ResultPage";
import ChangePwPage from "@/views/member/search/ChangePwPage";

import ChattingListPage from '@/views/chatting/ChattingListPage'
import ChattingReadView from '@/views/chatting/ChattingReadView'
import BoardTestPage from '@/views/chatting/BoardTestPage'
import PythonTest from '@/views/chatting/PythonTest'
import ReviewRegisterPage from '@/views/profile/ReviewRegisterPage'
import ReviewListPage from '@/views/profile/ReviewListPage'
import MannerReadPage from '@/views/profile/MannerReadPage'
import ProfileBasicPage from '@/views/profile/ProfileBasicPage'
import MyBuyHistoryList from '@/views/myPage/MyBuyHistoryList'
import MyFollowBoardList from '@/views/myPage/MyFollowBoardList'

import MyPageProfile from "@/views/myPage/MyPageProfile";
import MyInfoPage from "@/views/myPage/MyInfoPage";
import BossRegisterPage from "@/views/boss/BossRegisterPage";
import BossPage from "@/views/boss/BossPage";
import MyVillageProof from "@/views/myPage/MyVillageProof";
import OauthPage from "@/views/Util/OauthPage";
import SnsRegisterPage from "@/views/member/SnsRegisterPage";
import BossMarketInfo from "@/views/boss/BossMarketInfo";
import BossMarketInfoModify from "@/views/boss/BossMarketInfoModify";
import MyVillageSetting from "@/views/myPage/MyVillageSetting";
import NearPage from "@/views/near/NearPage";
import BossReadPage from "@/views/boss/client/BossReadPage";
import NearReadPage from "@/views/near/NearReadPage";
import ManagerPage from "@/views/Manger/ManagerPage";
import ReportManagementPage from "@/views/Manger/ReportManagementPage";
import ManagerMemberPage from "@/views/Manger/ManagerMemberPage";
import ReportManagementReadPage from "@/views/Manger/ReportManagementReadPage";
// import SearchKeyWordListPage from "@/views/search/SearchKeyWordListPage";
import SearchKeyWordListPage from "@/views/search/SearchKeyWordListPage";
import MyCouponPage from "@/views/myPage/MyCouponPage";

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
    path: '/register',
    name: 'RegisterMemberPage',
    component: RegisterMemberPage
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
    components: {
      default: ProductReadPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/myPurchase',
    name: 'MyPurchaseHistory',
    component: MyPurchaseHistory
  },
  {
    path: '/myBuy',
    name: 'MyBuyHistoryList',
    component: MyBuyHistoryList
  },
  {
    path: '/myFollowBoardList',
    name: 'MyFollowBoardList',
    component: MyFollowBoardList
  },
  {
    path: '/myLikes',
    name: 'MyLikesPage',
    component: MyLikesPage
  },
  {
    path: '/productModify/:productNo',
    name: 'ProductModifyPage',
    components: {
      default: ProductModifyPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/product/searchList',
    name: 'ProductSearchListPage',
    components: {
      default: ProductSearchListPage
    },
    props: {
      default: true
    }
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
  },
  {
    path: '/chatting',
    name: 'ChattingListPage',
    components: {
      default: ChattingListPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/chatting/:roomNo',
    name: 'ChattingReadView',
    components: {
      default: ChattingReadView
    },
    props: {
      default: true
    }
  },
  {
    path: '/reviewRegister/:memberId',
    name: 'ReviewRegisterPage',
    components: {
      default: ReviewRegisterPage,
    },
    props: {
      default: true
    }
  },
  {
    path: '/reviewList/:memberId',
    name: 'ReviewListPage',
    components: {
      default: ReviewListPage,
    },
    props: {
      default: true
    }
  },
  {
    path: '/mannerRead/:memberId',
    name: 'MannerReadPage',
    components: {
      default: MannerReadPage,
    },
    props: {
      default: true
    }
  },
  {
    path: '/profileBasic/:memberId',
    name: 'ProfileBasicPage',
    components: {
      default: ProfileBasicPage,
    },
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
  {
    path: '/myPageInfo',
    name: 'MyInfoPage',
    component: MyInfoPage,
    props: true
  },
  {
    path: '/myPage/profile',
    name: 'MyPageProfile',
    component: MyPageProfile
  },
  {
    path: '/bossRegister',
    name: 'BossRegisterPage',
    component: BossRegisterPage
  },
  {
    path: '/bossPage',
    name: 'BossPage',
    component: BossPage
  },
  {
    path: '/villageProof',
    name: 'MyVillageProof',
    components: {
      default: MyVillageProof,
    },
    props: {
      default: true
    },
  },
  {
    path: '/myVillageSetting',
    name: 'MyVillageSetting',
    component: MyVillageSetting,
  },
  {
    path: '/Tranders/CommunityList',
    name: 'CommunityBoardListPage',
    component: CommunityBoardListPage
  },
  {
    path: '/Tranders/CommunityRegister',
    name: 'CommunityBoardRegisterPage',
    component: CommunityBoardRegisterPage
  },
  {
    path: '/Tranders/CommunityRead/:boardNo',
    name: 'CommunityBoardReadPage',
    components: {
      default: CommunityBoardReadPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/Tranders/CommunityModify/:boardNo',
    name: 'CommunityBoardModifyPage',
    components: {
      default: CommunityBoardModifyPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/Tranders/CommunityList/SearchList',
    name: 'SearchListPage',
    components: {
      default: SearchListPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/snsRegister',
    name: 'SnsRegisterPage',
    component: SnsRegisterPage
  },
  {
    path: '/oauth2/*',
    component: OauthPage
  },
  {
    path: '/bossMarket/info',
    name: 'BossMarketInfo',
    component: BossMarketInfo
  },
  {
    path: '/bossMarket/infoModify',
    name: 'BossMarketInfoModify',
    components: {
      default: BossMarketInfoModify
    },
    props: {
      default: true
    }
  },
  {
    path: '/bossRead',
    name: 'BossReadPage',
    components: {
      default: BossReadPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/near',
    name: 'NearPage',
    component: NearPage
  },
  {
    path: '/nearRead',
    name: 'NearReadPage',
    components: {
      default: NearReadPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/manger',
    name: 'ManagerPage',
    component: ManagerPage
  },
  {
    path: '/repoManagement',
    name: 'ReportManagementPage',
    component: ReportManagementPage
  },
  {
    path: '/repoManagementRead',
    name: 'ReportManagementReadPage',
    components: {
      default: ReportManagementReadPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/mangerMember',
    name: 'ManagerMemberPage',
    component: ManagerMemberPage
  },
  // {
  //   path: '/search',
  //   name: 'SearchKeyWordListPage',
  //   components: {
  //     default: SearchKeyWordListPage
  //   },
  //   props: {
  //     default: true
  //   }
  // }
  {
    path: '/search',
    name: 'SearchKeyWordListPage',
    components: {
      default: SearchKeyWordListPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/myCoupon',
    name: "MyCouponPage",
    component: MyCouponPage
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


export default router
