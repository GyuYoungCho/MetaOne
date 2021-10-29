<template>
    <div class="page">
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
                        <button class="btn yellow-btn">&nbsp;&nbsp;&nbsp;&nbsp;시작하기&nbsp;&nbsp;&nbsp;&nbsp;</button>
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
            <button class="col-md-1" style="text-align:left; color:white;">회원가입</button>
            <button class="col-md-1" style="text-align:left; color:white;">비밀번호 찾기</button>
        </div>
    </div>
</template>

<script>
import Inputparam from '../components/Inputparam.vue'
import MainTitle from '../components/MainTitle.vue'

export default {
    name: "Login",
    components:{
        MainTitle,
        Inputparam,
    },
    data(){
        return{
            titles: ["Email", "Password"],
            placeholderDatas: ["Email", "Password"],

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
.page{
    /* background-color: rgba(154, 69, 235, 0.8); */
    color: white;
}

.input-label{
    list-style-type: none;
}

.img_main{
    width: 50%;
    height: 30%;
}
</style>