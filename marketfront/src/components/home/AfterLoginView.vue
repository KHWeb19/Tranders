<template>
  <header>
    <div id="header" style="height: 64px">
      <div id="header-title">
        <span id="title"> 오이마켓 </span>
      </div>

      <div>

        <router-link style="text-decoration: none;" :to="{name: 'HomePage'}">
          <v-btn text>
            중고거래
          </v-btn>

        </router-link>

        <router-link
          style="text-decoration: none"
          :to="{ name: 'CommunityBoardListPage' }"
        >
          <v-btn text> 동네생활 </v-btn>
        </router-link>

        <router-link style="text-decoration: none" :to="{ name: 'NearPage' }">
          <v-btn text> 내 근처 </v-btn>
        </router-link>

        <router-link style="text-decoration: none" :to="{ name: 'HomePage' }">
          <v-btn text> 채팅 </v-btn>
        </router-link>
      </div>

      <div style="display: flex; margin-left: auto">
        <div
          id="searchBar"
          style="display: flex; justify-content: center; padding-right: 20px"
        >
          <input
            @keyup.enter="searchStart"
            class="search"
            type="text"
            v-model="keyWord"
            placeholder="물품이나 동네를 검색해보세요"
          />
        </div>

        <div style="display: flex; margin-left: auto; align-items: center">
          <div id="searchBar" style="">
            <input type="text" placeholder="물품이나 동네를 검색해보세요">
          </div>

          <div>
            <v-menu offset-y>
              <template v-slot:activator="{on}">
                <v-btn fab  v-if="profileImg === null" v-on="on">
                  <v-img contain max-height="58" src="@/assets/profile/Tranders_base_profile_img.png"></v-img>
                </v-btn>

                <v-btn fab v-else v-on="on">
                  <v-img :src="require(`@/assets/profile/${profileImg}`)" id="img" style="height: 50px; width: 50px"></v-img>
                </v-btn>
              </template>

              <v-list>
                <v-list-item link @click="myPage">
                  <v-list-item-title>
                    My Page
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
  </header>
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
#searchBar {
  position: relative;
  width: 295px;
  padding-top: 2px;
  padding-left: 10px;
  padding-right: 10px;
}
input {
  width: 100%;
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 14px;
}
v-img {
  height: 20px;
}
</style>