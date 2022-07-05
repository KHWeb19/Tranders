<template>
  <div>
    <after-login-view></after-login-view>
    <v-form enctype="multipart/form-data" @submit.prevent="onSubmit">
      <table>
        <tr>
          <td v-if="!preview">
            <label for="files">
              <span>사진 선택</span>
            </label>
            <input
              type="file"
              id="files"
              ref="files"
              multiple
              v-on:change="handleFileUpload()"
            />
          </td>

          <td v-else rowspan="2">
            <div class="slide-3d">
              <v-container style="padding: 0px">
                <swiper class="swiper" :options="swiperOption">
                  <swiper-slide
                    ><v-img width="300px" height="300px" :src="preview"
                  /></swiper-slide>
                  <swiper-slide v-if="preview2"
                    ><v-img width="300px" height="300px" :src="preview2"
                  /></swiper-slide>
                  <swiper-slide v-if="preview3"
                    ><v-img width="300px" height="300px" :src="preview3"
                  /></swiper-slide>
                  <div class="swiper-pagination" slot="pagination"></div>
                  <div class="swiper-button-prev" slot="button-prev"></div>
                  <div class="swiper-button-next" slot="button-next"></div>
                </swiper>
              </v-container>
            </div>
          </td>
        </tr>

        <v-col>
          <v-select
            class="checkProcess"
            v-model="process"
            :items="selectProcess"
            label="판매상황 체크"
          ></v-select>
        </v-col>
        <tr>
          <td>카테고리</td>
          <td>
            <input
              type="text"
              solo
              style="width: 100%"
              v-model="category"
              placeholder="카테고리를 설정해주세요"
            />
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
import axios from "axios";
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import AfterLoginView from "../home/AfterLoginView.vue";
export default {
  name: "ProductRegisterForm",
  components: {
    AfterLoginView,
    Swiper,
    SwiperSlide,
  },
  data() {
    return {
      selectProcess: ["판매중", "판매완료"],
      process: "",
      productImage: "",
      productImage1: null,
      productImage2: null,
      preview: "",
      preview2: null,
      preview3: null,
      category: "",
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
    handleFileUpload() {
      this.files = this.$refs.files.files;
      this.preview = URL.createObjectURL(this.files[0]);
      if (this.files[1]) {
        this.preview2 = URL.createObjectURL(this.files[1]);
        if (this.files[2]) {
          this.preview3 = URL.createObjectURL(this.files[2]);
        }
      }
    },
    onSubmit() {
      let formData = new FormData();
      for (let idx = 0; idx < this.files.length; idx++) {
        formData.append("fileList", this.files[idx]);
      }
      axios
        .post("http://localhost:7777/product/uploadImg", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          this.productImage = this.files[0].name;
          if (this.files[1]) {
            this.productImage2 = this.files[1].name;
            if (this.files[2]) {
              this.productImage3 = this.files[2].name;
              const {
                process,
                category,
                productImage,
                productImage2,
                productImage3,
                title,
                price,
                content,
              } = this;
              this.$emit("submit", {
                memberNo: this.login.memberNo,
                process,
                category,
                productImage,
                productImage2,
                productImage3,
                title,
                price,
                content,
              });
            } else {
              const {
                process,
                productImage,
                category,
                productImage2,
                title,
                price,
                content,
              } = this;
              this.$emit("submit", {
                memberNo: this.login.memberNo,
                process,
                category,
                productImage,
                productImage2,
                title,
                price,
                content,
              });
            }
          } else {
            const { process, productImage, category, title, price, content } =
              this;
            this.$emit("submit", {
              memberNo: this.login.memberNo,
              process,
              category,
              productImage,
              title,
              price,
              content,
            });
          }
        })
        .catch((res) => {
          alert("처리 결과: " + res.message);
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.slide-3d {
  width: 300px;
}
.swiper {
  .swiper-slide {
    width: 300px;
    height: 300px;
    background-color: white;
    background-position: center;
    background-size: cover;
  }
}
</style>

