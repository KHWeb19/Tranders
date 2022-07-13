<template>
  <div>
    <after-login-view></after-login-view>
    <div v-if="near === null"><v-btn>죄송합니다 다시 시도해주세요</v-btn><div> 넘어왔다 {{nearNo}}</div></div>
    <near-read-page-view v-else :near="near" @saveReview="saveReview" @saveNoImageReview="saveNoImageReview" @modifyReview="modifyReview" @modifyImgReview="modifyImgReview" @deleteReview="deleteReview"></near-read-page-view>
  </div>
</template>

<script>

import axios from "axios";
import {API_BASE_URL} from "@/constant/login";
import cookies from "vue-cookies";
import {mapActions, mapState} from "vuex";
import NearReadPageView from "@/components/near/NearReadPageView";
import AfterLoginView from "@/components/home/AfterLoginView";

const config = {
  headers: {
    'Authorization': 'Bearer '+ cookies.get('access_token'),
    'Accept' : 'application/json',
    'Content-Type': 'application/json'
  }
};

export default {
  name: "NearReadPage",
  components: {AfterLoginView, NearReadPageView},
  data() {
    return {
      nearNo: this.$route.query.nearNo,
    }
  },
  methods: {
     ...mapActions(['fetchNearCommPage']),
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
              }, 100)
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
     ...mapState(['near'])
  },
  mounted() {
    this.fetchNearCommPage(this.nearNo);
  }
}
</script>

<style scoped>

</style>