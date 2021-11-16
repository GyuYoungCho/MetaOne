<template>
    <div class="user">
        <main-title :title="edu + ' 교육 랭킹'"></main-title>
        <div class="top-padding">
        </div>

        <div v-if="rank.length == 0" class="row">
            <div class="col-md-5"></div>
            <div class="col-md-4">
                해당 교육에 대한 순위 내역이 없습니다.
            </div>
        </div>

        <li class="input-label rank-list" v-for="e, i in rank" :key="i" >
            {{i+1}}위 <span style="padding-left: 30px;">{{e.nickname}}</span> <span style="padding-left: 50px; color: red;">{{(e.passTime - (e.passTime % 60)) / 60}}분 {{e.passTime % 60}}초</span>
        </li>
        
        <div class="row">
            <div class="col-md-5"></div>
            <button class="col-md-2" @click="toBack()">돌아가기</button>
        </div>
        
        
    </div>
</template>

<script>
import MainTitle from '../components/MainTitle.vue'
import {mapState, mapActions} from 'vuex'

export default {
    name: "Rank",
    components:{
        MainTitle,
    },
    data(){
        return{
            edu: "",
            kind: ["earthquake","fire", "corona", "typhoon"],
            // 교육 종목 : 화재, 지진, 코로나, 태풍
            // fire, earthquake, corona, typhoon
        }
    },
    methods:{
        ...mapActions('education', ['getRank']),
        toBack(){
            console.log("만들어야함")
        },
    }, 
    async mounted(){
        if(this.edunum == 1) this.edu = "지진"
        else if(this.edunum == 2) this.edu = "화재"
        else if(this.edunum == "corona") this.edu = "코로나"
        else if(this.edunum == "typhoon") this.edu = "태풍"
        await this.getRank(this.kind[this.edunum-1])

    },
    async created(){
    },
    computed:{
        ...mapState('education', ['rank','edunum'])
    },
    watch:{

    }
}
</script>

<style>

.top-padding{
    padding-top: 50px;
}


</style>