<template>
  <div id="login-wrap">
    <div id="login-box">
      <span class="title_g" style="font-size: 50px">오이 마켓</span>
      <br>
      <span class="title_b">-</span><span class="title_g">오</span><span class="title_b">늘은</span><span class="title_g"> 이</span><span class="title_b">거 사자</span><span class="title_b">-</span>

      <v-container style="padding-top: 30px;">
        <v-row style="height: 70px; margin-top: 25px">
          <v-col>
            <v-text-field v-model="id" solo style="width: 100%" placeholder="ID (이메일 형식)"> </v-text-field>
          </v-col>
        </v-row>

        <v-row style="height: 70px">
          <v-col>
            <v-text-field v-model="password" solo placeholder="Password"> </v-text-field>
          </v-col>
        </v-row>

        <v-row style="height: 70px; margin-top: 15px">
          <v-col>
            <v-btn @click="loginBtn" style="width: 100%; height: 100%; font-size: 20px; border-radius: 18px" class="light-green lighten-3">로그인</v-btn>
          </v-col>
        </v-row>

        <v-row >
          <v-col>
            <v-img style="border-radius: 12px; height: 46px; width: 436px" src="@/assets/login/kakao_login_medium_wide.png" @click="kakaoLogin"></v-img>
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col cols="4" @click="searchId">ID 찾기</v-col>
          <v-col cols="1">|</v-col>
          <v-col cols="4" @click="searchPw">비밀번호 찾기</v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
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
    kakaoLogin(){
      window.open("https://kauth.kakao.com/oauth/authorize?client_id=1f6ab7138670a91099012c0341d8fe60&redirect_uri=http://localhost:7777/member/auth/kakao/callback&response_type=code")
    },
    searchId(){
      this.$router.push({name: "IdSearchPage"});
    },
    searchPw(){
      this.$router.push({name: "PwSearchPage"});
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
  padding-top: 100px;
  padding-bottom: 90px;
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
</style>