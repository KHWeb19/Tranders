<template>
  <div>
    <v-form enctype="multipart/form-data" @submit.prevent="onSubmit">
      <table>
        <div>
          <div v-if="productBoard.productImage === null"></div>
          <v-img
            v-else
            height="300px"
            width="300px"
            :src="require(`@/assets/pImage/${productBoard.productImage}`)"
          />
        </div>
        <tr>
          <td>카테고리</td>
          <td>
            <input type="text" v-model="category" />
          </td>
        </tr>
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

        <v-btn text color="primary" type="submit">올리기</v-btn>
      </table>
    </v-form>
  </div>
</template>

<script>
import Vue from "vue";
import cookies from "vue-cookies";
Vue.use(cookies);

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
      selectProcess: ["판매중", "판매완료"],
      category: "",
      productImage: "",
      title: "",
      price: "",
      content: "",
      swiperOption: {
        effect: "coverflow",
        grabCursor: true,
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
      },
      login: {
        memberNo: cookies.get("memberNo"),
        id: cookies.get("id"),
        name: cookies.get("name"),
        access_token: cookies.get("access_token"),
      },
    };
  },
  methods: {
    onSubmit() {
      const { category, productImage, title, price, content } = this;
      this.$emit("submit", {
        memberNo: this.login.memberNo,
        category,
        productImage,
        title,
        price,
        content,
      });
    },
  },
  created() {
    this.productImage = this.productBoard.productImage;
    this.category = this.productBoard.category;
    this.title = this.productBoard.title;
    this.price = this.productBoard.price;
    this.content = this.productBoard.content;
  },
};
</script>






