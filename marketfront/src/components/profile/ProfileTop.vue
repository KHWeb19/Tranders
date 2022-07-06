<template>
    <div id='user_profile'>
        <h2 id='nickname'>{{userInfo.name}}<span id='region_name'>{{userInfo.address}}</span></h2>
        <div id='profile_detail'>매너온도 {{userInfo.temperature}}°C</div>
        
        <div id='profile_image'>
            <v-img id="image" :src="require(`@/assets/profile/${userInfo.profileImg}`)"/>
        </div>
    
        <div id='user_filter'>
            <ul>
            <li><router-link id='basic_router' :to="{name: 'ProfileBasicPage' }">판매 상품</router-link></li>
            <li><router-link id='basic_router' :to="{name: 'ReviewListPage' }">거래 후기</router-link></li>
            <li><router-link id='basic_router' :to="{name: 'MannerReadPage' }">매너 칭찬</router-link></li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    name: 'ProfileTop',
    props: {
        userInfo: {
            type: Object,
        },
    },
}
</script>

<style scoped>
#user_profile{
    display: block;
    position: relative;
    margin-top: 40px;
    margin-bottom: 40px;
}
#nickname{
    text-align: left;
    font-size: 22px;
    font-weight: 600;
    letter-spacing: -0.6px;
    margin-bottom: 10px;
    vertical-align: middle;
    margin-left: 80px;
}
#region_name{
    font-size: 17px;
    color: #212529;
    font-weight: normal;
    vertical-align: middle;
    margin-left: 6px;
}
#profile_detail{
    margin: 10px 0;
    margin-left: 80px;
    font-size: 15px;
    letter-spacing: -0.6px;
    color: #868e96;
}
#profile_image{
    display: block;    
    position: absolute;
    top: 0;
    left: 0;
}
#image{
    width: 58px;
    height: 58px;
    -o-object-fit: cover;
    object-fit: cover;
    border-radius: 50%;
    -webkit-border-radius: 50%;
}
#user_filter{
    margin-top: 20px;
    margin-bottom: 20px;
}
#user_filter ul{
    list-style-type: none;
    border-bottom: 2px solid #eaebee;
    padding-bottom: 7px;
    padding-left: 0px;
}
#user_filter li{
    display: inline-block;
    font-size: 17px;
}
#basic_router{
    text-decoration: none;
    color: #868e96;
    font-weight: 600;
    padding: 8px 20px;
}
#user_filter li .router-link-active, 
#user_filter li .router-link-exact-active {
    text-decoration: none;
    border-bottom: 3px solid green;
    color: green;
    font-weight: 600;
    padding: 8px 20px;
}
</style>