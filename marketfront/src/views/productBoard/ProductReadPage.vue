<template>
  <div>
    <after-login-view></after-login-view>
    <product-read-form
      v-if="productBoard"
      :productBoard="productBoard"
      @onChat="onChat"
      @sendReport="sendReport"
      @sendImgReport="sendImgReport"
    />

    <div class="delete-modify-button">
      <v-btn
        v-if="login.memberNo == productBoard.member.memberNo"
        @click="onDelete"
        text
        >삭제</v-btn
      >
      <router-link
        style="text-decoration: none"
        :to="{ name: 'ProductModifyPage' }"
      >
        <v-btn v-if="login.memberNo == productBoard.member.memberNo" text
          >수정하기</v-btn
        >
      </router-link>
    </div>

  </div>
</template>

<script>
import cookies from "vue-cookies";
const config = {
  headers: {
    Authorization: "Bearer " + cookies.get("access_token"),
    Accept: "application/json",
    "Content-Type": "application/json",
  },
};
import axios from "axios";
import { mapActions, mapState } from "vuex";
import ProductReadForm from "@/components/productBoard/ProductReadForm.vue";
import AfterLoginView from "../../components/home/AfterLoginView.vue";
import { API_BASE_URL } from "@/constant/login";
export default {
  name: "ProductReadPage",
  data() {
    return {
      login: {
        memberNo: cookies.get("memberNo"),
        id: cookies.get("id"),
        name: cookies.get("name"),
        access_token: cookies.get("access_token"),
      },
    };
  },
  props: {
    productNo: {
      type: String,
      required: true,
    },
  },
  data() {
    return{
      // registerChat: mapState(['registerChat'])
    }
  },
  components: {
    ProductReadForm,
    AfterLoginView,
  },
  computed: {
    ...mapState(["productBoard"]),
  },
  created() {
    this.fetchProductBoard(this.productNo).catch(() => {
      console.log(this.productNo);
      alert("상품 요청 실패!");
      this.$router.push();
    });
  },
  methods: {
    ...mapActions(["fetchProductBoard"]),
    onDelete() {
      const { productNo } = this.productBoard;
      axios
        .delete(`http://localhost:7777/product/${productNo}`)
        .then(() => {
          alert("삭제 성공");
          this.$router.push({ name: "ProductListPage" });
        })
        .catch(() => {
          alert("삭제실패 문제 발생");
        });
    },
    onChat(payload) {
      const {registerNo} = payload;
      this.$router.push({ name: "ChattingReadView",
                          params: { roomNo: registerNo.toString() }})
    },
    sendReport(payload) {
      const {
        category1,
        category2,
        content,
        banCheck,
        productNo,
        toId,
        fromId,
      } = payload;

      axios
        .post(
          API_BASE_URL + "/report/saveReport",
          { category1, category2, content, banCheck, productNo, toId, fromId },
          config
        )
        .then((res) => {
          console.log(res);
          this.$router.go();
        })
        .catch(() => {
          alert("에러");
        });
    },
    sendImgReport(payload) {
      // 이미지가 없을때 안받아지면.. sendReport 를 불러서 처리하자

      axios
        .post(API_BASE_URL + "/report/saveImgText", payload, {
          headers: {
            Authorization: "Bearer " + cookies.get("access_token"),
            Accept: "application/json",
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          console.log(res);
          this.$router.go();
        })
        .catch(() => {
          alert("에러");
        });
    },
    onLikes(payload) {
      const { productNo, memberNo } = payload;
      axios
        .post(`http://localhost:7777/productLike/${productNo}/${memberNo}`, {
          productNo,
        })
        .then(() => {
          history.go(0);
        })
        .catch(() => {
          alert("문제 발생!");
        });
    },
  },
};
</script>

<style scoped>
.delete-modify-button {
  padding-left: 1050px;
  padding-top: 20px;
  padding-bottom: 20px;
}
</style>