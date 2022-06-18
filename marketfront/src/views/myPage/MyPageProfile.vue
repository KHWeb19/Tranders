<template>
  <div id="login-wrap">
    <after-login-view></after-login-view>

    <div id="my_page_box">
      <v-row no-gutters>
        <v-col cols="4">
          <my-profile-bar></my-profile-bar>
        </v-col>

        <v-col cols="8">
          <my-page-view></my-page-view>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>

import AfterLoginView from "@/components/home/AfterLoginView";
import MyProfileBar from "@/components/myPage/MyPageBar";
import MyPageView from "@/components/myPage/MyProfileView";

export default {
  components: {MyPageView, MyProfileBar, AfterLoginView},
  name: "MyPageProfile",
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