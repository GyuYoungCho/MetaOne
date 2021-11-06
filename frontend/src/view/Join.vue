<template>
    <div class="user">
        <main-title :title="'회원가입'"></main-title>

        <li class="input-label" v-for="(e, i) in titles" :key="i">
            <div class="row mb-4 pb-2">
                <div class="col-md-9">
                    <inputparam :title="e" :placeholderData="placeholderDatas[i]" @join="join"></inputparam>
                </div>
                <div class="col-md-3">
                    <div style="float: left;" v-if="i == 1">
                        <button class="btn yellow-btn" style="margin-right: 20px;" id="emailD" @click="checkDuplicateMethod('email')" :disabled="!emailFormat">중복확인</button>
                        <button class="btn yellow-btn" @click="emailAuthenticate()" :disabled="!emailFormat || !emailPass">인증</button>
                    </div>
                    <div style="float: left;" v-if="i == 2">
                        <button class="btn yellow-btn" v-if="!nicknamePass" @click="checkDuplicateMethod('nickname')" :disabled="!nicknameFormat">중복확인</button>
                        <button class="btn yellow-btn" v-else  @click="reset('nickname')">재설정</button>
                    </div>
                    <div style="float: left;" v-if="i == 5">
                        <button class="btn yellow-btn" @click="AuthenticateNumber()" :disabled="!isSendNumber">인증확인</button>
                    </div>
                </div>
            </div>
        </li>

        <div class=" row col-md-10">
            <div class="col-md-2"> </div>
            <div class="row col-md-10" style="text-align:left;">
                <button class="btn yellow-btn col-md-5" @click="join()" :disabled="!joinConfirm">회원가입</button>      
                 <!-- disabled 조건   
                emailFormat: false,
                emailPass: false,
                nicknamePass: false,
                emailConfirm: false, -->
                <div class="col-md-1"></div>
                <button class="btn yellow-btn col-md-5" @click="cancel()">취소</button>
            </div>
        </div>
        
        
    </div>
</template>

<script>
import MainTitle from "../components/MainTitle.vue"
import Inputparam from '../components/Inputparam.vue'
import userApi from "../api/user.js"
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
            isSendNumber : false,
        }
    },
    methods:{
        ...mapActions('user', ['registerUser', 'checkEmail', 'authenticateNumber', 'checkDuplicate']),
        ...mapActions('process',['getSubComplete']),
        async join(){

            if(this.name == "" || this.email == "" || this.nickname == "" || this.password == "" || this.passwordConfirm == ""){
                alert("입력되지 않은 정보가 있습니다.")
                return
            }

            await this.registerUser();
            
        },
        async emailAuthenticate(){
            // this.checkEmail()
            this.isSendNumber= true
        },
        async AuthenticateNumber(){
            this.authenticateNumber()
        },
        async checkDuplicateMethod(type){
            
            let isPass = false
            
            let data = ""
            if(type == "email") data = this.email
            else if(type == "nickname") data = this.nickname
            this.getSubComplete(false);
            await userApi.checkDuplicate(data, type)
            .then((res) => {
                console.log(res)
                if (res.status == 200) isPass = true;
            })
            .catch((err) => {
                console.log(err)
            })

            if(isPass && type == 'email') {
                alert("사용가능한 메일입니다.")
                this.$store.commit('user/SET_JOIN_EMAILPASS', true);
            }
            else if(isPass && type == 'nickname'){
                alert("사용가능한 닉네임입니다.")
                this.$store.commit('user/SET_JOIN_NICKNAMEPASS', true);
            }
            else{
                alert("중복되는 정보가 있습니다.")
            }
            this.getSubComplete(true);
        },
        cancel(){
            this.$router.push({name: 'Login'})
        },
        reset(val){
            this.$store.commit('user/SET_JOIN_'+val.toUpperCase() + 'PASS', false);
        }
    },  
    mounted(){

    },
    created(){
        this.$store.commit("user/SET_JOIN_NAME","")
        this.$store.commit("user/SET_JOIN_USERID", "");
        this.$store.commit("user/SET_JOIN_NAME", "");
        this.$store.commit("user/SET_JOIN_EMAIL", "");
        this.$store.commit("user/SET_JOIN_NICKNAME", "");
        this.$store.commit("user/SET_JOIN_PASSWORD", "");
        this.$store.commit("user/SET_JOIN_PASSWORDCONFIRM", "");
        this.$store.commit("user/SET_JOIN_AUTHNUMBER", "");

        this.$store.commit("user/SET_JOIN_EMAILPASS", false);
        this.$store.commit("user/SET_JOIN_NICKNAMEPASS", false);
        this.$store.commit("user/SET_JOIN_EMAILCONFIRM", false);

        this.$store.commit('user/SET_JOIN_NAMEFORMAT', false);
        this.$store.commit('user/SET_JOIN_NICKNAMEFORMAT', false);
        this.$store.commit('user/SET_JOIN_EMAILFORMAT', false);
        this.$store.commit('user/SET_JOIN_PASSWORDFORMAT', false);
        this.$store.commit('user/SET_JOIN_PASSWORDCONFIRMFORMAT', false);
    },
    computed:{
        ...mapState('user', ['emailFormat','nameFormat','nicknameFormat','passwordFormat','passwordConfirmFormat',
             'nickname', 'email', 'name', 'password', 'passwordConfirm', 'emailPass', 'nicknamePass', 'emailConfirm']),
        joinConfirm(){
            return this.nameFormat && this.emailFormat && this.emailPass && this.nicknamePass && this.emailConfirm
                        && this.passwordFormat && this.passwordConfirmFormat
        },
    },
    watch:{
    }
}
</script>

<style>


</style>