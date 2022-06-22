<template>
  <div id="my_page_box" style="position: relative">
    <div id="profile_back_img">
      <div style=" width: 100%; position: relative; z-index: 3" @click="backProfileImgDialog = true">
        <v-img v-if="backProfileImgs === null" src="@/assets/bossProfile/back/Tranders_boss_base_backProfile.png" id="img" style="width: 100%; height: 500px;"></v-img>
        <swiper v-else class="swiper" :options="swiperOption" style="height: 500px">
          <swiper-slide v-for="(boardImg,index) in backProfileImgs" :key="index">
            <v-img :src="require(`@/assets/bossProfile/back/${boardImg.fileName}`)" id="img"></v-img>
          </swiper-slide>

          <div class="swiper-pagination" slot="pagination"></div>
<!--          <div class="swiper-button-prev" slot="button-prev"></div>
          <div class="swiper-button-next" slot="button-next"></div>-->
        </swiper>
      </div>

      <div style=" top: 25px; padding-left: 5px; position: absolute; z-index: 1">
        <v-btn icon @click="backPage"><v-icon x-large>mdi-chevron-left</v-icon></v-btn>
      </div>
      <div style="top: 25px; position: absolute; left: 88%">
        <v-btn text @click="modify"><v-icon x-large>mdi-camera</v-icon>&nbsp; &nbsp;수정</v-btn>
      </div>
    </div>

    <div id="profile" style="padding-top: 30px">

      <div id="profile_img">
        <v-img :src="require(`@/assets/bossProfile/front/${boss.profileImg}`)" height="200px" width="200px" @click="changeImg" style="border-radius: 70%"></v-img>
        <input type=file class="file-input" id="files" ref="files" multiple v-on:change="fileUpload()" style="display: none">
      </div>
      <div style="padding-left: 55px; font-size: 40px; width: 100%" class="pa-5">
        {{ boss.placeName }}
        <div style="font-size: 20px" class="pa-2">
          {{ boss.region }} ㆍ {{boss.category}} ㆍ 단골 0
        </div>
      </div>
    </div>

    <div style="padding-bottom: 50px; height: 40px; padding-top: 30px">
      <div v-if="isCheck" style="float: left; height: 40px; width: 50%; border-right: black solid 1px; font-size: 25px; text-align: center; border-bottom: 3px solid black">
        홈
      </div>

      <div v-else style="float: left; height: 40px; width: 50%; border-right: black solid 1px; font-size: 25px; text-align: center" @click="home">
        홈
      </div>

      <div v-if="isCheck" style="float: left; height: 40px; width: 50%; font-size: 25px; text-align: center" @click="review">
        후기
      </div>

      <div v-else style="float: left; height: 40px; width: 50%; font-size: 25px; text-align: center; border-bottom: 3px solid black">
        후기
      </div>
    </div>

      <div v-if="isCheck">
        <div style="padding-bottom: 20px" class="home_style" v-if="boss.marketInfo === null">
          <span style="font-size: 30px; font-weight: bold;"> 정보 </span>
          <!--   정보가 없을 경우에     -->
          <div style="margin-top: 20px" @click="infoDialog">
            <div style="border-style: dotted; height: 120px; border-radius: 8px; text-align: center; font-size: 23px; opacity: 0.5" class="pa-11">
              + &nbsp; 소개 문구 등록
            </div>

            <div style="padding-top: 30px">
              <span style="font-size: 25px; opacity: 0.5;"><v-icon x-large>mdi-map-marker-outline</v-icon> 주소를 추가해주세요.</span>
            </div>

            <div style="padding-top: 15px">
              <span style="font-size: 25px; opacity: 0.5;"><v-icon x-large>mdi-clock-time-five-outline</v-icon> 영업시간을 추가해 주세요.</span>
            </div>
          </div>

          <div style="padding-top: 30px" class="home_style_btn">
            <v-btn style="width: 100%; height: 50px; font-size: 25px; font-weight: bold" @click="infoDialog">
              정보 관리하기
            </v-btn>
          </div>

          <!--    정보가 있을 경우    -->
        </div> <!-- 정보 부분 -->

        <div style="padding-bottom: 20px" class="home_style" v-else>
          <span style="font-size: 30px; font-weight: bold;"> 정보 </span>
          <!--   정보가 없을 경우에     -->
          <div style="margin-top: 20px">
            <div style="height: auto; font-size: 23px;" class="pa-2">
              {{boss.marketInfo}}
            </div>

            <div class="kmap" ref="map" style="position: relative; overflow: hidden"></div>

            <div style="padding-top: 30px">
              <span style="font-size: 25px;"><v-icon x-large>mdi-map-marker-outline</v-icon> {{ boss.address }}</span>
            </div>

            <div style="padding-top: 15px">
              <span style="font-size: 25px;"><v-icon x-large>mdi-clock-time-five-outline</v-icon> {{boss.startTime}} ~ {{boss.endTime}}</span>
            </div>

            <div style="padding-top: 15px">
              <span style="font-size: 25px;"><v-icon x-large>mdi-earth</v-icon> {{boss.marketHomePage}}</span>
            </div>
          </div>

          <!--    정보가 없을 경우    -->
        </div> <!-- 정보 부분 -->


        <hr style="height: 15px; background-color: rgba(94,94,94,0.16); border: none"/>

        <div style="padding-bottom: 20px" class="home_style">
          <span style="font-size: 30px; font-weight: bold;"> 쿠폰 </span>
          <!--   정보가 없을 경우에     -->
          <div style="margin-top: 20px">
            <div style="padding-top: 30px">
              <span style="font-size: 25px; opacity: 0.5;">아직 쿠폰이 없어요.</span>
            </div>

            <div style="padding-top: 30px">
              <v-btn style="width: 100%; height: 50px; font-size: 25px; font-weight: bold">
                쿠폰 관리하기
              </v-btn>
            </div>
          </div>

        </div> <!-- 쿠폰 부분 -->

        <hr style="height: 15px; background-color: rgba(94,94,94,0.16); border: none"/>

        <div style="padding-bottom: 20px" class="home_style">
          <span style="font-size: 30px; font-weight: bold;"> 가격 </span>
          <!--   정보가 없을 경우에     -->
          <div style="margin-top: 20px">
            <div style="padding-top: 30px">
              <span style="font-size: 25px; opacity: 0.5;">상품이나 서비의 가격을 추가하고, 이웃의 눈길을 끌어보세요.</span>
            </div>

            <div style="padding-top: 30px">
              <v-btn style="width: 100%; height: 50px; font-size: 25px; font-weight: bold" @click="priceDialog = true">
                가격 설정
               </v-btn>
            </div>
          </div>

        </div> <!-- 가격 부분 -->
      </div>

      <div v-else style="border: 5px solid orange;">
        <div style="height: 320px; text-align: center; font-size: 23px; opacity: 0.5" class="pa-12">
          아직 후기가 없어요.
        </div>
      </div>

      <div style="background-color: rgba(187,187,187,0.23); height: 100px; text-align: center; font-size: 15px" class="pa-10">
        오이마켓에 등록된 가게의 정보는 가게 관리자가 직접 등록한 것으로 (주)오이마켓은 등록된 내용에 대하여 일체의 책임을 지지 않습니다.
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
            <v-btn class="light-green lighten-3" @click="saveProfile">Save</v-btn>
            <v-btn @click="changeImgDialog = false">Close</v-btn>
          </v-card-actions>
        </v-row>
      </v-card>
    </v-dialog>

    <v-dialog v-model="backProfileImgDialog">
      <v-card style="height: 700px">
        <div class="main-container">
          <div class="room-deal-information-container">
            <div class="room-deal-information-title">사진 등록</div>
            <div class="room-picture-notice">
              <ul class="room-write-wrapper">
                <li>
                  사진은 가로로 찍은 사진을 권장합니다. (가로 사이즈 최소 800px)
                </li>
                <li>사진 용량은 사진 한 장당 10MB 까지 등록이 가능합니다.</li>

              </ul>
            </div>
            <div class="room-file-upload-wrapper">
              <div v-if="!files.length" class="room-file-upload-example-container">
                <div class="room-file-upload-example">
                  <div class="room-file-image-example-wrapper">이미지</div>
                  <div class="room-file-notice-item">
                    실사진 최소 3장 이상 등록하셔야 하며, 가로사진을 권장합니다.
                  </div>
                  <div class="room-file-notice-item room-file-notice-item-red">
                    로고를 제외한 불필요한 정보(워터마크,상호,전화번호 등)가 있는 매물은 비공개처리됩니다
                  </div>
                  <div class="room-file-notice-item room-file-upload-button">
                    <div class="image-box">
                      <!-- <div class="image-profile">
  <img :src="profileImage" />
  </div>-->
                      <label for="file">일반 사진 등록</label>
                      <input type="file" id="file" ref="files" @change="imageUpload" multiple />
                    </div>
                  </div>
                </div>
              </div>
              <div v-else class="file-preview-content-container">
                <div class="file-preview-container">
                  <div v-for="(file, index) in files" :key="index" class="file-preview-wrapper">
                    <div class="file-close-button" @click="fileDeleteButton" :name="file.number">
                      x
                    </div>
                    <img :src="file.preview" />
                  </div>
                  <div class="file-preview-wrapper-upload">
                    <div class="image-box">
                      <label for="file">추가 사진 등록</label>
                      <input type="file" id="file" ref="files" @change="imageAddUpload" multiple />
                    </div>
                    <!-- <div class="file-close-button" @click="fileDeleteButton" :name="file.number">x</div> -->
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <v-btn @click="saveBackProfile">저장</v-btn>
      </v-card>
    </v-dialog>

    <v-dialog v-model="priceDialog" width="800px">
      <v-card width="800px" height="500px" class="pa-4">
        <v-row>
          <v-card-title>가격 목록</v-card-title>
        </v-row>

        <!--    리스트가 보이는 곳    -->

        <v-row>
          <v-btn @click="addPriceDialog = true">가격 추가</v-btn>
        </v-row>
      </v-card>
    </v-dialog>

    <v-dialog v-model="addPriceDialog"  width="800px">
      <v-card width="800px" class="pa-4">

        <div style="padding: 10px 10px 10px 10px">
          <div style="justify-content: center; display: flex">
            <v-card-title style="font-weight: bold; font-size: 25px">가격 추가</v-card-title>
          </div>

          <div class="priceTitle"> 항목 </div>

          <div>
            <v-text-field solo label="항목명을 입력하세요. (예: 아메리카노)"></v-text-field>
          </div>

          <div class="priceTitle">
            가격
          </div>

          <div>
            <v-text-field solo label="가격을 입력하세요"></v-text-field>
          </div>

          <div class="priceTitle">
            추가 설명 (선택)
          </div>

          <div>
            <v-textarea label="가격에 대한 설명이 필요하다면 적어주세요." solo></v-textarea>
          </div>

          <div style="display: flex; justify-content: end">
            <v-btn style="margin-right: 8px" @click="addPriceDialog = false">close</v-btn>
            <v-btn class="light-green lighten-3">save</v-btn>
          </div>
        </div>
      </v-card>
    </v-dialog>
    </div>

