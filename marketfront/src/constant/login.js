export const API_BASE_URL = 'http://localhost:7777';

export const SAVE_COOKIE_ACCESS = 60 * 60 * 3 // 3시

export const SAVE_COOKIE_REFRESH = 60 * 60 * 5 // 5간간

export const OAUTH2_REDIRECT_URI = 'http://localhost:8080/oauth2/redirect'

export const GOOGLE_AUTH_URL = API_BASE_URL + '/oauth2/authorization/google?redirect_uri=' + OAUTH2_REDIRECT_URI;

export const KAKAO_AUTH_URL = API_BASE_URL + '/oauth2/authorization/kakao?redirect_uri=' + OAUTH2_REDIRECT_URI;
