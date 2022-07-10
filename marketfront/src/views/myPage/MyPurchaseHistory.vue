<template>
  <div id="login-wrap">
    <after-login-view></after-login-view>

    <div id="content">
      <v-row no-gutters>
        <v-col cols="4">
          <my-profile-bar :userInfo="userInfo"></my-profile-bar>
        </v-col>

        <v-col cols="8">
          <my-purchase-history-view
            :myProductBoards="myProductBoards"
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
import cookies from "vue-cookies";
export default {
  components: {
    MyPurchaseHistoryView,
    MyProfileBar,
    AfterLoginView,
  },
  name: "MyPurchaseHistory",
  data() {
    return {
      memberId: cookies.get("id"),
      memberNo: cookies.get("memberNo"),
    };
  },
  computed: {
    ...mapState(["myProductBoards"]),
    ...mapState(["userInfo"]),
  },
  mounted() {
    this.fetchMyProductBoardList(this.memberNo);
    this.fetchMyPage(this.memberId);
  },
  methods: {
    ...mapActions(["fetchMyProductBoardList"]),
    ...mapActions(["fetchMyPage"]),
  },
};
</script>

<style scoped>
/* #login-wrap {
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
  padding: 15px 7px 0 7px; 
} */
#login-wrap {
  background: #f8f9fa;
  min-height: 100vh;
}
#content {
  display: block;
  margin-top: 72px;
  margin: 30px auto 0 auto;
  border-left: 1px solid #e9ecef;
  border-right: 1px solid #e9ecef;
  width: 1200px;
  margin: 0 auto;
  line-height: 24px;
  background: #fff;
}
</style>