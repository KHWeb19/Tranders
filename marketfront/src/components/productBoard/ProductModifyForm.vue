<template>
  <div>
    <form @submit.prevent="onSubmit">
      <table>
        <tr>
          <td>상품명</td>
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
            <textarea cols="50" rows="20" v-model="content" />
          </td>
        </tr>
      </table>

      <div>
        <button type="submit">수정완료</button>
        <router-link
          :to="{
            name: 'ProductReadPage',
            params: { productNo: productBoard.productNo.toString() },
          }"
        >
          취소
        </router-link>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "ProductModifyForm",
  props: {
    productBoard: {
      type: Object,
      required: true,
    },
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
  created() {
    this.title = this.productBoard.title;
    this.price = this.productBoard.price;
    this.content = this.productBoard.content;
  },
};
</script>