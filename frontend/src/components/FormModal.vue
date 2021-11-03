<template>
<div>
  <section class="FormModal">
    <div class="modal fade" id="FormModal" tabindex="-1" aria-labelledby="FormModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="FormModalLabel">방명록</h5>
          </div>
          <div class="modal-body">
            <form>
              <textarea class="form-content" v-model="content"></textarea>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            <button type="button" class="btn btn-primary" v-if="buttontype">작성</button>
            <button type="button" class="btn btn-primary" v-if="!buttontype">수정</button>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>
</template>

<script>
import guestbookAPI from "@/api/guestbook.js"

export default {
  data(){
    return{
      notuse : ''
    }
  },
  props:{
      sign : String,
      content : String,
  },
  computed:{
    buttontype(){
      return this.sign=="register"
    }
  },
  methods:{
    
    async registerGuestbook(){
      await guestbookAPI
        .regist(this.content)
        .then((res) => {
          console.log(res.data)
        })
        .catch((error) => {
          alert("못가져옴");
          console.log(error);
        });
    },
    async modifyGuestbook(){
      await guestbookAPI
        .modify(this.content)
        .then((res) => {
          console.log(res.data)
        })
        .catch((error) => {
          alert("못가져옴");
          console.log(error);
        });
    },
  },
  watch:{
    
  }
};
</script>

<style>
    
</style>