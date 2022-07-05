<template>
  <div id="manager_page_box" style="position: relative">
    <v-card style="min-height: 500px">
      <div id="wrapper">
        <table style="border-collapse: collapse; padding: 5px 20px 5px 20px; width: 100%;">
          <tr>
            <td align="center">신고번호</td>
            <td align="center">작성자</td>
            <td align="center">카테고리1</td>
            <td align="center">카테고리2</td>
            <td align="center">처리</td>
          </tr>
          <tr v-for="report in report" :key="report.reportNo" style="padding-bottom: 5px; width: 100%" class="pa-4" @click="showDetail(report)">
            <td align="center">
              {{report.reportNo}}
            </td>

            <td>
              {{report.writer}}
            </td>

            <td>
              {{report.category1}}
            </td>

            <td>
              {{report.category2}}
            </td>

            <td align="center">
              <v-icon v-if="report.state === 'PROCESSING'">mdi-checkbox-blank-circle-outline</v-icon>
              <v-icon v-else>mdi-check-circle-outline</v-icon>
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
  name: "ReportManagementView",
  props: {

  },
  data(){
    return {
      pageNum: 1,
    }
  },
  methods: {
    ...mapActions(['fetchTotalPage']),
    showDetail(repo){
      this.$router.push({name: 'ReportManagementReadPage', query: {repoNo: repo.reportNo.toString()}})
    },
    next(){
      if(this.pageNum < this.page) {
        this.pageNum += 1
        this.fetchReportList(this.pageNum - 1)
      }
    },
    pre(){
      if(this.pageNum > 1) {
        this.pageNum -= 1
        this.fetchReportList(this.pageNum-1)
      }
    },
    fetchReportList(num){
      axios.get(`http://localhost:7777/manager/reportList/${num}`, {
        headers: {
          'Authorization': 'Bearer '+ cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
          .then((res) => {
            this.$store.state.report = res.data;
          })
    }
  },
  computed: {
    ...mapState(['page']),
    ...mapState(['report']),
  },
  async created() {
    await this.fetchTotalPage();
    await this.fetchReportList(0);
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
  height: 500px;
  min-height: 100%;
  padding: 50px 25px 30px 25px;
}
#pageNum{
  height: 30px;
  position : relative;
  transform : translateY(-100%);
}
td{
  border: 1px solid black;
}
</style>