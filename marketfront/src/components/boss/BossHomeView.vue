<template>
  <div>
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
      <v-btn block depressed color="success" height="50" type="submit" style="width: 100%; height: 50px; font-size: 25px; font-weight: bold" @click="infoDialog"><h3><b>정보 관리하기</b></h3></v-btn>
    </div>

    <!--    정보가 있을 경우    -->
  </div>

    <div style="padding-bottom: 20px" class="home_style" v-else>
      <div style="display: flex">
        <span style="font-size: 30px; font-weight: bold; width: 30%"> 정보 </span> <span style="width: 70%; display: flex; justify-content: end;"><v-btn depressed color="success" height="40" type="submit" @click="modifyMarketInfo"><b>수정</b></v-btn></span>
      </div>
      <!--   정보가 없을 경우에     -->
      <div style="margin-top: 20px">
        <div style="height: auto; font-size: 23px;" class="pa-2">
          {{boss.marketInfo}}
        </div>

        <div class="kmap" ref="map" style="position: relative; overflow: hidden"></div>

        <div style="padding-top: 30px">
          <span style="font-size: 25px;"><v-icon x-large>mdi-map-marker-outline</v-icon> {{ boss.address }} {{boss.detailAddress}}</span>
        </div>

        <div style="padding-top: 15px">
          <span style="font-size: 25px;"><v-icon x-large>mdi-clock-time-five-outline</v-icon> {{boss.startTime}} ~ {{boss.endTime}}</span>
        </div>

        <div style="padding-top: 15px">
          <span style="font-size: 25px;"><v-icon x-large>mdi-earth</v-icon> {{boss.marketHomePage}}</span>
        </div>
      </div>

    </div> <!-- 정보 부분 -->


    <hr style="height: 15px; background-color: rgba(94,94,94,0.16); border: none"/>

    <div style="padding-bottom: 20px" class="home_style">
      <span style="font-size: 30px; font-weight: bold;"> 쿠폰 </span>
      <!--   정보가 없을 경우에     -->
      <div style="margin-top: 20px">
        <div v-if="!coupon || (Array.isArray(coupon) && coupon.length === 0)">
          <span style="font-size: 25px; opacity: 0.5;">아직 쿠폰이 없어요.</span>
        </div>

        <div v-else style="padding-top: 30px">
          <div v-for="(coupon, index) in coupon" :key="index" style="width: 100%; padding: 10px 10px 10px 10px">
            <div v-if="state[index]">
              <div style="width: 100%; border-radius: 8px; border: 1px solid green; min-height: 150px; display: flex; align-items: center">
                <div style="width: 80%; padding: 5px 10px 5px 15px;">
                  <div style="display: flex; align-items: center">
                    <v-chip color="green" style="margin-right: 20px">발급중</v-chip>
                    <div style="color: green" v-if="coupon.couponMax">남은 쿠폰: {{coupon.couponMax - coupon.giveCoupon}}</div>
                  </div>

                  <div style="font-weight: bolder; font-size: 30px">
                    {{coupon.couponName}}
                  </div>

                  <div style="font-weight: lighter">
                    {{coupon.couponDate}}까지
                  </div>
                </div>

                <div style="background-color: #96cb96; width: 20%; min-height: 150px; border-top-right-radius: 8px; border-bottom-right-radius: 8px">
                  <div style="display: flex; flex-direction: column; justify-content: center; align-items: center; min-height: 150px" @click="modifyCouponData(coupon)">
                    <v-icon large>mdi-pencil-outline</v-icon>
                    <div style="font-size: 18px">쿠폰 수정</div>
                  </div>
                </div>
              </div>
            </div>

            <div v-else style="width: 100%; background-color: rgba(145,145,145,0.37); border-radius: 8px; border: 1px solid black; min-height: 150px; display: flex; align-items: center">
              <div style="width: 80%; padding: 5px 10px 5px 15px;">
                <div style="display: flex; align-items: center">
                  <v-chip style="margin-right: 20px">발급완료</v-chip>
                  <div style="color: black" v-if="coupon.couponMax">남은 쿠폰: {{coupon.couponMax - coupon.giveCoupon}}</div>
                </div>

                <div style="font-weight: bolder; font-size: 30px">
                  {{coupon.couponName}}
                </div>

                <div style="font-weight: lighter">
                  {{coupon.couponDate}}까지
                </div>
              </div>

              <div style="background-color: #b9b9b9; width: 20%; min-height: 150px; border-top-right-radius: 8px; border-bottom-right-radius: 8px">
                <div style="display: flex; flex-direction: column; justify-content: center; align-items: center; min-height: 150px">
                  <v-icon large>mdi-download-outline</v-icon>
                  <div style="font-size: 18px">쿠폰 발급 완료</div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div style="padding-top: 30px">
          <v-btn block depressed color="success" height="50" type="submit" style="width: 100%; height: 50px; font-size: 25px; font-weight: bold" @click="couponDialog=true"><h3><b>쿠폰 관리하기</b></h3></v-btn>
        </div>
      </div>

    </div> <!-- 쿠폰 부분 -->

    <hr style="height: 15px; background-color: rgba(94,94,94,0.16); border: none"/>

    <div style="padding-bottom: 20px" class="home_style">
      <span style="font-size: 30px; font-weight: bold;"> 가격 </span>
      <!--   정보가 없을 경우에     -->
      <div v-if="!bossMenu || (Array.isArray(bossMenu) && bossMenu.length === 0)">
        <div style="padding-top: 30px">
          <span style="font-size: 25px; opacity: 0.5;">상품이나 서비의 가격을 추가하고, 이웃의 눈길을 끌어보세요.</span>
        </div>
      </div>

      <div v-else>
        <div v-for="(menu, index) in bossMenu" :key="index" style="padding-top: 20px">
          <div style="display: flex">
            <div style="font-size: 25px; width: 20%">{{menu.menuName}}</div>
            <div style="width: 65%; display: flex; align-items: center"><hr style="border: 1px dotted rgba(126,126,126,0.11); width: 100%"/></div>
            <div style="font-size: 25px; font-weight: bold; width: 15%; display: flex; justify-content: end">{{menu.menuPrice}}원</div>
          </div>

          <div style="background-color: rgba(197,192,192,0.22); border-radius: 5px; height: 40px; font-size: 20px" class="pa-2">
            {{menu.menuInfo}}
          </div>
        </div>
      </div>

      <div style="padding-top: 30px">
        <v-btn block depressed color="success" height="50" type="submit" style="width: 100%; height: 50px; font-size: 25px; font-weight: bold" @click="priceDialog = true"><h3><b>가격 설정</b></h3></v-btn>
      </div>


    </div> <!-- 가격 부분 -->

    <v-dialog v-model="priceDialog" width="800px">
      <v-card width="800px" min-height="550px" height=auto class="pa-4">
        <v-row style="padding-bottom: 30px">
          <v-col align-self="center" style="height: 40px;"> <v-btn @click="priceDialog = false" text><v-icon>mdi-chevron-left</v-icon></v-btn></v-col>
          <v-col><v-card-title style="height: 40px; font-size: 22px; display: flex; justify-content: center">가격 목록</v-card-title></v-col>
          <v-col align-self="center" style="height: 40px; display: flex; justify-content: end">
            <v-btn depressed color="success" height="40" type="submit" @click="modifyPriceDialog = true"><b>수정</b></v-btn>
          </v-col>
        </v-row>

        <div style="min-height: 400px">
          <!--    리스트가 보이는 곳    -->
          <div v-for="(menu, index) in bossMenu" :key="index" style="border-bottom: solid 1px rgba(0,0,0,0.24); height: 100px; padding: 10px 10px 10px 10px" >
            <div style="font-size: 20px; font-weight: revert">{{menu.menuName}}</div>
            <div style="font-size: 17px">{{menu.menuInfo}}</div>
            <div style="font-size: 17px; font-weight: bolder">{{menu.menuPrice}}원</div>
          </div>
        </div>
        <div style="padding-top: 20px; padding-bottom: 10px">
          <v-btn block depressed color="success" height="50" type="submit" @click="addPriceDialog = true"><h3><b>가격 추가</b></h3></v-btn>
        </div>
      </v-card>
    </v-dialog> <!-- 가격 추가 1 -->

    <v-dialog v-model="addPriceDialog" width="800px">
      <v-card width="800px" class="pa-4" min-height="550px">
        <div style="padding: 10px 10px 10px 10px">
          <div style="justify-content: center; display: flex">
            <v-card-title style="font-weight: bold; font-size: 25px">가격 추가</v-card-title>
          </div>

          <div class="priceTitle"> 항목 </div>

          <div>
            <v-text-field v-model="menuName" solo label="항목명을 입력하세요. (예: 아메리카노)"></v-text-field>
          </div>

          <div class="priceTitle">
            가격
          </div>

          <div>
            <v-text-field v-model="menuPrice" solo label="가격을 입력하세요"></v-text-field>
          </div>

          <div class="priceTitle">
            추가 설명 (선택)
          </div>

          <div>
            <v-textarea v-model="menuInfo" label="가격에 대한 설명이 필요하다면 적어주세요." solo></v-textarea>
          </div>

          <div style="display: flex; justify-content: end; align-items: end; height: 80px">
            <v-btn depressed height="40" type="submit" style="margin-right: 8px" @click="addPriceDialog = false"><b>close</b></v-btn>
            <v-btn depressed color="success" height="40" type="submit" @click="savePrice"><b>save</b></v-btn>
          </div>
        </div>
      </v-card>
    </v-dialog>  <!-- 가격 추가 2 -->

    <v-dialog v-model="modifyPriceDialog" width="800px">
      <v-card width="800px" min-height="550px" height=auto class="pa-4">
        <v-row style="padding-bottom: 30px" v-if="modifyCheck">
          <v-col align-self="center" style="height: 40px;"> <v-btn @click="modifyPriceDialog = false" text><v-icon>mdi-chevron-left</v-icon></v-btn></v-col>
          <v-col><v-card-title style="height: 40px; font-size: 22px; display: flex; justify-content: center">가격 목록</v-card-title></v-col>
          <v-col align-self="center" style="height: 40px; display: flex; justify-content: end">
            <v-btn depressed color="success" height="40" type="submit" @click="modifyPriceDialog = false"><b>완료</b></v-btn>
          </v-col>
        </v-row>

        <div v-if="modifyCheck">
          <!--    리스트가 보이는 곳    -->
          <div style="min-height: 400px;">
            <div v-for="(menu, index) in bossMenu" :key="index" style="border-bottom: solid 1px rgba(0,0,0,0.24); height: 100px; padding: 10px 10px 10px 10px" >
              <div style="font-size: 20px; font-weight: revert; display: flex">
                <div style="width: 95%" @click="modifyPrice(menu)">{{menu.menuName}}</div>
                <div style="width: 5%; justify-content: end"><v-btn icon @click="deletePrice(menu.menuNo)"><v-icon>mdi-close</v-icon></v-btn></div>
              </div>
              <div style="font-size: 17px" @click="modifyPrice(menu)">{{menu.menuInfo}}</div>
              <div style="font-size: 17px; font-weight: bolder" @click="modifyPrice(menu)">{{menu.menuPrice}}원</div>
            </div>
          </div>
          <div style="padding-top: 20px; padding-bottom: 10px">
            <v-btn block depressed color="success" height="50" type="submit" @click="addPriceDialog = true"><h3><b>가격 추가</b></h3></v-btn>
          </div>
        </div>

        <div v-else>
          <div style="padding: 10px 10px 10px 10px">
            <div class="priceTitle"> 항목 </div>

            <div>
              <v-text-field v-model="modifyMenuName" solo label="항목명을 입력하세요. (예: 아메리카노)"></v-text-field>
            </div>

            <div class="priceTitle">
              가격
            </div>

            <div>
              <v-text-field v-model="modifyMenuPrice" solo label="가격을 입력하세요"></v-text-field>
            </div>

            <div class="priceTitle">
              추가 설명 (선택)
            </div>

            <div>
              <v-textarea v-model="modifyMenuInfo" label="가격에 대한 설명이 필요하다면 적어주세요." solo></v-textarea>
            </div>

            <div style="display: flex; justify-content: end">
              <v-btn depressed color="success" height="40" type="submit" style="margin-right: 8px" @click="modifyCheck = true"><b>close</b></v-btn>
              <v-btn depressed color="success" height="40" type="submit" @click="modifySave"><b>save</b></v-btn>
            </div>
          </div>
        </div>
      </v-card>
    </v-dialog> <!-- 가격 수정 -->

    <v-dialog v-model="couponDialog" width="800px"> <!-- 쿠폰 추가 부분 -->
      <v-card width="800px" min-height="550px" height=auto class="pa-4">
        <v-row style="padding-bottom: 40px">
          <v-col align-self="center" style="height: 40px;"> <v-btn @click="couponDialog = false" text><v-icon>mdi-chevron-left</v-icon></v-btn></v-col>
          <v-col><v-card-title style="height: 40px; font-size: 22px; display: flex; justify-content: center">쿠폰 관리</v-card-title></v-col>
          <v-col align-self="center" style="height: 40px; display: flex; justify-content: end"></v-col>
        </v-row>

        <div style="min-height: 400px">
        <div v-for="(coupon, index) in coupon" :key="index" style="width: 100%; padding: 0 10px 10px 10px">
          <div style="width: 100%; border-radius: 8px; border: 1px solid green; min-height: 150px; display: flex; align-items: center">
            <div style="width: 80%; padding: 5px 10px 5px 15px;">
              <div style="display: flex; align-items: center">
                <v-chip color="green" style="margin-right: 20px">발급중</v-chip>
                <div style="color: green" v-if="coupon.couponMax">남은 쿠폰: {{coupon.couponMax}}</div>
              </div>

              <div style="font-weight: bolder; font-size: 30px">
                {{coupon.couponName}}
              </div>

              <div style="font-weight: lighter">
                {{coupon.couponDate}}까지
              </div>
            </div>

            <div style="background-color: #96cb96; width: 20%; min-height: 150px; border-top-right-radius: 8px; border-bottom-right-radius: 8px">
              <div style="display: flex; flex-direction: column; justify-content: center; align-items: center; min-height: 150px" @click="modifyCouponData(coupon)">
                <v-icon large>mdi-pencil-outline</v-icon>
                <div style="font-size: 18px">쿠폰 수정</div>
              </div>
            </div>
          </div>

          <v-divider style="height: 5px; margin-top: 20px"></v-divider>

          <div style="display: flex; flex-direction: row-reverse; padding: 10px 10px 10px 10px;">
            <div style="padding-right: 40px; font-weight: bolder;" @click="deleteCoupon(coupon)">삭제</div>
            <div style="padding-right: 40px; font-weight: bolder;" @click="showDetail(coupon)">상세보기</div>
          </div>
        </div>
        </div>

        <div style="padding-top: 20px; padding-bottom: 10px">
          <v-btn block depressed color="success" height="50" type="submit" @click="addCouponDialog = true"><h3><b>쿠폰 추가</b></h3></v-btn>
        </div>
      </v-card>
    </v-dialog> <!-- 쿠폰 리스트 -->

    <v-dialog v-model="addCouponDialog"  width="800px">
      <v-card width="800px" min-height="550px" class="pa-4">

        <div style="padding: 10px 10px 10px 10px">
          <div style="justify-content: center; display: flex">
            <v-card-title style="font-weight: bold; font-size: 25px">쿠폰 만들기</v-card-title>
          </div>

          <div class="priceTitle"> 혜택 </div>

          <div>
            <v-text-field v-model="couponName" solo label="예) 모든 메뉴 10% 할인"></v-text-field>
          </div>

          <div class="priceTitle">
            사용기한
          </div>

          <div>
            <v-menu ref="menu1" v-model="menu" :close-on-content-click="false" transition="scale-transition" offset-y max-width="290px" min-width="auto">
              <template v-slot:activator="{ on, attrs }">
                <v-text-field v-model="dateFormatted" label="사용 기한을 선택해 주세요." prepend-icon="mdi-calendar" v-bind="attrs" v-on="on" solo></v-text-field>
              </template>
              <v-date-picker v-model="date" no-title @input="menu = false"></v-date-picker>
            </v-menu>
          </div>

          <div>
            발급 건수 제한 <span style="opacity: 0.5; padding-left: 15px"> * 지정한 개수를 초과하면 쿠폰을 받을 수 없어요.</span>
          </div>

          <div>
            <v-radio-group v-model="radioGroup" row>
              <v-radio :value="0" label="제한없음" @click="radioCheckNoLimit"></v-radio>
              <v-radio :value="1" label="제한있음" @click="radioCheck">
              </v-radio>
            </v-radio-group>

            <v-text-field v-if="radioField" v-model="couponMax" solo label="발급 건수를 입력해 주세요"></v-text-field>

          </div>

          <div class="priceTitle">
            쿠폰 이용 안내 (선택)
          </div>

          <div>
            <v-textarea v-model="couponInfo" label="쿠폰 사용시 유의사항이나 이용조건이 따로 있다면 미리 알려주세요" solo></v-textarea>
          </div>

          <div style="display: flex; justify-content: end">
            <v-btn depressed height="40" type="submit" style="margin-right: 8px" @click="addCouponDialog = false"><b>close</b></v-btn>
            <v-btn depressed color="success" height="40" type="submit" @click="saveCoupon"><b>save</b></v-btn>
          </div>
        </div>
      </v-card>
    </v-dialog>  <!-- 쿠폰 추가 부분 -->

    <v-dialog v-model="modifyCouponDialog" width="800px"> <!-- 쿠폰 변경 부분 -->
      <v-card width="800px" height=auto class="pa-4">
        <div class="priceTitle"> 혜택 </div>

        <div>
          <v-text-field v-model="couponName" solo label="예) 모든 메뉴 10% 할인"></v-text-field>
        </div>

        <div class="priceTitle">
          사용기한
        </div>

        <div>
          <v-menu ref="menu1" v-model="menu" :close-on-content-click="false" transition="scale-transition" offset-y max-width="290px" min-width="auto">
            <template v-slot:activator="{ on, attrs }">
              <v-text-field v-model="dateFormatted" label="사용 기한을 선택해 주세요." prepend-icon="mdi-calendar" v-bind="attrs" v-on="on" solo></v-text-field>
            </template>
            <v-date-picker v-model="date" no-title @input="menu = false"></v-date-picker>
          </v-menu>
        </div>

        <div>
          발급 건수 제한
        </div>

        <div>
          <v-radio-group v-model="radioModifyGroup" row>
            <v-radio :value="0" label="제한없음" @click="radioCheckNoLimit"></v-radio>
            <v-radio :value="1" label="제한있음" @click="radioCheck">
            </v-radio>
          </v-radio-group>

          <v-text-field v-if="radioField" v-model="couponMax" solo label="발급 건수를 입력해 주세요"></v-text-field>
          <div>지정한 개수를 초과하면 쿠폰을 받을 수 없어요.</div>
        </div>

        <div class="priceTitle">
          쿠폰 이용 안내 (선택)
        </div>

        <div>
          <v-textarea v-model="couponInfo" label="쿠폰 사용시 유의사항이나 이용조건이 따로 있다면 미리 알려주세요" solo></v-textarea>
        </div>

        <div style="display: flex; justify-content: end">
          <v-btn depressed height="40" type="submit" style="margin-right: 8px" @click="modifyCouponDialog = false"><b>close</b></v-btn>
          <v-btn depressed color="success" height="40" type="submit" @click="modifyCoupon"><b>변경</b></v-btn>
        </div>

      </v-card>
    </v-dialog> <!-- 변경 리스트 -->

    <v-dialog v-model="couponDetailDialog" width="800px"> <!-- 쿠폰 상세보기 부분 -->
      <v-card width="800px" height=auto class="pa-4">
        <v-row style="padding-bottom: 40px">
          <v-col align-self="center" style="height: 40px;"> <v-btn @click="couponDetailDialog = false" text><v-icon>mdi-chevron-left</v-icon></v-btn></v-col>
          <v-col><v-card-title style="height: 40px; font-size: 22px; display: flex; justify-content: center">쿠폰 상세</v-card-title></v-col>
          <v-col align-self="center" style="height: 40px; display: flex; justify-content: end"></v-col>
        </v-row>

        <div style="width: 100%; padding: 0 10px 10px 10px">
          <div style="width: 100%; border-radius: 8px; border: 1px solid green; min-height: 80px; display: flex; align-items: center">
            <div style="width: 80%; padding: 5px 10px 5px 15px;">
              <div style="display: flex; align-items: center">
                <v-chip color="green" style="margin-right: 20px">발급중</v-chip>
                <div style="color: green" v-if="couponDetail.couponMax">남은 쿠폰: {{couponDetail.couponMax - couponDetail.giveCoupon}}</div>
              </div>

              <div style="font-weight: bolder; font-size: 30px">
                {{couponDetail.couponName}}
              </div>

              <div style="font-weight: lighter">
                {{couponDetail.couponDate}}까지
              </div>
            </div>

            <div style="background-color: #96cb96; width: 20%; min-height: 150px; border-top-right-radius: 8px; border-bottom-right-radius: 8px">
              <div style="display: flex; flex-direction: column; justify-content: center; align-items: center; min-height: 150px" @click="modifyCouponData(coupon)">
                <v-icon large>mdi-pencil-outline</v-icon>
                <div style="font-size: 18px">쿠폰 수정</div>
              </div>
            </div>
          </div>

          <div style="padding-top: 30px">
            <div style="font-weight: bolder; font-size: 18px">쿠폰 이용안내</div>
            <div>{{couponDetail.couponName}}</div>
            <div style="font-weight: lighter; padding-top: 20px">2022.07.07 생성</div>
          </div>

          <v-divider style="margin-top: 20px"></v-divider>

          <div style="display: flex; padding-top: 20px;">
            <div style="width: 50%; display: flex; align-items: start; font-size: 20px">총 발급 수</div>
            <div v-if="couponDetail.couponMax" style="width: 50%; display: flex; flex-direction: row-reverse; align-items: end; font-size: 20px; font-weight: bolder">{{couponDetail.couponMax}}개</div>
            <div v-else style="width: 50%; display: flex; flex-direction: row-reverse; align-items: end; font-size: 20px; font-weight: bolder">제한없음</div>
          </div>

          <div style="display: flex; padding-top: 15px; padding-bottom: 10px">
            <div style="width: 50%; display: flex; align-items: start; font-size: 20px">받은 쿠폰</div>
            <div style="width: 50%; display: flex; flex-direction: row-reverse; align-items: end; font-size: 20px; font-weight: bolder;">{{couponDetail.giveCoupon}}개</div>
          </div>
        </div>

        <hr style="height: 15px; background-color: rgba(222,222,222,0.4); border: none"/>

        <div style="width: 100%; padding: 15px 0 10px 0;">
          <div style="font-size: 20px; padding: 10px 8px 10px 8px; font-weight: bolder; display: flex; justify-content: start; align-items: center">받은 고객</div>

          <v-divider style="height: 3px"></v-divider>
          <div style="min-height: 120px">
            <div v-if="!memberCoupon || (Array.isArray(memberCoupon) && memberCoupon.length === 0)"
                 style="display: flex; justify-content: center; align-items: center; min-height: 120px">
              쿠폰을 받은 고객이 없어요
            </div>

            <div v-else>
            <div id="wrapper">
              <div v-for="(member, index) in memberCoupon" :key="index">
                <div style="display: flex">
                  <div style="display: flex; padding: 10px 10px 10px 10px; align-items: center; width: 50%">
                    <v-img max-width="50" max-height="50" style="border-radius: 70%" :src="require(`@/assets/profile/${member.profileImg}`)"></v-img>
                    <div style="padding-left: 15px; font-size: 22px">{{member.name}}</div>
                  </div>
                </div>
              </div>
            </div>

            <div id="pageNum" style="display: flex; align-items: center; justify-content: center">
              <v-btn text @click="pre"><v-icon>mdi-arrow-left</v-icon></v-btn>
              <div>{{pageNum}} / {{totalPage}}</div>
              <v-btn text @click="next"><v-icon>mdi-arrow-right</v-icon></v-btn>
            </div>
          </div>
          </div>
        </div>

      </v-card>
    </v-dialog> <!-- 쿠폰 상세보기 -->
  </div>
