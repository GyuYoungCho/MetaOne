<template>
    <div>
        <div class="row sort">
            <div class="col-md-2">{{title}}</div>
            <div v-if="title == '비밀번호' || title == '비밀번호확인'" class="col-md-10">
                <input type="password" class="form-control" v-model="dataIn" @keyup="saveJoinForm()" :placeholder="placeholderData">
            </div>
            <div v-else class="col-md-10">
                <input type="text" class="form-control" v-model="dataIn" @keyup="saveJoinForm()" :placeholder="placeholderData">
            </div>
        </div>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
    name: "Inputparam",
    props: ["title", "placeholderData"],
    data(){
        return{
            keys: ["이름", "Email", "닉네임", "비밀번호", "비밀번호확인", "인증번호"],
            keysMapping: ["name", "email", "nickname", "password", "passwordConfirm", "authNumber"],
            dataIn: "",
        }
    },
    methods:{ 
        ...mapActions('join', ['name', 'email', 'nickname', 'password', 'passwordConfirm', 'authNumber']),
        saveJoinForm(){
            let joinTitle = ""

            if(this.title =='이름') joinTitle = "SET_JOIN_NAME"
            else if(this.title =='Email') joinTitle = "SET_JOIN_EMAIL"
            else if(this.title =='닉네임') joinTitle = "SET_JOIN_NICKNAME"
            else if(this.title =='비밀번호') joinTitle = "SET_JOIN_PASSWORD"
            else if(this.title =='비밀번호확인') joinTitle = "SET_JOIN_PASSWORDCONFIRM"
            else if(this.title =='인증번호') joinTitle = "SET_JOIN_AUTHNUMBER"

            this.$store.commit('join/' + joinTitle, this.dataIn);
        },
    }, 
    mounted(){

    },
    created(){

    },
    computed:{

    },
    watch:{

    }
}
</script>

<style>
.sort{
    /* float: left; */
    text-align: left;
}
</style>