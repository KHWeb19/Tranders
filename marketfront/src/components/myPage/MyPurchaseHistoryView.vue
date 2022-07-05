<template>
  <div>
    <v-app>
      <v-tabs grow v-model="active_tab">
        <v-tab v-for="tab of tabs" :key="tab.index">{{ tab.name }} </v-tab>
      </v-tabs>

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
                      (Array.isArray(productBoards) &&
                        productBoards.length === 0)
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
                        params: {
                          productNo: productBoard.productNo.toString(),
                        },
                      }"
                    >
                      <v-col>
                        <h4>{{ productBoard.process }}</h4>
                      </v-col>
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
                            <div class="card-title">
                              {{ productBoard.title }}
                            </div>
                            <div class="card-title">
                              {{ productBoard.price }}원
                            </div>
                            <div class="card-counts">
                              <span> 관심 5 </span>∙ <span> 채팅 6 </span>∙
                              <span> 조회수 {{ productBoard.viewCnt }}</span>
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
    </v-app>
  </div>
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
      active_tab: "hist",
      tabs: [
        { index: "info", name: "판매중" },
        { index: "hist", name: "판매완료" },
      ],
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
    this.active_tab = "info";
  },
};
</script>

<style scoped>
</style>