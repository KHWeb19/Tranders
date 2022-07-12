<template>
  <div>
    <manager-header-view></manager-header-view>
    <manager-view ></manager-view>
  </div>
</template>

<script>
import ManagerHeaderView from "@/components/home/ManagerHeaderView";
import ManagerView from "@/components/manager/ManagerView";
export default {
  name: "ManagerPage",
  components: {ManagerView, ManagerHeaderView},
  data() {
    return {

    }
  },
  methods: {
    //...mapActions(['fetchReportRecent'])
  },
  computed: {
    //...mapState(['recentReport'])
  },
  async created() {
    let roles = this.$cookies.get('roles');

    if(roles !== 'ROLE_MANAGER'){
      alert('접근할 수 없는 페이지 입니다.')
      await this.$router.push({name: "HomePage"});
    }

  }
}
</script>

<style scoped>

</style>