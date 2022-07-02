<template>
  <v-container>
    <after-login-view></after-login-view>
    <product-search-form :searchList="searchList" />
  </v-container>
</template>

<script>
import AfterLoginView from "@/components/home/AfterLoginView";
import ProductSearchForm from "@/components/productBoard/ProductSearchForm";

export default {
  props: {
    searchList: {
      type: Array,
      required: true,
    },
  },
  components: {
    AfterLoginView,
    ProductSearchForm,
  },
};
</script>