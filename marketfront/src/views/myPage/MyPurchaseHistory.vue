<template>
  <div id="login-wrap">
    <after-login-view></after-login-view>

    <div id="my_page_box">
      <v-row no-gutters>
        <v-col cols="4">
          <my-profile-bar></my-profile-bar>
        </v-col>

        <v-col cols="8">
          <my-purchase-history-view
            :productBoards="productBoards"
          ></my-purchase-history-view>
        </v-col>
      </v-row>
    </div>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
import AfterLoginView from "@/components/home/AfterLoginView";
import MyProfileBar from "@/components/myPage/MyPageBar";
import MyPurchaseHistoryView from "@/components/myPage/MyPurchaseHistoryView";

export default {
  components: {
    MyPurchaseHistoryView,
    MyProfileBar,
    AfterLoginView,
  },
  name: "MypurchaseHistory",
  computed: {
    ...mapState(["productBoards"]),
  },
  mounted() {
    this.fetchProductBoardList();
  },
  methods: {
    ...mapActions(["fetchProductBoardList"]),
  },
};
</script>

<style scoped>
#login-wrap {
  width: 100%;
  max-width: 1440px;
  margin: 0 auto;
  text-align: left;
}
#my_page_box {
  display: flex;
  align-items: center;
  width: 100%;
  max-width: 1250px;
  margin: 0 auto;
  padding: 15px 7px 0 7px; /*위, 오른쪽, 아래, 왼쪽 */
}
</style>