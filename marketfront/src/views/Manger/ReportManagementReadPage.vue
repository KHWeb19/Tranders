<template>
  <div>
    <manager-header-view></manager-header-view>
    <report-management-read-view :reportNo="reportNo" @deleteBoard="deleteBoard" @done="done"></report-management-read-view>
  </div>
</template>

<script>
import ReportManagementReadView from "@/components/manager/ReportManagementReadView";
import ManagerHeaderView from "@/components/home/ManagerHeaderView";
import axios from "axios";
import cookies from "vue-cookies";
const config = {
  headers: {
    'Authorization': 'Bearer '+ cookies.get('access_token'),
    'Accept' : 'application/json',
    'Content-Type': 'application/json'
  }
};
export default {
  name: "ReportManagementReadPage",
  components: {ManagerHeaderView, ReportManagementReadView},
  data() {
    return {
      reportNo: ''
    }
  },
  methods: {
    deleteBoard(payload){
      const {reportNo} = payload

      axios.delete(`http://localhost:7777/manager/${reportNo}`, config)
          .then((res) => {
            console.log(res)
            this.$router.go()
          })
          .catch(() => {
            alert('에러')
          })
    },
    done(payload){
      const {reportNo} = payload

      axios.post(`http://localhost:7777/manager/done/${reportNo}`, config)
          .then((res) => {
            console.log(res)
            this.$router.go()
          })
          .catch(() => {
            alert('에러')
          })
    }
  },
  created() {
    this.reportNo = this.$route.query.repoNo
  }
}
</script>

<style scoped>

</style>