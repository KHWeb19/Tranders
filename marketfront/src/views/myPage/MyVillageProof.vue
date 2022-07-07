<template>
  <div id="login-wrap">
    <after-login-view></after-login-view>

      <div id='content'>
        <v-row no-gutters>
          <v-col cols="4">
            <my-profile-bar :index="index" :userInfo="userInfo"></my-profile-bar>
          </v-col>

          <v-col cols="8">
            <my-village-proof-view @modifyLocation="modifyLocation"></my-village-proof-view>
          </v-col>
        </v-row>
      </div>

  </div>
</template>

<script>

import AfterLoginView from "@/components/home/AfterLoginView";
import MyProfileBar from "@/components/myPage/MyPageBar";
import MyVillageProofView from "@/components/myPage/MyVillageProofView";
import cookies from "vue-cookies";
import axios from "axios";
import { mapState, mapActions } from "vuex";

const config = {
  headers: {
    'Authorization': 'Bearer '+ cookies.get('access_token'),
    'Accept' : 'application/json',
    'Content-Type': 'application/json'
  }
};

export default {
  components: {MyVillageProofView, MyProfileBar, AfterLoginView},
  name: "MyVillageProof",
  props: {

  },
  data(){
    return{
      index: 2,
      id: cookies.get("id"),
    }
  },
  methods: {
    ...mapActions(['fetchMyPage']),
    modifyLocation(payload) {
      const {lat, lng, region} = payload;
      let id = this.id

      axios.post(`http://localhost:7777/member/modifyRegion/${id}`, {lat, lng, region}, config)
          .then((res) => {
            console.log(res);
          })
    }
  },
  computed: {
      ...mapState(['userInfo']),
  },
  created() {
      this.fetchMyPage(this.id)
  },
};
</script>

<style scoped>
/* #login-wrap{
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
  padding: 15px 7px 0 7px;
} */
#login-wrap{
  background: #f8f9fa;
  height: 100vh;
}
#content{
  display: block;
  margin-top: 72px;
  margin: 30px auto 0 auto;
  border-left: 1px solid #e9ecef;
  border-right: 1px solid #e9ecef;
  width: 1200px;
  margin: 0 auto;
  line-height: 24px;
  background: #fff;
}
</style>