<template>
    <div class="page">
        <main-title :title="'마이페이지'"></main-title>

        <div class="row">
            <div class="col-md-3">
                <div>
                    <img src="" ref="">
                    캐릭터 이미지 자리
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
                                <button class="btn yellow-btn" @click="checkDuplication()">중복확인</button>
                            </div>
                        </div>
                    </div>
                </li>

                <div class=" row col-md-9">
                    <div class="col-md-3"> </div>
                    <div class="row col-md-9" style="text-align:left;">
                        <button class="btn yellow-btn col-md-5" @click="confirm()" :disabled="!this.nicknamePass">수정하기</button>
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
import EducateListVue from './EducateList.vue'

export default {
    name: "MyPage",
    components:{
        MainTitle,
        Inputparam,
    },
    data(){
        return{
            titles: ["이름", "Email", "닉네임", "기존 비밀번호", "비밀번호 수정", "비밀번호 확인"],
            placeholderDatas: ["이름 자리", "이메일 자리", "닉네임 자리", "Origin Password", "New Password", "Password Confirm"],
            originNickname: "",
        }
    },
    methods:{
        ...mapActions('user', ['getMyInfo', 'checkDuplicate', 'updateInfo']),

        async init(){     
            
            await this.getMyInfo()

            this.$store.commit('user/SET_JOIN_PASSWORD', "")
            this.$store.commit('user/SET_USER_ORIGINPASSWORD', "")
            this.$store.commit('user/SET_USER_NEWPASSWORD', "")
            this.$store.commit('user/SET_JOIN_PASSWORDCONFIRM', "")
            // this.$store.commit('user/SET_JOIN_NICKNAME', "")
            this.$store.commit('user/SET_JOIN_NICKNAMEPASS', false)

            this.originNickname = this.nickname
        },
        async checkDuplication(){
            if(this.nickname == ""){
                alert("닉네임을 입력해주세요.")
                return
            }

            if(this.originNickname == this.nickname){
                this.$store.commit('user/SET_JOIN_NICKNAMEPASS', true)
                alert("기존 닉네임과 동일한 사용 가능한 닉네임입니다.")
                return
            }

            await this.checkDuplicate('nickname')
        },
        async confirm(){
            if(this.originPassword == "") {
                alert("비밀번호를 입력해주세요.")
                return
            }
            if(this.newPassword != this.passwordConfirm){
                alert("비밀번호가 다릅니다.")
                return
            }
            if(!this.nicknamePass) {                        // 이중 체크
                alert("닉네임 중복 확인이 필요합니다.")
                return
            }

            await this.updateInfo()
        },
        cancel(){
            this.$router.go(-1)
        },
        toEducateList(){
            this.$router.push({name: 'EducateList'}).catch(() => {})
        }
    }, 
    async mounted(){
        this.init()
    },
    async created(){
        this.getMyInfo()
        this.placeholderDatas[0] = this.name
        this.placeholderDatas[1] = this.email
        this.placeholderDatas[2] = this.nickname
    },
    computed:{
        ...mapState('user', ['name', 'nickname', 'email', 'nicknamePass', 'originPassword', 'newPassword', 'passwordConfirm'])
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