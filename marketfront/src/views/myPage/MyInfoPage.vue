<template>
  <div id="login-wrap">
    <after-login-view></after-login-view>

    <div id="my_page_box">
      <v-row no-gutters>
        <v-col cols="4">
          <my-profile-bar :index="index"></my-profile-bar>
        </v-col>

        <v-col cols="8">
          <my-info-view></my-info-view>
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

export default {
  components: {MyInfoView, MyProfileBar, AfterLoginView},
  name: "MyInfoPage",
  data(){
    return{
      index: 0
    }
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