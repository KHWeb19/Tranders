import {
    FETCH_LOGIN_TOKEN, FETCH_REFRESH_TOKEN
} from "@/store/mutation-types";

export default {
    [FETCH_LOGIN_TOKEN] (state, token){
        state.accessToken = token.accessToken;
        state.refreshToken = token.refreshToken;
    },
    [FETCH_REFRESH_TOKEN] (state, token){
        state.accessToken = token.accessToken;
        state.refreshToken = token.refreshToken;

        localStorage.setItem("access_token", token.accessToken);
        localStorage.setItem("refresh_token", token.refreshToken);
    }
}