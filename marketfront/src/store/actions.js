import {
    FETCH_REFRESH_TOKEN,
    FETCH_PRODUCT_BOARD,
    FETCH_PRODUCT_BOARD_LIST,
    FETCH_REGION_PRODUCT_BOARD_LIST,
    FETCH_MY_PRODUCT_BOARD_LIST,

    FETCH_LIKE,
    FETCH_LIKES_LIST,
    FETCH_MY_LIKES_LIST,

    FETCH_CHATROOM,
    FETCH_CHATROOM_LIST,
    FETCH_REVIEW_LIST,
    FETCH_MANNER,
    FETCH_MEMBER_PROFILE,
    FETCH_BOSS_PAGE,
    FETCH_MY_PAGE,
    FETCH_COMMUNITY_BOARD_LIST,
    FETCH_COMMUNITY_BOARD,
    FETCH_COMMUNITY_COMMENTS_LIST,
    FETCH_MY_REGION,
    FETCH_BOSS_BACK_PROFILE,
    FETCH_BOSS_MENU_LIST,
    FETCH_MY_VILLAGE_SETTING,
    FETCH_NEAR_MAP,
    FETCH_CLIENT_BOSS_VIEW,
    FETCH_NEAR_REVIEW,
    FETCH_BOSS_REVIEW,
    FETCH_BOSS_REVIEW_IMAGE,
    FETCH_NEAR_COMMUNITY_MAP,
    FETCH_NEAR_COMM,
    FETCH_NEAR_COMM_PAGE,
    FETCH_REPORT_RECENT,
    FETCH_TOTAL_PAGE,
    FETCH_REPORT_READE,
    FETCH_REPORT_IMAGE_READE,
    FETCH_REPORT_BOARD_READE,
    FETCH_TODAY_REGISTER,
    FETCH_TODAY_REPORT, FETCH_SHOW_COUPON, FETCH_SHOW_MEMBER_COUPON,
} from './mutation-types'

import axios from 'axios'
import cookies from "vue-cookies";
import { API_BASE_URL } from "@/constant/login";

/*const config = {
    headers: {
        'Authorization': 'Bearer '+ cookies.get('access_token'),
        'Accept' : 'application/json',
        'Content-Type': 'application/json'
    }
};*/

