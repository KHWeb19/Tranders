<template>
    <form @submit.prevent="onSubmit">
        <div>           
            <!-- 상대방과의 채팅방이 있으면 이동, 없으면 생성하고 이동 -->
            <!-- params: 내No/상대방No 으로 이동 -->
            <!-- <router-link :to="{
                        name: 'ChattingListPage',
                        
                        }"> -->
                <!-- <v-btn>채팅하기</v-btn> -->
                <v-btn type="submit">채팅생성</v-btn>
            <!-- </router-link> -->
        </div>
    </form>
</template>

<script>
export default {
    name: 'BoardTestView',
    data() {
        return {
            sender: '지은'
        }
    },
    methods: {
        onSubmit() {
            // this.$emit('submit', {sender: this.loginMember.memberNo, receiver: this.board.memberNo})
            this.$emit('submit', {sender: this.sender})
        }
    }
}
</script>