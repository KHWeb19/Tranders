<template>
  <div>
    <after-login-view></after-login-view>
    <div v-if="!boss || (Array.isArray(boss) && boss.length === 0)">
      <v-btn>죄송합니다 다시 시도해주세요</v-btn>
    </div>
    <boss-page-view v-else :boss="boss"
                    @savePrice="savePrice"
                    @saveBackProfile="saveBackProfile"
                    @saveBossProfileImg="saveBossProfileImg"
                    @modifySave="modifySave"
                    @deletePrice="deletePrice"
                    @saveCoupon="saveCoupon"
                    @modifyCoupon="modifyCoupon"
                    @removeBoss="removeBoss"
                    @modifyAddBackProfile="modifyAddBackProfile"
                    @modifyADeleteBackProfile="modifyADeleteBackProfile"></boss-page-view>
  </div>
</template>

<script>

import AfterLoginView from "@/components/home/AfterLoginView";
const config = {
  headers: {
    'Authorization': 'Bearer '+ cookies.get('access_token'),
    'Accept' : 'application/json',
    'Content-Type': 'application/json'
  }
};

import BossPageView from "@/components/boss/BossPageView";
import {mapActions, mapState} from "vuex";
import cookies from "vue-cookies";
import axios from "axios";
import {API_BASE_URL} from "@/constant/login";
export default {
  name: "BossPage",
  components: {AfterLoginView, BossPageView},
  data() {
    return {
      memberNo: cookies.get('memberNo'),
    }
  },
  methods: {
    ...mapActions(['fetchBossPage']),
    saveBackProfile(formData){
      axios.post(API_BASE_URL+'/boss/saveBackImg', formData, {
        headers: {
          'Authorization': 'Bearer '+ cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'multipart/form-data'
        }
      })
          .then((res) => {
            console.log(res)
            setTimeout(() => {this.$router.go()}, 500);
          })
          .catch(() => {
            alert('에러')
          })
    },
    saveBossProfileImg(formData){
      axios.post(API_BASE_URL+'/boss/modifyProfile', formData, {
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
              setTimeout(() => {this.$router.go()}, 500);
            }
          })
          .catch(() => {
            alert('에러')
          })
    },
    savePrice(payload){
      const {menuName, menuPrice, menuInfo, bossNo} = payload;
      //alert(bossNo)
      axios.post(`http://localhost:7777/boss/addPrice/${bossNo}`, {menuName, menuPrice, menuInfo}, config)
          .then((res) => {
            console.log(res);
            this.$router.go();
          })
          .catch(() => {
            alert('에러')
          })
    },
    modifySave(payload){
      const {menuName, menuPrice, menuInfo, bossPriceNo} = payload;
      alert(bossPriceNo)
      axios.post(API_BASE_URL+'/boss/modifyMenu', {menuName, menuPrice, menuInfo, bossPriceNo}, config)
          .then((res) => {
            console.log(res);
            this.$router.go();
          })
          .catch(() => {
            alert('에러')
          })
    },
    deletePrice(payload){
      const {menuNo} = payload;

      axios.delete(`http://localhost:7777/boss/deleteMenu/${menuNo}`)
          .then((res) => {
            console.log(res)
            this.$router.go()
          })
          .catch(() => {
            alert('에러')
          })
    },
    saveCoupon(payload){
      const {bossNo, couponName, couponInfo, couponMax, couponDate}= payload;

      axios.post(API_BASE_URL+'/boss/addCoupon', {bossNo, couponName, couponInfo, couponMax, couponDate}, config)
          .then(() => {
            console.log('성공')
            this.$router.go()
          })
          .catch(() => {
            alert('쿠폰 생성 에러')
          })
    },
    modifyCoupon(payload){
      const {couponNo, couponName, couponInfo, couponMax, couponDate} = payload;

      axios.post(API_BASE_URL+'/boss/modifyCoupon', {couponNo, couponName, couponDate, couponMax, couponInfo}, config)
          .then(() => {
            console.log('성공')
            this.$router.go();
          })
          .catch((res) => {
            console.log(res)
          })
    },
    removeBoss(payload){
      const {bossNo} = payload;

      axios.delete(`http://localhost:7777/boss/${bossNo}`, config)
          .then(() => {
            console.log('성공')
            this.$router.push({name: 'MyPageProfile'});
          })
          .catch((res) => {
            console.log(res)
          })
    },
    modifyAddBackProfile(formData){ //fileList 가 있다.
      axios.post(API_BASE_URL+'/boss/modifyAddBackProfile', formData, {
        headers: {
          'Authorization': 'Bearer '+ cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'multipart/form-data'
        }
      })
          .then(() => {
            setTimeout(() => {
              this.$router.go()
            }, 80)
          })
          .catch(() => {
            alert('에러')
          })
    },
    modifyADeleteBackProfile(formData){ // fileList 가 없다.
      axios.post(API_BASE_URL+'/boss/modifyDeleteBackProfile', formData, {
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
              setTimeout(() => {
                this.$router.go()
              }, 80)
            }
          })
          .catch(() => {
            alert('에러')
          })
    }
  },
  computed: {
    ...mapState(['boss'])
  },
  mounted() {
    this.fetchBossPage(this.memberNo)
  }
}
</script>

<style scoped>

</style>