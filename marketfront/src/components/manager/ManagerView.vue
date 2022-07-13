<template>
  <div id="manager_page_box" style="position: relative">
    <div style="width: 100%; padding-top: 10px">
      <v-card style="min-width: 100%; min-height: 350px;">
        <div style="padding: 20px 20px 10px 20px">
          <v-row justify="center">
            <v-card-title style="font-size: 25px" @click="goReportList"> 최근 신고된 게시물 </v-card-title>
          </v-row>

          <v-row v-for="report in recentReport" :key="report.reportNo" style="padding-bottom: 5px" @click="showDetail(report)">
            <v-col style="margin-left: 50px;" cols="1">{{report.reportNo}}</v-col>
            <v-col cols="3">{{report.writer}}</v-col>
            <v-col>{{report.category1}}</v-col>
            <v-col>{{report.category2}}</v-col>
          </v-row>
        </div>
      </v-card>
    </div>

    <div style="padding-top: 30px; display: flex">
      <div style="width: 30%">
        <v-card style="height: 200px">
          <v-row justify="center">
            <v-card-title>오늘 가입자 수</v-card-title>
          </v-row>

          <v-row justify="center" style="font-size: 50px">
            {{todayMember}}
          </v-row>
        </v-card>
      </div>

      <div style="width: 30%; margin-left: 40px">
        <v-card style="height: 200px;">
          <v-row justify="center">
            <v-card-title>오늘 신고 수</v-card-title>
          </v-row>

          <v-row justify="center" style="font-size: 50px">
            {{todayReport}}
          </v-row>
        </v-card>
      </div>
    </div>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";

export default {
  name: "ManagerView",
  props: {
    /*recentReport: {
      type: Array
    }*/
  },
  methods: {
    ...mapActions(['fetchReportRecent', 'fetchTodayRegisterMember', 'fetchTodayReport']),
    showDetail(repo){
      //alert('goRead')
      this.$router.push({name: 'ReportManagementReadPage', query: {repoNo: repo.reportNo.toString()}})
    },
    goReportList(){
      //alert('goList')
      this.$router.push({name: 'ReportManagementPage'});
    }
  },
  computed: {
    ...mapState(['recentReport','todayMember', 'todayReport'])

  },
  async created() {
    await this.fetchReportRecent()
    await this.fetchTodayRegisterMember()
    await this.fetchTodayReport()
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
</style>