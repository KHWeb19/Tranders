<template>
  <product-list-form :products="products" />
</template>

<script>
import ProductListForm from "@/components/productBoard/ProductListForm";
import { mapState, mapActions } from "vuex";

export default {
  name: "ProductListPage",
  components: {
    ProductListForm,
  },
  computed: {
    ...mapState(["products"]),
  },
  mounted() {
    this.fetchProductList();
  },
  methods: {
    ...mapActions(["fetchProductList"]),
  },
};
</script>

<style scoped>
</style>