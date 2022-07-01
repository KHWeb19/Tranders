<template>
    <div id='user_reviews'>
        <ul id='review_list' v-for="review in reviews" :key="review.reviewNo">
            <li id='review' v-if="review.message">
                <div id='review_profile_image'>
                    <v-img id="image" :src="require(`@/assets/profile/${review.writer.profileImg}`)"/>
                </div>
                <router-link id='review_writer_nickname' :to="{
                    name: 'ProfileBasicPage', 
                    params: { memberId: review.writer.id.toString() } }">
                    {{review.writer.name}}
                </router-link>
                <div id='review_writer_region_name'>{{review.writer.region}}동</div>
                <div id='review_content'>{{review.message}}</div>
                <div id='review_time'>{{review.regDate}}</div>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'ReviewList',
        props: {
            reviews: {
                type: Array
            }
    },
}
</script>

<style scoped>
#user_reviews{
    display: block;
    position: relative;
    padding-bottom: 30px;
}
#review_list{
    list-style-type: none;
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 40px;
}
#review{
    padding: 16px 0;
    border-bottom: 1px solid #e9ecef;
    position: relative;
}
#review_profile_image{
    display: inline-block;
    vertical-align: middle;
}
#image{
    width: 24px;
    height: 24px;
    -o-object-fit: cover;
    object-fit: cover;
    border-radius: 50%;
    -webkit-border-radius: 50%;
}
#review_writer_nickname{
    display: inline-block;
    font-size: 15px;
    font-weight: 600;
    line-height: 1.47;
    letter-spacing: -0.5px;
    vertical-align: middle;
    margin-right: 8px;
    margin-left: 8px;
    color: #212529;
    text-decoration: none;
}
#review_writer_region_name{
    display: inline-block;
    font-size: 13px;
    line-height: 1.46;
    letter-spacing: -0.5px;
    vertical-align: middle;
}
#review_content{
    padding-top: 8px;
    font-size: 15px;
    line-height: 1.47;
    letter-spacing: -0.5px;
    width: calc(100% - 80px);
}
#review_time{
    display: block;
    font-size: 13px;
    line-height: 1.46;
    letter-spacing: -0.5px;
    color: #868e96;
    margin-top: 8px;
}
</style>