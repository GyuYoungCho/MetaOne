// 온라인 목록 보여줄 카드

<template>
  <div>
    <li class="list-group-item d-flex">
      
        <div class="col-4">
        <img class="avatar avatar-48 bg-transparent rounded-circle text-white p-1 "
            :src= "require(`@/assets/image/character/Ch${userCha}.png`)">
        </div>
        <div class="username col-8 mt-2">
          <span>{{user.nickname}}</span>
        </div>
      
    </li>
  </div>
</template>

<script>
export default {
    props: {
        user : Object,
    },
    computed:{
      userCha(){
        if(this.user.characid<10)
          return "0" + this.user.characid
        else return this.user.characid
      }
    }
}
</script>

<style>

</style>