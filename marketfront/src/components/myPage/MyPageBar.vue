<template>
  <div style="padding: 40px;">
    <div>
      <v-row>
        <v-col><h3>프로필</h3></v-col><v-icon @click="myProfile" large color="black">mdi-chevron-right</v-icon>
      </v-row>
      <div style="padding: 40px 0; border-bottom: 1px solid #e9ecef;">
        <div>
          <div id='user_profile'>
              <h2 id='nickname'>{{userInfo.name}}<span id='region_name'>{{userInfo.address}}</span></h2>
              <div id='profile_detail'>매너온도 {{userInfo.temperature}}°C</div>
              <div id='profile_image'>
                  <v-img id="image" :src="require(`@/assets/profile/${userInfo.profileImg}`)"/>
              </div>
          </div>
        </div>

        <div
          style="
            display:flex;
            justify-content: space-between;
            border: 1px solid green;
            width: 100%;
            height: 50px;
            border-radius: 8px;
            margin: 40px 0;
          "
          class="pa-3"
        >
          <div style="color: green">오이페이 </div><div>{{userInfo.money}} 원</div>
        </div>

        <div style="display:flex; justify-content: space-between; align-items: center;">
          <div>
              <v-btn
                fab
                depressed
                style="
                  height: 70px;
                  width: 70px;
                  background-color: rgb(202, 227, 164);
                "
              >
                <v-icon style="color: #008807">mdi-shopping</v-icon>
              </v-btn>

            <div class="statementBtn"> 구매 내역 </div>
          </div>

          <div>
              <v-btn
                fab
                depressed
                style="
                  height: 70px;
                  width: 70px;
                  background-color: rgb(202, 227, 164);
                "
                :to="{ name: 'MyPurchaseHistory' }"
              >
                <v-icon style="color: #008807">mdi-receipt</v-icon>
              </v-btn>
            <div class="statementBtn"> 판매 내역 </div>
          </div>

          <div>
              <v-btn
                fab
                depressed
                style="
                  height: 70px;
                  width: 70px;
                  background-color: rgb(202, 227, 164);
                "
              >
                <v-icon style="color: #008807">mdi-heart</v-icon>
              </v-btn>

            <div class="statementBtn"> 관심 내역 </div>
          </div>
        </div>
      </div>
    </div>

      <div>
        <v-row style="padding-top: 40px">
          <v-col><h3>나의 활동</h3></v-col>
        </v-row>
        <div style="padding: 40px 0; border-bottom: 1px solid #e9ecef;">
          <v-list nav dense style="padding:0px">
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
      </div>
  </div>
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
    userInfo: {
      type: Object,
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
        { text: "쿠폰보기", icon: "mdi-ticket-percent-outline" },
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
        case 5:
          this.$router.push({ name: "MyCouponPage" });
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
  margin-top: 10px;
  padding-left: 5px;
}
#user_profile{
    display: block;
    position: relative;
    /* margin-top: 40px; */
    margin-bottom: 40px;
}
#nickname{
    text-align: left;
    font-size: 22px;
    font-weight: 600;
    letter-spacing: -0.6px;
    margin-bottom: 10px;
    vertical-align: middle;
    margin-left: 80px;
}
#region_name{
    font-size: 17px;
    color: #212529;
    font-weight: normal;
    vertical-align: middle;
    margin-left: 6px;
}
#profile_detail{
    margin: 10px 0;
    margin-left: 80px;
    font-size: 15px;
    letter-spacing: -0.6px;
    color: #868e96;
}
#profile_image{
    display: block;    
    position: absolute;
    top: 0;
    left: 0;
}
#image{
    width: 58px;
    height: 58px;
    -o-object-fit: cover;
    object-fit: cover;
    border-radius: 50%;
    -webkit-border-radius: 50%;
}
</style>