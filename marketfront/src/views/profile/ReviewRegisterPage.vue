<template>
    <div>
        <review-register :userInfo="userInfo" @onSubmit="onSubmit"/>
    </div>
</template>

<script>
import ReviewRegister from '@/components/profile/ReviewRegister.vue'
import axios from "axios";
import { mapActions, mapState } from 'vuex'
export default {
    components: { ReviewRegister },
    name: 'ReviewRegisterPage',
    props: {
        memberId: {
            type: String,
            required: true
        }
    },
    computed: {
        ...mapState(['userInfo']),
    },
    created() {
        this.fetchMyPage(this.memberId)
    },
    methods: {
        ...mapActions(['fetchMyPage']),
        onSubmit(payload) {
            console.log(payload);
            const {memberId, member2Id, good1, good2, good3, good4, bad1, bad2, bad3, bad4, sum, message} = payload;
            axios.post(`http://localhost:7777/review/register/${memberId}/${member2Id}`, {
                memberId, member2Id, sum, message})
            axios.post(`http://localhost:7777/manner/register/${memberId}`, {
                memberId, good1: +good1, good2: +good2, good3: +good3, good4: +good4, bad1: +bad1, bad2: +bad2, bad3: +bad3, bad4: +bad4})
                
        },
    }
}
</script>