</template>

<script>
import cookies from "vue-cookies";
import {mapActions, mapState} from "vuex";
import 'swiper/css/swiper.css'
import {Swiper, SwiperSlide} from "vue-awesome-swiper";

let kakao = window.kakao;

export default {
  name: "BossPageView",
  props: {
    boss: {
      type: Object
    }
  },
  components:{
    Swiper, SwiperSlide
  },
  data(){
    return {
      name: 'test',
      isCheck: true,
      mapInstance: null,
      markInstance: null,
      backProfileImg: false,
      id: cookies.get("id"),
      changeImgDialog: false,
      backProfileImgDialog: false,
      addPriceDialog: false,
      priceDialog: false,
      itemImageInfo: {
        uploadImages: ''
      },
      file: null,
      changeIsCheck: true,
      files: [],
      filesPreview: [],
      uploadImageIndex: 0,
      swiperOption: {
        slidesPerView: 1,
        spaceBetween: 30,
        autoHeight: true,
        loop: false,
        pagination: {
          el: '.swiper-pagination',
          clickable: true
        },
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev'
        }
      }
    }
  },
  methods: {
    ...mapActions(['fetchBossBackProfile']),
    backPage(){
      this.$router.push({name: 'MyPageProfile'})
    },
    modify(){
      //alert('사진을 변경하겠다!')
      this.backProfileImg = true
    },
    home(){
      this.isCheck = true;
    },
    review(){
      this.isCheck = false;
    },
    infoDialog(){
      this.$router.push({name: 'BossMarketInfo'})
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
        let itemImage = this.$refs.uploadItemImage;

        itemImage.src = window.URL.createObjectURL(image.files[0]);

        this.itemImageInfo.uploadImages = itemImage.src;

        itemImage.width ='200';

        itemImage.onload = () => {
          window.URL.revokeObjectURL(this.src)
        }

      }
    },
    saveProfile(){
      if(this.fileLength === ''){
        alert('파일을 선택해주세요!')
      }else {
        let formData = new FormData;

        formData.append('imgFile', this.file[0])
        formData.append('id', this.id)

        this.$emit('saveBossProfileImg', formData)
      }

      this.changeImgDialog = false;
    },
    imageUpload() {
      console.log(this.$refs.files.files);

      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제및 관리를 위한 number
            number: i
          }
        ];
        num = i;
        //이미지 업로드용 프리뷰
        // this.filesPreview = [
        //   ...this.filesPreview,
        //   { file: URL.createObjectURL(this.$refs.files.files[i]), number: i }
        // ];
      }
      this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장
      console.log(this.files);
      // console.log(this.filesPreview);
    },
    imageAddUpload() {
      console.log(this.$refs.files.files);

      // this.files = [...this.files, this.$refs.files.files];
      //하나의 배열로 넣기c
      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        console.log(this.uploadImageIndex);
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제및 관리를 위한 number
            number: i + this.uploadImageIndex
          }
        ];
        num = i;
      }
      this.uploadImageIndex = this.uploadImageIndex + num + 1;

      console.log(this.files);
      // console.log(this.filesPreview);
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute('name');
      this.files = this.files.filter(data => data.number !== Number(name));
      // console.log(this.files);
    },
    saveBackProfile(){
      let formData = new FormData;

      for(let i = 0; i < this.files.length; i++){
        formData.append('fileList', this.files[i].file)
      }
      formData.append('id', this.id)
      formData.append('name', this.name)
      this.$emit('saveBackProfile', formData)
    }
  },
  mounted() {
    let container = this.$refs.map;

    this.mapInstance = new kakao.maps.Map(container, {
      center: new kakao.maps.LatLng(this.boss.lat, this.boss.lng),
      level: 2,
    });

    this.markInstance = new kakao.maps.Marker({
      position: new kakao.maps.LatLng(this.boss.lat, this.boss.lng),
      //clickable: true
    })

    this.markInstance.setMap(this.mapInstance);

  },
  computed: {
    ...mapState(['backProfileImgs'])
  },
  created() {
    this.fetchBossBackProfile(this.id)
  }
}
</script>

