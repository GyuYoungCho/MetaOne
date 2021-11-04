// 상대방과 나의 메세지 내역

<template>
  <div>
    <li class="list-group-item d-flex">
      <div class="enve-icon col-2 px-0">
          <i class="menuAddIcon fas fa-envelope" v-if="!message.whose && !message.read"></i>
          <i class="menuAddIcon fas fa-envelope-open-text" v-if="!message.whose && message.read" ></i>
      </div>
      <div class="onesmessagetitle col-10 ml-2">
        <span v-if="!message.whose">{{message.title}}</span>
        <span v-else class="myspan">{{message.title}}</span>
      </div>
    </li>
  </div>
</template>

<script>

export default {
    props: {
        message : Object,
    },
    methods:{
      
    }
}
</script>

<style>

</style>