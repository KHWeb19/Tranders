<template>
  <div align="centet">
    <product-read-form v-if="product" :product="product" />
    <p v-else>로딩중 ........</p>
    <router-link :to="{ name: 'ProductModifyPage', params: { productNo } }">
      상품 수정
    </router-link>
    <button @click="onDelete">삭제</button>
    <router-link :to="{ name: 'ProductListPage' }"> 리스토로 이동 </router-link>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";
import ProductReadForm from "@/components/productBoard/ProductReadForm.vue";

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
  },
  computed: {
    ...mapState(["product"]),
  },
  created() {
    this.fetchProduct(this.productNo).catch(() => {
      alert("상품 요청 실패!");
      this.$router.push();
    });
  },
  methods: {
    ...mapActions(["fetchProduct"]),
    onDelete() {
      axios
        .delete("http://localhost:7777/product/${productNo}")
        .then(() => {
          alert("삭제 성공!");
          this.$$router.push({ name: "ProductListPage" });
        })
        .catch(() => {
          alert("삭제 실패! 문제 발생!");
        });
    },
  },
};
</script>