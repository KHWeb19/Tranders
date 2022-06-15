<template>
  <div>
    <home-view></home-view>
    <after-login-view></after-login-view>

    <v-btn @click="test1()">test1</v-btn>
    <v-btn @click="test2()">test2</v-btn>
  </div>
</template>

<script>
import HomeView from "@/components/home/HomeView";
import AfterLoginView from "@/components/home/AfterLoginView";
import axios from "axios";
export default {
  name: 'HomePage',
  components: {
    HomeView,
    AfterLoginView
  },
  methods: {
    test1(){
      axios.get('http://localhost:7777/member/test1/test')
      .catch((res) => {
        alert(res +"에러")
      })
    },
    test2(){
      axios.get('http://localhost:7777/member/test2',{
        headers: {
          'Authorization': 'Bearer '+localStorage.getItem('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
          .catch((res) => {
            alert(res +"에러")
          })
          .then((res) => {
            console.log(res);
            // return  response;
          })
    }

  },
}

</script>

<style scoped>
</style>
