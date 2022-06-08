<template>
  <header class="fixed-bar-box-shadow">
    <div id="header" style="height: 80px;">
      <div id="header-title">
        <span id="title">
          오이 마켓
        </span>
      </div>

      <div id="searchBar" style="float: left; width: 45%; height: 50px;">
        <input type="text" placeholder="동네 이름, 상품 검색" style="width: 88%">
        <v-btn icon style="width: 12%">
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
      </div>

      <div style="width: 20%;"></div>
      <v-row justify="end">
        <div class="bar-menu" style="float: left; width: 10%; height: 50px;">
          <v-menu
              rounded
              offset-y
          >
            <template v-slot:activator="{attrs, on}">
              <v-btn v-bind="attrs" v-on="on" icon>
                <v-icon>mdi-menu</v-icon>
              </v-btn>
            </template>

            <v-list>
              <v-list-item
                v-for="(item, index) in menuList"
                :key="item"
                link
                @click="dropDown(index)"
              >
                <v-list-item-title v-text="item"></v-list-item-title>
              </v-list-item>
             </v-list>
          </v-menu>
        </div>
      </v-row>
      </div>
  </header>
</template>

<script>
export default {
  name: "AfterLoginView",
  data(){
    return{
      menuList: [
          "HOME", "동네생활", "내근처", "채팅", "MyPage"
      ]
    }
  },
  methods: {
    dropDown(index) {
      //alert(index);
       switch (index){
         case 0:
           this.$router.push({name: 'HomePage'})
           break;
         case 1:
           // 동네생활
           break;
         case 2:
           // 내근처
           break;
         case 3:
           // 채팅
           break;
         case 4:
           this.$router.push({name: 'myPage'})
           break;

       }
    }
  }
}
</script>

<style scoped>
@font-face {
  font-family: 'ONE-Mobile-POP';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/ONE-Mobile-POP.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
#header{
  display: flex;
  align-items: center;
  width: 100%;
  max-width: 1024px;
  margin: 0 auto;
  padding: 16px 0;
}
#searchBar{
  padding-top: 8px;
  padding-left: 10px;
  padding-right: 10px;
}
#header-title{
  display: inline-block;
  margin-right: 32px;
}
#title{
  color: green;
  font-family: ONE-Mobile-POP,serif;
  font-size: 30px;
}
input {
  width: 100%;
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 14px;
}
#searchBar{
  position: relative;
  width: 300px;
}
.bar-menu{
  display: inline-flex;
  width: 10%;
  height: 50px;
  justify-content: center;
  align-items: center;
}
v-btn{
  border-radius: 8px;
  border: solid 1px black;
}

</style>