<template>
    <div class="page">
        <main-title :title="'회원가입'"></main-title>

        <li class="input-label" v-for="(e, i) in titles" :key="i">
            <div class="row mb-4 pb-2">
                <div class="col-md-9">
                    <inputparam :title="e" :placeholderData="placeholderDatas[i]" v-on:data="dataIn"></inputparam>
                </div>
                <div class="col-md-3">
                    <div style="float: left;" v-if="i == 1">
                        <button class="btn yellow-btn" style="margin-right: 20px;" id="emailD" @click="checkDuplicateMethod('email')" :disabled="emailFormat == false">중복확인</button>
                        <button class="btn yellow-btn" @click="emailAuthenticate" :disabled="emailFormat == false">인증</button>
                    </div>
                    <div style="float: left;" v-if="i == 2">
                        <button class="btn yellow-btn" @click="checkDuplicateMethod('nickname')">중복확인</button>
                    </div>
                    <div style="float: left;" v-if="i == 5">
                        <button class="btn yellow-btn">인증확인</button>
                    </div>
                </div>
            </div>
        </li>

        <div class=" row col-md-10">
            <div class="col-md-2"> </div>
            <div class="row col-md-10" style="text-align:left;">
                <button class="btn yellow-btn col-md-5" @click="join()">회원가입</button>
                <div class="col-md-1"></div>
                <button class="btn yellow-btn col-md-5">취소</button>
            </div>
        </div>
        
        
    </div>
</template>

<script>
import MainTitle from "../components/MainTitle.vue"
import Inputparam from '../components/Inputparam.vue'
import joinApi from "../api/join.js"
import { mapState, mapActions } from 'vuex'

export default {
    
    name: "Join",
    components:{
        MainTitle,
        Inputparam,
    },
    data(){
        return{
            titles: ["이름", "Email", "닉네임", "비밀번호", "비밀번호확인", "인증번호"],
            placeholderDatas: ["뽀로로", "email@email.com", "메타몽", "Password", "Password Confirm", "이메일 인증번호"],

        }
    },
    methods:{
        ...mapActions('join', ['registerUser', 'checkDuplicate']),
        join(){
            this.registerUser();
        },
        emailAuthenticate(){
            
        },
        async checkDuplicateMethod(type){
            let isPass = false

            let data = ""
            if (type == 'nickname') data = this.nickname
            else if(type == 'email') data = this.email

            await joinApi.checkDuplicate(data, type)
            .then((res) => {
                console.log(res)
                if (res.status == 200) isPass = true;
            })
            .catch((err) => {
                console.log(err)
            })

            if(isPass && type == 'email') {
                alert("사용가능한 메일입니다.")
                this.$store.commit('join/SET_JOIN_EMAILPASS', true);
            }
            else if(isPass && type == 'nickname'){
                alert("사용가능한 닉네임입니다.")
                this.$store.commit('join/SET_JOIN_NICKNAMEPASS', true);
            }
            else{
                alert("중복되는 데이터가 있습니다.")
            }
        },
    },  
    mounted(){

    },
    created(){

    },
    computed:{
        ...mapState('join', ['emailFormat', 'nickname', 'email'])
    },
    watch:{
        // emailFormatCheck(val){
        //     console.log(val)
        //     console.log("여기")
        //     console.log(this.emailFormat)
        //     this.emailFormatCheck = this.emailFormat
        // }
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
    padding-top: 100px;
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