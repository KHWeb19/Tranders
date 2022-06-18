<template>
  <v-container>
    <v-card width="100%">
      <div style="padding-left: 50px; padding-right: 30px">
        <v-row>
          <v-col v-for="count in 4" :key="count"></v-col>
          <v-col>
            <div>
              <v-img v-if="profileImg === null" contain max-height="58" src="@/assets/profile/Tranders_base_profile_img.png"></v-img>
              <v-img v-else :src="require(`@/assets/profile/${profileImg}`)" id="img" style="height: 180px; width: 180px"></v-img>
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
              <v-text-field v-model="id" solo style="width: 100%" readonly disabled> </v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="3">이름</v-col>
            <v-col cols="9">
              <v-text-field v-model="id" solo style="width: 100%"> </v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="3">비밀번호</v-col>
            <v-col cols="9">
              <v-text-field v-model="id" :rules="rules" solo style="width: 100%" placeholder="ID"> </v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="3">전화번호</v-col>
            <v-col cols="9">
              <v-text-field v-model="id" solo style="width: 100%" placeholder="ID"> </v-text-field>
            </v-col>
          </v-row>

          <v-row style="height: 70px; margin-top: 35px">
            <v-col>
              <v-btn @click="registerBtn" style="width: 100%; height: 100%; font-size: 20px; border-radius: 18px" class="light-green lighten-3" :disabled="checkDoubleId === true">변경완료</v-btn>
            </v-col>
          </v-row>
      </div>

      </div>

    </v-card>
  </v-container>
</template>

<script>
import cookies from "vue-cookies";
import {mapActions, mapState} from "vuex";

export default {
  name: "MyInfoView",
  components: {},
  data(){
    return{
      id: cookies.get('id'),
    }
  },
  methods: {
    ...mapActions(['fetchMemberProfile']),
  },
  computed: {
    ...mapState(['profileImg'])
  },
  mounted() {
    this.id = cookies.get('id');
    this.fetchMemberProfile(this.id)
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