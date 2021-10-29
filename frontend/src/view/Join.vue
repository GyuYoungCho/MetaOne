<template>
    <div class="page">
        <div class="top-padding">

        </div>

        <li class="input-label" v-for="(e, i) in titles" :key="i">
            <div class="row mb-4 pb-2">
                <div class="col-md-8">
                    <inputparam :title="e" :placeholderData="placeholderDatas[i]" v-on:data="dataIn"></inputparam>
                </div>
                <div class="col-md-4">
                    <div style="float: left;" v-if="i == 1">
                        <button class="btn yellow-btn" style="margin-right: 20px;">중복확인</button>
                        <button class="btn yellow-btn">인증</button>
                    </div>
                    <div style="float: left;" v-if="i == 2">
                        <button class="btn yellow-btn">중복확인</button>
                    </div>
                    <div style="float: left;" v-if="i == 5">
                        <button class="btn yellow-btn">인증확인</button>
                    </div>
                </div>
            </div>
        </li>

        <div class=" row col-md-9">
            <div class="col-md-2"> </div>
            <div class="row col-md-10" style="text-align:left;">
                <button class="btn yellow-btn col-md-5">회원가입</button>
                <div class="col-md-1"></div>
                <button class="btn yellow-btn col-md-5">취소</button>
            </div>
        </div>
        
        
    </div>
</template>

<script>
import Inputparam from '../components/Inputparam.vue'
export default {
    name: "Join",
    components:{
        Inputparam,
    },
    data(){
        return{
            titles: ["이름", "Email", "닉네임", "비밀번호", "비밀번호확인", "인증번호"],
            placeholderDatas: ["뽀로로", "email@email.com", "메타몽", "Password", "Password Confirm", "이메일 인증번호"],
        }
    },
    methods:{

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
@page {
    size: 4in 6in landscape;
}

.page{
    /* background-color: rgba(154, 69, 235, 0.8); */
    color: white;
    padding-left: 10%;
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