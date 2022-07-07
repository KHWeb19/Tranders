<template>
  <div id="login-wrap">
    <div id="login-box">
      <span class="title_g" style="font-size: 50px">오이마켓</span>
      <!-- <br>
      <span class="title_b">-</span><span class="title_g">오</span><span class="title_b">늘은</span><span class="title_g"> 이</span><span class="title_b">거 사자</span><span class="title_b">-</span> -->

      <v-container style="padding-top: 30px;">
        <v-row style="height: 70px; margin-top: 25px">
          <v-col>
            <input v-model="id" placeholder="ID (이메일 형식)"/>
          </v-col>
        </v-row>

        <v-row style="height: 70px">
          <v-col>
            <v-text-field v-model="password" type="password" solo placeholder="Password"> </v-text-field>
          </v-col>
        </v-row>

        <v-row style="height: 70px; margin-top: 15px">
          <v-col>
            <v-btn @click="loginBtn" block depressed color="success" height="50"><h3><b>로그인</b></h3></v-btn>
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col cols="3" @click="searchId">ID 찾기</v-col>
          <v-col cols="1">|</v-col>
          <v-col cols="3" @click="searchPw">비밀번호 찾기</v-col>
          <v-col cols="1">|</v-col>
          <v-col cols="3" @click="register">회원 가입</v-col>
        </v-row>

        <v-row>
          <v-col><hr></v-col>
          <v-col style="margin-top: 23px; opacity: 0.5; font-size: 14px">간편 로그인</v-col>
          <v-col><hr></v-col>
        </v-row>

        <v-row>
          <v-col cols="6"  style="margin-top:3px">
            <v-img style="border-radius: 4px; height: 46px;" src="@/assets/login/kakao_login.png" @click="kakaoLogin"></v-img>
          </v-col>
          <v-col cols="6">
            <v-img style="width: 436px" src="@/assets/login/google_login.png" @click="googleLogin"></v-img>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
import {GOOGLE_AUTH_URL, KAKAO_AUTH_URL} from "@/constant/login";

export default {
  name: "LoginView",
  data() {
    return{
      id: "",
      password: "",
    }
  },
  methods: {
    loginBtn(){
      const {id, password} = this;
      this.$emit("login", {id, password});
    },
    async kakaoLogin(){
      location.href=KAKAO_AUTH_URL;
    },
    googleLogin(){
      location.href=GOOGLE_AUTH_URL;
    },
    searchId(){
      this.$router.push({name: "IdSearchPage"});
    },
    searchPw(){
      this.$router.push({name: "PwSearchPage"});
    },
    register() {
      this.$router.push({ name: "RegisterMemberPage" });
    },
  }
}
</script>

<style scoped>
#login-wrap{
  width: 100%;
  max-width: 800px;
  text-align: center;
  display: block;
  border: 1px solid #e9ecef;
  margin: 0 auto;
  padding: 140px;
  line-height: 24px;
  background: #fff;
  height: 100vh;

}
#login-box{
  max-width: 460px;
  margin: 0 auto;
  padding-top: 100px;
  padding-bottom: 90px;
}
input{
    width: 100%;
    border: 2px solid #EAEBEE;
    /* border: 1px solid #d1d3d8; */
    box-sizing: border-box;
    border-radius: 6px;
    font-size: 16px;
    box-shadow: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    padding: 16px;
    outline-style: none;
}
.title_g{
  font-family: ONE-Mobile-POP, serif;
  font-size: 25px;
  color: green;
}
.title_b {
  font-family: ONE-Mobile-POP, serif;
  font-size: 18px;
}

hr {
  margin-top: 35px;
  border: none;
  height: 1px;
  background-color: black;
  opacity: 0.2;
}

</style>