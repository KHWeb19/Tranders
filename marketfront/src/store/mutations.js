import {
    FETCH_PRODUCT,
    FETCH_PRODUCT_LIST,
    FETCH_CHATROOM,
    FETCH_CHATROOM_LIST,
    FETCH_LOGIN_TOKEN, FETCH_REFRESH_TOKEN,
} from './mutation-types'

export default {
    [FETCH_PRODUCT](state, product) {
        state.product = product
    },
    [FETCH_PRODUCT_LIST](state, products) {
        state.products = products
    },
    [FETCH_LOGIN_TOKEN] (state, token){
        state.accessToken = token.accessToken;
        state.refreshToken = token.refreshToken;
    },
    [FETCH_REFRESH_TOKEN] (state, token){
        state.accessToken = token.accessToken;
        state.refreshToken = token.refreshToken;

        localStorage.setItem("access_token", token.accessToken);
        localStorage.setItem("refresh_token", token.refreshToken);

    },
    [FETCH_CHATROOM] (state, chatroom) {
        state.chatroom = chatroom
    },
    [FETCH_CHATROOM_LIST] (state, chatrooms) {
        state.chatrooms = chatrooms
    },
}

