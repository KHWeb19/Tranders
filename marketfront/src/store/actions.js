import {
    FETCH_REFRESH_TOKEN,
    FETCH_PRODUCT,
    FETCH_PRODUCT_LIST,
    FETCH_MEMBER_PROFILE,
    FETCH_CHATROOM,
    FETCH_CHATROOM_LIST,
    FETCH_COMMUNITY_BOARD_LIST,
    FETCH_COMMUNITY_BOARD,
} from './mutation-types'

import axios from 'axios'
import cookies from "vue-cookies";

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
}