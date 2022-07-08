<template>
  <product-modify-form :productBoard="productBoard" @submit="onSubmit" />
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";
import ProductModifyForm from "@/components/productBoard/ProductModifyForm.vue";

export default {
  name: "ProductModifyPage",
  components: {
    ProductModifyForm,
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
      const {
        memberNo,
        process,
        category,
        productImage,
        title,
        price,
        content,
      } = payload;
      console.log(memberNo);
      axios
        .put(`http://localhost:7777/product/${memberNo}/${this.productNo}`, {
          memberNo,
          process,
          category,
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