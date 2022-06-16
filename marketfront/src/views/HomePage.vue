<template>
  <div>
    <home-view></home-view>

    <v-btn @click="test1()">test1</v-btn>
    <v-btn @click="test2()">test2</v-btn>
    <v-btn @click="test3()">test3</v-btn>
  </div>
</template>

<script>

import HomeView from "@/components/home/HomeView";
import axios from "axios";

import cookies from "vue-cookies";
export default {
  name: 'HomePage',
  components: {
    HomeView,
  },
  methods: {
    test1(){
      axios.get('http://localhost:7777/member/test1/test')
      .catch((res) => {
        alert(res +"에러")
      })
          .then((res) => {
            console.log(res)
          })
    },
    test2(){
      axios.get('http://localhost:7777/member/test2',{
        headers: {
          'Authorization': 'Bearer '+ cookies.get('oauth2_auth_request'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
          .catch((res) => {
            console.log(res);
          })
          .then((res) => {
            console.log(res)
          })
    },
    test3(){
      axios.post('http://localhost:7777/member/info', {})
          .then((res) => {
            console.log(res);
          })
    }
  }
};
</script>
