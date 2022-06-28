import {
    FETCH_PRODUCT,
    FETCH_PRODUCT_LIST,
    FETCH_CHATROOM,
    FETCH_CHATROOM_LIST,
    FETCH_LOGIN_TOKEN,
    FETCH_REFRESH_TOKEN,
    FETCH_MEMBER_PROFILE,
    FETCH_BOSS_PAGE,
    FETCH_MY_PAGE,
    FETCH_MY_REGION,
    FETCH_COMMUNITY_BOARD_LIST,
    FETCH_COMMUNITY_BOARD,
    FETCH_BOSS_BACK_PROFILE,
    FETCH_BOSS_MENU_LIST,
    FETCH_BOSS_MARKET_INFO,
    FETCH_MY_VILLAGE_SETTING, FETCH_NEAR_MAP,
} from './mutation-types'
import cookies from "vue-cookies";
import {ParsingInfo} from "@/views/Util/LoginUtil";

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
        /*state.accessToken = token.accessToken;
        state.refreshToken = token.refreshToken;*/

        cookies.set("access_token", token.access_token);
        cookies.set("refresh_token", token.refresh_token);

        ParsingInfo(token.access_token);

    },
    [FETCH_CHATROOM] (state, chatroom) {
        state.chatroom = chatroom
    },
    [FETCH_CHATROOM_LIST] (state, chatrooms) {
        state.chatrooms = chatrooms
    },
    [FETCH_MEMBER_PROFILE] (state, profileImg) {
        console.log(profileImg);
        state.profileImg = profileImg;
    },
    [FETCH_COMMUNITY_BOARD_LIST] (state, communityBoards) {
        state.communityBoards = communityBoards
    },
    [FETCH_COMMUNITY_BOARD] (state, communityBoard) {
        state.communityBoard = communityBoard
    },
    [FETCH_BOSS_PAGE] (state, boss){
        state.boss = boss;
    },
    [FETCH_MY_PAGE] (state, userInfo) {
        state.userInfo = userInfo;
    },
    [FETCH_MY_REGION] (state, mapOption){
        state.mapOption = mapOption;
    },
    [FETCH_BOSS_BACK_PROFILE] (state, backProfileImg){
        state.backProfileImgs = backProfileImg;
    },
    [FETCH_BOSS_MENU_LIST] (state, bossMenu) {
        state.bossMenu = bossMenu;
    },
    [FETCH_BOSS_MARKET_INFO] (state, marketInfo) {
        state.marketInfo = marketInfo;
    },
    [FETCH_MY_VILLAGE_SETTING] (state, villageInfo){
        state.villageInfo = villageInfo;
    },
    [FETCH_NEAR_MAP] (state, nearMap){
        state.nearMap = nearMap;
    }
}

