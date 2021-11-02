<template>
    <div class="user">
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
                <button class="btn yellow-btn col-md-5" @click="sendTempPwMethod()">임시 비밀번호 발송</button>
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
            placeholderDatas: ["이름", "email@domain.com"],

        }
    },
    methods:{
        ...mapActions('user', ['sendTempPw']),
        init(){
            this.$store.commit('user/SET_JOIN_NAME', "")
            this.$store.commit('user/SET_JOIN_EMAIL', "")

        },
        async sendTempPwMethod(){
            // 이름, 이메일 비었는지 체크
            if(this.name == "" || this.email == ""){
                alert("이름과 이메일을 입력해주세요.")
                return
            }

            // 임시 메일 발송 API 호출
            await this.sendTempPw()

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

.top-padding{
    padding-top: 50px;
}


</style>