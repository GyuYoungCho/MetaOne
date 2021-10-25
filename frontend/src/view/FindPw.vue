<template>
    <div class="page">
        <div class="top-padding">

        </div>
        <li class="input-label" v-for="(e, i) in titles" :key="i">
            <div class="row mb-4 pb-2">
                <div class="col-md-8">
                    <inputparam :title="e" :placeholderData="placeholderDatas[i]"></inputparam>
                </div>
                <div class="col-md-4">
                </div>
            </div>
        </li>
        <div style="height:200px;">

        </div>

        <div class=" row col-md-9">
            <div class="col-md-2"> </div>
            <div class="row col-md-10" style="text-align:left;">
                <button class="btn yellow-btn col-md-5">확인하기</button>
                <div class="col-md-1"></div>
                <button class="btn yellow-btn col-md-5">취소</button>
            </div>
        </div>
        
        
    </div>
</template>

<script>
import Inputparam from '../components/Inputparam.vue'
export default {
    name: "FindPw",
    components:{
        Inputparam,
    },
    data(){
        return{
            titles: ["이름", "Email"],
            placeholderDatas: ["바이든", "email@email.com"],

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