<style lang="scss" scoped>
#my_page_box{
  align-items: center;
  width: 100%;
  max-width: 1250px;
  margin: 0 auto;
  padding: 15px 7px 0 7px; /*위, 오른쪽, 아래, 왼쪽 */
}
#profile {
  padding: 15px 20px 20px 20px;
  display: flex;
}
.home_style {
  padding: 10px 20px 10px 20px;
}
.home_style_btn {
  padding: 10px 0 10px 0;
}
.kmap {
  width: 100%;
  height: 300px;
}
.main-container {
  width: 1200px;
  height: 400px;
  margin: 0 auto;
}

.room-deal-information-container {
  margin-top: 50px;
  color: #222222;
  border: 1px solid #dddddd;
}

.room-deal-information-title {
  text-align: center;
  font-size: 18px;
  line-height: 60px;
  border-bottom: 1px solid #dddddd;
}

.room-deal-information-content-wrapper {
  min-height: 50px;
  display: flex;
}

.room-deal-informtaion-content-title {
  font-size: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 150px;
  background-color: #f9f9f9;
}

.room-deal-information-content {
  width: 100%;
  font-size: 14px;
}

.room-deal-option-selector {
  display: flex;
  align-items: center;
  padding: 15px;
}

.room-deal-option-item {
  width: 100px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid #cccccc;
  border-radius: 5px;
  cursor: pointer;
}

