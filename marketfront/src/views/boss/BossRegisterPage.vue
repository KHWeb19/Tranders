<template>
  <div id="login-wrap">
    <after-login-view></after-login-view>
    <boss-register-view @registerBoss="registerBoss"></boss-register-view>
  </div>
</template>

<script>
import cookies from "vue-cookies";
import AfterLoginView from "@/components/home/AfterLoginView";
import BossRegisterView from "@/components/boss/BossRegisterView";
import axios from "axios";
import {API_BASE_URL} from "@/constant/login";

/*const config = {
  headers: {
    'Authorization': 'Bearer '+ cookies.get('access_token'),
    'Accept' : 'application/json',
    'Content-Type': 'multipart/form-data'
  }
};*/

export default {
  name: "BossRegisterPage",
  components: {BossRegisterView, AfterLoginView},
  data(){
    return{
      id: cookies.get('id'),

    }
  },
  methods: {
    registerBoss(formData) {

      axios.post(API_BASE_URL+'/boss/register', formData)
          .then((res) => {
            console.log(res)
            if(res.data === true){
              this.$router.push({name: 'BossPage'})
            }
            else {
              alert('에러 발생했습니다')
              this.$router.go();
            }
          })
          .catch(() => {
            alert('에러');
          })
    }
  }
}
</script>

<style scoped>
#login-wrap{
  width: 100%;
  max-width: 1440px;
  margin: 0 auto;
  text-align: left;
}

</style>