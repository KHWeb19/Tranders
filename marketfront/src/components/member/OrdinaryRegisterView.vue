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
            <v-btn style="width: 100%" id="checkDub" @click="checkDub" outlined>중복 확인</v-btn>
          </v-col>
        </v-row>

        <v-row style="height: 70px">
          <v-col>
            <v-text-field v-model="password"
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
            <v-text-field solo v-model="phoneNumber" style="width: 100%" placeholder="PHONE NUMBER"> </v-text-field>
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
            <v-btn @click="registerBtn" style="width: 100%; height: 100%; font-size: 20px; border-radius: 18px" class="light-green lighten-3" :disabled="checkDoubleId === true">오이 마켓 시작하기</v-btn>
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
  props: ['checkDoubleId'],
  data(){
    return {
      isIndividual: true,
      isManager: false,
      id: '',
      password: '',
      name: '',
      phoneNumber: '',
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
      navigator.geolocation.getCurrentPosition(this.showYourLocation, this.showErrorMsg);
    },
    showYourLocation(position) {  // 성공했을때 실행
      let y = position.coords.latitude;
      let x = position.coords.longitude;

      console.log(x);
      console.log(y);


      axios.post('http://127.0.0.1:5000/address-region', {x, y})
          .catch((res) => {
            console.log(res)
          })
          .then((res) => {
            console.log(res)
            this.region = res.data;
          })
    },
    showErrorMsg(error) {
      alert('에러발생')
      switch(error.code) {
        case error.PERMISSION_DENIED:
          alert("이 문장은 사용자가 Geolocation API의 사용 요청을 거부했을 때 나타납니다!")
          break;

        case error.POSITION_UNAVAILABLE:
          alert("이 문장은 가져온 위치 정보를 사용할 수 없을 때 나타납니다!")
          break;

        case error.TIMEOUT:
          alert("이 문장은 위치 정보를 가져오기 위한 요청이 허용 시간을 초과했을 때 나타납니다!")
          break;
      }
    },
    registerBtn() {
      let roles;

      if (this.isManager) {
        roles = 0;
      } else {
        roles = 1;
      }

      const {id, password, name, phoneNumber, region} = this;

      this.$emit('register', {id, password, name, phoneNumber, region, roles})
    },
    checkDub(){
      const {id} = this;
      this.$emit('check', {id});
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