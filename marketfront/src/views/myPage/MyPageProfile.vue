<template>
  <div id="login-wrap">
    <after-login-view></after-login-view>

      <div id='content'>
        <v-row no-gutters>
          <v-col cols="4">
            <my-profile-bar :userInfo="userInfo"></my-profile-bar>
          </v-col>

          <v-col cols="8">
            <my-page-view :userInfo="userInfo"></my-page-view>
          </v-col>
        </v-row>
      </div>
  </div>
</template>

<script>

import AfterLoginView from "@/components/home/AfterLoginView";
import MyProfileBar from "@/components/myPage/MyPageBar";
import MyPageView from "@/components/myPage/MyProfileView";
import { mapState, mapActions } from 'vuex'
import cookies from "vue-cookies";

export default {
  components: {MyPageView, MyProfileBar, 
  AfterLoginView,
  },
  name: "MyPageProfile",
  data() {
    return {
      memberId: cookies.get("id")
    }
  },
  computed: {
      ...mapState(['userInfo']),
  },
  created() {
      this.fetchMyPage(this.memberId)
  },
  methods: {
      ...mapActions(['fetchMyPage']),
  }
};
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