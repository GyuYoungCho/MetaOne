<template>
    <div class="page user">
        <main-title :title="'마이페이지'"></main-title>

        <div class="row mypage">
            <div class="col-md-2 profile">
                <div>
                    <img :src="require(`@/assets/image/character/Ch${userCha}.png`)" ref="">
                </div>
                <div>
                    <button class="btn yellow-btn" style="float: center;" @click="toEducateList()">교육내역 확인하기</button>
                </div>
            </div>
            <div class="col-md-9">
                <li class="input-label" v-for="(e, i) in titles" :key="i">
                    <div class="row mb-4 pb-2">
                        <div v-if=" i < 2" class="row col-md-9">
                            <div class="col-md-2">{{e}}</div>
                            <div class="col-md-10">
                                {{placeholderDatas[i]}}
                            </div>
                        </div>
                        <div v-else class="col-md-9">
                            <inputparam :title="e" :placeholderData="placeholderDatas[i]"></inputparam>
                        </div>
                        <div class="col-md-3">
                            <div style="float: left;" v-if="i == 2">
                                <button class="btn yellow-btn" v-if="!nicknamePass" @click="checkDuplication()" :disabled="!nicknameFormat">중복확인</button>
                                <button class="btn yellow-btn" v-else @click="reset()">재설정</button>
                            </div>
                        </div>
                    </div>
                </li>

                <div class=" row col-md-10">
                    <div class="col-md-2"> </div>
                    <div class="row col-md-10" style="text-align:left;">
                        <button class="btn col-md-5" @click="confirm()" :disabled="writeAllInfo">수정하기</button>
                        <div class="col-md-1"></div>
                        <button class="btn yellow-btn col-md-5" @click="cancel()">취소</button>
                    </div>
                </div>
            </div>
        </div>
        
    </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import Inputparam from '../components/Inputparam.vue'
import MainTitle from '../components/MainTitle.vue'

export default {
    name: "MyPage",
    components:{
        MainTitle,
        Inputparam,
    },
    data(){
        return{
            myinfotitles: ["이름", "Email", "닉네임", "기존 비밀번호", "비밀번호 수정", "비밀번호 확인"],
            kakaotitles: ["이름", "Email", "닉네임"],
            placeholderDatas: ["이름 자리", "이메일 자리", "닉네임 자리", "Origin Password", "New Password", "Password Confirm"],
            originNickname: "",
        }
    },
    methods:{
        ...mapActions('user', ['getMyInfo', 'checkDuplicate', 'updateInfo','updateNickname']),

        async init(){     
            
            await this.getMyInfo()

            this.$store.commit('user/SET_JOIN_PASSWORD', "")
            this.$store.commit('user/SET_USER_ORIGINPASSWORD', "")
            this.$store.commit('user/SET_USER_NEWPASSWORD', "")
            this.$store.commit('user/SET_JOIN_PASSWORDCONFIRM', "")
            // this.$store.commit('user/SET_JOIN_NICKNAME', "")
            this.$store.commit('user/SET_JOIN_NICKNAMEPASS', false);
            this.$store.commit('user/SET_JOIN_NICKNAMEFORMAT', true);
            this.$store.commit('user/SET_JOIN_PASSWORDFORMAT', false);
            this.$store.commit('user/SET_JOIN_PASSWORDCONFIRMFORMAT', false);
            
            this.originNickname = this.nickname

            const target = document.querySelector('.input-label:nth-child(3) .form-control')
            target.value = this.nickname
        },
        async checkDuplication(){

            if(this.originNickname == this.nickname){
                this.$store.commit('user/SET_JOIN_NICKNAMEPASS', true)
                alert("기존 닉네임과 동일한 사용 가능한 닉네임입니다.")
                const target = document.querySelector('.input-label:nth-child(3) .form-control')
                target.disabled = true
                return
            }

            await this.checkDuplicate('nickname')

            if(this.nicknamePass){
                const target = document.querySelector('.input-label:nth-child(3) .form-control')
                target.disabled = true
            }
        },
        async confirm(){
            if(!this.isKakaoLogin && this.originPassword == "") {
                alert("비밀번호를 입력해주세요.")
                return
            }
            if(!this.isKakaoLogin && !this.passwordConfirm){
                alert("비밀번호가 다릅니다.")
                return
            }
            if(!this.nicknamePass) {
                alert("닉네임 중복 확인이 필요합니다.")
                return
            }
            
            if(this.isKakaoLogin){
                await this.updateNickname()
            }
            else{
                await this.updateInfo()
            }
        },
        cancel(){
            this.$router.go(-1)
        },
        toEducateList(){
            this.$router.push({name: 'EducateList'}).catch(() => {})
        },
        reset(){
            const target = document.querySelector('.input-label:nth-child(3) .form-control')
            target.disabled = false
            this.$store.commit('user/SET_JOIN_NICKNAMEPASS', false);
        }
    }, 
    async mounted(){
        this.init()
    },
    created(){
        this.getMyInfo()
        this.placeholderDatas[0] = this.name
        this.placeholderDatas[1] = this.email
    },
    computed:{
        ...mapState('user', ['name', 'nickname', 'email', 'nicknamePass', 'originPassword', 'newPassword', 'passwordConfirm',
                            'nicknameFormat','passwordFormat','passwordConfirmFormat', 'isKakaoLogin','characid']),
        writeAllInfo(){
            if(this.isKakaoLogin) return !this.nicknamePass
            else return !this.nicknamePass || !this.passwordConfirmFormat
        },
        titles(){
            if(this.isKakaoLogin) return this.kakaotitles
            else return this.myinfotitles
        },

        userCha(){
            if(!this.characid) return "00"
            if(this.characid<10) return "0" + new String(this.characid)
            else return this.characid
        }
    },
    watch:{

    }
}
</script>

<style>
</style>