<template>
  <div>
<!--    <after-login-view></after-login-view> // 없는게 더 괜찮아 보이는데 다른분들은 어떤지 물어보기!-->
    <div v-if="this.boss === null"><v-btn>죄송합니다 다시 시도해주세요</v-btn></div>
    <boss-page-view v-else :boss="boss" @savePrice="savePrice" @saveBackProfile="saveBackProfile" @saveBossProfileImg="saveBossProfileImg" @modifySave="modifySave" @deletePrice="deletePrice"></boss-page-view>
  </div>
</template>

<script>

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
  components: {BossPageView},
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
              this.$router.go()
            }
          })
          .catch(() => {
            alert('에러')
          })
    },
    savePrice(payload){
      const {menuName, menuPrice, menuInfo, bossNo} = payload;
      alert(bossNo)
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