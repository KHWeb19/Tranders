import {
    FETCH_REFRESH_TOKEN,
    FETCH_PRODUCT,
    FETCH_PRODUCT_LIST,
    FETCH_CHATROOM,
    FETCH_CHATROOM_LIST,
    FETCH_MEMBER_PROFILE,
    FETCH_BOSS_PAGE,
    FETCH_MY_PAGE,
    FETCH_COMMUNITY_BOARD_LIST,
    FETCH_COMMUNITY_BOARD,
    FETCH_MY_REGION, FETCH_BOSS_BACK_PROFILE, FETCH_BOSS_MENU_LIST, FETCH_MY_VILLAGE_SETTING
} from './mutation-types'

import axios from 'axios'
import cookies from "vue-cookies";
import {API_BASE_URL} from "@/constant/login";

/*const config = {
    headers: {
        'Authorization': 'Bearer '+ cookies.get('access_token'),
        'Accept' : 'application/json',
        'Content-Type': 'application/json'
    }
};*/

export default {
    fetchProduct({ commit }) {
        return axios.get('http://localhost:7777/product/${productNo}')
            .then((res) => {
                commit(FETCH_PRODUCT, res.data)
            })
    },
    fetchProductList({ commit }) {
        return axios.get('http://localhost:7777/product/list')
            .then((res) => {
                commit(FETCH_PRODUCT_LIST, res.data)
            })
    },
    refreshToken ({commit}) {
        return axios.get('http://localhost:7777/member/refreshToken',{
            headers: {
                'Authorization': 'Bearer '+cookies.get('refresh_token'),
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_REFRESH_TOKEN, res.data)
                console.log('refresh Token')
            })
    },
    fetchChatroom ({ commit }, roomNo) {
        return axios.get(`http://localhost:7777/chatting/${roomNo}`)
            .then((res) => {
                commit(FETCH_CHATROOM, res.data)
            })
    },
    fetchChatroomList ({ commit }, memberNo) {
        return axios.get(`http://localhost:7777/chatting/list/${memberNo}`)
            .then((res) => {
                commit(FETCH_CHATROOM_LIST, res.data)
            })
    },
    fetchMemberProfile ({commit}, id) {
        return axios.get(`http://localhost:7777/member/profile/${id}`, {
            headers: {
                'Authorization': 'Bearer '+ cookies.get('access_token'),
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_MEMBER_PROFILE, res.data)
            })
    },
    fetchCommunityBoardList ({ commit }) {
        return axios.get('http://localhost:7777/communityboard/list')
            .then((res) => {
                commit(FETCH_COMMUNITY_BOARD_LIST, res.data)
            })
    },
    fetchCommunityBoard ({ commit }, boardNo) {
        return axios.get(`http://localhost:7777/communityboard/${boardNo}`)
            .then((res) => {
                commit(FETCH_COMMUNITY_BOARD, res.data)
            })
    },
    fetchBossPage ({commit}, memberNo) {
        return axios.post(API_BASE_URL+'/boss/pageView', {memberNo}, {
            headers: {
                'Authorization': 'Bearer '+ cookies.get('access_token'),
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_BOSS_PAGE, res.data)
            })
    },
    fetchMyPage ({commit}, id) {
        return axios.post(API_BASE_URL+'/member/userInfo', {id}, {
            headers: {
                'Authorization': 'Bearer '+ cookies.get('access_token'),
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_MY_PAGE, res.data)
            })
    },
    async fetchMyRegion({commit}, id) {
        return await axios.post(API_BASE_URL+'/member/region', {id}, {
            headers: {
                'Authorization': 'Bearer '+ cookies.get('access_token'),
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_MY_REGION, res.data)
            })
    },
    fetchBossBackProfile({commit}, id) {
        return axios.post(API_BASE_URL+'/boss/getBackProfile', {id}, {
            headers: {
                'Authorization': 'Bearer '+ cookies.get('access_token'),
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => [
                commit(FETCH_BOSS_BACK_PROFILE, res.data)
            ])
    },
    fetchBossMenuList({commit}, id){
        return axios.post(`http://localhost:7777/boss/getMenu/${id}`, {}, {
            headers: {
                'Authorization': 'Bearer '+ cookies.get('access_token'),
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_BOSS_MENU_LIST, res.data)
            })
    },
    fetchMyVillageSetting({commit}, id){
        return axios.post(`http://localhost:7777/member/getMyVillage/${id}`, {}, {
            headers: {
                'Authorization': 'Bearer '+ cookies.get('access_token'),
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_MY_VILLAGE_SETTING, res.data)
            })
    }
}