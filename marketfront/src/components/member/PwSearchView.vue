<template>
  <div id="login-wrap">
    <div id="login-box">
      <span class="title_g" style="font-size: 50px">오이 마켓</span>
      <br>
      <span class="title_b">-</span><span class="title_g">오</span><span class="title_b">늘은</span><span class="title_g"> 이</span><span class="title_b">거 사자</span><span class="title_b">-</span>
      <br/><br/>
      <span style="font-size: 20px">
        가입 시 정보를 정확히 입력해주시기 바랍니다.
      </span>

      <v-container style="padding-top: 15px;">
        <v-row style="height: 70px; margin-top: 25px">
          <v-col>
            <v-text-field v-model="id" solo style="width: 100%" placeholder="아이디ID (이메일 형식)"> </v-text-field>
          </v-col>
        </v-row>


        <v-row style="height: 70px; margin-top: 15px">
          <v-col>
            <v-text-field v-model="name" solo style="width: 100%" placeholder="이름"> </v-text-field>
          </v-col>
        </v-row>

        <v-row style="height: 70px">
          <v-col>
            <v-text-field v-model="phoneNumber" solo placeholder="휴대전화번호"> </v-text-field>
          </v-col>
        </v-row>

        <v-row style="height: 70px; margin-top: 5px">
          <v-col>
            <v-btn @click="searchPw" depressed color="success" height="50" width="100" type="submit"><b>비밀번호 찾기</b></v-btn>
          </v-col>
        </v-row>

        <v-row style="height: 70px; margin-top: 5px">
          <v-col>
            <v-btn @click="loginForm" depressed color="success" height="50" width="100" type="submit"><b>로그인</b></v-btn>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
export default {
  name: "PwSearchView",
  data(){
    return{
      id: "",
      name: "",
      phoneNumber: "",
    }
  },
  methods: {
    searchPw(){
      const {id, name, phoneNumber} = this;

      this.$emit('searchPw', {id, name, phoneNumber});
    },
    loginForm(){
      this.$router.push({name: 'LoginPage'});
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