<template>
<div>
  <section class="ConfirmModal">
    <div class="modal fade" id="ConfirmModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <form>
              <textarea class="form-content" v-model="contentBody"></textarea>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">확인</button>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>
</template>

<script>

export default {
  props:{
      contentBody : String,
  },
};
</script>

<style>
    
</style>