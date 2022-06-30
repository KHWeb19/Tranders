<template>
    <div>
        <v-btn @click="goBasic">판매 상품 ()</v-btn>
        <v-btn @click="goReview">거래 후기 ()</v-btn>
        <v-btn @click="goManner">매너 칭찬</v-btn>
    </div>
</template>

<script>
export default {
    name: 'ProfileTop',
    methods: {
        goBasic(){
            this.$router.push({name: "ProfileBasicPage"})
        },
        goReview(){
            this.$router.push({name: "ReviewListPage"})
        },
        goManner(){
            this.$router.push({name: "MannerReadPage"})
        }
    },
}
</script>