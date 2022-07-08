<template>
  <div>
    <article id="content">
      <section id="profile">
        <div class="space-between">
          <div style="display: flex">
            <div id="profile-image">
              <swiper class="swiper position-relative" :options="swiperOption">
                <swiper-slide
                  ><v-img
                    width="650px"
                    height="650px"
                    :src="
                      require(`@/assets/pImage/${productBoard.productImage}`)
                    "
                /></swiper-slide>
                <swiper-slide v-if="productBoard.productImage2"
                  ><v-img
                    width="650px"
                    height="650px"
                    :src="
                      require(`@/assets/pImage/${productBoard.productImage2}`)
                    "
                /></swiper-slide>
                <swiper-slide v-if="productBoard.productImage3"
                  ><v-img
                    width="650px"
                    height="650px"
                    :src="
                      require(`@/assets/pImage/${productBoard.productImage3}`)
                    "
                /></swiper-slide>
                <div class="swiper-pagination" slot="pagination"></div>
                <div class="swiper-button-prev" slot="button-prev"></div>
                <div class="swiper-button-next" slot="button-next"></div>
              </swiper>
            </div>
          </div>
        </div>
      </section>
      <router-link
        :to="{
          name: 'ProfileBasicPage',
          params: { memberId: productBoard.member.id.toString() },
        }"
        style="text-decoration: none"
      >
        <section id="article-profile">
          <div id="article-profile-link">
            <div class="space-between">
              <div style="display: flex">
                <div id="article-profile-image">
                  <img src="@/assets/profile/Tranders_base_profile_img.png" />
                </div>

                <div id="profile-left">
                  <div id="userName">
                    {{ productBoard.member.name }}
                  </div>
                  <div id="region-name">{{ productBoard.member.region }}</div>
                </div>
              </div>

              <div id="profile-right">
                <dl id="temperature-wrap">
                  <dt>매너온도</dt>
                  <dd class="text-color-03">
                    {{ productBoard.member.temperature }}
                    <span>°C</span>
                  </dd>
                </dl>
                <v-row>
                  <v-col style="padding: 0 6px">
                    <v-slider
                      max="100"
                      min="0"
                      :label="ex1.label"
                      :color="ex1.color"
                      :track-color="ex1.color"
                      readonly
                      :value="productBoard.member.temperature"
                      class="slider"
                    ></v-slider>
                  </v-col>
                </v-row>
                <div class="meters">
                  <div class="bar bar-color-03" style="width: 37%"></div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </router-link>

      <section id="description">
        <p id="process">
          {{ productBoard.process }}
        </p>
        <h1 property="schema:name" id="title" style="margin-top: 0px">
          {{ productBoard.title }}
        </h1>

        <p id="category">
          {{ productBoard.category }} •
          {{ productBoard.updDate | timeForToday }}
        </p>
        <p
          id="price"
          property="schema:price"
          content
          style="font-size: 18px; font-weight: bold"
        >
          <span class="text-light-gray"
            >{{ productBoard.price | makeComma }}원</span
          >
        </p>

        <div property="schema:content" id="detail">
          <p>{{ productBoard.content }}</p>
        </div>

        <p id="counts">
          관심 5 ∙ 채팅 {{ productBoard.chatCnt }} ∙ 조회
          {{ productBoard.viewCnt }}
        </p>

        <v-btn
          v-if="login.memberNo != productBoard.member.memberNo"
          @click="onChat"
          >채팅하기</v-btn
        >
      </section>

      <section
        @click="reportDialog = true"
        id="repo"
        style="display: flex; align-items: center; height: 50px"
      >
        <!-- 작성자와 같은지 확인하기 -->
        <div style="width: 100%">이 게시글 신고하기</div>
        <div style="display: flex; justify-content: end">
          <v-icon style="color: black">mdi-chevron-right</v-icon>
        </div>
      </section>

      <report-dialog-view
        v-if="reportDialog"
        @closeDialog="closeDialog"
        @sendReport="sendReport"
        @sendImgReport="sendImgReport"
        :productNo="this.productBoard.productNo"
      ></report-dialog-view>
    </article>
  </div>
</template>

<script>
import Vue from "vue";
import cookies from "vue-cookies";
Vue.use(cookies);

import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import ReportDialogView from "@/components/productBoard/report/ReportDialogView";
import { mapActions, mapState } from "vuex";
import axios from "axios";

