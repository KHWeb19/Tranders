<template>
  <div>
    <after-login-view :num="1"></after-login-view>
    <product-list-form :productBoards="productBoards" />
  </div>
</template>

<script>
import ProductListForm from "@/components/productBoard/ProductListForm";
import { mapState, mapActions } from "vuex";
import AfterLoginView from "../../components/home/AfterLoginView.vue";
export default {
  name: "ProductListPage",
  components: {
    ProductListForm,
    AfterLoginView,
  },
  computed: {
    ...mapState(["productBoards"]),
  },
  mounted() {
    this.fetchProductBoardList();
  },
  methods: {
    ...mapActions(["fetchProductBoardList"]),
  },
};
</script>