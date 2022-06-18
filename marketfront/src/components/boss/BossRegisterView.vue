<template>
    <div id="my_page_box">
      <v-card width="100%" class="pa-5">
        <div id="card_box">
        <v-row no-gutters>
          <v-col cols="1" style="padding-top: 5px">
            <v-btn icon @click="backPage"><v-icon x-large>mdi-chevron-left</v-icon></v-btn>
          </v-col>
          <v-col cols="10">
            <v-card-text style="font-size: 25px;" align="center">사장님 가게 등록</v-card-text>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="3">가게 이름</v-col>
          <v-col cols="9">
            <v-text-field solo v-model="name" style="width: 100%" placeholder="예) 오이베이커리, 오이슈퍼"> </v-text-field>
          </v-col>
        </v-row>

        <v-row style="padding-top: 50px; padding-bottom: 50px">
          <v-col>가게 대표 이미지</v-col>
          <v-col>
            <v-btn @click="onClickFile" icon v-if="isCheck">
              <v-img src="@/assets/plus.png" style="height: 200px; width: 250px"> </v-img>
            </v-btn>
            <div class="itemFileBox" ref="itemFileBox">
              <input type="file" class="item-file-image" id="uploadItemFile" ref="uploadItemFile" @change="onFileSelected" accept="image/*" style="display: none"/>

              <label for="uploadItemFile">
                <div class="wrapper-image">
                  <img ref="uploadItemImage" alt="" src=""/>
                </div>
              </label>
            </div>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="3">활동 지역</v-col>
          <v-col cols="9">
            <v-text-field solo v-model="region" style="width: 100%" placeholder="활동 지역을 설정해 주세요."> </v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="3">카테고리</v-col>
          <v-col cols="9">
            <v-text-field solo style="width: 100%" v-model="category" placeholder="카테고리를 설정해 주세를."> </v-text-field>
          </v-col>
        </v-row>

        <v-row style="height: 70px; margin-top: 35px">
          <v-col>
            <v-btn @click="registerBoss" style="width: 100%; height: 100%; font-size: 20px; border-radius: 18px" class="light-green lighten-3">변경완료</v-btn>
          </v-col>
        </v-row>
        </div>
      </v-card>
    </div>
</template>

<script>
import cookies from "vue-cookies";

export default {
  name: "BossRegisterView",
  data(){
    return{
      id: cookies.get('id'),
      name: '',
      region: '',
      category: '',
      fileLength: 0,
      isCheck: true,
      itemImageInfo: {
        uploadImages: ''
      },
      file: null
    }
  },
  methods: {
    backPage(){
      this.$router.push({name: 'MyPageProfile'})
    },
    registerBoss(){
      if(this.name === '' || this.region === '' || this.category === '' || this.fileLength === 0){
        alert('내용을 작성해주세요!')
      }else {
        //console.log(this.file)

        let formData = new FormData;

        formData.append('imgFile', this.file[0])
        formData.append('name', this.name)
        formData.append('region', this.region)
        formData.append('category', this.category)
        formData.append('id', this.id)

        console.log(formData)
        this.$emit('registerBoss', formData)
      }
    },
    onClickFile(){
      this.$refs.uploadItemFile.click()
    },
    onFileSelected(event){
      let image = event.target;

      if(image.files[0]){
        this.isCheck = false;
        this.file = this.$refs.uploadItemFile.files;
        console.log(this.file)
        this.fileLength = this.file.length
        let itemImage = this.$refs.uploadItemImage; //img dom 접근

        itemImage.src = window.URL.createObjectURL(image.files[0]);//img src에 blob주소 변환

        this.itemImageInfo.uploadImages = itemImage.src; //이미지 주소 data 변수에 바인딩해서 나타내게 처리

        itemImage.width ='150'; // 이미지 넓이

        itemImage.onload = () => {
          window.URL.revokeObjectURL(this.src)  //나중에 반드시 해제해주어야 메모리 누수가 안생김.
        }

      }
    }
  }
}
</script>

<style scoped>
#my_page_box{
  display: flex;
  align-items: center;
  width: 100%;
  max-width: 1250px;
  margin: 0 auto;
  padding: 15px 7px 0 7px; /*위, 오른쪽, 아래, 왼쪽 */
}
#card_box{
  padding-right: 20px;
  padding-left: 20px;
}
</style>