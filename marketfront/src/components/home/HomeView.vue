<template>
  <header class="fixed-bar-box-shadow">
    <div id="header" style="height: 70px">
      <div id="header-title">
        <span id="title"> 오이마켓 </span>
      </div>

      <div>
        <router-link style="text-decoration: none;" :to="{name: 'HomePage'}">
          <v-btn text>
            중고거래
          </v-btn>
        </router-link>
      </div>

      <div style="display: flex; margin-left: auto; align-items: center">
        <div id="searchBar">
          <input
            type="text"
            placeholder="물품이나 동네를 검색해보세요"
          />
        </div>

        <div class="bar-menu" style="float: left; width: auto; height: auto; padding-left: 20px">
          <v-btn style="color: black; font-size: 20px; font-weight: lighter" icon @click="loginBtn">
            <span class="btn-text">로그인</span>
          </v-btn>
        </div>
      </div>
    </div>
  </header>

</template>

<script>
export default {
  name: "HomeView",
  methods: {
    loginBtn() {
      this.$router.push({ name: "LoginPage" });
    }
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
#searchBar {
  position: relative;
  width: 295px;
  padding-top: 8px;
  padding-left: 10px;
  padding-right: 10px;
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
input {
  width: 100%;
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 14px;
}
.bar-menu {
  display: inline-flex;
  width: 10%;
  height: 50px;
  justify-content: center;
  align-items: center;
}
.btn-text {
  color: black;
}
v-btn {
  border-radius: 8px;
  border: solid 1px black;
}
</style>