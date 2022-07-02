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

                    <div v-if="repoCheck">
                      <div id="repo" v-for="(repo, index) in repoList" :key="repo">
                        <div style="display: flex; align-items: center; height: 50px; padding-top: 30px; padding-bottom: 30px;" @click="clickReport(index)">
                          <div style="width: 100%">{{repo}}</div><div style="display: flex; justify-content: end"><v-icon style="color: black">mdi-chevron-right</v-icon></div>
                        </div>
                        <v-divider></v-divider>
                      </div>
                    </div>

                    <div v-else>
                      <div id="repo" v-for="(repo, index) in check" :key="repo">
                        <div style="display: flex; align-items: center; height: 50px; padding-top: 30px; padding-bottom: 30px;" @click="clickReportDetail(index, repo)">
                          <div style="width: 100%">{{repo}}</div><div style="display: flex; justify-content: end"><v-icon style="color: black">mdi-chevron-right</v-icon></div>
                        </div>
                        <v-divider></v-divider>
                      </div>
                    </div>
                  </div>
                </div>

                <div v-if="memRepoCheck" style="padding: 50px 15px 0 15px">
                  <div style="font-size: 20px; font-weight: bolder; padding-bottom: 20px">
                    혹시 '{{productBoard.member.name}}'을 신고하고 싶으신가요?
                  </div>
                  <v-divider></v-divider>
                  <div style="padding-top: 30px; padding-bottom: 30px; display: flex; align-items: center; height: 50px" @click="clickMemRepo">
                    <div style="width: 100%">사용자 신고하러 가기</div><div style="display: flex; justify-content: end"><v-icon style="color: black">mdi-chevron-right</v-icon></div>
                  </div>
                  <v-divider></v-divider>
                </div>

                <div v-else>
                  <div style="padding: 25px 15px 0 15px">
                    <div style="font-size: 20px; font-weight: bolder; padding-bottom: 20px">
                      '{{productBoard.member.name}}'<br/>
                      사용자를 신고하는 이유를 선택해주세요.
                    </div>
                    <v-divider></v-divider>
                    <div id="repo" v-for="repo in memberReport" :key="repo">
                      <div style="display: flex; align-items: center; height: 50px; padding-top: 30px; padding-bottom: 30px;">
                        <div style="width: 100%">{{repo}}</div><div style="display: flex; justify-content: end"><v-icon style="color: black">mdi-chevron-right</v-icon></div>
                      </div>

                      <v-divider></v-divider>
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
                      <v-checkbox v-model="reportCheck" color="green" hide-details>
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
                  <div style="padding: 25px 15px 0 15px">
                    <div style="font-weight: bolder; font-size: 20px">{{reportTitle}}</div>

                  <div style="margin-top: 25px">
                    <v-textarea solo value="신고 내용을 작성해주세요"></v-textarea>
                  </div>

                  <div>
                    <v-checkbox v-model="reportCheck" color="green" hide-details>
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
      sound: true,
      widgets: false,
      repoCheck: true,
      memRepoCheck: true,
      check: [],
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
      reportCheck: false
    }
  },
  methods: {
    ...mapActions(["fetchProductBoard"]),
    closeDialog(){

      if(this.repoCheck) {
        if(this.memRepoCheck) {
          let dialog = false;
          this.$emit('closeDialog', {dialog});
          this.dialog = false;
        }else{
          this.memRepoCheck = true;
          this.repoCheck = true;
        }
      }else {
        if(this.sendForm) {
          //alert('111')
          this.sendForm = false;
          this.memRepoCheck = true;
          this.repoCheck = true;
        }else {
          this.repoCheck = true;
        }
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

      this.repoCheck = false;
    },
    clickMemRepo(){
      this.memRepoCheck = false;
    },
    clickReportDetail(num, repo){
      this.reportTitle = repo;

      this.checkBoxOrArea = num === 1;

      this.sendForm = true;
    },
    sendReport(){
      alert(this.reportCheck)
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
</style>