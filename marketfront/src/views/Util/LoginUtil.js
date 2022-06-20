import {Base64} from "js-base64";
import cookies from "vue-cookies";
import {SAVE_COOKIE_ACCESS} from "@/constant/login";

function ParsingInfo(token){
    alert('token parser')
    const accessTokenInfo = token.split(".");

    let base64Encoded = Base64.decode(accessTokenInfo[1]);

    let result = JSON.parse(base64Encoded.toString())

    let userId = result.sub;
    let roles = result.roles;
    let name = result.name;
    let memberNo = result.memberNo;

    alert('token parser')
    console.log(userId);

    if(userId.indexOf('@') !== -1){
        //alert('@이가 있어요!')
        userId = userId.split('@');

        //alert(userId);

        cookies.set('id', userId[0], SAVE_COOKIE_ACCESS);
        cookies.set('mail', userId[1], SAVE_COOKIE_ACCESS);
    }else {
        //alert('@ 가 없어요')
        cookies.set('id', userId, SAVE_COOKIE_ACCESS);
    }
    cookies.set('memberNo', memberNo, SAVE_COOKIE_ACCESS);
    cookies.set('name', name, SAVE_COOKIE_ACCESS);
    cookies.set('roles', roles, SAVE_COOKIE_ACCESS);
}

function logout(){
    cookies.remove('id');
    cookies.remove('memberNo');
    cookies.remove('name');
    cookies.remove('roles');
    cookies.remove('access_token');
    cookies.remove('refresh_token');

}

export {
    ParsingInfo,
    logout
}