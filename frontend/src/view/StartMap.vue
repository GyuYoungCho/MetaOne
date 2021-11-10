<template>
    <div class="user">
        <div class="row start">
            <div class="col-md-2"></div>
            <button class="btn yellow-btn col-md-3" @click="goUnityMap()">시작하기</button>
            <div class="col-md-2"></div>
            <button class="btn yellow-btn col-md-3" @click="goMypage()">마이페이지</button>
            <div class="col-md-2"></div>
        </div>
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
    name: "StartMap",
    computed:{
        ...mapGetters('process',['subComplete'])
    },
    methods:{
        ...mapActions('process',['getSubComplete']),
        goUnityMap(){
            this.getSubComplete(false)
            this.$router.push({name: 'UnityMap'})
        },
        goMypage(){
            this.getSubComplete(false)
            this.$router.push({name: 'MyPage'})
        },
    },
    created(){
        this.getSubComplete(true)
    }
}
</script>

<style>

</style>