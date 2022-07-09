<template>
  <div>
      <div class="map_box" style="padding: 40px;">
        <v-row justify="center">
          <v-card-title style="font-size: 30px;">내 동네 설정</v-card-title>
        </v-row>

        <v-slide-group v-model="model" class="pa-4" show-arrows style="width: 100%;">
          <v-slide-item v-slot="{ active, toggle }">
            <v-card
                :color="active ? 'light-green lighten-3' : 'grey lighten-2'" class="ma-4" height="120" width="30%" @click="toggle">
              <v-row class="fill-height" align="center" justify="center">
                <v-scale-transition>
                  <div v-if="active" style="padding-top: 20px; font-size: 25px">{{ villageInfo.city }}</div>
                </v-scale-transition>
              </v-row>
            </v-card>
          </v-slide-item>

          <v-slide-item v-slot="{ active, toggle }">
            <v-card
                :color="active ? 'light-green lighten-3' : 'grey lighten-2'" class="ma-4" height="120" width="30%" @click="toggle">
              <v-row class="fill-height" align="center" justify="center">
                <v-scale-transition>
                  <span v-if="active" style="padding-top: 20px; font-size: 25px">{{ villageInfo.district }}</span>
                </v-scale-transition>
              </v-row>
            </v-card>
          </v-slide-item>

          <v-slide-item v-slot="{ active, toggle }">
            <v-card
                :color="active ? 'light-green lighten-3' : 'grey lighten-2'" class="ma-4" height="120" width="30%" @click="toggle">
              <v-row class="fill-height" align="center" justify="center">
                <v-scale-transition>
                  <span v-if="active" style="padding-top: 20px; font-size: 25px">{{ villageInfo.villageName }}</span>
                </v-scale-transition>
              </v-row>
            </v-card>
          </v-slide-item>
        </v-slide-group>

<!--        <v-expand-transition>
          <v-sheet v-if="model != null" height="100" tile>
            <v-row class="fill-height" align="center" justify="center">
              <div style="font-size: 30px" v-if="model === 0 ? data = villageInfo.city : model === 1 ? data = villageInfo.district : data = villageInfo.villageName">
                {{data}}
              </div>
            </v-row>
          </v-sheet>
        </v-expand-transition>-->


          <v-row justify="center" style="height: 60px">
            <v-btn depressed color="success" @click="saveRegion"><b>동네 인증 완료</b></v-btn>
          </v-row>

      </div>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import cookies from "vue-cookies";

export default {
  name: "MyVillageSettingView",
  data(){
    return {
      id: cookies.get('id'),
      data: '',
      model: 0,
    }
  },
  methods: {
    ...mapActions(['fetchMyVillageSetting']),
    saveRegion(){
      alert(this.data);
      let data = this.data;

      this.$emit('saveRegion', {data});
    }
  },
  computed: {
    ...mapState(['villageInfo'])
  },
  async created() {
    await this.fetchMyVillageSetting(this.id);
    this.model = this.villageInfo.searchId;
  },
  mounted() {
  }

}
</script>

<style scoped>

</style>