<template>
    <div>
        <h2>게시물 읽기 페이지</h2>
        <board-test-view @onChat="onChat"/>
    </div>
</template>

<script>
import BoardTestView from '@/components/chatting/BoardTestView.vue'
// import { mapState, mapActions } from 'vuex'
import axios from 'axios'
export default {
    components: { BoardTestView },
    name: 'BoardTestPage',
    // props: {
    //     member1: {
    //         type: String,
    //         required: true
    //     },
    //     member2: {
    //         type: String,
    //         required: true
    //     }
    // },
    // computed: {
    //     ...mapState(['chatroom']),
    // },
    created() {
    //   this.fetchChatroom(this.roomNo)
    //       .catch(()=>{
    //           alert('채팅방 요청 실패')
    //           this.$router.push()
    //       })
    },
    methods: {
        // ...mapActions(['fetchChatroom']),
        onChat(payload) {
            const {member1No, member2} = payload
            axios.post(`http://localhost:7777/chatting/register/${member1No}/${member2}`, {member1No, member2})
            // const {member2} = payload
            // axios.post('http://localhost:7777/chatting/register', {member2})
                .then(() => {
                    alert('채팅방 등록 성공!')
                })
                .catch(() => {
                    alert('문제 발생!')
                })
            // this.fetchChatroom(member1, member2)
            
        }    
    }
}
</script>