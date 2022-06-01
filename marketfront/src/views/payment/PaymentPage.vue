<template>
  <div>
    <home-view></home-view>
    <v-divider></v-divider>
    <div>
      <v-menu offset-y>
        <template v-slot:activator="{ on }">
          <v-btn color="teal darken-1" class="paymentButton" v-on="on">
            결제 금액
          </v-btn>
        </template>

        <v-list>
          <v-list-item v-for="(dropItem, index) in dropItems" :key="index" link>
            <v-list-item-title>
              {{ dropItem.title }}
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </div>
  </div>
</template>

<script>
import HomeView from "../../components/home/HomeView.vue";
export default {
  components: { HomeView },
  name: "PaymentPage",
  data() {
    return {
      dropItems: [
        { title: "1,000원" },
        { title: "5,000원" },
        { title: "10,000원" },
        { title: "50,000원" },
        { title: "100,000원" },
      ],
    };
  },
};
</script>

<style scoped>
</style>