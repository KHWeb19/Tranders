<template>
  <v-container>
    <v-card width="100%">
      <div style="padding-left: 50px; padding-right: 30px">

      <v-row @click="myProfile" style="padding-top: 20px" >
        <v-col cols="4">
          <div>
            <v-img v-if="profileImg === null" contain max-height="58" src="@/assets/profile/Tranders_base_profile_img.png"></v-img>
            <v-img v-else :src="require(`@/assets/profile/${profileImg}`)" id="img" max-width="80" min-height="80" style="height: 80px; width: 80px; border-radius: 70%"></v-img>
          </div>
        </v-col>

        <v-col cols="6" style="padding-top: 35px">
          <span style="font-size: 30px;">
            {{name}}
          </span>
        </v-col>

        <v-col cols="2" style="padding-top: 35px">
          <v-btn style="margin-top: 5px" icon>
            <v-icon>mdi-chevron-right</v-icon>
          </v-btn>
        </v-col>
      </v-row>

        <v-row style="margin-top: 20px">
          <div
            style="
              border: 1px solid green;
              width: 100%;
              height: 50px;
              margin-right: 20px;
              border-radius: 8px;
              right: 0;
            "
            class="pa-3"
          >
            <div style="color: green">오이페이</div>
          </div>
        </v-row>

        <v-row style="margin-top: 50px; margin-left: 2px">
          <v-col>
            <v-row>
              <v-btn
                fab
                style="
                  height: 70px;
                  width: 70px;
                  background-color: rgb(202, 227, 164);
                "
              >
                <v-icon style="color: #008807">mdi-shopping</v-icon>
              </v-btn>
            </v-row>

            <v-row class="statementBtn"> 구매 내역 </v-row>
          </v-col>

          <v-col>
            <v-row>
              <v-btn
                fab
                style="
                  height: 70px;
                  width: 70px;
                  background-color: rgb(202, 227, 164);
                "
                :to="{ name: 'MyPurchaseHistory' }"
              >
                <v-icon style="color: #008807">mdi-receipt</v-icon>
              </v-btn>
            </v-row>

            <v-row class="statementBtn"> 판매 내역 </v-row>
          </v-col>

          <v-col>
            <v-row>
              <v-btn
                fab
                style="
                  height: 70px;
                  width: 70px;
                  background-color: rgb(202, 227, 164);
                "
              >
                <v-icon style="color: #008807">mdi-heart</v-icon>
              </v-btn>
            </v-row>

            <v-row class="statementBtn"> 관심 내역 </v-row>
          </v-col>
        </v-row>
      </div>

      <hr
        style="
          margin-top: 30px;
          height: 10px;
          background-color: rgba(94, 94, 94, 0.16);
          border: none;
        "
      />

      <div style="margin-left: 10px; margin-right: 10px; margin-top: 10px">
        <v-row style="margin-top: 10px">
          <v-card-text
            style="font-size: 20px; font-weight: bold; margin-left: 12px"
            >나의 활동</v-card-text
          >
        </v-row>

        <v-list nav dense style="margin-top: 20px">
          <v-list-item-group v-model="selectedItem" color="green">
            <v-list-item
              v-for="(item, i) in items"
              :key="i"
              style="margin-bottom: 15px"
              @click="moveMyPage(i)"
            >
              <v-list-item-icon>
                <v-icon v-text="item.icon" x-large></v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title
                  v-text="item.text"
                  style="font-size: 18px"
                ></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </div>

      <v-row style="height: 50px"> </v-row>
    </v-card>
  </v-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
import cookies from "vue-cookies";
import axios from "axios";
import { API_BASE_URL } from "@/constant/login";

export default {
  name: "MyProfileBar",
  props: {
    index: {
      type: Number,
      default: null,
    },
  },
  data() {
    return {
      id: cookies.get("id"),
      name: cookies.get("name"),
      access_token: cookies.get("access_token"),
      selectedItem: this.index,
      items: [
        { text: "내 정보 설정", icon: "mdi-account-circle-outline" },
        { text: "내 동네 설정", icon: "mdi-map-marker-outline" },
        { text: "동네 인증하기", icon: "mdi-target" },
        { text: "사장님 프로필", icon: "mdi-bookmark-outline" },
        { text: "모아보기", icon: "mdi-archive-outline" },
      ],
    };
  },
  methods: {
    ...mapActions(["fetchMemberProfile"]),
    moveMyPage(index) {
      //alert(index)
      switch (index) {
        case 0:
          this.$router.push({ name: "MyInfoPage" });
          break;
        case 1:
          this.$router.push({ name: "MyVillageSetting" });
          break;
        case 2:
          //navigator.geolocation.getCurrentPosition(this.showYourLocation, this.showErrorMsg);
          this.$router.push({ name: "MyVillageProof" });
          break;
        case 3:
          this.registerBoss();
          break;
        case 4:
          this.$router.push({ name: "MyPageGathering" });
          break;
      }
    },
    myProfile() {
      this.$router.push({ name: "MyPageProfile" });
    },
    registerBoss() {
      alert("사장님 만들기!");
      let id = this.id;

      axios
        .post(
          API_BASE_URL + "/boss/checkMember",
          { id },
          {
            headers: {
              Authorization: "Bearer " + this.access_token,
              Accept: "application/json",
              "Content-Type": "application/json",
            },
          }
        )
        .catch((res) => {
          alert(res + "에러");
        })

        .then((res) => {
          if (res.data === false) {
            this.$router.push({ name: "BossRegisterPage" });
          } else {
            this.$router.push({ name: "BossPage" });
          }
        });
    } /*,
    showYourLocation(position) {  // 성공했을때 실행
      let y = position.coords.latitude;
      let x = position.coords.longitude;

      console.log(x);
      console.log(y);

      this.$router.push({name: 'MyVillageProof', params: {lat: y, lng: x}})
    },
    showErrorMsg(error) {
      alert('에러발생')
      switch(error.code) {
        case error.PERMISSION_DENIED:
          alert("이 문장은 사용자가 Geolocation API의 사용 요청을 거부했을 때 나타납니다!")
          break;

        case error.POSITION_UNAVAILABLE:
          alert("이 문장은 가져온 위치 정보를 사용할 수 없을 때 나타납니다!")
          break;

        case error.TIMEOUT:
          alert("이 문장은 위치 정보를 가져오기 위한 요청이 허용 시간을 초과했을 때 나타납니다!")
          break;
      }
    },*/,
  },
  computed: {
    ...mapState(["profileImg"]),
  },
  mounted() {
    this.id = cookies.get("id");
    this.fetchMemberProfile(this.id);
  },
  created() {
    this.access_token = cookies.get("access_token");
    //alert(cookies.get("access_token"))
  },
};
</script>

<style scoped>
.statementBtn {
  margin-top: 30px;
  padding-left: 5px;
}
</style>