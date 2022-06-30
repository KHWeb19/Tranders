<template>
    <div>
        <h2>거래 후기 보내기</h2>
        <review-register :memberId="memberId" @onSubmit="onSubmit"/>
    </div>
</template>

<script>
import ReviewRegister from '@/components/profile/ReviewRegister.vue'
import axios from "axios";
export default {
    components: { ReviewRegister },
    name: 'ReviewRegisterPage',
    props: {
        memberId: {
            type: String,
        }
    },
    methods: {
        onSubmit(payload) {
            console.log(payload);
            const {memberId, member2Id, select1, select2, select3, select4, sum, message} = payload;
            axios.post(`http://localhost:7777/review/register/${memberId}/${member2Id}`, {
                memberId, member2Id, sum, message})
            axios.post(`http://localhost:7777/manner/register/${memberId}`, {
                memberId, count1: +select1, count2: +select2, count3: +select3, count4: +select4, })
                
        },
    }
}
</script>