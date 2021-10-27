<template>
  <div class="avatar">
    <div class="col-2">
        <i class="menuAddIcon fas fa-envelope" v-if="!message.isRead"></i>
        <i class="menuAddIcon fas fa-envelope-open-text" v-if="message.isRead" ></i>
    </div>
    <div class="col-5 mt-2">
      <p>{{message.title}}</p>
    </div>
  </div>
</template>

<script>
export default {
    props: {
        message : Object,
    },
}
</script>

<style>

</style>