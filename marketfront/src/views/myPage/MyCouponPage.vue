<template>
  <div id="login-wrap">
    <after-login-view></after-login-view>

    <div id='content'>
      <v-row no-gutters>
        <v-col cols="4">
          <my-profile-bar :index="index" :userInfo="userInfo"></my-profile-bar>
        </v-col>

        <v-col cols="8">
          <my-coupon-view @useCoupon="useCoupon"></my-coupon-view>
        </v-col>
      </v-row>
    </div>

  </div>
</template>

<script>
import MyProfileBar from "@/components/myPage/MyPageBar";
import MyCouponView from "@/components/myPage/MyCouponView";
import {mapActions, mapState} from "vuex";
import axios from "axios";
import {API_BASE_URL} from "@/constant/login";
import cookies from "vue-cookies";
import AfterLoginView from "@/components/home/AfterLoginView";

const config = {
  headers: {
    'Authorization': 'Bearer '+ cookies.get('access_token'),
    'Accept' : 'application/json',
    'Content-Type': 'application/json'
  }
};

export default {
  name: "MyCouponPage",
  components: {AfterLoginView, MyCouponView, MyProfileBar},
  data() {
    return {
      index: 5,
      memberId: cookies.get("id")
    }
  },
  methods: {
    ...mapActions(['fetchMyPage']),
    useCoupon(payload){
      const {couponNo, id} = payload;

      axios.post(API_BASE_URL+'/member/couponUse', {couponNo, id}, config)
          .then((res) => {
            console.log(res);
          })
          .catch(() => {
            alert('에러')
          })
    }
  },
  computed: {
    ...mapState(['userInfo']),
  },
  created() {
    this.fetchMyPage(this.memberId)
  }
}
</script>

<style scoped>
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