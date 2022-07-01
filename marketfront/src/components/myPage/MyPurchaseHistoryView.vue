<template>
  <v-container>
    <v-card width="100%">
      <div style="padding-left: 50px; padding-right: 30px">
        <v-row>
          <v-col v-for="count in 4" :key="count"></v-col>
          <v-col>
            <div>
              <v-col
                class="none-product"
                v-if="
                  !productBoards ||
                  (Array.isArray(productBoards) && productBoards.length === 0)
                "
              >
                현재 등록된 상품이 없습니다!
              </v-col>
              <v-col
                v-else
                v-for="productBoard in productBoards"
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
                          :src="
                            require(`@/assets/pImage/${productBoard.productImage}`)
                          "
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
          </v-col>
        </v-row>

        <v-row style="height: 520px"> </v-row>
      </div>
    </v-card>
  </v-container>
</template>

<script>
import cookies from "vue-cookies";
import { mapActions, mapState } from "vuex";
export default {
  name: "MyPurchaseHostpryView",
  components: {},
  data() {
    return {
      id: cookies.get("id"),
    };
  },
  props: {
    productBoards: {
      type: Array,
    },
  },
  methods: {
    ...mapActions(["fetchMemberProfile"]),
  },
  computed: {
    ...mapState(["profileImg"]),
  },
  mounted() {
    this.id = cookies.get("id");
    this.fetchMemberProfile(this.id);
  },
};
</script>

<style scoped>
</style>