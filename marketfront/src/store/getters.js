import cookies from "vue-cookies";

export default {
    getToken () {
        let accessToken = cookies.get('access_token');
        let refreshToken = cookies.get('refresh_token');

        return {
            accessToken, refreshToken
        }
    }
}