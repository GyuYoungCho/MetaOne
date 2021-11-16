<template>
  <div class="unityScreen">
    <div class="start">
        <div class="card help" style="max-width: 35rem;">
            <div class="card-body">
            
                <p class="card-text">메뉴바를 통해 다양한 기능을 체험해봐요!</p>
            </div>
        </div>
        <div class="card chatting" style="max-width: 35rem;">
            
            <div class="card-body">
                
                <p class="card-text">방을 선택하고 온라인인 모든 친구들과 함께 채팅해요!</p>
            </div>
        </div>
        <div class="card game" style="max-width: 35rem;">
            
            <div class="card-body">
                
                <p class="card-text">입장 후 캐릭터 및 방을 선택하고 안전 교육을 이수할 수 있어요!</p>
            </div>
        </div>
        <button class="btn yellow-btn" @click="startUnityMap()">시작하기</button>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
    methods:{
        ...mapActions('user',['comTutorial']),
        ...mapActions('process',['getSubComplete']),
        startUnityMap(){
            this.comTutorial()
            this.getSubComplete(true)
            setTimeout(() => {
                this.getSubComplete(false)
            }, 5000);
        }
    }
}
</script>

<style>

</style>