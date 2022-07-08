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
                    글쓰기를 취소하시겠습니까?
                  </v-card-title>
                  <v-card-text> * 작성하던 내용이 사라져요 </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn text color="ted" @click="goBack()"> 안쓸래요 </v-btn>
                    <v-btn text @click="dialog.value = false">
                      계속 쓸래요
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </template>
            </v-dialog>
          </v-layout>
        </div>
        <div>
          <h2 style="margin-top: 6px; margin-left: 5px">
            중고거래 상품등록하기
          </h2>
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
              >
              </v-select>
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
              <label for="files"><v-icon large>mdi-camera</v-icon></label>
              <input
                type="file"
                id="files"
                ref="files"
                dense
                style="width: 0px"
                multiple
                v-on:change="handleFileUpload()"
              />
              <swiper class="swiper" :options="swiperOption">
                <swiper-slide
                  ><v-img width="100%" height="100%" :src="preview"
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
import axios from "axios";
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
export default {
  name: "ProductRegisterForm",
  components: {
    Swiper,
    SwiperSlide,
  },
  data() {
    return {
      selectProcess: ["판매중", "판매완료"],
      process: 0,
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
                regDate,
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
                regDate,
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
                regDate,
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
                regDate,
              });
            }
          } else {
            const {
              process,
              productImage,
              category,
              title,
              price,
              content,
              regDate,
            } = this;
            this.$emit("submit", {
              memberNo: this.login.memberNo,
              process,
              category,
              productImage,
              title,
              price,
              content,
              regDate,
            });
          }
        })
        .catch((res) => {
          alert("처리 결과: " + res.message);
        });
    },
    goBack() {
      this.$router.go(-1);
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
    width: 100%;
    height: 100%;
    background-color: white;
    background-position: center;
    background-size: cover;
  }
}
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
  width: 300px;
  height: 350px;
}
</style>



