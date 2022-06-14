<template>
  <div align="center">
    <product-register-form @submit="onSubmit" />
  </div>
</template>

<script>
import ProductRegisterForm from "@/components/productBoard/ProductRegisterForm";
import axios from "axios";

export default {
  name: "ProductRegisterPage",
  components: {
    ProductRegisterForm,
  },
  methods: {
    onSubmit(payload) {
      const { title, price, content } = payload;
      axios
        .post("http://localhost:7777/product/register", {
          title,
          price,
          content,
        })
        .then(() => {
          alert("상품 등록 성공!");

          this.$router.push({
            name: "ProductListPage",
          });
        })
        .catch(() => {
          alert("문제 발생!");
        });
    },
  },
};
</script>