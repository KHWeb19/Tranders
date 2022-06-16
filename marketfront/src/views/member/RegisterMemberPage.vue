<template>
  <div id="login-wrap">
    <div id="login-box">
      <span id="title">오이마켓</span>
      <br>
      <span>"오늘이 이거 사자"</span>

      <div style="margin-top: 80px">
        <v-btn id="login-btn" @click="ordinaryRegister">일반 회원가입</v-btn>
      </div>

      <div style="margin-top: 30px">
        SNS 아이디로 회원가입할 수 있습니다!
      </div>

      <div style="margin-top: 15px">
        <v-btn fab @click="kakaoLogin">
          <v-img contain max-height="58" src="@/assets/login/kakao.png"></v-img>
        </v-btn>
      </div>

    </div>
  </div>
</template>

<script>
import {KAKAO_AUTH_URL} from "@/constant/login";
import {ParsingInfo} from "@/views/Util/LoginUtil";
import cookies from "vue-cookies";

export default {
  name: "RegisterMemberPage",
  methods: {
    kakaoLogin(){

      location.href=KAKAO_AUTH_URL;
      ParsingInfo(cookies.get('access_token'));
    },
    ordinaryRegister(){
      this.$router.push({name: 'ordinaryRegisterPage'})
    }
  }
}
</script>

<style scoped>
#login-wrap{
  width: 100%;
  max-width: 1440px;
  margin: 0 auto;
  text-align: center;
}
#login-box{
  max-width: 460px;
  margin: 0 auto;
  padding-top: 130px;
  padding-bottom: 90px;
}
#title{
  font-family: ONE-Mobile-POP, serif;
  font-size: 50px;
  color: green;
}
#login-btn{
  padding: 15px 0;
  margin-bottom: 22px;
  width: 100%;
  height: 50px;
  color: #fff;
  background: #4c7732;
  border-radius: 100px;
  -webkit-border-radius: 100px;
}
v-img{
  height: 20px;
}
</style>