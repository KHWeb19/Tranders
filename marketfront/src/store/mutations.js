import {
    FETCH_PRODUCT_BOARD,
    FETCH_PRODUCT_BOARD_LIST,
    FETCH_CHATROOM,
    FETCH_CHATROOM_LIST,
    FETCH_REVIEW_LIST,
    FETCH_MANNER,
    FETCH_LOGIN_TOKEN,
    FETCH_REFRESH_TOKEN,
    FETCH_MEMBER_PROFILE,
    FETCH_BOSS_PAGE,
    FETCH_MY_PAGE,
    FETCH_MY_REGION,
    FETCH_COMMUNITY_BOARD_LIST,
    FETCH_COMMUNITY_BOARD,
    FETCH_COMMUNITY_COMMENTS_LIST,
    FETCH_BOSS_BACK_PROFILE,
    FETCH_BOSS_MENU_LIST,
    FETCH_BOSS_MARKET_INFO,
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
    FETCH_MEMBER_LIST,
    FETCH_TODAY_REGISTER, FETCH_TODAY_REPORT
} from './mutation-types'
import cookies from "vue-cookies";
import {ParsingInfo} from "@/views/Util/LoginUtil";

export default {
    [FETCH_PRODUCT_BOARD](state, productBoard) {
        state.productBoard = productBoard
    },
    [FETCH_PRODUCT_BOARD_LIST](state, productBoards) {
        state.productBoards = productBoards
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
    [FETCH_REVIEW_LIST] (state, reviews) {
        state.reviews = reviews
    },
    [FETCH_MANNER] (state, manner) {
        state.manner = manner
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
    [FETCH_COMMUNITY_COMMENTS_LIST](state, communityComments) {
        state.communityComments = communityComments
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
    },
    [FETCH_CLIENT_BOSS_VIEW] (state, boss){
        state.boss = boss;
    },
    [FETCH_NEAR_REVIEW] (state, nearReview){
        state.nearReview = nearReview;
    },
    [FETCH_BOSS_REVIEW] (state, review) {
        state.bossReview = review;
    },
    [FETCH_BOSS_REVIEW_IMAGE] (state, reviewImg){
        state.bossReviewImg = reviewImg;
    },
    [FETCH_NEAR_COMMUNITY_MAP] (state, nearMapComm){
        state.nearMapComm = nearMapComm;
    },
    [FETCH_NEAR_COMM] (state, nearComm){
        state.nearComm = nearComm;
    },
    [FETCH_NEAR_COMM_PAGE] (state, near){
        state.near = near;
    },
    [FETCH_REPORT_RECENT] (state, recentReport){
        state.recentReport = recentReport;
    },
    [FETCH_TOTAL_PAGE] (state, page){
        state.page = page;
    },
    [FETCH_REPORT_READE] (state, reportRead){
        state.reportRead = reportRead;
    },
    [FETCH_REPORT_IMAGE_READE] (state, reportImg){
        state.reportImageRead = reportImg;
    },
    [FETCH_REPORT_BOARD_READE] (state, reportBoard){
        state.reportBoardRead = reportBoard;
    },
    [FETCH_MEMBER_LIST] (state, member) {
        state.member = member;
    },
    [FETCH_TODAY_REGISTER] (state, todayMember) {
        state.todayMember = todayMember;
    },
    [FETCH_TODAY_REPORT] (state, todayReport) {
        state.todayReport = todayReport;
    }

}

