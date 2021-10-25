<template>
    <div class="page">
        
        <li class="input-label" v-for="(e, i) in titles" :key="i">
            <div class="row mb-4">
                <div class="col-md-8">
                    <inputparam :title="e" :placeholderData="placeholderDatas[i]"></inputparam>
                </div>
                <div class="col-md-4">
                </div>
            </div>
            
        </li>
        
    </div>
</template>

<script>
import Inputparam from '../components/Inputparam.vue'
export default {
    name: "Login",
    components:{
        Inputparam,
    },
    data(){
        return{
            titles: ["이름", "Email", "닉네임", "비밀번호", "비밀번호확인", "인증번호"],
            placeholderDatas: ["바이든", "email@email.com", "메타몽", "Password", "Password Confirm", "이메일 인증번호"],

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
</style>