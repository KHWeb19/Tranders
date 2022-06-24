<template>
  <div>
    <home-view></home-view>
    <product-read-form v-if="productBoard" :productBoard="productBoard" @onChat="onChat"/>
    <p v-else>로딩중 ........</p>
    <v-btn @click="onDelete">삭제</v-btn>
    <router-link :to="{ name: 'ProductModifyPage' }">
      <v-btn>수정하기</v-btn>
    </router-link>
    <router-link :to="{ name: 'ProductListPage' }">
      <v-btn>리스트로 이동</v-btn>
    </router-link>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import ProductReadForm from "@/components/productBoard/ProductReadForm.vue";
import HomeView from "../../components/home/HomeView.vue";
import axios from "axios";

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
    HomeView,
  },
  computed: {
    ...mapState(["productBoard"]),
  },
  created() {
    this.fetchProductBoard(this.productNo).catch(() => {
      console.log(this.productNo);
      alert("상품 요청 실패!");
      this.$router.push();
    });
  },
  methods: {
    ...mapActions(["fetchProductBoard"]),
    onDelete() {
      const { productNo } = this.productBoard;
      axios
        .delete(`http://localhost:7777/product/${productNo}`)
        .then(() => {
          alert("삭제 성공");
          this.$router.push({ name: "ProductListPage" });
        })
        .catch(() => {
          alert("삭제실패 문제 발생");
        });
    },
    onChat(payload) {
      const {member1No, member2No, productNo} = payload
      console.log(member1No, member2No, productNo)
      axios.post(`http://localhost:7777/chatting/register/${member1No}/${member2No}/${productNo}`, {member1No, member2No, productNo})
          .then(() => {
              alert('채팅방 등록 성공!')
          })
          .catch(() => {
              alert('문제 발생!')
          })
      // this.fetchChatroom(member1, member2)
    }
  },
};
</script>