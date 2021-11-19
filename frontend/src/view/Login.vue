<template>
    <div class="user">
        <main-title :title="'위기탈출 메타원'" class="logo-text"></main-title>
        <div class="img_main">
            <img src="@/assets/image/metaone.gif" ref="캐릭터 공간" class="logo-image"/>
        </div>
        <div class="login_form">
            <div class="row">
                <div class="col-md-8">
                    <li class="input-label" v-for="(e, i) in titles" :key="i">
                        <div class="row mb-4" >
                            <div class="col-md-3">
                            </div>
                            <div class="col-md-9">
                                <inputparam :title="e" :placeholderData="placeholderDatas[i]" @tryLogin="tryLogin"></inputparam>
                            </div>
                        </div>
                    </li>
                    <div class="row mb-4">
                        <div class="col-md-3"></div>
                        <button class="col-md-1 not-submit" style="text-align:left; width:100px;" @click="toJoin()">회원가입</button>
                        <button class="col-md-2 not-submit" style="text-align:left; width:150px;" @click="toFindPw()">비밀번호 찾기</button>
                        <div class="col-md-3"></div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="row">
                        <div style="float: left;">
                            <button class="btn yellow-btn" @click="tryLogin()">&nbsp;&nbsp;시작하기&nbsp;&nbsp;</button>
                        </div>
                    </div>
                    <div>
                        <button class="kakao-btn mt-3" @click="kakaoLogin()">
                            <img src="@/assets/image/kakao.png" class="kakao-img" />
                        </button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import Inputparam from "@/components/basic/Inputparam.vue";
import MainTitle from "@/components/basic/MainTitle.vue";
import { mapState, mapActions } from 'vuex'
import BASE from "@/api/client.js";

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
        kakaoLogin(){
            window.Kakao.Auth.authorize({
                redirectUri: BASE.url + '/auth'
            })
        },  
        toJoin(){
            this.$router.push({name: 'Join'}).catch(() => {})
        },
        toFindPw(){
            this.$router.push({name: 'FindPw'}).catch(() => {})
        }
    }, 
    computed:{
        ...mapState('user', ['email', 'password'])
    },
}
</script>

<style>


</style>