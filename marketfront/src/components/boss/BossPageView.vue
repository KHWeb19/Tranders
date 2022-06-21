<template>
  <div id="my_page_box" style="position: relative">
    <div id="profile_back_img">
      <div style=" width: 100%; position: relative">
        <v-img src="@/assets/umu.jpg" id="img" style="width: 100%; height: 500px;"></v-img>
      </div>
      <div style=" top: 25px; padding-left: 5px; position: absolute">
        <v-btn icon @click="backPage"><v-icon x-large>mdi-chevron-left</v-icon></v-btn>
      </div>
      <div style="top: 25px; position: absolute; left: 88%">
        <v-btn text @click="modify"><v-icon x-large>mdi-camera</v-icon>&nbsp; &nbsp;수정</v-btn>
      </div>
    </div>

    <div id="profile" style="border: 1px solid black; padding-top: 30px">
<!--      <div  id="profile_img" style="border: 1px solid black">
        <v-img src="@/assets/profile.jpg" height="200px" width="200px" style="border-radius: 70%"></v-img>
      </div>-->
      <div id="profile_img" style="border: 1px solid black">
        <v-img :src="require(`@/assets/bossProfile/${boss.profileImg}`)" height="200px" width="200px" style="border-radius: 70%"></v-img>
      </div>
      <div style="padding-left: 55px; border: 1px solid black; font-size: 35px" class="pa-5">
        {{ boss.placeName }}
        <div style="border: 1px solid black; font-size: 15px" class="pa-2">
          {{ boss.region }} ㆍ {{boss.category}} ㆍ 단골 0
        </div>
      </div>

    </div>

  </div>
</template>

<script>

export default {
  name: "BossPageView",
  props: {
    boss: {
      type: Object
    }
  },
  data(){
    return {
      name: 'test'
    }
  },
  methods: {
    backPage(){
      this.$router.push({name: 'MyPageProfile'})
    },
    modify(){
      alert('사진을 변경하겠다!')
    }
  }
}
</script>

<style scoped>
#my_page_box{
  align-items: center;
  width: 100%;
  max-width: 1250px;
  margin: 0 auto;
  padding: 15px 7px 0 7px; /*위, 오른쪽, 아래, 왼쪽 */
}
#profile {
  padding: 15px 20px 20px 20px;
  display: flex;
}
</style>