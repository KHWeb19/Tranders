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

    <v-btn @click="onDelete">삭제</v-btn>
    <router-link :to="{ name: 'ProductModifyPage' }">
      <v-btn>수정하기</v-btn>
    </router-link>
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
import { mapActions, mapState } from "vuex";
import ProductReadForm from "@/components/productBoard/ProductReadForm.vue";
import AfterLoginView from "../../components/home/AfterLoginView.vue";
import axios from "axios";
import { API_BASE_URL } from "@/constant/login";
export default {
  name: "ProductReadPage",
  props: {
    productNo: {
      type: String,
      required: true,
    },
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
      const { member1No, member2No, productNo } = payload;
      console.log(member1No, member2No, productNo);
      axios
        .post(
          `http://localhost:7777/chatting/register/${member1No}/${member2No}/${productNo}`,
          { member1No, member2No, productNo }
        )
        .then(() => {
          this.$router.push({ name: "ChattingListPage" });
        })
        .catch(() => {
          alert("문제 발생!");
        });
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
  },
};
</script>