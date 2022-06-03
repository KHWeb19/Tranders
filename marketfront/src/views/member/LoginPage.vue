<template>
  <div>
    <login-view @login="login"></login-view>
  </div>
</template>

<script>
import LoginView from "@/components/member/LoginView";
import axios from "axios";
export default {
  name: "LoginPage",
  components: {LoginView},
  methods: {
    login(payload){
      const {id, password} = payload;

      axios.post("http://localhost:7777/login", {id, password})
        .catch((res) => {
          alert(res + "에러 발생")
        })
        .then((res) => {
          console.log(res)
          console.log(res.headers.Authorization)
          console.log(res.headers)
          console.log(res.status)
          console.log(res.headers['Authorization'])
          this.$router.push({name: "HomePage"})
        })
    }
  }
}
</script>

<style scoped>

</style>