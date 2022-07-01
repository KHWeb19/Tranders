<template>
  <div>
    <h2 class="head-title">중고거래 인기매물</h2>
    <router-link :to="{ name: 'ProductRegisterPage' }">
      게시물 작성
    </router-link>
    <v-col
      class="none-product"
      v-if="
        !searchList || (Array.isArray(searchList) && searchList.length === 0)
      "
    >
      현재 등록된 상품이 없습니다!
    </v-col>
    <v-col
      v-else
      v-for="productBoard in searchList"
      :key="productBoard.productNo"
      cols="4"
    >
      <router-link
        class="product-link"
        :to="{
          name: 'ProductReadPage',
          params: { productNo: productBoard.productNo.toString() },
        }"
      >
        <section class="card-wrap">
          <article class="card-top">
            <div class="card-photo">
              <v-img
                :src="require(`@/assets/pImage/${productBoard.productImage}`)"
              />
            </div>
            <div class="card-desc">
              <div class="card-title">{{ productBoard.title }}</div>
              <div class="card-title">{{ productBoard.price }}원</div>
              <div class="card-counts">
                <span> 관심 5 </span>∙
                <span> 채팅 6 </span>
              </div>
            </div>
          </article>
        </section>
      </router-link>
    </v-col>
  </div>
</template>

<script>
export default {
  name: "ProductSearchForm",
  components: {},
  props: {
    searchList: {
      type: Array,
      required: true,
    },
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
.card-wrap {
  width: 980px;
  margin: 0 auto;
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}
.card-top {
  width: calc(25% - 44px);
  margin-right: 44px;
  margin-bottom: 56px;
}
.card-photo {
  width: 100%;
  padding-top: 100%;
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
</style>