export default {
  name: "ProductReadPage",
  props: {
    productBoard: {
      type: Object,
      required: true,
    },
  },
  components: {
    ReportDialogView,
    Swiper,
    SwiperSlide,
  },
  data() {
    return {
      swiperOption: {
        effect: "coverflow",
        grabCursor: true,
        pagination: {
          el: ".swiper-pagination",
        },
        swiperOption: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
      },
      login: {
        memberNo: cookies.get("memberNo"),
        id: cookies.get("id"),
        name: cookies.get("name"),
        access_token: cookies.get("access_token"),
      },
      reportDialog: false,
      ex1: {
        color: "green",
      },
    };
  },
  computed: {
    ...mapState(["myProductLikes"]),
    ...mapState(['registerChat']),
  },
  mounted() {
    this.fetchRegisterChat({member1No:this.login.memberNo, member2No:this.productBoard.member.memberNo})
  },
  methods: {
    ...mapActions(["fetchProductLike"]),
    ...mapActions(['fetchRegisterChat']),
    onChat() {
      axios
        .post(
          `http://localhost:7777/chatting/register/${this.login.memberNo}/${this.productBoard.member.memberNo}/${this.productBoard.productNo}`,
          { member1No:this.login.memberNo, member2No:this.productBoard.member.memberNo, productNo:this.productBoard.productNo })
        .then(() => {
          if(!(this.registerChat)){
          this.fetchRegisterChat({member1No:this.login.memberNo, member2No:this.productBoard.member.memberNo})
          }
          this.$emit("onChat", {registerNo: this.registerChat.roomNo})
        })
    },
    closeDialog() {
      this.reportDialog = false;
    },
    sendReport(payload) {
      this.$emit("sendReport", payload);
    },
    sendImgReport(payload) {
      this.$emit("sendImgReport", payload);
    },
  },
};
</script>

<style scoped>
.hide {
  position: absolute;
  left: -9999px;
  top: -9999px;
}
#content {
  margin-top: 94px;
  padding-bottom: 0;
  display: block;
}
#profile {
  width: 677px;
  margin: auto;
  text-decoration: none;
  display: block;
  margin-top: 25px;
  padding-bottom: 23px;
  position: relative;
  border-bottom: 1px solid #e9ecef;
}
#article-profile {
  width: 677px;
  margin: 0 auto;
  text-decoration: none;
}
#article-profile-link {
  text-decoration: none;
  display: block;
  margin-top: 25px;
  padding-bottom: 23px;
  position: relative;
  border-bottom: 1px solid #e9ecef;
}
.space-between {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
#profile-left {
  display: inline-block;
  margin-left: 8px;
}
#article-profile-image {
  display: inline-block;
}
img {
  width: 40px;
  height: 40px;
  object-fit: cover;
}
#userName {
  font-size: 15px;
  font-weight: 600;
  line-height: 1.5;
  letter-spacing: -0.6px;
  color: #212529;
  text-decoration: none;
}
#region-name {
  font-size: 13px;
  line-height: 1.46;
  letter-spacing: -0.6px;
  color: #212529;
}
#profile-right {
  position: absolute;
  right: 0;
  top: 0;
  padding-right: 30px;
}
#description {
  padding: 15px 0;
  width: 677px;
  margin: 0 auto;
  border-bottom: 1px solid #e9ecef;
}
#title {
  font-size: 20px;
  font-weight: 600;
  line-height: 1.5;
  letter-spacing: -0.6px;
  margin-bottom: 0;
}
#process {
  max-width: 80px;
  font-size: 20px;
  font-weight: 700;
  line-height: 1.5;
  letter-spacing: -0.6px;
  margin-top: 0;
  border: 0px solid white;
  background-color: green;
  border-radius: 6px;
  text-align: center;
  color: white;
}
#category {
  margin-top: 0px;
  font-size: 13px;
  line-height: 1.46;
  letter-spacing: -0.6px;
  color: #868e96;
}
#price {
  margin-top: 0px;
  font-size: 18px;
  font-weight: bold;
  line-height: 1.76;
  letter-spacing: -0.6px;
}
#detail p {
  font-size: 17px;
  line-height: 1.6;
  letter-spacing: -0.6px;
  margin: 16px 0;
  word-break: break-all;
}
#counts {
  font-size: 13px;
  line-height: 1.46;
  letter-spacing: -0.6px;
  color: #868e96;
}
#repo {
  padding: 32px 0;
  width: 677px;
  margin: 0 auto;
  border-top: 1px solid #868e96;
  border-bottom: 1px solid #868e96;
  font-weight: bolder;
}
dl {
  display: block;
  width: 100px;
}
dl dt {
  position: absolute;
  top: 25px;
  left: 60px;
  font-size: 12px;
  line-height: 1;
  letter-spacing: -0.6px;
  color: #868e96;
}
.text-color-03 {
  color: green;
}
dl dd {
  position: relative;
  font-size: 16px;
  font-weight: bold;
  line-height: 1;
  letter-spacing: -0.5px;
  margin-top: 0px;
  width: 100px;
  text-align: right;
}
.swiper {
  width: 100%;
  height: 100%;
  border-radius: 18px;
}
.swiper-slide {
  background-color: white;
  background-position: center;
  background-size: cover;
}
.dark {
  background: #444;
}
.light {
  background: #fff;
}
.slider {
  clear: both;
  display: block;
  position: relative;
}
</style>