<template>
  <div>
    <manager-header-view></manager-header-view>
    <report-management-view></report-management-view>
  </div>
</template>

<script>
import ReportManagementView from "@/components/manager/ReportManagementView";
import ManagerHeaderView from "@/components/home/ManagerHeaderView";

export default {
  name: "ReportManagementPage",
  components: {ManagerHeaderView, ReportManagementView},
  data() {
    return {

    }
  }
}
</script>

<style scoped>

</style>