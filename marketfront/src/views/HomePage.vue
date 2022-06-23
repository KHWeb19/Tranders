<template>
  <div>
    <home-view></home-view>
    <after-login-view></after-login-view>
    <v-btn @click="test1()">test1</v-btn>
    <v-btn @click="test2()">test2</v-btn>
    <v-btn @click="test3()">test3</v-btn>
    <div id="home">
      <div id="top">
        <h1 id="title">오늘은 이거사자<br/>오이마켓</h1>
        <p id="text">중고 거래부터 동네 정보까지, 이웃과 함께해요.<br/>가깝고 따뜻한 당신의 근처를 만들어요.</p>
      </div>
    </div>
    <div>중고거래 인기매물 컴포넌트 부분</div>
    <footer-view/>
  </div>
</template>

<script>
import axios from "axios";
import cookies from "vue-cookies";
import HomeView from "@/components/home/HomeView";
import AfterLoginView from "@/components/home/AfterLoginView";
import FooterView from '@/components/home/FooterView.vue';

const config = {
  headers: {
    'Authorization': 'Bearer '+ cookies.get('access_token'),
    'Accept' : 'application/json',
    'Content-Type': 'application/json'
  }
};

export default {
  name: 'HomePage',
  components: {
    AfterLoginView,
    HomeView,
    FooterView
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
          'Authorization': 'Bearer '+cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
      axios.get('http://localhost:7777/member/test2', config)
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

<style scoped>
#home{
  padding-top: 24px;
  background-color: #FBF7F2;
}
#top{
  width: 1024px;
  height: 760px;
  margin: 0 auto;
}
#title{
  font-size: 48px;
  line-height: 1.3;
  padding-top: 270px;
  margin-bottom: 32px;
  letter-spacing: -0.4px;
}
#text{
  font-size: 16px;
  line-height: 1.5;
  letter-spacing: -0.3px;
}
</style>