.room-deal-option-item:last-child {
  margin-left: 10px;
}

.room-deal-option-notice {
  margin-left: auto;
  font-size: 14px;
  color: #888888;
}

.room-deal-option-item-deposit {
  margin-left: 10px;
}

.room-deal-information-wrapper {
  display: flex;
  flex-direction: column;
}

.room-deal-information-option {
  padding: 10px;
  display: flex;
  align-items: center;
}

.room-deal-information-option:last-child {
  border-bottom: 1px solid #dddddd;
}

.room-deal-information-item-type {
  font-size: 13px;
  color: #fff;
  background-color: #61b6e5;
  min-width: 50px;
  height: 26px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 3px;
}

.room-deal-information-item-wrapper {
  display: flex;
  align-items: center;
  margin-left: 10px;
  height: 46px;
  width: 100%;
}

.room-deal-information-item-wrapper>input {
  border: 1px solid #dddddd;
  width: 140px;
  height: 100%;
  padding: 0 15px;
  font-size: 15px;
}

.room-deal-inforamtion-won {
  margin: 0 10px;
}

.room-deal-information-example {
  color: #888888;
}

.room-deal-information-option:not(:first-child) {
  margin-top: 10px;
}

.room-deal-inforamtion-divide {
  font-size: 22px;
  margin: 0 8px;
  color: #222222;
  font-weight: 100;
}

