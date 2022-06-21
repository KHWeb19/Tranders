<template>

  <header>
    <div id="header" style="height: 64px;">

      <div id="header-title">
        <span id="title">
          오이마켓
        </span>
      </div>

      <div id="searchBar">
        <!-- <input type="text" placeholder="동네 이름, 상품 검색" style=" width: 88%">
        <v-btn icon style="width: 12%"> -->

        <input type="text" placeholder="물품이나 동네를 검색해보세요">
        <!-- <v-btn icon style="width: 12%">
          <v-icon>mdi-magnify</v-icon>
        </v-btn> -->
      </div>


      <div id="itemList" style="margin-left: auto;">
        <!-- <v-btn text class="black--text">
          <router-link style="text-decoration: none;" :to="{name: 'HomePage'}"> -->

      <div style="margin-left: auto;">
        <router-link style="text-decoration: none;" :to="{name: 'HomePage'}">
          <v-btn text>
            중고거래
          </v-btn>
        </router-link>

        <router-link style="text-decoration: none;" :to="{name: 'CommunityBoardListPage'}">
          <v-btn text>
            동네생활
          </v-btn>
        </router-link>

        <router-link style="text-decoration: none;" :to="{name: 'HomePage'}">
          <v-btn text>
            내 근처
          </v-btn>
        </router-link>

        <router-link style="text-decoration: none;" :to="{name: 'HomePage'}">
          <v-btn text>
            채팅
          </v-btn>
        </router-link>

        <v-btn text>
          <router-link style="text-decoration: none;" :to="{name: 'HomePage'}">
            나의 당근 
          </router-link>
        </v-btn>

        <!-- <div style=""> -->
          <v-btn fab @click="myPage" v-if="profileImg === null">
            <v-img contain max-height="58" src="@/assets/profile/Tranders_base_profile_img.png"></v-img>
          </v-btn>

          <v-btn fab @click="myPage" v-else>
            <v-img :src="require(`@/assets/profile/${profileImg}`)" id="img" style="height: 50px; width: 50px"></v-img>
          </v-btn>
        <!-- </div> -->
      </div>


    </div>
    </div>
  </header>
</template>

<script>
import {mapActions, mapState} from "vuex";
import cookies from "vue-cookies";

export default {
  name: "AfterLoginView",
  data(){
    return{
      id: ""
    }
  },
  methods: {
    ...mapActions(['fetchMemberProfile']),
    myPage(){
      this.$router.push({name: 'MyPageProfile'})
    }
  },
  computed: {
    ...mapState(['profileImg'])
  },
  mounted() {
    this.id = cookies.get('id');
    this.fetchMemberProfile(this.id)
  }
}
</script>

<style scoped>
@font-face {
  font-family: 'ONE-Mobile-POP';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/ONE-Mobile-POP.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
#header{
  display: flex;
  align-items: center;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 16px 0;
}
#header-title{
  display: inline-block;
  margin-right: 16px;
}
#title{
  color: green;
  font-family: ONE-Mobile-POP,serif;
  font-size: 26px;
}
#searchBar{
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
/* .bar-menu{
  display: inline-flex;
  width: 10%;
  height: 50px;
  justify-content: center;
  align-items: center;
} */
.btn-text {
  color: black;
}
/* v-btn{
  border-radius: 8px;
  border: solid 1px black;
} */
v-img{
  height: 20px;
}
</style>