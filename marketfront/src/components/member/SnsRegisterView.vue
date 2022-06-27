<template>
  <div id="login-wrap">
    <div id="login-box">
      <span class="title_g" style="font-size: 50px">오이 마켓</span>
      <br>
      <span class="title_b">-</span><span class="title_g">오</span><span class="title_b">늘은</span><span class="title_g"> 이</span><span class="title_b">거 사자</span><span class="title_b">-</span>

      <v-row style="height: 70px; margin-top: 25px">
        <v-col>
          <v-text-field solo style="width: 100%" :value="id" placeholder="ID" readonly disabled> </v-text-field>
        </v-col>
      </v-row>

      <v-row style="height: 70px">
        <v-col>
          <v-text-field solo style="width: 100%" v-model="name" placeholder="NAME"> </v-text-field>
        </v-col>
      </v-row>

      <v-row style="height: 70px">
        <v-col cols="10">
          <v-text-field solo style="width: 100%" v-model="region" placeholder="ADDRESS" readonly> </v-text-field>
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
    </div>
  </div>
</template>

<script>
import axios from "axios";
import cookies from "vue-cookies";

export default {
  name: "SnsRegisterView",
  data(){
    return {
      id: cookies.get("id"),
      name: '',
      region: '',
      lat: '',
      lng: ''
    }
  },
  methods: {
    whereami() {
      navigator.geolocation.getCurrentPosition(this.showYourLocation, this.showErrorMsg);
    },
    showYourLocation(position) {  // 성공했을때 실행
      let y = position.coords.latitude;
      let x = position.coords.longitude;

      console.log(x);
      console.log(y);

      this.lat = y;
      this.lng = x;

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
      const {id, name, region, lat, lng} = this;

      this.$emit('register', {id, name, region, lat, lng})
    },
  },
  mounted() {
    this.name = cookies.get('name');
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