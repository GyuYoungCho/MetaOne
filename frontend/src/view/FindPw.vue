<template>
    <div class="page">
        <main-title :title="'비밀번호 찾기'"></main-title>
        <div class="top-padding">

        </div>
        <li class="input-label" v-for="(e, i) in titles" :key="i">
            <div class="row mb-4 pb-2">
                <div class="col-md-2">
                </div>
                <div class="col-md-7">
                    <inputparam :title="e" :placeholderData="placeholderDatas[i]"></inputparam>
                </div>
                <div class="col-md-3">
                </div>
            </div>
        </li>
        <div style="height:50px;">

        </div>

        <div class=" row col-md-9">
            <div class="col-md-5"> </div>
            <div class="row col-md-7" style="text-align:left;">
                <button class="btn yellow-btn col-md-5" @click="sendTempPw()">임시 비밀번호 발송</button>
                <div class="col-md-1"></div>
                <button class="btn yellow-btn col-md-5" @click="cancel()">취소</button>
            </div>
        </div>
        
        
    </div>
</template>

<script>
import Inputparam from '../components/Inputparam.vue'
import MainTitle from '../components/MainTitle.vue'
import {mapState, mapActions} from 'vuex'

export default {
    name: "FindPw",
    components:{
        MainTitle,
        Inputparam,
    },
    data(){
        return{
            titles: ["이름", "Email"],
            placeholderDatas: ["뽀로로", "Email"],

        }
    },
    methods:{
        ...mapActions('user', ['sendTempPw']),
        init(){
            this.$store.commit('user/SET_JOIN_NAME', "")
            this.$store.commit('user/SET_JOIN_EMAIL', "")

        },
        async sendTempPw(){
            // 이름, 이메일 비었는지 체크
            
            // 임시 메일 발송 API 호출

        },
        cancel(){
            this.$router.push({name: 'Login'}).catch(() => {})
        },
    }, 
    mounted(){

    },
    async created(){
        await this.init()
    },
    computed:{
        ...mapState('user', ['name', 'email'])
    },
    watch:{

    }
}
</script>

<style>
@page {
    size: 4in 6in landscape;
}

.page{
    /* background-color: rgba(154, 69, 235, 0.8); */
    color: white;
    padding-left: 10%;
    padding-right: 10%;
}

.input-label{
    list-style-type: none;
}

.top-padding{
    padding-top: 50px;
}

.yellow-btn{
    background-color: rgba(248, 248, 16, 0.8);
    box-shadow: 2px 2px 3px rgb(70, 69, 69);
}

.yellow-btn:hover{
    background-color: rgba(248, 248, 16, 0.8);
    box-shadow: 2px 2px 3px rgb(70, 69, 69);
    border: 2px black solid;
}


</style>