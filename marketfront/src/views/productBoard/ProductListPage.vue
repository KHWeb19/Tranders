<template>
  <div>
    <after-login-view></after-login-view>
    <product-list-form :productBoards="productBoards" />
    <v-btn
      id="writeBtn"
      depressed
      fixed
      right
      bottom
      fab
      big
      class="success"
      type="button"
      onclick="location.href='http://localhost:8080/productRegister'"
    >
      <v-icon>mdi-plus</v-icon>
    </v-btn>
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