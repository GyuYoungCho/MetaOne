<template>
    <div class="user">
        <main-title :title="'위기탈출 메타원'"></main-title>
        <div class="img_main">
            <img src="" ref="캐릭터 공간" />
        </div>

        <li class="input-label" v-for="(e, i) in titles" :key="i">
            <div class="row mb-4">
                <div class="col-md-3">
                </div>
                <div class="col-md-5">
                    <inputparam :title="e" :placeholderData="placeholderDatas[i]"></inputparam>
                </div>
                <div class="col-md-2">
                    <div style="float: left;" v-if="i == 0">
                        <button class="btn yellow-btn" @click="tryLogin()">&nbsp;&nbsp;&nbsp;&nbsp;시작하기&nbsp;&nbsp;&nbsp;&nbsp;</button>
                    </div>
                    <div style="float: left;" v-if="i == 1">
                        <button class="btn yellow-btn">카카오 로그인</button>
                    </div>
                </div>
                <div class="col-md-2">
                </div>
            </div>
        </li>
        
        <div class="row" >
            <div class="col-md-3"></div>
            <button class="col-md-1 not-submit" style="text-align:left; width:100px;" @click="toJoin()">회원가입</button>
            <button class="col-md-2 not-submit" style="text-align:left; width:150px;" @click="toFindPw()">비밀번호 찾기</button>
        </div>
    </div>
</template>

<script>
import Inputparam from '../components/Inputparam.vue'
import MainTitle from '../components/MainTitle.vue'
import { mapState, mapActions } from 'vuex'

export default {
    name: "Login",
    components:{
        MainTitle,
        Inputparam,
    },
    data(){
        return{
            titles: ["Email", "비밀번호"],
            placeholderDatas: ["Email", "Password"],

        }
    },
    methods:{
        ...mapActions('user', ['login']),
        tryLogin(){
            if(this.email == "") {
                alert("이메일을 입력해주세요.")
                return
            }
            else if(this.password == "") {
                alert("비밀번호를 입력해주세요.")
                return
            }

            this.login()
        },
        toJoin(){
            this.$router.push({name: 'Join'}).catch(() => {})
        },
        toFindPw(){
            this.$router.push({name: 'FindPw'}).catch(() => {})
        }
    }, 
    mounted(){

    },
    created(){

    },
    computed:{
        ...mapState('user', ['email', 'password'])
    },
    watch:{

    }
}
</script>

<style>


.img_main{
    width: 50%;
    height: 30%;
}
</style>