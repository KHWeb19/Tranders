<template>
  <div id="login-wrap">
    <div id="login-box">
      <span class="title_g" style="font-size: 50px">오이 마켓</span>
      <br>
      <span class="title_b">-</span><span class="title_g">오</span><span class="title_b">늘은</span><span class="title_g"> 이</span><span class="title_b">거 사자</span><span class="title_b">-</span>

      <v-container style="padding-top: 30px">
        <v-row style="height: 70px">
          <v-col>
            <v-btn @click="btnI" style="width: 100%; height: 100%; font-size: 20px" :class="{'light-green': isIndividual, 'lighten-3': true}">개인</v-btn>
          </v-col>

          <v-col>
            <v-btn @click="btnM" style="width: 100%; height: 100%; font-size: 18px"  :class="{'light-green': isManager, 'lighten-3': true}">관리자</v-btn>
          </v-col>
        </v-row>

        <v-row style="height: 70px; margin-top: 25px">
          <v-col cols="9">
            <v-text-field v-model="id" :rules="rules" solo style="width: 100%" placeholder="ID"> </v-text-field>
          </v-col>
          <v-col cols="3" class="pt-5">
            <v-btn style="width: 100%" id="checkDub" outlined>중복 확인</v-btn>
          </v-col>
        </v-row>

        <v-row style="height: 70px">
          <v-col>
            <v-text-field v-model="pw"
                          :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                          :rules="[pwRules.required, pwRules.min, pwRules.max]"
                          :type="show ? 'text' : 'password'"
                          placeholder="PW"
                          @click:append="show = !show" solo style="width: 100%"> </v-text-field>
          </v-col>
        </v-row>

        <v-row style="height: 70px">
          <v-col>
            <v-text-field solo style="width: 100%" v-model="name" placeholder="NAME"> </v-text-field>
          </v-col>
        </v-row>

        <v-row style="height: 70px">
          <v-col>
            <v-text-field solo v-model="phoneNum" style="width: 100%" placeholder="PHONE NUMBER"> </v-text-field>
          </v-col>
        </v-row>

        <v-row style="height: 70px">
          <v-col cols="10">
            <v-text-field solo style="width: 100%" placeholder="ADDRESS" v-model="region" readonly> </v-text-field>
          </v-col>

          <v-col cols="2" class="pt-5">
            <v-btn style="width: 100%" id="address" @click="whereami" class="red--text" icon><v-icon>mdi-map-marker</v-icon></v-btn>
          </v-col>
        </v-row>

        <v-row style="height: 70px; margin-top: 35px">
          <v-col>
            <v-btn @click="registerBtn" style="width: 100%; height: 100%; font-size: 20px; border-radius: 18px" class="light-green lighten-3">오이 마켓 시작하기</v-btn>
          </v-col>
        </v-row>

      </v-container>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ordinaryRegisterView",
  data(){
    return {
      isIndividual: true,
      isManager: false,
      id: '',
      pw: '',
      name: '',
      phoneNum: '',
      region: '',
      rules: [v => v.length <= 12 || '12자리 이하 입력', v => v.length >= 8 || '8자리 이상 입력'],
      show: false,
      pwRules: {
        required: value => !!value || 'Required.',
        min: v => v.length >= 8 || 'Min 8 characters',
        max: v => v.length <= 18 || 'Max 18 characters'
      }
    }
  },
  methods: {
    btnI() {
      if (!this.isIndividual === true) {
        this.isIndividual = !this.isIndividual;
        this.isManager = !this.isManager;
      }
    },
    btnM() {
      if (!this.isIndividual !== true) {
        this.isIndividual = !this.isIndividual;
        this.isManager = !this.isManager;
      }
    },
    whereami() {
      let options = {
        enableHighAccuracy: true,
        maximumAge: 3000,
        timeout: 15000
      }

      if (navigator.geolocation)
        navigator.geolocation.getCurrentPosition(this.success, this.error, options);
      else
        alert('지원 안함')
    },
    error() {
      alert('에러발생')
    },
    success(pos) {
      console.log(pos);

      let y = pos.coords.latitude;
      let x = pos.coords.longitude;
      axios.post('http://localhost:5000/test-address', {x, y})
          .catch((res) => {
            console.log(res)
          })
          .then((res) => {
            console.log(res)
            this.region = res.data;
          })
    },
    registerBtn() {
      let auth;

      if (this.isManager) {
        auth = 0;
      } else {
        auth = 1;
      }

      const {id, pw, name, phoneNum, region} = this;

      this.$emit('register', {id, pw, name, phoneNum, region, auth})
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
#checkDub{
  border-radius: 18px;
  border-color: #154d15;
}
</style>