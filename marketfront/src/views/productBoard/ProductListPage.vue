<template>
  <div>
    <home-view></home-view>
    <product-list-form :productBoards="productBoards" />
  </div>
</template>

<script>
import ProductListForm from "@/components/productBoard/ProductListForm";
import { mapState, mapActions } from "vuex";
import HomeView from "../../components/home/HomeView.vue";

export default {
  name: "ProductListPage",
  components: {
    ProductListForm,
    HomeView,
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