<template>
  <div>
    <li class="list-group-item d-flex">
      <div class="col-2 px-0">
          <i class="menuAddIcon fas fa-envelope" v-if="!message.isRead"></i>
          <i class="menuAddIcon fas fa-envelope-open-text" v-if="message.isRead" ></i>
      </div>
      <div class="messagetitle col-4 mt-2 ml-2"
      @click="confirmMessage(message)">
        <span>{{message.title}}</span>
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
      confirmMessage(mm){
        console.log(mm)
      }
    }
}
</script>

<style>

</style>