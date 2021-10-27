<template>
  <div class="avatar mt-4">
    <li class="list-group-item d-flex justify-content-between align-items-start">
        <div class="row">
        <div class="col-8">
        <img class="avatar avatar-48 bg-light rounded-circle text-white p-1 "
            src="@/assets/image/profileExample.png">
        </div>
        <div class="col-4 mt-2">
          <span>{{user.name}}</span>
        </div>
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