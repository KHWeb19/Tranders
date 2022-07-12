<template>
  <div class="map_box" style="padding: 40px; border-left: 1px solid #e9ecef;">
    <div id="wrapper">
      <div v-for="(coupon, index) in coupon" :key="index" style="width: 100%; padding: 10px 10px 10px 10px">
        <div v-if="coupon.couponState === 'BEFORE_USE'">
          <div style="width: 100%; border-radius: 8px; border: 1px solid green; min-height: 170px; display: flex; align-items: center">
            <div style="width: 80%; padding: 0 10px 5px 15px;">
              <div style="display: flex; align-items: center">
                <v-chip color="green lighten-2" style="margin-right: 20px">발급중</v-chip>
              </div>

              <div style="font-weight: bolder; font-size: 30px; padding-top: 20px">
                {{coupon.couponName}}
              </div>

              <div style="font-weight: lighter; padding-top: 5px">
                {{coupon.couponDate}}까지
              </div>

              <div style="font-weight: lighter; padding-top: 10px; font-size: 20px">
                {{coupon.couponInfo}}
              </div>

            </div>

            <div style="background-color: #96cb96; width: 20%; min-height: 150px; border-top-right-radius: 8px; border-bottom-right-radius: 8px">
              <div style="display: flex; flex-direction: column; justify-content: center; align-items: center; min-height: 170px" @click="useCoupon(coupon)">
                <v-icon large>mdi-cash</v-icon>
                <div style="font-size: 18px">쿠폰 사용</div>
              </div>
            </div>
          </div>
        </div>

        <div v-else style="width: 100%; background-color: rgba(145,145,145,0.37); border-radius: 8px; border: 1px solid black; min-height: 150px; display: flex; align-items: center">
          <div style="width: 80%; padding: 5px 10px 5px 15px;">
            <div style="display: flex; align-items: center">
              <v-chip style="margin-right: 20px; margin-bottom: 10px">사용완료</v-chip>
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
              <div style="font-size: 18px">사용 완료</div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div id="pageNum">
      <div style="display: flex; align-items: center; justify-content: center">
        <v-btn @click="pre" text>
          <v-icon> mdi-chevron-left</v-icon> 이전
        </v-btn>

        {{pageNum}} / {{page}}

        <v-btn @click="next" text>
          다음 <v-icon>mdi-chevron-right</v-icon>
        </v-btn>
      </div>
    </div>


    <v-dialog v-model="useCouponDialog"  width="500px">
      <v-card width="800px" class="pa-4">

        <div style="padding: 10px 10px 10px 10px">
          <div style="justify-content: center; display: flex">
            <v-card-title style="font-weight: bold; font-size: 25px">쿠폰 사용</v-card-title>
          </div>

          <v-card-text>
            * 한번 사용하시면 다시 돌릴 수 없어요
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text color="red" @click="use">사용</v-btn>
            <v-btn text @click="useCouponDialog = false">취소</v-btn>
          </v-card-actions>
        </div>
      </v-card>
    </v-dialog>  <!-- 쿠폰 사용 -->
  </div>
</template>

<script>
import {mapState} from "vuex";
import cookies from "vue-cookies";
import axios from "axios";

const config = {
  headers: {
    'Authorization': 'Bearer '+ cookies.get('access_token'),
    'Accept' : 'application/json',
    'Content-Type': 'application/json'
  }
};

export default {
  name: "MyCouponView",
  data(){
    return {
      id: cookies.get("id"),
      pageNum: 1,
      useCouponDialog: false,
      modifyCoupon: []
    }
  },
  methods: {
    next(){
      if(this.pageNum < this.page) {
        this.pageNum += 1
        this.fetchMyCoupon(this.pageNum - 1)
      }
    },
    pre(){
      if(this.pageNum > 1) {
        this.pageNum -= 1
        this.fetchMyCoupon(this.pageNum-1)
      }
    },
    fetchMyCoupon(page){
      let id = this.id;
      axios.get(`http://localhost:7777/boss/MyCoupon/${id}/${page}`, config)
          .then((res) => {
            this.$store.state.coupon = res.data;
          })
    },
    fetchMyCouponTotalPage(){
      let id = this.id;
      axios.get(`http://localhost:7777/boss/MyCoupon/${id}`, config)
          .then((res) => {
            this.$store.state.page = res.data;
          })
    },
    useCoupon(coupon){
      console.log(coupon)
      this.modifyCoupon = coupon;
      this.useCouponDialog = true;
    },
    use(){
      let couponNo = this.modifyCoupon.couponNo;
      console.log(this.modifyCoupon)
      let id = this.id;
      //alert(couponNo)
      this.$emit('useCoupon', {couponNo, id})
    }
  },
  computed: {
    ...mapState(['page']),
    ...mapState(['coupon']),
  },
  async created() {
    await this.fetchMyCouponTotalPage();
    this.fetchMyCoupon(0)
  }
}
</script>

<style scoped>
#wrapper{
  height: 830px;
  min-height: 100%;
}

#pageNum{
  height: 30px;
  position : relative;
  transform : translateY(-100%);
}
</style>