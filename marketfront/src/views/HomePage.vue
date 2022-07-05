<template>
  <div>
    <home-view></home-view>
    <after-login-view></after-login-view>
    <div id="home">
      <div id="top">
        <div>
          <h1 id="title">오늘은 이거사자<br/>오이마켓</h1>
          <p id="text">중고 거래부터 동네 정보까지, 이웃과 함께해요.<br/>가깝고 따뜻한 당신의 근처를 만들어요.</p>
        </div>
        <div id='image'>
          <v-img src="https://d1unjqcospf8gs.cloudfront.net/assets/home/main/3x/image-top-d6869a79bc4cb58ea59aa5a408decfdf4a4ba60ac639837081da12861083cdbb.webp "/>
        </div>
      </div>
    </div>
    <div>중고거래 인기매물 컴포넌트 부분</div>
    <footer-view/>
  </div>
</template>

<script>
import HomeView from "@/components/home/HomeView";
import AfterLoginView from "@/components/home/AfterLoginView";
import FooterView from '@/components/home/FooterView.vue';

export default {
  name: 'HomePage',
  components: {
    AfterLoginView,
    HomeView,
    FooterView
  },
  methods: {

  }
};
</script>

<style scoped>
#home{
  padding-top: 24px;
  background-color: #E6F3E6;;
}
#top{
  width: 1024px;
  height: 760px;
  margin: 0 auto;
  position: relative;
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
#image{
  position: absolute;
  right: -164px;
  bottom: 0;
  width: 804px;
  height: 685px;
  /* background-color: #FBF7F2; */
  background-repeat: no-repeat;
  background-size: 804px 685px;
}
</style>