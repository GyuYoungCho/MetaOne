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
                    <button class="btn yellow-btn" style="float: center;">교육내역 확인하기</button>
                </div>
            </div>
            <div class="col-md-9">
                <li class="input-label" v-for="(e, i) in titles" :key="i">
                    <div class="row mb-4 pb-2">
                        <div v-if=" i < 2" class="row col-md-9">
                            <div class="col-md-2">{{e}}</div>
                            <div class="col-md-10">
                                {{i}}
                            </div>
                        </div>
                        <div v-else class="col-md-9">
                            <inputparam :title="e" :placeholderData="placeholderDatas[i]" v-on:data="dataIn"></inputparam>
                        </div>
                        <div class="col-md-3">
                            <div style="float: left;" v-if="i == 2">
                                <button class="btn yellow-btn">중복확인</button>
                            </div>
                        </div>
                    </div>
                </li>

                <div class=" row col-md-9">
                    <div class="col-md-3"> </div>
                    <div class="row col-md-9" style="text-align:left;">
                        <button class="btn yellow-btn col-md-5">수정하기</button>
                        <div class="col-md-1"></div>
                        <button class="btn yellow-btn col-md-5">취소</button>
                    </div>
                </div>
            </div>
        </div>
        
    </div>
</template>

<script>
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
            titles: ["이름", "Email", "닉네임", "기존 비밀번호", "비밀번호 수정", "비밀번호 확인"],
            placeholderDatas: ["뽀로로", "email@email.com", "메타몽", "Origin Password", "New Password", "Password Confirm"],
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
    padding-right: 10%;
}

.input-label{
    list-style-type: none;
}

.top-padding{
    padding-top: 200px;
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