</template>

<script>

import cookies from "vue-cookies";
import {mapActions, mapState} from "vuex";
import axios from "axios";

let kakao = window.kakao;

export default {
  name: "BossHomeView",
  props: {
    boss: {
      type: Object
    }
  },
  data(){
    return {
      mapInstance: null,
      markInstance: null,
      id: cookies.get("id"),
      name: cookies.get("name"),
      reviewDialog: false,
      addPriceDialog: false,
      modifyPriceDialog: false,
      priceDialog: false,
      couponDialog: false,
      addCouponDialog: false,
      modifyCouponDialog: false,
      selection: [],
      reviewTag: [
        '친절해요',
        '가격이 저렴해요',
        '만족스러워요',
        '아쉬워요'
      ],
      menuName: '',
      menuPrice: '',
      menuInfo: '',
      modifyCheck: true,
      modifyMenuNo: '',
      modifyMenuName: '',
      modifyMenuPrice: '',
      modifyMenuInfo: '',
      date: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      menu: false,
      dateFormatted: '',
      couponName: '',
      couponInfo: '',
      couponDate: '',
      couponMax: '',
      radioGroup: 0,
      radioField: false,
      radioModifyGroup: '',
      couponNo: '',
      couponDetailDialog: false,
      couponDetail: [],
      totalPage: '',
      pageNum: 1,
      nowDay: '',
      state: []
    }
  },
  methods: {
    ...mapActions(['fetchBossMenuList']),
    ...mapActions(['fetchShowCoupon']),
    addImg(){
      this.$refs.files.click()
    },
    imageUpload() {
      console.log(this.$refs.files.files);

      if(this.files.length < 10) {
        let num = -1;

        if(this.files.length === 0 ) {
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
    initMap(){
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
    infoDialog(){
      this.$router.push({name: 'BossMarketInfo'})
    },
    modifyMarketInfo(){
      this.$router.push({name: 'BossMarketInfoModify', params: {bossNo: this.boss.bossAuthNo.toString()}})
    },
    savePrice(){
      const {menuName, menuPrice, menuInfo} = this
      let bossNo = this.boss.bossAuthNo;
      //alert('savePrice')
      this.$emit('savePrice', {menuName, menuPrice, menuInfo, bossNo})
      this.addPriceDialog = false;
      this.priceDialog = false;
    },
    modifyPrice(menu){
      this.modifyCheck = false;
      this.modifyMenuNo = menu.menuNo;
      this.modifyMenuPrice = menu.menuPrice;
      this.modifyMenuName = menu.menuName;
      this.modifyMenuInfo = menu.menuInfo;
    },
    modifySave(){
      let menuName = this.modifyMenuName;
      let menuPrice = this.modifyMenuPrice;
      let menuInfo = this.modifyMenuInfo;
      let bossPriceNo = this.modifyMenuNo;

      this.$emit('modifySave', {menuName, menuPrice, menuInfo, bossPriceNo})
    },
    deletePrice(menuNo){
      this.$emit('deletePrice', {menuNo})
      this.modifyCheck = true
    },
    radioCheckNoLimit(){
      this.radioField = false;
    },
    radioCheck(){
      this.radioField = true;
    },
    saveCoupon(){
      const {couponName, couponInfo, couponMax} = this;
      let couponDate = this.dateFormatted;
      let bossNo = this.boss.bossAuthNo;

      alert(couponMax)
      alert(this.couponMax)
      this.$emit('saveCoupon', {bossNo, couponName, couponInfo, couponMax, couponDate})
    },
    formatDate (date) {
      if (!date) return null

      const [year, month, day] = date.split('-')
      return `${year}/ ${month}/ ${day}`
    },
    modifyCouponData(coupon){
      this.couponName = coupon.couponName;
      this.couponInfo = coupon.couponInfo;
      this.dateFormatted = coupon.couponDate;
      this.radioModifyGroup = coupon.remain_coupon === null ? 0 : 1
      this.radioField = coupon.remain_coupon !== null
      alert(this.radioModifyGroup)
      this.couponMax = coupon.couponMax;
      this.couponNo = coupon.couponNo;
      this.modifyCouponDialog = true;
    },
    modifyCoupon(){
      const {couponName, couponInfo, couponMax, couponNo} = this;
      let couponDate = this.dateFormatted;

      this.$emit('modifyCoupon', {couponName, couponInfo, couponMax, couponDate, couponNo})
    },
    async showDetail(coupon){
      this.couponDetail = coupon;
      await this.fetchMemberCouponList(this.boss.bossAuthNo, 0);
      await this.fetchTotalPage(this.boss.bossAuthNo);

    },
    fetchTotalPage(bossNo){
      axios.post(`http://localhost:7777/boss/memberCoupon/totalPage/${bossNo}`)
          .then((res) => {
            this.totalPage = res.data;
            this.couponDetailDialog = true;
          })
          .catch(() => {
            alert('에러')
          })
    },
    next(){
      let bossNo = this.boss.bossAuthNo;
      if(this.pageNum < this.totalPage) {
        this.pageNum += 1
        this.fetchMemberCouponList(bossNo, this.pageNum - 1)
      }
    },
    pre(){
      let bossNo = this.boss.bossAuthNo;
      if(this.pageNum > 1) {
        this.pageNum -= 1
        this.fetchMemberCouponList(bossNo,this.pageNum-1)
      }
    },
    fetchMemberCouponList(bossNo, num){
      axios.get(`http://localhost:7777/boss/couponMember/${bossNo}/${num}`, {
        headers: {
          'Authorization': 'Bearer '+ cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
          .then((res) => {
            this.$store.state.memberCoupon = res.data;
          })
    },
    deleteCoupon(coupon){
      let couponNo = coupon.couponNo;
      axios.delete(`http://localhost:7777/boss/deleteCoupon/${couponNo}`, {
        headers: {
          'Authorization': 'Bearer '+ cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
          .then(() => {
            console.log('성공')
            this.$router.go()
          })
          .catch(() => {
            alert('에러')
          })
    },
    day(){
      let today = new Date();

      let year = today.getFullYear();
      let month = ('0' + (today.getMonth() + 1)).slice(-2);
      let day = ('0' + today.getDate()).slice(-2);

      console.log(this.coupon)

      for(let i = 0;  i < this.coupon.length; i++){
        let date = this.coupon[i].couponDate.split('/');
        console.log(date[0])
        console.log(date[1])
        console.log(date[2])

        if(date[0] - year > 0){
          this.state.push(true)
        }else if(date[1] - month > 0){
          this.state.push(true)
        }else if(date[2] - day > 0){
          this.state.push(true)
        }else{
          this.state.push(false)
        }

        console.log(this.state[i])
      }
      let dateString = year + '-' + month  + '-' + day;
      console.log(dateString)
    }
  },
  mounted() {
    this.initMap();
  },
  computed: {
    ...mapState(['bossMenu']),
    ...mapState(['coupon']),
    ...mapState(['memberCoupon']),
  },
  watch: {
    date () {
      this.dateFormatted = this.formatDate(this.date)
    },
  },
  async created() {
    await this.fetchBossMenuList(this.boss.bossAuthNo)
    await this.fetchShowCoupon(this.boss.bossAuthNo)
    await this.day()
  }
}
</script>

<style scoped>
.kmap {
  width: 100%;
  height: 300px;
}

.file-preview-wrapper>img {
  position: relative;
  width: 170px;
  height: 120px;
}

#wrapper{
  height: 200px;
  min-height: 100%;
}

#pageNum{
  height: 30px;
  position : relative;
  transform : translateY(-100%);
}
</style>