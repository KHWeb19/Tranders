<template>
  <div>
    <manager-header-view></manager-header-view>
    <report-management-read-view :reportNo="reportNo" @deleteBoard="deleteBoard" @done="done" @removeMember="removeMember"></report-management-read-view>
  </div>
</template>

<script>
import ReportManagementReadView from "@/components/manager/ReportManagementReadView";
import ManagerHeaderView from "@/components/home/ManagerHeaderView";
import axios from "axios";

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

      axios.delete(`http://localhost:7777/manager/${reportNo}`)
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

      axios.post(`http://localhost:7777/manager/done/${reportNo}`)
          .then((res) => {
            console.log(res)
            this.$router.go()
          })
          .catch(() => {
            alert('에러')
          })
    },
    removeMember(payload){
      const {id} = payload;
      let num = 0;
      axios.get(`http://localhost:7777/manager/removeMember/${id}/${num}`)
          .then((res) => {
            console.log(res)
            this.$router.go()
          })
          .catch(() => {
            //alert('에러')
            this.$router.go()
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