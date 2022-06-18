<template>
  <div>
<!--    <after-login-view></after-login-view> // 없는게 더 괜찮아 보이는데 다른분들은 어떤지 물어보기!-->
    <boss-page-view></boss-page-view>
  </div>
</template>

<script>
import BossPageView from "@/components/boss/BossPageView";
export default {
  name: "BossPage",
  components: {BossPageView}
}
</script>

<style scoped>

</style>