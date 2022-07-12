<template>
  <div>
    <manager-header-view></manager-header-view>
    <manager-member-view @changeRole="changeRole"></manager-member-view>
  </div>
</template>

<script>
import ManagerMemberView from "@/components/manager/ManagerMemberView";
import ManagerHeaderView from "@/components/home/ManagerHeaderView";
import axios from "axios";

export default {
  name: "ManagerMemberPage",
  components: {ManagerHeaderView, ManagerMemberView},
  methods: {
    changeRole(payload){
      const {memberNo} = payload;
      //alert(memberNo)
      axios.post(`http://localhost:7777/manager/changeRole/${memberNo}`, {})
          .then((res) => {
            console.log(res)
            this.$router.go()
          })
          .catch(() => {
            alert('에러')
          })
    }
  }
}
</script>

<style scoped>

</style>