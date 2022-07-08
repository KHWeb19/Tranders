<template>
  <product-register-form @submit="onSubmit" />
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
      console.log(payload);
      const {
        memberNo,
        process,
        category,
        productImage,
        productImage1,
        productImage2,
        title,
        price,
        content,
        regDate,
      } = payload;
      axios
        .post(`http://localhost:7777/product/register/${memberNo}`, {
          memberNo,
          process,
          category,
          productImage,
          productImage1,
          productImage2,
          title,
          price,
          content,
          regDate,
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