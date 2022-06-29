<template>
  <div>
    <home-view></home-view>
    <product-modify-form
      v-if="productBoard"
      :productBoard="productBoard"
      @submit="onSubmit"
    />
    <p v-else>로딩중......</p>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";
import ProductModifyForm from "@/components/productBoard/ProductModifyForm.vue";
import HomeView from "../../components/home/HomeView.vue";

export default {
  name: "ProductModifyPage",
  components: {
    ProductModifyForm,
    HomeView,
  },
  props: {
    productNo: {
      type: String,
      required: true,
    },
  },
  computed: {
    ...mapState(["productBoard"]),
  },
  methods: {
    ...mapActions(["fetchProductBoard"]),
    onSubmit(payload) {
      const { productImage, title, price, content } = payload;

      axios
        .put(`http://localhost:7777/product/${this.productNo}`, {
          productImage,
          title,
          price,
          content,
        })
        .then((res) => {
          console.log(this.productBoard);
          this.$router.push({
            name: "ProductReadPage",
            params: { productNo: res.data.productNo.toString() },
          });
        })
        .catch(() => {
          alert("게시물 수정 실패!");
        });
    },
    created() {
      this.fetchProductBoard(this.productNo).catch(() => {
        alert("게시물 DB 조회 실패!");
        this.$router.back();
      });
    },
  },
};
</script>