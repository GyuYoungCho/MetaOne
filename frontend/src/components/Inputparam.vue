<template>
    <div>
        <div class="row sort">
            <div class="col-md-2">{{title}}</div>
            <div v-if="title == '비밀번호' || title == '비밀번호확인'" class="col-md-10">
                <input type="password" class="form-control" v-on:input="typing" v-model="dataIn" @keyup="saveJoinForm()" :placeholder="placeholderData">
            </div>
            <div v-else class="col-md-10">
                <input type="text" class="form-control" v-on:input="typing" v-model="dataIn" @keyup="saveJoinForm()" :placeholder="placeholderData">
            </div>
        </div>
    </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
    name: "Inputparam",
    props: ["title", "placeholderData"],
    data(){
        return{
            dataIn: null,
        }
    },
    methods:{ 
        saveJoinForm(){
            let joinTitle = ""
            
            this.$store.commit('join/SET_JOIN_EMAILPASS', false)
            this.$store.commit('join/SET_JOIN_NICKNAMEPASS', false)

            if(this.title =='이름') joinTitle = "SET_JOIN_NAME"
            else if(this.title =='Email') joinTitle = "SET_JOIN_EMAIL"
            else if(this.title =='닉네임') joinTitle = "SET_JOIN_NICKNAME"
            else if(this.title =='비밀번호') joinTitle = "SET_JOIN_PASSWORD"
            else if(this.title =='비밀번호확인') joinTitle = "SET_JOIN_PASSWORDCONFIRM"
            else if(this.title =='인증번호') joinTitle = "SET_JOIN_AUTHNUMBER"

            this.$store.commit('join/' + joinTitle, this.dataIn);
        },
        typing:function(e){
            this.dataIn = e.target.value;
        },
    }, 
    mounted(){

    },
    created(){

    },
    computed:{
        ...mapState('join', ['name', 'email', 'nickname', 'password', 'passwordConfirm', 'authNumber']),
    },
    watch:{
        dataIn(){
            var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/

            if(this.title == 'Email' && exptext.test(this.dataIn)){
                console.log("이메일형식이 올바릅니다.");
                this.$store.commit('join/SET_JOIN_EMAILFORMAT', true);
            }
            else if(this.title == 'Email'){
                console.log("이메일형식이 올바르지 않습니다.");
                this.$store.commit('join/SET_JOIN_EMAILFORMAT', false);
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