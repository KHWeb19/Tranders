<template>
    <div id='chatList'>
        <div id='chatroom' v-for="chatroom in chatrooms" :key="chatroom.roomNo">
            <router-link id='chatroom_link' :to="{
                name: 'ChattingReadView',
                params: {roomNo: chatroom.roomNo.toString()}}">
                <div>
                    <div style="border-radius: 50%; overflow: hidden; margin-right: 12px; width: 40px; height: 40px">
                        <v-img v-if="login.memberNo==chatroom.member2.memberNo" :src="require(`@/assets/profile/${chatroom.member1.profileImg}`)"/>
                        <v-img v-if="login.memberNo==chatroom.member1.memberNo" :src="require(`@/assets/profile/${chatroom.member2.profileImg}`)"/>
                    </div>
                </div>
                <div v-if="login.memberNo==chatroom.member2.memberNo" id='name_image'>
                    <div>
                        <div style="display: flex; align-items: center; height: 20px;">
                            <span style="font-weight: bold; font-size: 13px;">{{chatroom.member1.name}}&nbsp;</span>
                            <span style="font-size: 12px;">{{chatroom.member1.region}}&nbsp;·&nbsp;</span>
                            <span style="font-size: 12px;">{{chatroom.updDate | timeForToday}}</span>
                        </div>
                        <div v-if="lastMessage.roomNo==chatroom.roomNo" style="display: flex;
                        -webkit-box-align: center;
                        align-items: center;
                        height: 20px;
                        font-size: 13px;
                        color: var(--seed-scale-color-gray-700);">{{lastMessage.message}}
                        </div>
                        <div v-else style="display: flex;
                        -webkit-box-align: center;
                        align-items: center;
                        height: 20px;
                        font-size: 13px;
                        color: var(--seed-scale-color-gray-700);">{{chatroom.lastMessage}}
                        </div>
                    </div>
                    <div v-if="chatroom.productBoard" >
                        <v-img style="margin-right: 4px;
                        border: 1px solid var(--profile-image-border);
                        border-radius: 4px;
                        width: 40px;
                        height: 40px;
                        object-fit: cover;" :src="require(`@/assets/pImage/${chatroom.productBoard.productImage}`)"/>
                    </div>
                </div>
                <div v-if="login.memberNo==chatroom.member1.memberNo" id='name_image'>
                    <div>
                        <div style="display: flex; align-items: center; height: 20px;">
                            <span style="font-weight: bold; font-size: 13px;">{{chatroom.member2.name}}&nbsp;</span>
                            <span style="font-size: 12px;">{{chatroom.member2.region}}&nbsp;·&nbsp;</span>
                            <span style="font-size: 12px;">{{chatroom.updDate | timeForToday}}</span>
                        </div>
                        <div v-if="lastMessage.roomNo==chatroom.roomNo" style="display: flex;
                        -webkit-box-align: center;
                        align-items: center;
                        height: 20px;
                        font-size: 13px;
                        color: var(--seed-scale-color-gray-700);">{{lastMessage.message}}
                        </div>
                        <div v-else style="display: flex;
                        -webkit-box-align: center;
                        align-items: center;
                        height: 20px;
                        font-size: 13px;
                        color: var(--seed-scale-color-gray-700);">{{chatroom.lastMessage}}
                        </div>
                    </div>
                    <div v-if="chatroom.productBoard" >
                        <v-img style="margin-right: 4px;
                        border: 1px solid var(--profile-image-border);
                        border-radius: 4px;
                        width: 40px;
                        height: 40px;
                        object-fit: cover;" :src="require(`@/assets/pImage/${chatroom.productBoard.productImage}`)"/>
                    </div>
                    <div v-else >
                        <v-icon style="margin-right: 4px;
                        border: 1px solid var(--profile-image-border);
                        border-radius: 4px;
                        width: 40px;
                        height: 40px;
                        object-fit: cover;" x-large color="green">mdi-storefront-outline</v-icon>
                    </div>
                </div>
            </router-link>
        </div>
    </div>
</template>

<script>
import Vue from 'vue'
import cookies from "vue-cookies";
Vue.use(cookies)
export default {
    name: 'ChattingSelect',
    props: {
        lastMessage: {
            type: Object,
            required: true
        },
        chatrooms: {
            type: Array
        }
    },
    data() {
        return {
            login: {
                memberNo: cookies.get("memberNo"),
            },
        }
    }
}
</script>

<style scoped>
#chatList{
	width: 310px;
	/* height: 100%; */
    border-right: 2px solid #e9ecef;
}
#chatroom_link{
  display: flex;
  padding: 16px;
  height: 72px;
  border-bottom: 2px solid #e9ecef;
  -webkit-box-align: center;
  align-items: center;
  position: relative;
  overflow: hidden;
  background-position: center center;
  contain: content;
  color: inherit;
  cursor: pointer;
  text-decoration: none;
  
}
#chatroom_link:hover{
  background-color: #f2f3f6;
}
#name_image{
    width: 100%; 
    display: flex; 
    justify-content: space-between;
}
</style>