.room-deal-close-button-wrapper {
  margin-left: auto;
  cursor: pointer;
}

.room-deal-close-button {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 22px;
  height: 22px;
  background-color: #666666;
  color: rgb(220, 220, 220);
}

.room-deal-cliked {
  background-color: rgb(235, 235, 235);
  color: rgb(170, 170, 170);
}

.room-file-upload-example {
  height: 100%;
}

.room-write-content-container {
  border-top: 1px solid #dddddd;
  min-height: 260px;
}

.room-picture-notice {
  margin: 20px;
  padding: 20px 40px;
  border: 1px solid #dddddd;
}

.file-preview-content-container {
  height: 100%;
}

.room-file-upload-wrapper {
  margin: 20px;
  border: 1px solid #dddddd;
  background-color: #f4f4f4;
  min-height: 350px;
  font-size: 15px;
  color: #888888;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.room-file-upload-example-container {
  display: flex;
  align-items: center;
  justify-content: center;
  /* height: 100%;
width: 100%; */
}

.room-file-image-example-wrapper {
  text-align: center;
}

.room-file-notice-item {
  margin-top: 5px;
  text-align: center;
}

.room-file-notice-item-red {
  color: #ef4351;
}

.image-box {
  margin-top: 30px;
  padding-bottom: 20px;
  text-align: center;
}

.image-box input[type='file'] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}

.image-box label {
  display: inline-block;
  padding: 10px 20px;
  background-color: #232d4a;
  color: #fff;
  vertical-align: middle;
  font-size: 15px;
  cursor: pointer;
  border-radius: 5px;
}

.file-preview-wrapper {
  padding: 10px;
  position: relative;
}

.file-preview-wrapper>img {
  position: relative;
  width: 190px;
  height: 130px;
  z-index: 10;
}

.file-close-button {
  position: absolute;
  /* align-items: center; */
  line-height: 18px;
  z-index: 99;
  font-size: 18px;
  right: 5px;
  top: 10px;
  color: #fff;
  font-weight: bold;
  background-color: #666666;
  width: 20px;
  height: 20px;
  text-align: center;
  cursor: pointer;
}

.file-preview-container {
  height: 100%;
  display: flex;
  flex-wrap: wrap;
}

.file-preview-wrapper-upload {
  margin: 10px;
  padding-top: 20px;
  background-color: #888888;
  width: 190px;
  height: 130px;
}

.room-write-button-wrapper {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #222222;
}

.room-write-button-wrapper>div {
  width: 160px;
  height: 50px;
  border: 1px solid #dddddd;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 15px;
  cursor: pointer;
}

.room-write-button {
  margin-left: 15px;
  color: #fff;
  background-color: #1564f9;
}

.room-write-button:hover {
  opacity: 0.8;
}
.swiper {
  height: 900px;
  width: 100%;

  .swiper-slide {
    display: flex;
    justify-content: center;
    align-items: center;
  }
}

#img {
  width: 100%; height: 500px;
  max-width: 100%;
  max-height: 500px;
}
.priceTitle {
  padding-bottom: 10px;
  font-size: 18px;
}
</style>