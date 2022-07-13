<template>
  <div id="login-wrap">
    <div id="login-box">
      <span class="title_g" style="font-size: 50px">오이 마켓</span>
      <br/>
      <span></span>

      <v-card style="margin-top: 30px; padding-top: 25px; font-size: 20px; height: 200px">

        <v-row justify="center">
          <v-card-title>
            당신의 id는<span style="font-size: 30px">" {{resultId}} "</span>
          </v-card-title>
        </v-row>

        <v-row style="height: 70px; margin-top: 15px; padding: 0 10px 0 10px">
          <v-col>
            <v-btn block @click="changePw" depressed color="success" height="50" width="100" type="submit"><h3><b>비밀번호 변경</b></h3></v-btn>
          </v-col>
        </v-row>
      </v-card>

    </div>
  </div>
</template>

<script>
export default {
  name: "ResultPage",
  props: {
    resultId: {
      type: String,
      default: null,
    }
  },
  methods: {
    changePw(){
      this.$router.push({name: "PwSearchPage"})
    }
  }
}
</script>

<style scoped>
#login-wrap{
  width: 100%;
  max-width: 1440px;
  margin: 0 auto;
  text-align: center;
}
#login-box{
  max-width: 460px;
  margin: 0 auto;
  padding-top: 100px;
  padding-bottom: 90px;
}
.title_g{
  font-family: ONE-Mobile-POP, serif;
  font-size: 25px;
  color: green;
}
</style>