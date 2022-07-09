<template>
  <div>
    <v-app id="inspire">
      <v-row justify="center">
        <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
          <v-card>
            <div id="report_box" style="position: relative;">
              <div style="width: 100%">
                <v-toolbar dark elevation="3" color="light-green lighten-1">
                  <v-btn icon dark @click="closeDialog">
                    <v-icon>mdi-chevron-left</v-icon>
                  </v-btn>
                  <v-spacer></v-spacer>
                  <v-toolbar-title>게시글 신고</v-toolbar-title>
                  <v-spacer></v-spacer>
                </v-toolbar>
              </div>

              <div v-if="!sendForm">
                <div v-if="memRepoCheck">
                  <div style="padding: 25px 15px 0 15px">
                    <div style="font-size: 20px; font-weight: bolder; padding-bottom: 20px">
                      '{{productBoard.title}}'<br/>
                      게시글을 신고하는 이유를 선택해주세요.
                    </div>

                    <v-divider></v-divider>

                    <div>
                      <div id="repo" v-for="(repo, index) in repoList" :key="repo">
                        <div style="display: flex; align-items: center; height: 50px; padding-top: 30px; padding-bottom: 30px;" @click="clickReport(index)">
                          <div style="width: 100%">{{repo}}</div><div style="display: flex; justify-content: end"><v-icon style="color: black">mdi-chevron-right</v-icon></div>
                        </div>
                        <v-divider></v-divider>
                      </div>
                    </div>

                    <div style="padding-top: 50px">
                      <div style="font-size: 20px; font-weight: bolder; padding-bottom: 20px">
                        혹시 '{{productBoard.member.name}}'을 신고하고 싶으신가요?
                      </div>
                      <v-divider></v-divider>
                      <div style="padding-top: 30px; padding-bottom: 30px; display: flex; align-items: center; height: 50px" @click="clickMemRepo">
                        <div style="width: 100%">사용자 신고하러 가기</div><div style="display: flex; justify-content: end"><v-icon style="color: black">mdi-chevron-right</v-icon></div>
                      </div>
                      <v-divider></v-divider>
                    </div>

                  </div>
                </div>

                <div v-else>
                  <div v-if="!repoCheck">
                    <div id="repo" v-for="(repo, index) in check" :key="repo">
                      <div style="display: flex; align-items: center; height: 50px; padding-top: 30px; padding-bottom: 30px;" @click="clickReportDetail(index, repo)">
                        <div style="width: 100%">{{repo}}</div><div style="display: flex; justify-content: end"><v-icon style="color: black">mdi-chevron-right</v-icon></div>
                      </div>
                      <v-divider></v-divider>
                    </div>
                  </div>

                  <div v-else>
                    <div style="padding: 25px 15px 0 15px">
                      <div style="font-size: 20px; font-weight: bolder; padding-bottom: 20px">
                        '{{productBoard.member.name}}'<br/>
                        사용자를 신고하는 이유를 선택해주세요.
                      </div>
                      <v-divider></v-divider>
                      <div id="repo" v-for="(repo, index) in memberReport" :key="repo">
                        <div style="display: flex; align-items: center; height: 50px; padding-top: 30px; padding-bottom: 30px;" @click="clickReportDetail(index, repo)">
                          <div style="width: 100%">{{repo}}</div><div style="display: flex; justify-content: end"><v-icon style="color: black">mdi-chevron-right</v-icon></div>
                        </div>

                        <v-divider></v-divider>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div v-else>
              <!--        체크박스       -->
                <div v-if="checkBoxOrArea">
                  <div style="padding: 25px 15px 0 15px">
                    <div style="font-weight: bolder; font-size: 20px">{{reportTitle}}</div>

                    <div>
                      <v-checkbox v-model="banCheck" color="green" hide-details>
                        <template v-slot:label>
                          <div style="font-size: 18px; color: green">이 사용자의 글 보지 않기</div>
                        </template>
                      </v-checkbox>
                    </div>

                    <div style="opacity: 0.5">('나의 당근 > 설정 > 게시글 미노출 사용자 관리' 에서 취소할 수 있습니다.)</div>

                    <div style="padding-top: 20px; display: flex; justify-content: end">
                      <v-btn class="light-green lighten-3" style="font-weight: bolder" @click="sendReport">
                        오이마켓팀에게 보내기
                      </v-btn>
                    </div>
                  </div>
                </div>

              <!--       텍스트 박스     -->
                <div v-else>
                  <div v-if="!textImg">
                    <div style="padding: 25px 15px 0 15px">
                      <div style="font-weight: bolder; font-size: 20px">{{reportTitle}}</div>

                      <div style="margin-top: 25px">
                        <v-textarea v-model="content" solo placeholder="신고 내용을 작성해주세요"></v-textarea>
                      </div>

                      <div>
                        <v-checkbox v-model="banCheck" color="green" hide-details>
                          <template v-slot:label>
                            <div style="font-size: 18px; color: green">이 사용자의 글 보지 않기</div>
                          </template>
                        </v-checkbox>
                      </div>

                      <div style="opacity: 0.5">('나의 당근 > 설정 > 게시글 미노출 사용자 관리' 에서 취소할 수 있습니다.)</div>

                      <div style="padding-top: 20px; display: flex; justify-content: end">
                        <v-btn class="light-green lighten-3" style="font-weight: bolder" @click="sendReport">
                          오이마켓팀에게 보내기
                        </v-btn>
                      </div>
                    </div>
                  </div>

                  <div v-else>
                    <div style="padding: 25px 15px 0 15px;">
                      <div style="font-weight: bolder; font-size: 20px">{{reportTitle}}</div>

                      <div style="padding-top: 15px; opacity: 0.5" >
                        오이마켓 팀에게 신고해주시면, 서비스 내에서 할 수 있는 모든 조치를 취하고 최대한 도움을 드리겠습니다. <br/>
                        해당 거래 채팅방에서 사기 피해 관련 내용을 충분히 남겨주시면 증거로 활용될 수 있으니 외부 연락 수단보다는 오이마켓 채팅을 이용해주세요. <br/>
                        * 증거가 있을 경우 사진을 추가 부탁드립니다.
                      </div>

                      <div style="margin-top: 25px">
                        <v-textarea v-model="content" solo placeholder="신고 내용을 작성해주세요"></v-textarea>
                      </div>

                      <div class="image_upload_box" style="display: flex; align-items: center;">
                        <div @click="addImg" style="width: 150px; height: 120px; border-radius: 7px; display: flex; flex-direction: column; justify-content: center; align-items: center ">
                          <div><v-img width="40px" height="40px" src="@/assets/icon/camera.png"></v-img></div>
                          <div><span style="color: #06850e">{{files.length}}</span> / 10</div>
                        </div>
                        <input type="file" id="file" ref="files" @change="imageUpload" multiple style="display: none"/>

                        <div style="display: flex; overflow: scroll; align-items: center;">
                          <div v-for="(file, index) in files" :key="index" class="file-preview-wrapper">
                            <div class="file-close-button" @click="fileDeleteButton" :name="file.number">
                              x
                            </div>
                            <img :src="file.preview"  alt=""/>
                          </div>
                        </div>
                      </div>

                      <div>
                        <v-checkbox v-model="banCheck" color="green" hide-details>
                          <template v-slot:label>
                            <div style="font-size: 18px; color: green">이 사용자의 글 보지 않기</div>
                          </template>
                        </v-checkbox>
                      </div>

                      <div style="opacity: 0.5">('나의 당근 > 설정 > 게시글 미노출 사용자 관리' 에서 취소할 수 있습니다.)</div>

                      <div style="padding-top: 20px; display: flex; justify-content: end">
                        <v-btn class="light-green lighten-3" style="font-weight: bolder" @click="sendImgReport">
                          오이마켓팀에게 보내기
                        </v-btn>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </v-card>
        </v-dialog>
      </v-row>
    </v-app>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import cookies from "vue-cookies";

