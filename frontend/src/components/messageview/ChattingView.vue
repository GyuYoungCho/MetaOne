<template>
  <div>
    <section class="Chatting">
      <button class="icon-btn" data-bs-toggle="offcanvas" data-bs-target="#chatting" aria-controls="chatting">
        <i class="far fa-comments fa-5x"></i>
      </button>
      
      <div class="offcanvas offcanvas-start" tabindex="-1" id="chatting" aria-labelledby="chattingLabel">
        <div class="offcanvas-header">
          <h5 class="offcanvas-title" id="chattingLabel">채팅창</h5>
          <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
      <div class="offcanvas-body">
        <div>
         
          
        </div>
        <div class="offcanvas-footer">
          <div class="row message_com m_title mt-3">
            <div class="col-md-9">
                <input type="text" class="form-control" v-model="title">
            </div>
            <div class="col-md-2 message_com mt-4 m_submit">
              <button @click="sendMessage()">전송</button>
            </div>
          </div>
        </div>
      </div>
      
    </div>
    </section>
  </div>
</template>

<script>
export default {
    methods:{
        sendMessage(){
            console.log("hi")
        }
    }
}
</script>

<style>

</style>