<template>
  <div class="avatar mt-4">
    <li class="list-group-item d-flex">
      <div class="row justify-content-end">
        <div class="col-2">
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