<template>
    <div class="user">
        <main-title :title="'캐릭터 선택'"></main-title>

        <div class="row" style="height: 400px;">
            캐릭터 선택 창
        </div>

        <div class="row">
            <div class="col-md-2"></div>
            <button class="btn yellow-btn col-md-3" @click="confirm()">선택하기</button>
            <div class="col-md-2"></div>
            <button class="btn yellow-btn col-md-3" @click="cancel()">취소</button>
            <div class="col-md-2"></div>
        </div>
    </div>
</template>

<script>
import MainTitle from "../components/MainTitle.vue";

export default {
    name: "SelectCharacter",
    components:{
        MainTitle,
    },
    methods:{
        confirm(){
            this.$router.push({name: 'SelectRoom'})
        },
        cancel(){
            this.$router.push({name: 'MyPage'})
        },
    }
}
</script>

<style>

</style>