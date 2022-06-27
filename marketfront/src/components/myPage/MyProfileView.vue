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

        <v-row style="margin-top: 20px;">
          <div style="border: 1px solid green; width: 100%; height: 50px; padding-right: 20px; border-radius: 8px; right: 0" class="pa-3">
            <div style="color: green">
              프로필이 보이는 장소
            </div>

          </div>
        </v-row>

        <v-row style="height: 520px">
          <v-card-text style="font-size: 20px; font-weight: bold; margin-left: 12px">나의 활동</v-card-text>
        </v-row>
      </div>
    </v-card>
  </v-container>
</template>

<script>
import cookies from "vue-cookies";
import {mapActions, mapState} from "vuex";

export default {
  name: "MyPageView",
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

</style>