<template>
  <div>
    <li class="list-group-item d-flex">
      
        <div class="col-4">
        <img class="avatar avatar-48 bg-transparent rounded-circle text-white p-1 "
            src="@/assets/image/profileExample.png">
        </div>
        <div class="username col-4 mt-2">
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
}
</script>

<style>

</style>