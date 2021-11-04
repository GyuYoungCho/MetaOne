<template>
  <div>
    <li class="list-group-item d-flex">
      <div class="col-2 px-0">
          <i class="menuAddIcon fas fa-envelope" v-if="!message.read"></i>
          <i class="menuAddIcon fas fa-envelope-open-text" v-if="message.read" ></i>
      </div>
      <div class="messagetitle col-10">
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
      
    }
}
</script>

<style>

</style>