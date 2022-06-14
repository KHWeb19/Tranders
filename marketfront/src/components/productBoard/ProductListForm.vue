<template>
  <div>
    <home-view></home-view>
    <h1 class="head-title" id="hot-articles-head-title">중고거래 인기매물</h1>
    <nav id="hot-articles-navigation">
      <select
        name="region1"
        id="region1"
        onchange="changeRegion($event)"
        v-model="key"
        class="hot-articles-nav-select"
      >
        <option value>지역을 선택하세요</option>
        <option value="서울특별시">서울특별시</option>
        <option value="부산광역시">부산광역시</option>
        <option value="대구광역시">대구광역시</option>
        <option value="인천광역시">인천광역시</option>
        <option value="광주광역시">광주광역시</option>
        <option value="대전광역시">대전광역시</option>
        <option value="울산광역시">울산광역시</option>
        <option value="경기도">경기도</option>
      </select>
      <v-btn :to="{ name: 'ProductRegisterPage' }">
        <v-icon>mdi-pencil</v-icon>
      </v-btn>
    </nav>
    <v-container>
      <v-flex>
        <v-row>
          <v-col
            class="none-product"
            v-if="
              !products || (Array.isArray(products) && products.length === 0)
            "
          >
            현재 등록된 상품이 없습니다!
          </v-col>
          <v-col
            v-else
            v-for="product in products"
            :key="product.products"
            cols="4"
          >
            <router-link
              style="text-decoration: none"
              :to="{
                name: 'ProductReadPage',
                params: { productNo: product.productNo.toString() },
              }"
            >
              <div class="card-wrap">
                <div class="card-top">
                  <div class="card-photo">
                    <v-img></v-img>
                  </div>
                  <div class="card-desc">
                    <div class="card-title">{{ product.title }}</div>
                    <div class="card-price">{{ product.price }}</div>
                    <div class="card-counts">
                      <span> 관심 5 </span><br />
                      <span> 채팅 6 </span>
                    </div>
                  </div>
                </div>
              </div>
            </router-link>
          </v-col>
        </v-row>
      </v-flex>
    </v-container>
  </div>
</template>

<script>
import HomeView from "@/components/home/HomeView";

export default {
  name: "ProductList",
  components: {
    HomeView,
  },
  props: {
    products: {
      type: Array,
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
.none-product {
  text-align: center;
}
#hot-articles-navigation {
  width: 980px;
  margin: 0 auto;
  text-align: right;
  margin-bottom: 30px;
}
#hot-articles-navigation .hot-articles-nav-select {
  width: 180px;
  margin-left: 8px;
  appearance: none;
  padding: 12px 16px;
  border: solid 1px #e9ecef;
  box-sizing: border-box;
  font-size: 17px;
  letter-spacing: -0.6px;
  background-color: #fff no-repeat 89% 50%;
  border-radius: 12px;
}
.product {
  width: 980px;
  margin: 0 auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  flex-direction: row;
  align-content: center;
  align-items: normal;
}
.card-top {
  width: calc(100% - 44px);
  margin-right: 44px;
  margin-bottom: 56px;
}
.card-photo {
  width: 100%;
  position: relative;
  overflow: hidden;
  border-radius: 12px;
  background-color: #f8f9fa;
  box-shadow: inset 0px 0px 0px 1px rgb(0, 0, 0 / 15);
  box-sizing: border-box;
  border: 1px solid transparent;
}
.cards-wrap .card-link {
  text-decoration: none;
  color: #212529;
  font-size: 62.5%;
}
.cards-wrap .card-top .card-desc {
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
  font-weight: 700;
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
  color: #858e96;
  font-size: 13px;
}
a {
  text-decoration: none;
}
nav {
  display: block;
}
</style>