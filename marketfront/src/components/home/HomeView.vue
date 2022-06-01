<template>
  <header class="fixed-bar-box-shadow">
    <div id="header" style="height: 80px">
      <div id="header-title">
        <span id="title"> 오이 마켓 </span>
      </div>

      <div id="searchBar" style="float: left; width: 45%; height: 50px">
        <input
          type="text"
          placeholder="동네 이름, 상품 검색"
          style="width: 88%"
        />
        <v-btn icon style="width: 12%">
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
      </div>

      <div style="width: 20%"></div>

      <div class="bar-menu" style="float: left; width: 10%; height: 50px">
        <v-btn style="color: black" icon @click="loginBtn">
          <span class="btn-text">로그인</span>
        </v-btn>
      </div>

      <div class="bar-menu" style="float: left; width: 10%; height: 50px">
        <v-btn icon @click="register">
          <span class="btn-text">회원가입</span>
        </v-btn>
      </div>

      <div class="bar-menu" style="float: left; width: 10%; height: 50px">
        <v-btn icon @click="payment">
          <span class="btn-text">결제</span>
        </v-btn>
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
    },
    register() {
      this.$router.push({ name: "registerMemberPage" });
    },
    payment() {
      this.$router.push({ name: "PaymentPage" });
    },
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
  max-width: 1024px;
  margin: 0 auto;
  padding: 16px 0;
}
#searchBar {
  padding-top: 8px;
  padding-left: 10px;
  padding-right: 10px;
}
#header-title {
  display: inline-block;
  margin-right: 32px;
}
#title {
  color: green;
  font-family: ONE-Mobile-POP, serif;
  font-size: 30px;
}
input {
  width: 100%;
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 14px;
}
#searchBar {
  position: relative;
  width: 300px;
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