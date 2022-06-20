<template>
  <div>
<!--    <after-login-view></after-login-view> // 없는게 더 괜찮아 보이는데 다른분들은 어떤지 물어보기!-->
    <div v-if="this.boss === null"><v-btn>죄송합니다 다시 시도해주세요</v-btn></div>
    <boss-page-view v-else :boss="boss"></boss-page-view>
  </div>
</template>

<script>
import BossPageView from "@/components/boss/BossPageView";
import {mapActions, mapState} from "vuex";
import cookies from "vue-cookies";
export default {
  name: "BossPage",
  components: {BossPageView},
  data() {
    return {
      memberNo: cookies.get('memberNo'),
    }
  },
  methods: {
    ...mapActions(['fetchBossPage']),
  },
  computed: {
    ...mapState(['boss'])
  },
  mounted() {
    this.fetchBossPage(this.memberNo)
  }
}
</script>

<style scoped>

</style>