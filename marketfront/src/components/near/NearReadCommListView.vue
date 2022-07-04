<template>
  <div>
    <div>
      <div v-for="(boss, index) in bossReview" :key="index" style="padding: 20px 15px 0 20px; border-bottom: 1px solid rgba(187,187,187,0.23); min-height: 200px">
        <div @click="moveComm(boss.boardNo)">
          <div style="padding-bottom: 12px">{{boss.usedSubject }}</div>
          <div style="display: flex; margin-top: 2px">
            <div style="padding-right: 10px"><v-img :src="require(`@/assets/profile/${boss.profileImg}`)" max-height="60" max-width="60"></v-img></div>
            <div>
              <div style="font-size: 22px; font-weight: bolder">{{boss.writer}}</div>
              <div> {{boss.region}} ㆍ {{ boss.createdDate }}</div>
            </div>
          </div>

          <div style="padding-top: 8px; font-size: 25px">
            {{boss.content}}
          </div>

          <div v-if="boss.imgSrc == null"></div>

          <div v-else>
            <v-img :src="require(`@/assets/uploadImg/community/${boss.imgSrc}`)"></v-img>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";

export default {
  name: "NearReadCommListView",
  props: {
    nearNo: {
      type: Number,
    }
  },
  components: {
  },
  data(){
    return {

    }
  },
  methods: {
    ...mapActions(['fetchBossComm']),
    moveComm(boardNo){
      this.$router.push({ name: 'CommunityBoardReadPage', params: { boardNo: boardNo.toString()}})
    }
  },
  computed: {
    ...mapState(['bossReview']),
  },
  async mounted() {
    let num = '00' + this.nearNo;
    await this.fetchBossComm(num);
  }
}
</script>

<style scoped>

</style>