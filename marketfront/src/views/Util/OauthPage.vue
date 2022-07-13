<template>
  <div>
  </div>


</template>

<script>

import {ParsingInfo} from "@/views/Util/LoginUtil";
import cookies from "vue-cookies";
import {SAVE_COOKIE_ACCESS, SAVE_COOKIE_REFRESH} from "@/constant/login";

export default {
  name: "OauthPage",
  data(){
    return {
      token: '',
      data: '',
    }
  },
  methods: {
    test(){

    }
  },
  created() {
    this.token = this.$route.query;

    cookies.set('access_token', this.token.access_token, SAVE_COOKIE_ACCESS);
    cookies.set('refresh_token', this.token.refresh_token, SAVE_COOKIE_REFRESH);

    setTimeout(() => {ParsingInfo(this.token.access_token)}, 500);

    const promise = new Promise((resolve) => {
      setTimeout(function () {
        console.log(cookies.get('registerStatus'))
        resolve(cookies.get('registerStatus'))
      }, 500)
    })

    promise.then((response) => {
      console.log(response)
      if(response === 'true') {
        this.$router.push({name: "HomePage"}) // 로그인 후 어디로?
      }else{
        this.$router.push({name: 'SnsRegisterPage'})
      }
    })

    /*let status = cookies.get('registerStatus')
    console.log(status);

    if(status) {
      this.$router.push({name: "HomePage"}) // 로그인 후 어디로?
    }else{
      this.$router.push({name: 'SnsRegisterPage'})
    }*/
  }
}
</script>

<style scoped>

</style>