export default {
    fetchProductBoard({ commit }, productNo) {
        return axios.get(`http://localhost:7777/product/${productNo}`)
            .then((res) => {
                commit(FETCH_PRODUCT_BOARD, res.data)
            })
    },
    fetchProductBoardList({ commit }) {
        return axios.get('http://localhost:7777/product/list/')
            .then((res) => {
                commit(FETCH_PRODUCT_BOARD_LIST, res.data)
            })
    },
    fetchRegionProductBoardList({ commit }, memberNo) {
        return axios.get(`http://localhost:7777/product/list/region/${memberNo}`)
            .then((res) => {
                commit(FETCH_REGION_PRODUCT_BOARD_LIST, res.data)
            })
    },
    fetchMyProductBoardList({ commit }, memberNo) {
        return axios.get(`http://localhost:7777/product/list/${memberNo}`)
            .then((res) => {
                commit(FETCH_MY_PRODUCT_BOARD_LIST, res.data)
            })
    },

    fetchLike({ commit }, { productNo, memberNo }) {
        return axios.get(`http://localhost:7777/productLike/${productNo}/${memberNo}`)
            .then((res) => {
                commit(FETCH_LIKE, res.data)
            })
    },
    fetchLikesList({ commit }, productNo) {
        return axios.get(`http://localhost:7777/productLike/list/${productNo}`)
            .then((res) => {
                commit(FETCH_LIKES_LIST, res.data)
            })
    },
    fetchMyLikesList({ commit }, memberNo) {
        return axios.get(`http://localhost:7777/productLike/list/my/${memberNo}`)
            .then((res) => {
                commit(FETCH_MY_LIKES_LIST, res.data)
            })
    },

    refreshToken({ commit }) {
        return axios.get('http://localhost:7777/member/refreshToken', {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('refresh_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_REFRESH_TOKEN, res.data)
                console.log('refresh Token')
            })
    },
    fetchChatroom({ commit }, roomNo) {
        return axios.get(`http://localhost:7777/chatting/${roomNo}`)
            .then((res) => {
                commit(FETCH_CHATROOM, res.data)
            })
    },
    fetchChatroomList({ commit }, memberNo) {
        return axios.get(`http://localhost:7777/chatting/list/${memberNo}`)
            .then((res) => {
                commit(FETCH_CHATROOM_LIST, res.data)
            })
    },
    fetchReviewList({ commit }, id) {
        return axios.get(`http://localhost:7777/review/list/${id}`)
            .then((res) => {
                commit(FETCH_REVIEW_LIST, res.data)
            })
    },
    fetchManner({ commit }, id) {
        return axios.get(`http://localhost:7777/manner/${id}`)
            .then((res) => {
                commit(FETCH_MANNER, res.data)
            })
    },
    fetchMemberProfile({ commit }, id) {
        return axios.get(`http://localhost:7777/member/profile/${id}`, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_MEMBER_PROFILE, res.data)
            })
    },
    fetchCommunityBoardList({ commit },memberNo) {
        return axios.get(`http://localhost:7777/communityboard/list/${memberNo}`)
            .then((res) => {
                commit(FETCH_COMMUNITY_BOARD_LIST, res.data)
            })
    },
    fetchCommunityBoard({ commit }, boardNo) {
        return axios.get(`http://localhost:7777/communityboard/${boardNo}`)
            .then((res) => {
                commit(FETCH_COMMUNITY_BOARD, res.data)
            })
    },
    fetchCommunityCommentsList({ commit }, boardNo) {
        return axios.get(`http://localhost:7777/communityboard/${boardNo}/comment/list`)
            .then((res) => {
                commit(FETCH_COMMUNITY_COMMENTS_LIST, res.data)
            })
    },
    fetchBossPage({ commit }, memberNo) {
        return axios.post(API_BASE_URL + '/boss/pageView', { memberNo }, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_BOSS_PAGE, res.data)
            })
    },
    fetchMyPage({ commit }, id) {
        return axios.post(API_BASE_URL + '/member/userInfo', { id }, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_MY_PAGE, res.data)
            })
    },
    async fetchMyRegion({ commit }, id) {
        return await axios.post(API_BASE_URL + '/member/region', { id }, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_MY_REGION, res.data)
            })
    },
    fetchBossBackProfile({ commit }, bossNo) {
        return axios.post(API_BASE_URL + '/boss/getBackProfile', { bossNo }, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => [
                commit(FETCH_BOSS_BACK_PROFILE, res.data)
            ])
    },
    fetchBossMenuList({ commit }, id) {
        return axios.post(`http://localhost:7777/boss/getMenu/${id}`, {}, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_BOSS_MENU_LIST, res.data)
            })
    },
    fetchMyVillageSetting({ commit }, id) {
        return axios.post(`http://localhost:7777/member/getMyVillage/${id}`, {}, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_MY_VILLAGE_SETTING, res.data)
            })
    },
    fetchShowNearMap({ commit }) {
        return axios.post(API_BASE_URL + '/near/showMap', {}, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_NEAR_MAP, res.data)
            })
    },
    fetchClientBossView({ commit }, bossNo) {
        return axios.post(`http://localhost:7777/near/readBossPage/${bossNo}`, {}, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_CLIENT_BOSS_VIEW, res.data)
            })
    },
    fetchNearReview({ commit }) {
        return axios.post(`http://localhost:7777/near/review`, {}, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_NEAR_REVIEW, res.data)
            })
    },
    fetchBossReview({ commit }, num) {
        return axios.post('http://localhost:7777/boss/review', { num }, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_BOSS_REVIEW, res.data)
            })
    },
    fetchBossReviewImage({ commit }, num) {
        return axios.post('http://localhost:7777/boss/reviewImg', { num }, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_BOSS_REVIEW_IMAGE, res.data)
            })
    },
    fetchNearCommunityMap({ commit }) {
        return axios.post(API_BASE_URL + '/near/communityMap', {}, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_NEAR_COMMUNITY_MAP, res.data)
            })
    },
    fetchNearComm({ commit }) {
        return axios.post(API_BASE_URL + '/near/communityBoard', {}, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_NEAR_COMM, res.data)
            })
    },
    fetchNearCommPage({ commit }, nearNo) {
        return axios.post(`http://localhost:7777/near/${nearNo}`, {}, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_NEAR_COMM_PAGE, res.data)
            })
    },
    fetchBossComm({ commit }, num) {
        return axios.post('http://localhost:7777/boss/comm', { num }, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_BOSS_REVIEW, res.data)
            })
    },
    fetchReportRecent({ commit }) {
        return axios.post(API_BASE_URL + '/manager/reportListRecent', {}, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_REPORT_RECENT, res.data)
            })
    },
    fetchTotalPage({ commit }) {
        return axios.get(API_BASE_URL + '/manager/getTotalPage', {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_TOTAL_PAGE, res.data)
            })
    },
    fetchReportRead({ commit }, reportNo) {
        return axios.post(`http://localhost:7777/manager/${reportNo}`, {}, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_REPORT_READE, res.data)
            })
    },
    fetchReportReadImage({ commit }, reportNo) {
        return axios.post(`http://localhost:7777/manager/image/${reportNo}`, {}, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_REPORT_IMAGE_READE, res.data)
            })
    },
    fetchReportReadBoard({ commit }, reportNo) {
        return axios.post(`http://localhost:7777/manager/board/${reportNo}`, {}, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_REPORT_BOARD_READE, res.data)
            })
    },
    fetchMemberTotalPage({ commit }) {
        return axios.get(API_BASE_URL + '/manager/member/getTotalPage', {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_TOTAL_PAGE, res.data)
            })
    },
    fetchTodayRegisterMember({ commit }) {
        return axios.post(API_BASE_URL + '/manager/todayRegister', {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_TODAY_REGISTER, res.data)
            })
    },
    fetchTodayReport({ commit }) {
        return axios.post(API_BASE_URL + '/manager/todayReport', {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_TODAY_REPORT, res.data)
            })
    },
    fetchShowCoupon({ commit }, bossNo) {
        return axios.post(`http://localhost:7777/boss/showCoupon/${bossNo}`, {}, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_SHOW_COUPON, res.data)
            })
    },
    fetchShowCouponMember({ commit }, bossNo) {
        return axios.post(`http://localhost:7777/boss/couponMember/${bossNo}`, {}, {
            headers: {
                'Authorization': 'Bearer ' + cookies.get('access_token'),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_SHOW_MEMBER_COUPON, res.data)
            })
    }
}