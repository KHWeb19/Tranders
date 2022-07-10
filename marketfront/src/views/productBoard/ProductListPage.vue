<template>
  <div>
    <after-login-view :num="1"></after-login-view>
    <product-list-form :regionProductBoards="regionProductBoards" />
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
import Vue from "vue";
import cookies from "vue-cookies";
Vue.use(cookies);

import ProductListForm from "@/components/productBoard/ProductListForm";
import { mapState, mapActions } from "vuex";
import AfterLoginView from "../../components/home/AfterLoginView.vue";
export default {
  name: "ProductListPage",
  data() {
    return {
      memberNo: cookies.get("memberNo"),
    };
  },
  components: {
    ProductListForm,
    AfterLoginView,
  },
  computed: {
    ...mapState(["regionProductBoards"]),
  },
  mounted() {
    this.fetchRegionProductBoardList(this.memberNo);
  },
  methods: {
    ...mapActions(["fetchRegionProductBoardList"]),
  },
};
</script>