<template>
    <div class="user">
        <main-title :title="edu + ' 교육 랭킹'"></main-title>

        <div class="rank">
            <div class="m-contain"></div>
            <div v-if="rank.length == 0" class="row rank">
                
                <div class="col-md-5"></div>
                <div class="col-md-4">
                    해당 교육에 대한 순위 내역이 없습니다.
                </div>
            </div>
            <div class="row rank-list">
                <ul class="list-group">
                    <li class="list-group-item" v-for="e, i in rank" :key="i" >
                        <div class="row" :class="{'first' : i==0,'second' : i==1,'third' : i==2}">
                            <div class="col-2"></div>
                            <div class="col-2">{{i+1}}위</div> 
                            <div class="col-4"> {{e.nickname}}</div> 
                            <div class="col-4">{{(e.passTime - (e.passTime % 60)) / 60}}분 {{e.passTime % 60}}초</div>
                        </div>
                    </li>
                </ul>
            </div>
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
            
        }
    },
    methods:{
        ...mapActions('education', ['getRank']),
    }, 
    async mounted(){
        if(this.edunum == 1) this.edu = "지진"
        else if(this.edunum == 2) this.edu = "화재"
        await this.getRank(this.edu)

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