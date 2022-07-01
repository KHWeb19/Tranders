<template>
  <div id='nav'>
    <div id="header">
      <router-link id="title" :to="{name: 'HomePage'}">
        오이마켓
      </router-link>

      <div id='nav_list'>
        <router-link id='nav_item' :to="{name: 'ProductListPage'}">
          중고거래
        </router-link>
        <router-link id='nav_item' :to="{ name: 'CommunityBoardListPage' }">
          동네생활
        </router-link>
        <router-link id='nav_item' :to="{ name: 'NearPage' }">
          내 근처
        </router-link>
        <router-link id='nav_item' :to="{ name: 'ChattingListPage' }">
          채팅
        </router-link>
      </div>

      <div id='nav_right'>
        <div id="searchBar">
          <input
          @keyup.enter="searchStart"
          class="search"
          type="text"
          v-model="keyWord"
          placeholder="물품이나 동네를 검색해보세요"
          />
        </div>

        <div>
          <v-menu offset-y>
            <template v-slot:activator="{on}">
              <v-btn v-if="profileImg === null" v-on="on">
                <v-img contain max-height="58" src="@/assets/profile/Tranders_base_profile_img.png"></v-img>
              </v-btn>

              <v-btn id='my_button' depressed v-else v-on="on">
                <v-img width="24" height="24" :src="require(`@/assets/profile/${profileImg}`)"></v-img>&nbsp;▼
              </v-btn>
            </template>

            <v-list>
              <v-list-item link @click="myPage">
                <v-list-item-title>
                  내 정보
                </v-list-item-title>
              </v-list-item>

              <v-list-item link @click="logout">
                <v-list-item-title>
                  로그아웃
                </v-list-item-title>
              </v-list-item>

            </v-list>
          </v-menu>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import cookies from "vue-cookies";
import { logout } from "@/views/Util/LoginUtil";
import axios from "axios";

export default {
  name: "AfterLoginView",
  data() {
    return {
      id: "",
    }
  },
  methods: {
    ...mapActions(["fetchMemberProfile"]),
    myPage() {
      this.$router.push({ name: "MyPageProfile" });
    },
    logout() {
      logout();
      this.$router.push({name: 'HomePage'})
    },
    test() {
      alert("test");
    },
    searchStart() {
      const keyWord = this.keyWord;
      console.log(keyWord);

      axios
        .post("http://localhost:7777/product/search", { keyWord })
        .then((res) => {
          console.log("검색성공");
          console.log(res.data);
          if (this.$route.path !== "/productSearchList")
            this.$router.push({
              name: "ProductSearchListPage",
              params: { searchList: res.data },
            });
        });
    },
  },
  computed: {
    ...mapState(["profileImg"]),
  },
  mounted() {
    this.id = cookies.get("id");
    this.fetchMemberProfile(this.id);
  },
};
</script>

<style scoped>
@font-face {
  font-family: "ONE-Mobile-POP";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/ONE-Mobile-POP.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
#nav{
  background-color: white;
  color: #212124;
  top: 0;
  left: 0;
  width: 100%;
  max-width: 100vw;
  /* position: fixed;
  z-index: 999; */
}
#header {
  max-width: 1200px;
  height: 64px;
  padding: 12px 16px;
  box-sizing: border-box;
  justify-content: space-between;
  width: 100%;
  margin: 0 auto;
  position: relative;
  display: flex;
  align-items: center;
}
#title {
  text-decoration: none;
  width: 160px;
  color: green;
  font-family: ONE-Mobile-POP, serif;
  font-size: 26px;
}
#nav_list{
  display: block;
  width: 100%;
  padding-top: 0;
  padding-right: 40px;
  padding-bottom: 0;
  padding-left: 40px;
}
#nav_list .router-link-active, 
#nav_list .router-link-exact-active {
  text-decoration: none;
  line-height: 1.32;
  font-size: 18px;
  font-weight: 700;
  font-style: normal;
  margin-right: 30px;
  color: green
}
#nav_item{
  text-decoration: none;
  line-height: 1.32;
  font-size: 18px;
  font-weight: 700;
  font-style: normal;
  margin-right: 30px;
  color: #4d5159;
}
#nav_right{
  display: flex;
  justify-content: center;
  align-items: center;
}
#searchBar {
  display: block;
  position: relative;
  margin-right: 12px;
}
input {
  width: 288px;
  line-height: 1.36;
  font-size: 16px;
  background-color: #f2f3f6;
  box-sizing: border-box;
  height: 40px;
  padding: 9px 12px;
  border: none;
  border-radius: 6px;
  color: #212124;
}
#my_button{
  line-height: 1.3;
  font-size: 16px;
  letter-spacing: -2%;
  display: block;
  white-space: nowrap;
  background-color: white;
  color: #212124;
  height: 40px;
  font-weight: 700;
  /* width: fit-content; */
  min-height: 28px;
  padding: 5px 10px;
  border-radius: 3px;
  border: 1px solid #d1d3d8;
  background: none;
  appearance: none;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
</style>