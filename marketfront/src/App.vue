<template>
  <v-app>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
export default {

  name: 'App',

  data: () => ({
    //
  }),
  created() {
    let token = this.$store.getters.getToken;

    if(token.accessToken === null && token.refreshToken === null){
      this.$router.push({name: 'LoginPage'})
    }
    if(token.accessToken === null && token.refreshToken !== null){
      this.$store.dispatch("refreshToken");
    }

  }

};
</script>
