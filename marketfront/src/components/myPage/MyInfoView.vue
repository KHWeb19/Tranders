<template>
  <v-container>
    <v-card width="100%">
      <div style="padding-left: 50px; padding-right: 30px">
        <div v-if="!isCheck && providerType === 'undefined'">
          <v-row justify="center" style="padding-top: 40px">
            <v-card-title style="font-size: 30px">비밀번호를 입력해주세요</v-card-title>
          </v-row>

          <v-row style="height: 80px; padding-top: 10px">
            <v-col cols="3">아이디</v-col>
            <v-col cols="9">
              <v-text-field v-model="id" solo style="width: 100%" readonly disabled></v-text-field>
            </v-col>
          </v-row>

          <v-row v-if="providerType === 'undefined'">
            <v-col cols="3">비밀번호</v-col>
            <v-col cols="9">
              <v-text-field solo v-model="password" style="width: 100%" placeholder="PASSWORD" type="password"> </v-text-field>
            </v-col>
          </v-row>

<!--          <v-row v-else>
            <v-col cols="3">인증번호</v-col> &lt;!&ndash; 카톡으로 인증하든, 메일로 인증하든 변경 &ndash;&gt;
            <v-col cols="9">
              <v-text-field solo v-model="password" style="width: 100%" placeholder="인증번호"> </v-text-field>
            </v-col>
          </v-row>-->

          <v-row>
            <v-btn @click="memberCheck" style="width: 100%; height: 50px; font-size: 20px; border-radius: 18px" class="light-green lighten-3">인증</v-btn>
          </v-row>

          <v-row style="height: 35px"></v-row>
        </div>

        <div v-else>
          <v-row>
            <v-col v-for="count in 4" :key="count"></v-col>
            <v-col>
              <div>
                <v-img v-if="userInfo.profileImg === null" contain max-height="58" src="@/assets/profile/Tranders_base_profile_img.png"></v-img>
                <v-img v-else :src="require(`@/assets/profile/${userInfo.profileImg}`)" id="img" style="height: 180px; width: 180px; border-radius: 70%" @click="changeImg"></v-img>
                <input type=file class="file-input" id="files" ref="files" multiple v-on:change="fileUpload()" style="display: none">
              </div>
            </v-col>
            <v-col></v-col><v-col></v-col><v-col></v-col><v-col></v-col>
          </v-row>

          <v-row justify="end" style="font-size: 10px; padding-right: 15px">
            *프로필 이미지 클릭시 이미지 변경 가능
          </v-row>

          <div id="userInfo">
            <v-row>
              <v-col cols="3">아이디</v-col>
              <v-col cols="9">
                <v-text-field v-model="id" solo style="width: 100%" readonly disabled></v-text-field>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="3">이름</v-col>
              <v-col cols="9">
                <v-text-field v-model="name" solo style="width: 100%" ></v-text-field>
              </v-col>
            </v-row>

            <v-row v-if="providerType === 'undefined'">
              <v-col cols="3">비밀번호</v-col>
              <v-col cols="7">
                <v-form v-model="form" ref="form">
                  <v-text-field v-model="password" solo style="width: 100%" :rules="[pwRules.required, pwRules.min, pwRules.max]" placeholder="변경할 비밀번호를 적어주세요"  type="password"> </v-text-field>
                </v-form>
              </v-col>
              <v-col cols="2" class="pt-5">
                <v-btn style="width: 100%" class="light-green lighten-2" id="checkDub" :disabled="!form" @click="changePassword">확인</v-btn>
              </v-col>
            </v-row>

<!--            <v-row v-if="providerType === 'undefined'">
              <v-col cols="3">전화번호</v-col>
              <v-col cols="7">
                <v-text-field v-model="phoneNumber"  solo style="width: 100%" ></v-text-field>
              </v-col>
            </v-row>-->

            <v-row v-if="providerType === 'undefined'">
              <v-col cols="3">전화번호</v-col>
              <v-col cols="7">
                <v-text-field v-model="phoneNumber"  solo style="width: 100%" ></v-text-field>
              </v-col>
              <v-col cols="2" class="pt-5">
                <v-btn style="width: 100%" class="light-green lighten-2" id="checkDub" @click="checkPhoneNum">확인</v-btn>
              </v-col>
            </v-row>

            <v-row style="height: 70px" v-if="ifCheck">
              <v-col cols="10">
                <v-text-field solo v-model="checkNum" style="width: 100%" placeholder="인증번호"> </v-text-field>
              </v-col>
              <v-col cols="2" class="pt-5">
                <v-btn style="width: 100%" id="checkDub" @click="certification" outlined>인증하기</v-btn>
              </v-col>
            </v-row>

            <v-row style="height: 70px; margin-top: 35px">
              <v-col>
                <v-btn @click="registerBtn" style="width: 100%; height: 100%; font-size: 20px; border-radius: 18px" class="light-green lighten-3">변경완료</v-btn>
              </v-col>
            </v-row>
          </div>

          <v-dialog width="500" v-model="changeImgDialog">
            <v-card style="width: 500px; position: relative">
              <v-row justify="center" style="width: 500px; padding-bottom: 10px">
                <v-card-title>이미지 변경</v-card-title>
              </v-row>

              <v-row style="height: 300px; width: 500px;" justify="center">
                <div style="width: 300px; height: 250px; justify-content: center; display: flex; align-items: center">
                  <v-btn @click="onClickFile" icon v-if="changeIsCheck">
                    <v-img src="@/assets/plus.png" style="height: 300px; width: 350px"> </v-img>
                  </v-btn>

                  <div class="itemFileBox" ref="itemFileBox">
                    <input type="file" class="item-file-image" id="uploadItemFile" ref="uploadItemFile" @change="onFileSelected" accept="image/*" style="display: none"/>
                    <label for="uploadItemFile">
                      <div class="wrapper-image">
                        <img ref="uploadItemImage" alt="" src=""/>
                      </div>
                    </label>
                  </div>
                </div>
              </v-row>

              <v-row justify="end" no-gutters>
                <v-card-actions>
                  <v-btn class="light-green lighten-3" @click="saveImg">Save</v-btn>
                  <v-btn @click="dialogClose">Close</v-btn>
                </v-card-actions>
              </v-row>
            </v-card>
          </v-dialog>

        </div>
      </div>
    </v-card>
  </v-container>
