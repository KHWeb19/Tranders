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

            <div itemid="profile-left">
              <div id="userName">야용</div>
              <div id="region-name">강동구 둔촌동</div>
            </div>

            <div id="profile-right">
              <dl id="temperature">
                <dt>매너온도</dt>
                <dd class="text-color-03">
                  36.5
                  <span>°C</span>
                </dd>
              </dl>
            </div>

            <div class="meters">
              <div class="bar bar-color-03" style="width: 37%"></div>
            </div>
            <div class="face face-03"></div>
          </div>
        </div>
      </section>

      <section id="description">
        <h1 property="schema:name" id="title" style="margin-top: 0px">
          {{ productBoard.title }}
        </h1>
        <p id="category">" 식품 "</p>

        <p
          id="price"
          property="schema:price"
          content
          style="font-size: 18px; font-weight: bold"
        >
          <span class="text-light-gray">{{ productBoard.price }}</span>
        </p>

        <div property="schema:content" id="detail">
          <p>{{ productBoard.content }}</p>
        </div>

        <p id="counts">관심 8 ∙ 채팅 11 ∙ 조회 111</p>

        <v-btn @click="onChat">채팅하기</v-btn>
      </section>
    </article>
  </div>
</template>

<script>
import Vue from "vue";
import cookies from "vue-cookies";
Vue.use(cookies);
import { Swiper, SwiperSlide } from "vue-awesome-swiper";

export default {
  name: "ProductReadPage",
  props: {
    productBoard: {
      type: Object,
      required: true,
    },
  },
  components: {
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
    };
  },
  methods: {
    onChat() {
        //채팅방생성하고 해당채팅방정보 받아서 이동해야함
        // this.$router.push({ name: "ChattingListPage", 
        //params: {
        //     member1No: this.login.memberNo.toString(), 
        //     member2: '10'.toString(),
        //     boardNo: '테스트게시물1',
        //     }
        // });
        this.$emit('onChat', {member1No: this.login.memberNo, productNo: this.productBoard.productNo})
    }
  }
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
.space-between {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

#profile-left {
  display: inline-block;
  margin-left: 8px;
}

#profile-right {
  position: absolute;
  right: 0;
  padding-right: 36px;
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
</style>