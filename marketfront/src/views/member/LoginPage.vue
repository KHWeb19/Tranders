<template>
  <div>
    <login-view @login="login"></login-view>
  </div>
</template>
<script>
import LoginView from "@/components/member/LoginView";
import axios from "axios";
import {FETCH_LOGIN_TOKEN} from "@/store/mutation-types";
import cookies from "core-js/internals/queue";

export default {
  name: "LoginPage",
  components: {LoginView},
  data () {
    return {
      isLogin: false
    }
  },
  mounted() {
    this.isLogin = !!(cookies.get('access_token') === null && cookies.get('refresh_token'));

    alert(this.isLogin)
  },
  methods: {
    login(payload){
      if(!this.isLogin) {
        const {id, password} = payload;

        axios.post("http://localhost:7777/member/login", {id, password})
            .catch((res) => {
              alert(res + "에러 발생")
            })
            .then((res) => {
              console.log(res)
              let accessToken = res.data.access_token;
              let refreshToken = res.data.refresh_token;
              this.$store.commit(FETCH_LOGIN_TOKEN, res.data)
              localStorage.setItem('refresh_token', refreshToken)
              cookies.set("access_token", accessToken, 60 * 10); // 10분
              cookies.set("refresh_token", refreshToken, 60 * 60 * 3); // 3시간
              this.$router.push({name: "HomePage"})
            })
      }else {
        alert('이미 로그인 되어있는 사용자입니다.');
        this.$router.push({name: "HomePage"}) // 로그인 후 이동 페이지
      }
    }
  }
}
</script>

<style scoped>

</style>