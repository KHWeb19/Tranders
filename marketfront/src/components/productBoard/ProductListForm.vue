<template>
  <div>
    <section id="content">
      <h2 class="head-title">중고거래 리스트</h2>
      <section class="card-wrap" style="justify-content: left">
        <v-col
          class="none-product"
          v-if="
            !regionProductBoards ||
            (Array.isArray(regionProductBoards) &&
              regionProductBoards.length === 0)
          "
          style="text-align: center; margin-top: 200px"
        >
          현재 등록된 상품이 없습니다!
        </v-col>

        <v-col
          v-else
          v-for="regionProductBoard in regionProductBoards"
          :key="regionProductBoard.productNo"
          cols="4"
        >
          <article class="card-top">
            <router-link
              class="product-link"
              :to="{
                name: 'ProductReadPage',
                params: { productNo: regionProductBoard.productNo.toString() },
              }"
            >
              <div class="card-photo">
                <v-img
                  style="height: 250px; width: 300px"
                  :src="
                    require(`@/assets/pImage/${regionProductBoard.productImage}`)
                  "
                />
              </div>
              <div class="card-desc">
                <p class="card-process">
                  {{ regionProductBoard.process }}
                </p>
                <div class="card-title">{{ regionProductBoard.title }}</div>
                <div class="card-price">
                  {{ regionProductBoard.price | makeComma }}원
                </div>
                <div class="card-region-name">
                  {{ regionProductBoard.member.region }}
                </div>
                <div class="card-counts">
                  <span> 관심 {{ regionProductBoard.productLike.length }}</span
                  >∙ <span> 채팅 {{ regionProductBoard.chatCnt }} </span>∙
                  <span> 조회수 {{ regionProductBoard.viewCnt }} </span>
                </div>
              </div>
            </router-link>
          </article>
        </v-col>
      </section>
    </section>
  </div>
</template>

<script>
import Vue from "vue";
import cookies from "vue-cookies";
Vue.use(cookies);

export default {
  name: "ProductListForm",
  components: {},
  props: {
    regionProductBoards: {
      type: Array,
    },
  },
  data() {
    return {
      viewCnt: [],
      searchList: [],
      keyWord: "",
      login: {
        memberNo: cookies.get("memberNo"),
        id: cookies.get("id"),
        name: cookies.get("name"),
        access_token: cookies.get("access_token"),
      },
    };
  },
};
</script>

<style scoped>
.head-title {
  font-size: 32px;
  font-weight: 600;
  letter-spacing: -0.6px;
  text-align: center;
  counter-reset: #212529;
  margin-bottom: 40px;
  margin-top: 40px;
}
section {
  display: block;
}
section#content {
  margin-top: 72px;
  margin: 0;
  padding: 0;
}
.card-wrap {
  width: 980px;
  margin: 0 auto;
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}
.card-top {
  margin-right: 44px;
  margin-bottom: 56px;
}
.card-photo {
  position: relative;
  overflow: hidden;
  border-radius: 12px;
  background-color: #f8f9fa;
  box-shadow: inset 0px 0px 0px 1px rgb(0, 0, 0 / 15%);
  box-sizing: border-box;
}
.card-desc {
  margin-top: 12px;
}
.card-title {
  font-size: 16px;
  letter-spacing: -0.02px;
  color: #212529;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 4px;
  line-height: 1.5;
  font-weight: normal;
}
.card-process {
  font-size: 18px;
  font-weight: 700;
  text-align: center;
  border: 1px solid white;
  border-radius: 5px;
  background-color: green;
  max-width: 70px;
  color: white;
  margin-bottom: 5px;
}
.card-price {
  font-size: 15px;
  font-weight: 700;
  line-height: 1.5;
  margin-bottom: 4px;
  color: #212529;
}
.card-region-name {
  font-size: 13px;
  color: #212529;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 4px;
  line-height: 1.5;
}
.card-counts {
  color: #868e96;
  font-size: 13px;
}
.product-link {
  text-decoration: none !important;
}
img {
  position: absolute;
  top: 0;
  bottom: 0;
  width: 100%;
  box-sizing: border-box;
  border-radius: 12px;
  border: 1px solid transparent;
}
</style>