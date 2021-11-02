<template>
    <div class="page">
        <main-title :title="'방 설정'"></main-title>

        <div class="row">
            <div class="col-md-2">
                
            </div>
            <div class="col-md-6">
                <inputparam class="mb-4" :title="'방 이름'"></inputparam>
                <inputparam class="mb-4" :title="'최대 인원'" :placeholderData="'4인 이하'"></inputparam>
            </div>
            <div class="col-md-4">
                맵 이미지
            </div>
        </div>
        

        <div class="row">
            <div class="col-md-2"></div>
            <button class="btn yellow-btn col-md-3" @click="confirm()">생성완료</button>
            <div class="col-md-2"></div>
            <button class="btn yellow-btn col-md-3" @click="cancel()">취소</button>
            <div class="col-md-2"></div>
        </div>
    </div>
</template>

<script>
import Inputparam from "../components/Inputparam.vue";
import MainTitle from "../components/MainTitle.vue";

export default {
    name: "SettingRoom",
    components:{
        MainTitle,
        Inputparam,
    },
    methods:{
        confirm(){
            // Unity 맵 입장

            
        },
        cancel(){
            this.$router.push({name: 'SelectRoom'})
        },
    }
}
</script>

<style>

</style>