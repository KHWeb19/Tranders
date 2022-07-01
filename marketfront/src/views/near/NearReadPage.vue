<template>
  <div>
    <div v-if="near === null"><v-btn>죄송합니다 다시 시도해주세요</v-btn><div> 넘어왔다 {{nearNo}}</div></div>
<!--    <boss-page-view v-else @savePrice="savePrice" @saveBackProfile="saveBackProfile" @saveBossProfileImg="saveBossProfileImg" @modifySave="modifySave" @deletePrice="deletePrice"></boss-page-view>-->
<!--    <boss-read-page-view v-else :boss="boss" @saveReview="saveReview"></boss-read-page-view>-->
    <near-read-page-view v-else :near="near" @saveReview="saveReview"></near-read-page-view>
  </div>
</template>

<script>

import axios from "axios";
import {API_BASE_URL} from "@/constant/login";
import cookies from "vue-cookies";
import {mapActions, mapState} from "vuex";
import NearReadPageView from "@/components/near/NearReadPageView";

export default {
  name: "NearReadPage",
  components: {NearReadPageView},
  data() {
    return {
      nearNo: this.$route.query.nearNo,
    }
  },
  methods: {
     ...mapActions(['fetchNearCommPage']),
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
              this.$router.go()
            }
          })
          .catch(() => {
            alert('에러')
          })
    },
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