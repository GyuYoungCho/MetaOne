<template>
  <section class="MessageList">
    <div class="m_title">
      <p>접속자 목록</p>
    </div>
    <div class="m-contain overflow-auto">
        <ul class="list-group">
            <AvatarCard  v-for="(user, index) in onlinelist" :key="index" :user="user"/>
        </ul>
    </div>
    
  </section>
</template>

<script>
import AvatarCard from "@/components/AvatarCard.vue"
export default {
    components:{
        AvatarCard
    },
    data(){
        return{
            onlinelist:[
                {name:'KIM'},{name:'KWON'},{name:'JO'}
            ]
        }
    }
}
</script>

<style>

</style>