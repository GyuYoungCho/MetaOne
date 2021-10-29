<template>
    <div class="page">
        <main-title :title="'방 선택'"></main-title>

        <div class="row" style="height: 400px;">
            캐릭터 선택 창
        </div>

        <div class="row">
            <div class="col-md-2"></div>
            <button class="btn yellow-btn col-md-3">생성/선택하기</button>
            <div class="col-md-2"></div>
            <button class="btn yellow-btn col-md-3">취소</button>
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
    }
}
</script>

<style>

</style>