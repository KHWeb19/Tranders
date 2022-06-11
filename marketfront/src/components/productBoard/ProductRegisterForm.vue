<template>
  <div>
    <home-view></home-view>
    <form @submit.prevent="onSubmit">
      <table>
        <tr>
          <td>제목</td>
          <td>
            <input type="text" v-model="title" />
          </td>
        </tr>
        <tr>
          <td>가격</td>
          <td>
            <input type="text" v-model="price" />
          </td>
        </tr>
        <tr>
          <td>설명</td>
          <td>
            <textarea cols="50" rows="20" v-model="content"> </textarea>
          </td>
        </tr>
      </table>

      <div>
        <button type="submit">등록</button>
        <router-link :to="{ name: 'ProductListPage' }"> 취소 </router-link>
      </div>
    </form>
  </div>
</template>

<script>
import HomeView from "../home/HomeView.vue";
export default {
  name: "ProductRegisterForm",
  components: {
    HomeView,
  },
  data() {
    return {
      title: "",
      price: "",
      content: "",
    };
  },
  methods: {
    onSubmit() {
      const { title, price, content } = this;
      this.$emit("submit", { title, price, content });
    },
  },
};
</script>

<style lang="scss" scoped>
</style>
