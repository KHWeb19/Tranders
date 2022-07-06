<template>
  <div style="padding: 40px; border-left: 1px solid #e9ecef;">
        <!-- <v-row>
          <v-col v-for="count in 4" :key="count"></v-col>
          <v-col>
            <div>
              <v-img v-if="profileImg === null" contain max-height="58" src="@/assets/profile/Tranders_base_profile_img.png"></v-img>
              <v-img v-else :src="require(`@/assets/profile/${profileImg}`)" id="img" style="height: 180px; width: 180px"></v-img>
            </div>
          </v-col>
          <v-col></v-col><v-col></v-col><v-col></v-col><v-col></v-col>
        </v-row> -->
    
        <v-row>
          <v-col style="display: flex; justify-content: space-between;">
            <div><h3>매너온도</h3></div>
            <div><h3 style="color: #868e96">{{userInfo.temperature}}</h3></div>
          </v-col>
        </v-row>

        <v-row>
          <v-col style="padding:0 6px">
            <v-slider
              max="100"
              min="0"
              readonly
              :value="userInfo.temperature"
            ></v-slider>
          </v-col>
        </v-row>

        <v-row>
          <v-col><h3 @click="onManner">받은 매너 평가</h3></v-col>
        </v-row>

        <v-row v-if="mannerOn">
          <v-col style="padding:0 12px"><manner-read :manner="manner"></manner-read></v-col>
        </v-row>

        <v-row>
          <v-col><h3>받은 거래 후기</h3></v-col>
        </v-row>

        <v-row>
          <v-col style="padding:0 12px"><review-list :reviews="reviews"></review-list></v-col>
        </v-row>

  </div>
</template>

<script>
import cookies from "vue-cookies";
import {mapActions, mapState} from "vuex";
import MannerRead from '../profile/MannerRead.vue';
import ReviewList from '../profile/ReviewList.vue';

export default {
  name: "MyPageView",
  components: {MannerRead, ReviewList},
  props: {
      userInfo: {
          type: Object,
      },
  },
  data(){
    return{
      id: cookies.get('id'),
      mannerOn: true,
    }
  },
  methods: {
    ...mapActions(['fetchMemberProfile']),
    ...mapActions(['fetchManner']),
    ...mapActions(['fetchReviewList']),
    onManner(){
      if(!this.mannerOn){
        this.mannerOn=true;
      } else{
        this.mannerOn=false;
      }

    }
  },
  computed: {
    ...mapState(['profileImg']),
    ...mapState(['manner']),
    ...mapState(['reviews']),
  },
  created() {
    this.fetchManner(this.id)
  },
  mounted() {
    this.id = cookies.get('id');
    this.fetchMemberProfile(this.id)
    this.fetchReviewList(this.id)
  }
}
</script>

<style scoped>

</style>