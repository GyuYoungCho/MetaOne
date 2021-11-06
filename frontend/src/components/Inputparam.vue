<template>
    <div>
        <div class="row sort">
            <div class="col-md-2">{{title}}</div>
            <div v-if="title == '비밀번호' || title == '비밀번호확인'" class="col-md-10">
                <input type="password" class="form-control" v-on:input="typing" v-model="dataIn" @keyup="saveJoinForm()" @keyup.enter="tryProcess()" :placeholder="placeholderData">
                    <p class="invalidTxt" v-if="title == '비밀번호' && $route.name == 'Join'">
                        {{passwordContent}}
                    </p>
                    <p class="invalidTxt" v-if="title == '비밀번호확인'">
                        {{passwordConfirmContent}}
                    </p>
            </div>
            <div v-else class="col-md-10">
                <input type="text" class="form-control" v-on:input="typing" v-model="dataIn" @keyup="saveJoinForm()" @keyup.enter="tryProcess()" 
                                 :placeholder="placeholderData">
                    <p class="invalidTxt" v-if="title == '이름'">
                        {{nameContent}}
                    </p>
                    <p class="invalidTxt" v-else-if="$route.name == 'Join' && title == 'Email'">
                        {{emailContent}}
                    </p>
                    <p class="invalidTxt" v-else-if="title == '닉네임'">
                        {{nicknameContent}}
                    </p>
            </div>
        </div>
    </div>
</template>

<script>
import { mapState } from "vuex";

export default {
    name: "Inputparam",
    props:{
        title : String,
        placeholderData : String,
    },
    data(){
        return{
            dataIn: "",
            nameContent:"",
            emailContent:"",
            nicknameContent:"",
            passwordContent:"",
            passwordConfirmContent:"",
        }
    },
    methods:{ 
        saveJoinForm(){
            let joinTitle = ""    
            if(this.title == 'Email') this.$store.commit('user/SET_JOIN_EMAILPASS', false)
            if(this.title == '닉네임') this.$store.commit('user/SET_JOIN_NICKNAMEPASS', false)
           

            if(this.title =='이름') joinTitle = "SET_JOIN_NAME"
            else if(this.title =='Email') joinTitle = "SET_JOIN_EMAIL"
            else if(this.title =='닉네임') joinTitle = "SET_JOIN_NICKNAME"
            else if(this.title =='비밀번호') joinTitle = "SET_JOIN_PASSWORD"
            else if(this.title =='비밀번호확인') joinTitle = "SET_JOIN_PASSWORDCONFIRM"
            else if(this.title =='인증번호') joinTitle = "SET_JOIN_AUTHNUMBER"

            else if(this.title =='기존 비밀번호') joinTitle = "SET_USER_ORIGINPASSWORD"
            else if(this.title =='비밀번호 수정') joinTitle = "SET_USER_NEWPASSWORD"
            else if(this.title =='비밀번호 확인') joinTitle = "SET_JOIN_PASSWORDCONFIRM"


            this.$store.commit('user/' + joinTitle, this.dataIn);
        },
        typing:function(e){
            this.dataIn = e.target.value;               // 한글 입력 대응
        },

        tryProcess(){
            if(this.$route.name=="Login"){  
                this.$emit('tryLogin');
            }
        },
    }, 
    mounted(){

    },
    created(){

    },
    computed:{
        ...mapState('user', ['name', 'email', 'nickname', 'password', 'passwordConfirm', 'authNumber']),
        nameFormValid() {
            return !this.dataIn || /^[가-힣]+$/.test(this.dataIn) || /^[a-zA-Z]+$/.test(this.dataIn)
        },
        nicknameFormValid () {
            return !this.dataIn || (this.dataIn.length < 7 && /^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$/.test(this.dataIn))
        },
        emailFormValid () {
            return !this.dataIn || /^[A-Za-z0-9_]+@[A-Za-z0-9]+\.[A-Za-z0-9]+/.test(this.dataIn)
        },
        passwordFormValid () {
            return !this.dataIn || (this.dataIn.length > 7 && /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d$@$!%*#?&]{8,20}$/.test(this.dataIn))
        },
        passwordCheckFormValid () {
            return !this.dataIn || (this.password===this.dataIn)
        },
    },
    watch:{
        dataIn(){
            // let emailtext = /^[A-Za-z0-9_]+@[A-Za-z0-9]+\.[A-Za-z0-9]+/
            // let passtext = /^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$/

            if(this.title == '이름' && this.nameFormValid){
                this.nameContent = "";
                if(this.dataIn) this.$store.commit('user/SET_JOIN_NAMEFORMAT', true);
            }
            else if(this.title == '이름'){
                this.nameContent = "이름은 한글이나 영어로만 할 수 있습니다."
                this.$store.commit('user/SET_JOIN_NAMEFORMAT', false);
            }

            if(this.title == '닉네임' && this.nicknameFormValid){
                this.nicknameContent = "";
                if(this.dataIn) this.$store.commit('user/SET_JOIN_NICKNAMEFORMAT', true);
            }
            else if(this.title == '닉네임'){
                this.nicknameContent = "영문 한글 숫자 6자 이내로 해주세요"
                this.$store.commit('user/SET_JOIN_NICKNAMEFORMAT', false);
            }


            if(this.title == 'Email' && this.emailFormValid){
                this.emailContent = "";
                if(this.dataIn) this.$store.commit('user/SET_JOIN_EMAILFORMAT', true);
            }
            else if(this.title == 'Email'){
                this.emailContent = "이메일 형식이 올바르지 않습니다."
                this.$store.commit('user/SET_JOIN_EMAILFORMAT', false);
            }

            if(this.title == '비밀번호' && this.passwordFormValid){
                this.passwordContent = "";
                if(this.dataIn) this.$store.commit('user/SET_JOIN_PASSWORDFORMAT', true);
            }
            else if(this.title == '비밀번호'){
                this.passwordContent = "영문,숫자 포함 8-20자로 해주세요"
                this.$store.commit('user/SET_JOIN_PASSWORDFORMAT', false);
            }
            
            if(this.title == '비밀번호확인' && this.passwordCheckFormValid){
                this.passwordConfirmContent = "";
                if(this.dataIn) this.$store.commit('user/SET_JOIN_PASSWORDCONFIRMFORMAT', true);
            }
            else if(this.title == '비밀번호확인'){
                this.passwordConfirmContent = "비밀번호와 일치하지 않습니다"
                this.$store.commit('user/SET_JOIN_PASSWORDCONFIRMFORMAT', false);
            }
        },
    }
}
</script>

<style>
.sort{
    /* float: left; */
    text-align: left;
}
</style>