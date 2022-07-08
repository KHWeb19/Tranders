<template>
  <div id="content">
    <v-form enctype="multipart/form-data" @submit.prevent="onSubmit">
      <div id="title">
        <div>
          <v-layout>
            <v-dialog persistent max-width="400">
              <template v-slot:activator="{ on }">
                <v-icon v-on="on" large color="black">mdi-chevron-left</v-icon>
              </template>
              <template v-slot:default="dialog">
                <v-card>
                  <v-card-title class="headline">
                    수정하기를 취소하시겠습니까?
                  </v-card-title>
                  <v-card-text> * 작성하던 내용이 사라져요 </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn text color="ted" @click="goBack()">
                      수정 안할래요
                    </v-btn>
                    <v-btn text @click="dialog.value = false">
                      계속 수정할래요
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </template>
            </v-dialog>
          </v-layout>
        </div>
        <div>
          <h2 style="margin-top: 6px; margin-left: 5px">등록상품 수정하기</h2>
        </div>
      </div>
      <table>
        <v-row>
          <v-col>
            <v-col cols="3">
              <v-select
                class="checkProcess"
                v-model="process"
                :items="selectProcess"
                label="판매 현황"
              ></v-select>
            </v-col>
            <h3>상품명</h3>
          </v-col>
          <v-col cols="12">
            <input
              id="input"
              placeholder="상품명을 입력해주세요."
              v-model="title"
            />
          </v-col>
        </v-row>
        <v-row>
          <v-col><h3>카테고리</h3></v-col>
          <v-col cols="12">
            <input
              id="input"
              placeholder="카테고리를 입력해주세요."
              v-model="category"
            />
          </v-col>
        </v-row>
        <v-row>
          <v-col><h3>가격</h3></v-col>
          <v-col cols="12">
            <input
              id="input"
              placeholder="가격을 입력해주세요."
              v-model="price"
            />
          </v-col>
        </v-row>
        <v-row>
          <v-col><h3>설명</h3></v-col>
          <v-col cols="12">
            <textarea
              placeholder="상품에 관련된 상세 설명을 작성해주세요."
              v-model="content"
            />
          </v-col>
        </v-row>
        <v-row>
          <v-col><h3>사진</h3></v-col>
          <v-col cols="12">
            <div id="image">
              <div v-if="productBoard.productImage === null"></div>
              <v-img
                v-else
                height="100%"
                width="100%"
                :src="require(`@/assets/pImage/${productBoard.productImage}`)"
              />
            </div>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-btn block depressed color="success" height="50" type="submit"
              ><h3>작성 완료</h3>
            </v-btn>
          </v-col>
        </v-row>
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
      process: "",
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
      const { process, category, productImage, title, price, content } = this;
      this.$emit("submit", {
        memberNo: this.login.memberNo,
        process,
        category,
        productImage,
        title,
        price,
        content,
      });
    },
  },
  created() {
    this.process = this.productBoard.process;
    this.productImage = this.productBoard.productImage;
    this.category = this.productBoard.category;
    this.title = this.productBoard.title;
    this.price = this.productBoard.price;
    this.content = this.productBoard.content;
  },
};
</script>
<style lang="scss" scoped>
table {
  width: 100%;
}
#content {
  display: block;
  margin-top: 72px;
  margin: 30px auto 0 auto;
  border: 1px solid #e9ecef;
  width: 800px;
  margin: 0 auto;
  padding: 40px;
  line-height: 24px;
  background: #fff;
}
#title {
  display: flex;
  padding-bottom: 20px;
}
#input {
  width: 100%;
  border: 2px solid #eaebee;
  box-sizing: border-box;
  border-radius: 6px;
  font-size: 16px;
  box-shadow: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  padding: 16px;
}
textarea {
  border: 2px solid #eaebee;
  box-sizing: border-box;
  border-radius: 6px;
  width: 100%;
  height: 300px;
  padding: 16px;
  resize: none;
}
#image {
  border: 2px solid #eaebee;
  box-sizing: border-box;
  border-radius: 6px;
  width: 100%;
  padding: 16px;
  resize: none;
}

.preview {
  position: relative;
  margin-left: auto;
  margin-right: auto;
  max-width: 350px;
  height: 350px;
}
</style>









