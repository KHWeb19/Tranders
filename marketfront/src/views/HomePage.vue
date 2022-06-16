<template>
  <div>
    <home-view></home-view>
    <after-login-view></after-login-view>

    <v-btn @click="test1()">test1</v-btn>
    <v-btn @click="test2()">test2</v-btn>
    <v-btn @click="test3()">test3</v-btn>
  </div>
</template>

<script>
import HomeView from "@/components/home/HomeView";
import AfterLoginView from "@/components/home/AfterLoginView";
import axios from "axios";
import cookies from "vue-cookies";
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
          'Authorization': 'Bearer '+cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
          .catch((res) => {
            console.log(res);
          })
    },
    test3(){
      axios.post('http://localhost:7777/member/info')
          .then((res) => {
            console.log(res);
          })
    }
  }
};
</script>