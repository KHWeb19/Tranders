<template>
  <div style="height: 100vh; background: #f8f9fa;">
      <after-login-view></after-login-view>
      <near-page-view></near-page-view>
  </div>
</template>

<script>
import AfterLoginView from "@/components/home/AfterLoginView";
import NearPageView from "@/components/near/NearPageView";
export default {
  name: "NearPage",
  components: {NearPageView, AfterLoginView}
}
</script>

<style scoped>
</style>