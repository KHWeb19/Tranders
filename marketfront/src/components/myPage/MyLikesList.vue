<template>
  <div>
    <v-app>
      <v-container>
        <v-card width="95%" height="95%">
          <div
            style="
              padding-left: 50px;
              padding-right: 30px;
              padding-bottom: 320px;
            "
          >
            <h2 class="head-title">관심 리스트</h2>
            <section class="card-wrap">
              <v-col
                class="none-product"
                v-if="
                  !myLikes || (Array.isArray(myLikes) && myLikes.length === 0)
                "
                style="text-align: center"
              >
                현재 등록된 상품이 없습니다!
              </v-col>
              <v-col
                v-else
                v-for="myLike in myLikes"
                :key="myLike.productBoard.productNo"
                cols="4"
              >
                <article class="card-top">
                  <router-link
                    class="product-link"
                    :to="{
                      name: 'ProductReadPage',
                      params: {
                        productNo: myLike.productBoard.productNo.toString(),
                      },
                    }"
                  >
                    <div class="card-photo">
                      <v-img
                        style="height: 150px; width: 250px"
                        :src="
                          require(`@/assets/pImage/${myLike.productBoard.productImage}`)
                        "
                      />
                    </div>
                    <div class="card-desc">
                      <div class="card-process">
                        {{ myLike.productBoard.process }}
                      </div>
                      <div class="card-title">
                        {{ myLike.productBoard.title }}
                      </div>

                      <div class="card-price">
                        {{ myLike.productBoard.price | makeComma }}원
                      </div>
                      <div class="card-region-name">
                        {{ myLike.member.region }}
                      </div>
                      <div class="card-counts">
                        <span>
                          관심
                          {{ myLike.productBoard.productLike.length }} </span
                        >∙
                        <span> 채팅 {{ myLike.productBoard.chatCnt }} </span>∙
                        <span> 조회수 {{ myLike.productBoard.viewCnt }}</span>
                      </div>
                    </div>
                  </router-link>
                </article>
              </v-col>
            </section>
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
      memberNo: cookies.get("memberNo"),
    };
  },
  methods: {
    ...mapActions(["fetchMemberProfile"]),
    ...mapActions(["fetchMyLikesList"]),
  },
  computed: {
    ...mapState(["profileImg"]),
    ...mapState(["myLikes"]),
  },
  created() {
    this.fetchMyLikesList(this.memberNo).catch(() => {
      alert("좋아요 정보 요청 실패");
      this.$router.push();
    });
  },
  mounted() {
    this.id = cookies.get("id");
    this.fetchMemberProfile(this.id);
    this.active_tab = "info";
    this.fetchMyLikesList(this.memberNo);
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
  padding-top: 30px;
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
  width: 700px;
  margin: 0 auto;
  display: flex;
  justify-content: left;
  flex-wrap: wrap;
  text-align: center;
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
  text-align: left;
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
.card-title {
  font-size: 16px;
  letter-spacing: -0.02px;
  color: #212529;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 2px;
  line-height: 1.5;
  font-weight: normal;
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