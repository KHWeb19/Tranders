<template>
    <div>
        <div style="padding:40px">
            <v-row justify="center" style="padding-bottom: 20px">
                <v-card-title style="font-size: 30px;">모아보기</v-card-title>
            </v-row>
            <section class="card-wrap">
                <v-col
                    class="none-product"
                    v-if="!myFollowBoards || (Array.isArray(myFollowBoards) && myFollowBoards.length === 0)">
                    현재 모아보기를 할 게시글이 존재하지 않습니다!
                    <p>* 사용자 프로필에서 [모아보기] 버튼을 눌러 주세요.</p>
                </v-col>
                <v-col v-else
                    v-for="productBoard in myFollowBoards" :key="productBoard.productNo"
                    cols="4">
                <article class="card-top">
                    <router-link class="product-link"
                    :to="{
                        name: 'ProductReadPage',
                        params: {productNo: productBoard.productNo.toString(),},}">
                    <div class="card-photo">
                        <v-img style="height: 150px; width: 250px"
                            :src="require(`@/assets/pImage/${productBoard.productImage}`)"/>
                    </div>
                    <div class="card-desc">
                        <div class="card-process">
                        {{ productBoard.process }}
                        </div>
                        <div class="card-title">
                        {{ productBoard.title }}
                        </div>

                        <div class="card-price">
                        {{ productBoard.price | makeComma }}원
                        </div>
                        <div class="card-region-name">
                        {{ productBoard.member.region }}
                        </div>
                        <div class="card-counts">
                        <span> 관심 5 </span>∙ <span> 채팅 6 </span>∙
                        <span> 조회수 {{ productBoard.viewCnt }}</span>
                        </div>
                    </div>
                    </router-link>
                </article>
                </v-col>
            </section>
        </div>
    </div>
</template>

<script>
import cookies from "vue-cookies";
import { mapActions, mapState } from "vuex";
export default {
    name: "MyFollowBoard.vue",
    components: {},
    data() {
        return {
        id: cookies.get("id"),
        };
    },
    props: {
        myFollowBoards: {
            type: Array,
        },
    },
    methods: {
        ...mapActions(["fetchMemberProfile"]),
    },
    computed: {
        ...mapState(["profileImg"]),
    },
    mounted() {
        this.fetchMemberProfile(this.id);
    },
};
</script>

<style scoped>
section {
  display: block;
}
section#content {
  margin-top: 72px;
  margin: 0;
  padding: 0;
}
.card-wrap {
  width: 700px;
  /* margin: 0 auto; */
  margin: 0 33px;
  display: flex;
  justify-content: left;
  flex-wrap: wrap;
}
.card-top {
  margin-right: 44px;
  margin-bottom: 56px;
}
.card-photo {
  position: relative;
  overflow: hidden;
  border-radius: 12px;
  background-color: #f8f9fa;
  box-shadow: inset 0px 0px 0px 1px rgb(0, 0, 0 / 15%);
  box-sizing: border-box;
}
.card-desc {
  margin-top: 12px;
}
.card-process {
  font-size: 18px;
  font-weight: 700;
  text-align: center;
  border: 1px solid white;
  border-radius: 5px;
  background-color: green;
  max-width: 70px;
  color: white;
  margin-bottom: 5px;
}
.card-title {
  font-size: 16px;
  letter-spacing: -0.02px;
  color: #212529;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 2px;
  line-height: 1.5;
  font-weight: normal;
}
.card-price {
  font-size: 15px;
  font-weight: 700;
  line-height: 1.5;
  margin-bottom: 4px;
  color: #212529;
}
.card-region-name {
  font-size: 13px;
  color: #212529;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 4px;
  line-height: 1.5;
}
.card-counts {
  color: #868e96;
  font-size: 13px;
}
.product-link {
  text-decoration: none !important;
}
img {
  position: absolute;
  top: 0;
  bottom: 0;
  width: 100%;
  box-sizing: border-box;
  border-radius: 12px;
  border: 1px solid transparent;
}
</style>