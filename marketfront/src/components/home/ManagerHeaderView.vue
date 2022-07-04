<template>
  <header>
    <div id="header" style="height: 64px;">
      <div style="width: 60%; display: flex">
        <div id="header-title">
          <span id="title"> 오이마켓 </span>
        </div>

        <div>
          <router-link style="text-decoration: none; padding-left: 10px" :to="{name: 'ReportManagementPage'}">
            <v-btn text>
              신고 관리
            </v-btn>
          </router-link>
        </div>

        <div>
          <router-link style="text-decoration: none; padding-left: 10px" :to="{name: 'ManagerMemberPage'}">
            <v-btn text>
              사용자 관리
            </v-btn>
          </router-link>
        </div>
      </div>
      <div class="bar-menu" style="display: flex; justify-content: end; float: left; width: 40%; height: auto; padding-left: 20px;">
        <v-btn style="color: black; font-size: 20px; font-weight: lighter;" text @click="logout">
          <span class="btn-text">로그아웃</span>
        </v-btn>
      </div>
<!--      <div style="display: flex; justify-content: end">
        <div>
          <v-img max-height="58" max-width="58" src="@/assets/profile/Tranders_base_profile_img.png"></v-img>
        </div>
      </div>-->
    </div>
  </header>
</template>

<script>
import {logout} from "@/views/Util/LoginUtil";

export default {
  name: "ManagerHeaderView",
  methods: {
    logout() {
      logout();
      this.$router.push({name: 'HomePage'})
    }
  }
}
</script>

<style scoped>
#header {
  display: flex;
  align-items: center;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 16px 0;
}
#header-title {
  display: inline-block;
  margin-right: 16px;
}
#title {
  color: green;
  font-family: ONE-Mobile-POP, serif;
  font-size: 26px;
}
</style>