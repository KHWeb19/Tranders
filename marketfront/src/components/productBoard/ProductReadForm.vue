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

      <section id="article-profile">
        <div id="article-profile-link">
          <div class="space-between">
            <div style="display: flex">
              <div id="article-profile-image">
                <img src="@/assets/profile/Tranders_base_profile_img.png" />
              </div>

              <div id="profile-left">
                <div id="userName">
                  <router-link
                    :to="{
                      name: 'ProfileBasicPage',
                      params: { memberId: productBoard.member.id.toString() },
                    }"
                  >
                    {{ productBoard.member.name }}
                  </router-link>
                </div>
                <div id="region-name">{{ productBoard.member.address }}</div>
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
              <div class="meters">
                <div class="bar bar-color-03" style="width: 37%"></div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <section id="description">
        <h2 style="margin-bottom: 5px">{{ productBoard.process }}</h2>
        <h1 property="schema:name" id="title" style="margin-top: 0px">
          {{ productBoard.title }}
        </h1>

        <p id="category">
          {{ productBoard.category }} •
          {{ productBoard.regDate }}
        </p>
        <p
          id="price"
          property="schema:price"
          content
          style="font-size: 18px; font-weight: bold"
        >
          <span class="text-light-gray">{{ productBoard.price }}원</span>
        </p>

        <div property="schema:content" id="detail">
          <p>{{ productBoard.content }}</p>
        </div>

        <p id="counts">관심 5 ∙ 채팅 11 ∙ 조회 {{ productBoard.viewCnt }}</p>

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
    };
  },
  methods: {
    onChat() {
      this.$emit("onChat", {
        member1No: this.login.memberNo,
        member2No: this.productBoard.member.memberNo,
        productNo: this.productBoard.productNo,
      });
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
  padding: 32px 0;
  width: 677px;
  margin: 0 auto;
  border-bottom: 1px solid #e9ecef;
}
#title {
  font-size: 20px;
  font-weight: 600;
  line-height: 1.5;
  letter-spacing: -0.6px;
}
#category {
  margin-top: 4px;
  font-size: 13px;
  line-height: 1.46;
  letter-spacing: -0.6px;
  color: #868e96;
}
#price {
  margin-top: 4px;
  font-size: 18px;
  font-weight: bold;
  line-height: 1.76;
  letter-spacing: -0.6px;
}
#detail p {
  margin-bottom: 16px;
  margin-top: 8px;
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
.slide-3d {
  width: 650px;
}
.swiper-slide {
  width: 650px;
  height: 650px;
  background-color: white;
  background-position: center;
  background-size: cover;
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
  color: lightgreen;
}
dl dd {
  position: relative;
  font-size: 16px;
  font-weight: bold;
  line-height: 1;
  letter-spacing: -0.5px;
  margin-top: 1px;
  width: 100px;
  text-align: right;
}
</style>