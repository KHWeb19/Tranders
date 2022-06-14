<template>
  <div id="login-wrap">
    <div id="login-box">
      <span class="title_g" style="font-size: 50px">오이 마켓</span>
      <br/>
      <span>변경할 비밀번호를 작성해주세요</span>

      <v-container style="padding-top: 30px;">

        <v-row style="height: 70px">
          <v-col>
            <v-text-field solo :value="id" readonly> </v-text-field>
          </v-col>
        </v-row>

        <v-row style="height: 70px">
          <v-col>
            <v-text-field v-model="password" solo placeholder="Password"> </v-text-field>
          </v-col>
        </v-row>

        <v-row style="height: 70px; margin-top: 5px">
          <v-col>
            <v-btn @click="changePw" style="width: 100%; height: 100%; font-size: 20px; border-radius: 18px" class="light-green lighten-3">비밀번호 변경</v-btn>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ChangePwPage",
  props: {
    id: {
      type: String,
      default: "",
    }
  },
  data() {
    return {
      password: ""
    }
  },
  methods: {
    changePw(){
      const {id, password} = this;

      axios.post('http://localhost:7777/search/changePw', {id, password})
        .then((res) => {
          console.log(res)
          this.$router.push({name: 'LoginPage'})
        })
        .catch(() => {
          alert('에러 발생')
        })
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
</style>