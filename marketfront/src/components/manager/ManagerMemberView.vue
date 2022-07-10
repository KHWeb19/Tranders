<template>
  <div id="manager_page_box" style="position: relative">
    <v-card style="min-height: 600px; padding: 10px 10px 10px 10px">
      <div id="wrapper">
        <table style="border-collapse: collapse; padding: 5px 20px 5px 20px; width: 100%">
          <tr>
            <td align="center">번호</td>
            <td align="center">아이디</td>
            <td align="center">이름</td>
            <td align="center">권한</td>
            <td align="center">권한 변경</td>
          </tr>

          <tr v-for="member in member" :key="member.memberNo" style="width: 100%">
            <td align="center">
              {{member.memberNo}}
            </td>

            <td>
              {{member.id}}
            </td>

            <td align="center">
              {{member.name}}
            </td>

            <td align="center" v-if="member.role === 'ROLE_USER'">
              <v-chip>일반 회원</v-chip>
            </td>

            <td align="center" v-else>
              <v-chip color="green" text-color="white">&nbsp;관리자&nbsp;</v-chip>
            </td>

            <td align="center">
              <v-btn v-if="member.role === 'ROLE_MANAGER'" @click="changeRole(member.memberNo)">관리자 탈퇴</v-btn>
            </td>

          </tr>
        </table>
      </div>

      <v-row id="pageNum" justify="center">
        <div style="display: flex; align-items: center">
          <v-btn @click="pre" text>
            <v-icon> mdi-chevron-left</v-icon> 이전
          </v-btn>

          {{pageNum}} / {{page}}

          <v-btn @click="next" text>
            다음 <v-icon>mdi-chevron-right</v-icon>
          </v-btn>
        </div>
      </v-row>

    </v-card>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import axios from "axios";
import cookies from "vue-cookies";

export default {
  name: "ManagerMemberView",
  data() {
    return {
      pageNum: 1,
    }
  },
  methods: {
    ...mapActions(['fetchMemberTotalPage']),
    fetchMemberListPage(num){
      axios.get(`http://localhost:7777/manager/memList/${num}`, {
        headers: {
          'Authorization': 'Bearer '+ cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
          .then((res) => {
            this.$store.state.member = res.data;
          })
    },
    next(){
      if(this.pageNum < this.page) {
        this.pageNum += 1
        this.fetchMemberListPage(this.pageNum - 1)
      }
    },
    pre(){
      if(this.pageNum > 1) {
        this.pageNum -= 1
        this.fetchMemberListPage(this.pageNum-1)
      }
    },
    changeRole(memberNo){
      //alert(memberNo)
      this.$emit('changeRole', {memberNo});
    }
  },
  computed: {
    ...mapState(['page']),
    ...mapState(['member'])
  },
  async created() {
    await this.fetchMemberTotalPage();
    await this.fetchMemberListPage(0);
  }
}
</script>

<style scoped>
#manager_page_box {
  align-items: center;
  width: 100%;
  max-width: 1250px;
  margin: 0 auto;
  padding: 15px 7px 0 7px; /*위, 오른쪽, 아래, 왼쪽 */
}
#wrapper{
  height: 550px;
  min-height: 100%;
  padding: 50px 25px 30px 25px;
}
td {
  border: 1px solid black;
  padding: 5px 0 5px 5px;
}
</style>