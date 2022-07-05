<template>
  <div>
    <div v-if="boss === null"><v-btn>죄송합니다 다시 시도해주세요</v-btn></div>
<!--    <boss-page-view v-else @savePrice="savePrice" @saveBackProfile="saveBackProfile" @saveBossProfileImg="saveBossProfileImg" @modifySave="modifySave" @deletePrice="deletePrice"></boss-page-view>-->
    <boss-read-page-view v-else :boss="boss" @saveReview="saveReview" @saveNoImageReview="saveNoImageReview" @modifyReview="modifyReview" @modifyImgReview="modifyImgReview" @deleteReview="deleteReview" @onChat="onChat"></boss-read-page-view>
  </div>
</template>

<script>

import BossReadPageView from "@/components/boss/client/BossReadPageView";
import {mapActions, mapState} from "vuex";
import axios from "axios";
import {API_BASE_URL} from "@/constant/login";
import cookies from "vue-cookies";

const config = {
  headers: {
    'Authorization': 'Bearer '+ cookies.get('access_token'),
    'Accept' : 'application/json',
    'Content-Type': 'application/json'
  }
};

export default {
  name: "BossReadPage",
  components: {BossReadPageView},
  data() {
    return {
      bossNo: this.$route.query.bossNo,
    }
  },
  methods: {
    ...mapActions(['fetchClientBossView']),
    saveReview(formData){
      axios.post(API_BASE_URL+'/boss/registerReview', formData, {
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
    },
    onChat(payload) {
      const {member1No, member2No} = payload
      console.log(member1No, member2No)
      axios.post(`http://localhost:7777/chatting/register/${member1No}/${member2No}`, {member1No, member2No})
          .then(() => {
              this.$router.push({ name: "ChattingListPage" })
          })
          .catch(() => {
              alert('문제 발생!')
          })
    },
    saveNoImageReview(formData){
      axios.post(API_BASE_URL+'/boss/registerNoImgReview', formData, {
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
    },
    modifyImgReview(formData){
      axios.post(API_BASE_URL+'/boss/modifyImgReview', formData, {
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
    modifyReview(formData){
      axios.post(API_BASE_URL+'/boss/modifyReview', formData, {
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
    deleteReview(payload){
      const {reviewNo, entityNo} = payload;
      //alert(entityNo)

      axios.post(`http://localhost:7777/boss/deleteReview/${reviewNo}/${entityNo}`, {}, config)
          .then(() => {
            this.$router.go()
          })
          .catch(() => {
            alert('삭제 에러')
          })
    }
  },
  computed: {
    ...mapState(['boss'])
  },
  mounted() {
    this.fetchClientBossView(this.bossNo);
  }
}
</script>

<style scoped>

</style>