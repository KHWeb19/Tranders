<template>
  <div>
    <home-view></home-view>
    <v-divider></v-divider>
  </div>
</template>

<script>
import HomeView from "../../components/home/HomeView.vue";

export default {
  components: { HomeView },
  name: "PaymentPage",
};
</script>

<style scoped>
</style>