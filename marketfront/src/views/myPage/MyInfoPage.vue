<template>
  <div id="login-wrap">
    <after-login-view></after-login-view>

    <div id="my_page_box">
      <v-row no-gutters>
        <v-col cols="4">
          <my-profile-bar :index="index"></my-profile-bar>
        </v-col>

        <v-col cols="8">
          <div v-if="userInfo === null"> <v-btn>죄송합니다 다시 부탁드려요</v-btn></div>
          <my-info-view v-else :userInfo="userInfo" @modifyMember="modifyMember" @saveProfileImg="saveProfileImg" @checkPhoneNum="checkPhoneNum"
                        @certification="certification" :ifCheck="ifCheck"></my-info-view>
        </v-col>

        <v-col>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>

import AfterLoginView from "@/components/home/AfterLoginView";
import MyProfileBar from "@/components/myPage/MyPageBar";
import MyInfoView from "@/components/myPage/MyInfoView";
import cookies from "vue-cookies";
import {mapActions, mapState} from "vuex";
import axios from "axios";
import {API_BASE_URL} from "@/constant/login";

const config = {
  headers: {
    'Authorization': 'Bearer '+ cookies.get('access_token'),
    'Accept' : 'application/json',
    'Content-Type': 'application/json'
  }
};

export default {
  components: {MyInfoView, MyProfileBar, AfterLoginView},
  name: "MyInfoPage",
  data(){
    return{
      index: 0,
      id: cookies.get("id"),
      randomNum: '',
      ifCheck: false,
    }
  },
  methods: {
    ...mapActions(['fetchMyPage']),
    modifyMember(payload){
      const {id, password, name, phoneNumber} = payload

      axios.post(API_BASE_URL+'/member/modify', {id, password, name, phoneNumber}, config)
          .then((res) => {
            console.log(res);
            cookies.remove('access_token');
            cookies.remove('refresh_token');

            this.$router.push({name: 'LoginPage'})
          })
          .catch(() => {
            alert('에러')
          })
    },
    saveProfileImg(formData){
      axios.post(API_BASE_URL+'/member/modifyProfileImg', formData, {
        headers: {
          'Authorization': 'Bearer '+ cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'multipart/form-data'
        }
      })
          .then((res) => {
            console.log(res);
            if(res.data === false){
              alert('에러 발생')
            }else{
              this.$router.go()
            }
          })
          .catch(() => {
            alert('에러')
          })

    },
    checkPhoneNum(payload){
      const {phoneNumber} = payload;
      console.log(phoneNumber)
      axios.post(API_BASE_URL+'/member/register/check/sendSMS', {phoneNumber})
          .then((res) => {
            console.log(res);

            if(res.data === 'error'){
              alert('다른 번호를 입력해주세요!')
            } else {
              this.ifCheck = true;
              this.randomNum = res.data;
            }
          })
          .catch(() => {
            alert('에러발생')
          })
    },
    certification(payload){
      const {num} = payload;
      console.log(num)
      console.log(this.randomNum)
      if(num === this.randomNum){
        alert('ok!')
        this.checkDoublePhoneNum = true;
      } else {
        alert('error!')
        this.checkDoublePhoneNum = false;
      }
    }
  },
  computed: {
    ...mapState(['userInfo'])
  },
  mounted() {
    this.fetchMyPage(this.id)
  }
};
</script>

<style scoped>
#login-wrap{
  width: 100%;
  max-width: 1440px;
  margin: 0 auto;
  text-align: left;
}
#my_page_box{
  display: flex;
  align-items: center;
  width: 100%;
  max-width: 1250px;
  margin: 0 auto;
  padding: 15px 7px 0 7px; /*위, 오른쪽, 아래, 왼쪽 */
}
</style>