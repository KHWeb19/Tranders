<template>
  <div>
    <hottest-product-list-form :productBoards="productBoards" />
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import HottestProductListForm from "../../components/productBoard/HottestProductListForm.vue";
export default {
  name: "ProductListPage",
  data() {
    return {
      keyWord: "",
    };
  },
  components: {
    HottestProductListForm,
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