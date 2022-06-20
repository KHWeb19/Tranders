<template>
  <div>
    <login-view @login="login"></login-view>
  </div>
</template>
<script>
import LoginView from "@/components/member/LoginView";
import cookies from "vue-cookies";
import {ParsingInfo} from "@/views/Util/LoginUtil";
import axios from "axios";
import {SAVE_COOKIE_ACCESS, SAVE_COOKIE_REFRESH} from "@/constant/login";

export default {
  name: "LoginPage",
  components: {LoginView},
  data () {
    return {
      isLogin: false
    }
  },
  mounted() {
    this.isLogin = !(cookies.get('access_token') === null && cookies.get('refresh_token') === null);

    alert(this.isLogin)
  },
  methods: {
    async login(payload){
      if(!this.isLogin) {
        const {id, password} = payload;
        await axios.post("http://localhost:7777/member/login", {id, password})
            .catch((res) => {
              alert(res + "에러 발생")
            })
            .then((res) => {
              if(res.data.access_token == null){
                alert('이메일 또는 비밀번호를 확인해주세요.');
                this.$router.go();
              } else {
                console.log(res)

                cookies.set('access_token', res.data.access_token, SAVE_COOKIE_ACCESS);
                cookies.set('refresh_token', res.data.refresh_token, SAVE_COOKIE_REFRESH);

                ParsingInfo(res.data.access_token);
                this.$router.push({name: "HomePage"}) // 로그인 후 어디로?
              }

            });
      }else {
        alert('이미 로그인 되어있습니다.');
        this.$router.push({name: "HomePage"}) // 로그인 후 어디로?
      }
    }
  }
}

</script>

<style scoped>

</style>