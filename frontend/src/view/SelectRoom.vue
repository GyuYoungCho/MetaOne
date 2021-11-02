<template>
    <div class="page">
        <main-title :title="'방 선택'"></main-title>

        <div class="row" style="height: 400px;">
            방 선택 창
        </div>

        <div class="row">
            <div class="col-md-2"></div>
            <button class="btn yellow-btn col-md-3" @click="confirm()">생성/선택하기</button>
            <div class="col-md-2"></div>
            <button class="btn yellow-btn col-md-3" @click="cancel()">취소</button>
            <div class="col-md-2"></div>
        </div>
    </div>
</template>

<script>
import MainTitle from "../components/MainTitle.vue";

export default {
    name: "SelectRoom",
    components:{
        MainTitle,
    },
    data(){
        return{
            roomIdx: 0,
            
        }
    },
    methods:{
        confirm(){
            // 사람이 있는 방을 택했을 때


            // 빈 방을 택했을 때
            this.$router.push({name: 'SettingRoom'})
        },
        cancel(){
            this.$router.push({name: 'SelectCharacter'})
        },
    }
}
</script>

<style>

</style>