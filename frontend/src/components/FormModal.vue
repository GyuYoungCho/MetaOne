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
              <textarea class="form-content" v-model="selectguestbook.content"></textarea>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            <button type="button" class="btn btn-primary" v-if="buttontype" @click="registerGuestbook(selectguestbook.content)" data-bs-dismiss="modal">작성</button>
            <button type="button" class="btn btn-primary" v-if="!buttontype" @click="modifyGuestbook(selectguestbook)" data-bs-dismiss="modal">수정</button>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>
</template>

<script>
import { mapGetters,mapActions } from "vuex";
import guestbookAPI from "@/api/guestbook.js";
import moment from 'moment';

export default {
  data(){
    return{
      content : ''
    }
  },
  props:{
      sign : String,
  },
  computed:{
    ...mapGetters("guestbook", ["selectguestbook"]),
    buttontype(){
      return this.sign=="register"
    },
  },
  methods:{
    ...mapActions('guestbook', ['selectGuestbook','getGuestbooks']),
    async registerGuestbook(val){
      await guestbookAPI
        .regist(val)
        .then((res) => {
          console.log(res.data)
        })
        .catch((error) => {
          alert("못가져옴");
          console.log(error);
        });
      let today = moment(new Date()).format("yyyy-MM-DD")
      await this.getGuestbooks(today)
    },
    async modifyGuestbook(val){
      await guestbookAPI
        .modify(val)
        .then((res) => {
          console.log(res.data)
        })
        .catch((error) => {
          alert("못가져옴");
          console.log(error);
        });
    },
  },
};
</script>

<style>
    
</style>