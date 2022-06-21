import {Base64} from "js-base64";
import cookies from "vue-cookies";
import {SAVE_COOKIE_ACCESS} from "@/constant/login";

function ParsingInfo(token){
    //alert('token parser')
    const accessTokenInfo = token.split(".");

    let base64Encoded = Base64.decode(accessTokenInfo[1]);

    let result = JSON.parse(base64Encoded.toString())
    console.log(result)

    let userId = result.sub;
    let roles = result.roles;
    let name = result.name;
    let memberNo = result.memberNo;
    let providerType = result.providerType;

    //alert('token parser')
    console.log(userId);

    if (providerType !== null){
        let registerStatus = result.registerStatus;
        cookies.set('registerStatus', registerStatus, SAVE_COOKIE_ACCESS)
    }
    cookies.set('id', userId, SAVE_COOKIE_ACCESS);
    cookies.set('memberNo', memberNo, SAVE_COOKIE_ACCESS);
    cookies.set('name', name, SAVE_COOKIE_ACCESS);
    cookies.set('roles', roles, SAVE_COOKIE_ACCESS);
    cookies.set('providerType', providerType, SAVE_COOKIE_ACCESS);
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