export default {
  name: "ReportDialogView",
  props: {
    dialogCheck: {
      type: Boolean,
      default: true
    },
    productNo: {
      type: Number
    }
  },
  data () {
    return {
      dialog: '',
      notifications: false,
      id: cookies.get('id'),
      sound: true,
      widgets: false,
      repoCheck: true,
      memRepoCheck: true,
      textImg: false,
      check: [],
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0,
      repoCheckList: [
          'banProduct',
          'categoryDiff',
          'professional',
          'fraud',
          'etc',
      ],
      repoList: [
          '판매 금지 물품이에요',
          '중고거래 게시글이 아니에요',
          '전문 판매업자 같아요',
          '사기 글이에요',
          '기타 사유 선택',
      ],
      banProduct: [
          '의약품, 의료기기, 건강기능식품',
          '상표권 침해: 가품, 이미테이션',
          '청소년 유해: 주류, 담배, 라이터',
          '개인정보: 신분증, 통장 계정',
          '무허가 식품: 수제 음식, 개봉 식춤',
          '불법 기기: 안전인증 누락, 불법 개조, 렌탈',
          '위험한 물품: 총포, 도검, 전자충격기',
      ],
      categoryDiff: [
        '지역업체 소개 / 광고',
        '부동산 거래',
        '농수산물 판매',
        '구인구직',
        '과외 /클래스 모집',
        '비방 / 저격',
        '일반 게시글 (잡담, 질문 등)',
      ],
      professional: [
          '여러 계정으로 같은 물건을 팔아요',
          '한 계정으로 같은 물건을 상업적으로 팔아요',
          '인터넷 쇼핑몰을 홍보하는 판매업자예요',
      ],
      fraud: [
          '카톡/문자 등 당근채팅 밖에서 대화를 유도해요',
          '가짜 안전결제/ 안전거래 사이트 링크를 보내요',
          '특수문자/ 사진등 계좌/ 전화번호를 이상하게 보내요',
          '입금했는데 판매자가 물건을 주지 않고 잠적했어요',
          '물건을 보냈는데 구매자가 입금하지 않고 잠적했어요',
          '대리 구매를 요구해요',
      ],
      etc: [
          '사용할 수 없는 상품',
          '중복 게시글',
          '상품 설명 부족',
          '나에게 구매 후 비싸게 재판매',
          '거래/ 환불 분쟁',
          '신고 항목 제안',
          '무료나눔/ 금전 요구 글',
      ],
      memberReport: [
          '전문 판매업자 같아요',
          '비매너 사용자예요',
          '욕설을 해요',
          '성희롱을 해요',
          '거래 / 환불 분쟁 신고',
          '사기당했어요',
          '연애 목적의 대화를 시도해요',
          '다른 문제가 있어요',
      ],
      reportTitle: '',
      checkBoxOrArea: true,
      sendForm: false,
      banCheck: false,
      category1: '',
      category1Num: '',
      category2: '',
      content: ''
    }
  },
  methods: {
    ...mapActions(["fetchProductBoard"]),
    closeDialog(){
      if(!this.sendForm){
        if(this.memRepoCheck){
          let dialog = false;
          this.$emit('closeDialog', {dialog});
          this.dialog = false;
        }else{
          this.memRepoCheck = true;
          alert(this.memRepoCheck)
        }
      }else {
        this.sendForm = false;
        this.memRepoCheck = false;
      }

    },
    clickReport(num){
      switch (num){
        case 0:
          this.check = this.banProduct;
          break;
        case 1:
          this.check = this.categoryDiff;
          break;
        case 2:
          this.check = this.professional;
          break;
        case 3:
          this.check = this.fraud;
          break;
        case 4:
          this.check = this.etc;
          break;
      }
      this.category1 = this.repoList[num];
      this.category1Num = num;
      this.memRepoCheck = false;
      this.repoCheck = false;
      this.memberReport = false
    },
    clickMemRepo(){
      this.memRepoCheck = false;
    },
    clickReportDetail(num, repo){
      this.reportTitle = repo;

      this.repoCheckCheckOrText(num);

      if(!this.repoCheck) {
        this.category2 = this.check[num];
      }else {
        this.category2 = this.memberReport[num];
      }

      this.sendForm = true;
    },
    repoCheckCheckOrText(num){
      //alert('게시글')
      if(this.category1Num < 3){
        this.checkBoxOrArea = true
      } else if(this.category1Num === 3){ // 사기 부분, 사진 추가하는 다이얼 로그 추가
        this.checkBoxOrArea = false;
        this.textImg = true;
      } else {
        this.checkBoxOrArea = num === 0 || num === 1;
      }
    },
    addImg(){
      this.$refs.files.click()
    },
    imageUpload() {
      console.log(this.$refs.files.files);

      if (this.files.length < 10) {
        let num = -1;

        if (this.files.length === 0) {
          for (let i = 0; i < this.$refs.files.files.length; i++) {
            this.files = [
              ...this.files,
              {
                file: this.$refs.files.files[i],
                preview: URL.createObjectURL(this.$refs.files.files[i]),
                number: i
              }
            ];
            num = i;
          }
          this.uploadImageIndex = num + 1;
        } else {
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
        }
      } else {
        alert('사진 삭제 후 사용해주세요 ')
      }
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute('name');
      this.files = this.files.filter(data => data.number !== Number(name));
    },
    sendReport(){
      const {category1, category2, content, banCheck, productNo} = this
      let toId = this.productBoard.member.id;
      let fromId = this.id;

      //alert(this.category2)
      this.$emit('sendReport', {category1, category2, content, banCheck, productNo, toId, fromId});
    },
    sendImgReport(){
      //alert(this.files.length)

      if(this.files.length !== 0) {

        let formData = new FormData;

        for (let i = 0; i < this.files.length; i++) {
          formData.append('fileList', this.files[i].file)
        }

        formData.append('category1', this.category1);
        formData.append('category2', this.category2);
        formData.append('content', this.content);
        formData.append('banCheck', this.banCheck);
        formData.append('productNo', this.productNo);
        formData.append('toId', this.productBoard.member.id);
        formData.append('fromId', this.id);

        this.$emit('sendImgReport', formData)
      }else {
        this.sendReport();
      }

    }
  },
  computed: {
    ...mapState(["productBoard"]),
  },
  created() {
    this.dialog = this.dialogCheck;
    this.fetchProductBoard(this.productNo)
  }
}
</script>

<style scoped>
#report_box{
  align-items: center;
  width: 100%;
  max-width: 1250px;
  margin: 0 auto;
  padding: 0 7px 0 7px; /*위, 오른쪽, 아래, 왼쪽 */
}
.file-preview-wrapper {
  padding: 10px;
  position: relative;
}

.file-preview-wrapper>img {
  position: relative;
  width: 180px;
  height: 120px;
}

.file-close-button {
  position: absolute;
  line-height: 18px;
  z-index: 99;
  font-size: 18px;
  right: 10px;
  top: 10px;
  color: white;
  background-color: #cae3a4;
  width: 20px;
  height: 20px;
  text-align: center;
  cursor: pointer;
}
</style>