</template>

<script>

import axios from "axios";
import {API_BASE_URL} from "@/constant/login";
import cookies from "vue-cookies";

const config = {
  headers: {
    'Authorization': 'Bearer '+ cookies.get('access_token'),
    'Accept' : 'application/json',
    'Content-Type': 'application/json'
  }
};

export default {
  name: "MyInfoView",
  data() {
    return {
      form: false,
      isCheck: false, // false로 꼭 변경해두기!
      changeIsCheck: true,
      id: cookies.get("id"),
      providerType: cookies.get("providerType"),
      password: '',
      name: '',
      phoneNumber: '',
      changeImgDialog: false,
      itemImageInfo: {
        uploadImages: ''
      },
      file: null,
      checkNum: '',
      pwRules: {
        required: value => !!value || 'Required.',
        min: v => v.length >= 8 || 'Min 8 characters',
        max: v => v.length <= 18 || 'Max 18 characters'
      },
    }
  },
  props: {
    userInfo: {
      type: Object
    },
    ifCheck: {
      type: Boolean
    }
  },
  methods: {
    registerBtn(){
      if(this.providerType === 'undefined') {
        const {id, name, phoneNumber} = this;
        this.$emit('modifyMember', {id, name, phoneNumber});
      }else {
        const {id, name} = this;
        this.$emit('modifyMemberSns', {id, name});
      }
    },
    changePassword(){
      const {id, password} = this;
      this.$emit('changePassword', {id, password});
    },
    memberCheck() {
      let id = this.userInfo.id;
      let password = this.password
      axios.post(API_BASE_URL+'/member/myPageCheck', {id, password}, config)
          .then((res) => {
            console.log(res);
            if(res.data === true){
              this.isCheck = res.data;
              this.password = '';
            }else {
              alert('잘못된 비밀번호 입니다.')
              this.$router.go();
            }
          })
          .catch(() => {
            alert('에러')
          })
    },
    changeImg(){
      alert('changeImg')
      this.changeImgDialog = true;
    },
    onClickFile(){
      this.$refs.uploadItemFile.click()
    },
    onFileSelected(event){
      let image = event.target;

      if(image.files[0]){
        this.changeIsCheck = false;
        this.file = this.$refs.uploadItemFile.files;
        console.log(this.file)
        this.fileLength = this.file.length
        let itemImage = this.$refs.uploadItemImage; //img dom 접근

        itemImage.src = window.URL.createObjectURL(image.files[0]);//img src에 blob주소 변환

        this.itemImageInfo.uploadImages = itemImage.src; //이미지 주소 data 변수에 바인딩해서 나타내게 처리

        itemImage.width ='200'; // 이미지 넓이

        itemImage.onload = () => {
          window.URL.revokeObjectURL(this.src)  //나중에 반드시 해제해주어야 메모리 누수가 안생김.
        }
      }
    },
    dialogClose(){
      this.changeImgDialog = false;
    },
    saveImg(){
      if(this.fileLength === ''){
        alert('파일을 선택해주세요!')
      }else {
        let formData = new FormData;

        formData.append('imgFile', this.file[0])
        formData.append('id', this.id)

        this.$emit('saveProfileImg', formData)
      }

      this.changeImgDialog = false;
    },
    checkPhoneNum(){
      const {phoneNumber} = this;

      this.$emit('checkPhoneNum', {phoneNumber});
    },
    certification(){
      let num = this.checkNum;

      this.$emit('certification', {num})
    }
  },
  created() {
    this.name = this.userInfo.name
    this.phoneNumber = this.userInfo.phoneNumber
  }

}
</script>

<style scoped>
#userInfo{
  max-width: 460px;
  margin: 0 auto;
  padding-top: 60px;
  padding-bottom: 35px;
}
</style>