<template>
  <div>
    <section class="guestbookList">
      <div class="sketch">
        <img src="@/assets/image/sketch.png" alt="">
        <p class="guest_title">방명록</p>
        <div class="writer justify-content-center">
          <table class="table table-borderless table-responsive mt-3">
            
            <tbody>
              <tr v-for="(guestitem, index) in column" :key="index" :guestitem="guestitem">
                <td class="nickname col-2">{{guestitem}}</td>
                <td class="guest-content col-6">{{guestitem}}</td>
                <td class="createAt">{{register_time(new Date())}}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="justify-content-center">
        <button @click="openModal('register')" data-bs-toggle="modal" data-bs-target="#FormModal">작성</button>
      </div>
      <FormModal :sign="sign" :content="content" @openModal="openModal"></FormModal>
    </section>
  </div>
</template>

<script>
import moment from 'moment';
import FormModal from '@/components/FormModal.vue'
import { mapGetters,mapActions } from "vuex";
export default {
  components:{
    FormModal
  },
  data(){
    return{
      column : ['nickname','content','createAt'],
      sign : '',
      content: ''
    }
  },
  computed:{
    ...mapGetters("guestbook", ["guestbooks"]),
  },
  methods:{
    ...mapActions('guestbook', ['selectGuestbook','getGuestbooks']),
    openModal(sign,content){
        this.sign = sign
        if(sign=="register") this.content='f'
        // this.selectGuestbook(this.guestbook)
        // this.$emit('openModal', val)
    },
    register_time(val){
        return moment(val).format("yyyy.MM.DD HH:mm")
    },
  },
  created(){
    
  }
}
</script>

<style>

</style>