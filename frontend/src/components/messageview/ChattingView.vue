<template>
  <div>
    <section class="Chatting">

      <!-- 채팅 페이지 여는 버튼 -->
      <!-- v-if="unityRoom!=''" 추가하기 -->
      <button class="icon-btn" data-bs-toggle="offcanvas" data-bs-target="#chatting" aria-controls="chatting">
        <i class="far fa-comments fa-5x"></i>
      </button>

      <!-- 새 메시지용 아이콘 -->
      <i class="fas fa-exclamation-circle fa-lg" v-if="newMessageMark"></i>

      <!-- 채팅 전체 영역 -->
      <div class="offcanvas offcanvas-start" tabindex="-1" id="chatting" aria-labelledby="chattingLabel">
        <div class="offcanvas-header">
          <h5 class="offcanvas-title" id="chattingLabel">채팅창</h5>
          <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>

      <!-- 채팅 내용 부분 -->
      <div class="offcanvas-body">
        <div>
          <div class="msg_history">
            <div v-for="(message, index) in messages" :key="index" :message="message">

              <!-- 보내는사람 -->
              <div v-if="isme(message.nickname)" class="incoming_msg">
                <div class="col incoming_info">
                  <div class="row incoming_msg_img"> <img :src="require(`@/assets/image/character/Ch`+ userCha(message.character) +`.png`)" 
                  alt="@/assets/image/character/Ch00.png"> </div>
                  <span class="incoming_name">{{message.nickname}}</span>
                </div>
                <div class="received_msg">
                  <div class="received_withd_msg">
                    <p>{{message.content}}</p>
                    <span class="time_date"> {{register_time(message.sendDate)}}</span>
                  </div>
                </div>
              </div>

              <!-- 내가 보내는거 -->
              <div v-else class="outgoing_msg">
                <div class="sent_msg">
                <p>{{message.content}}</p>
                <span class="time_date"> {{register_time(message.sendDate)}}</span> </div>
              </div>
            </div>
          </div>
        </div>
        <div class="offcanvas-footer">
          <div class="row message_com m_title mt-3">
            <div class="col-md-9">
              <input type="text" @keyup.enter="sendMessage()" class="form-control" v-model="content">
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
import { firebase } from "@/api/firebase.js"
import "firebase/compat/database";
import moment from 'moment';
import { mapActions, mapState } from 'vuex'

export default {
    data(){
        return{
            content:'',
            messages:null,
            newMessageMark:false,
        }
    },
    computed:{
      ...mapState('user', ['nickname']),
      ...mapState('message', ['messSize']),
      ...mapState('unity',['unityRoom','roomid','unityCharacter']),
      ...mapState('process',['allMap','chattingOpen']),

    },
    methods:{
        ...mapActions('message', ['getMessSize']),
        ...mapActions('process', ['getChattingOpen']),

        sendMessage(){
            let today = new Date()
            let ymd = moment(today).format("yyyyMMDD")
            let newData = firebase.database().ref('chats/' + ymd + '/'+this.roomid).push();
            newData.set({
                character : this.unityCharacter,
                nickname: this.nickname,
                content: this.content,
                sendDate: Date()
            }).then(()=>{
                this.scrollToBottom()
            })
            this.content=''
        },

        async fetchMessages(){
            try {
              let today = new Date()
              let ymd = moment(today).format("yyyyMMDD")
              
              await firebase.database().ref('chats/' + ymd + '/'+this.roomid).on('value', (snapshot) => {
                  
                  if (snapshot.exists()) {
                      this.messages = [];
                      snapshot.forEach((doc) => {
                          let item = doc.val()
                          item.key = doc.key
                          this.messages.push(item)
                      });
                      console.log(this.messages)
                      setTimeout(()=>{
                          this.scrollToBottom()
                      },1000)
                  } else {
                      console.log("No data available");
                  }
              })
                
            } catch (e) {
              console.log("catch")
                throw e
            }
        },
        scrollToBottom(){
            let box = document.querySelector('.msg_history');
            box.scrollTop = box.scrollHeight;
        },

        isme(name){
            return name!=this.nickname 
        },
        register_time(val){
            return moment(val).format("hh:mm A")
        },
        chatOpen(){
          this.newMessageMark=false 
          this.getChattingOpen(true)
        },
        chatClose(){
          this.newMessageMark=false
          this.getChattingOpen(false)
        },
        userCha(chaid){
          console.log(chaid)
          if(chaid<10)
            return "0" + chaid
          else return chaid
        }
    },

    async created(){
        this.getChattingOpen(false)
    },
    async mounted(){
      setTimeout(() => {
        this.fetchMessages()
      }, 3000);
      let offcanvas = document.getElementById('chatting')
      offcanvas.addEventListener('show.bs.offcanvas',this.chatOpen )
      offcanvas.addEventListener('hide.bs.offcanvas', this.chatClose)
    },

    watch:{
        messages(val){
          if(val.length>0 && this.messSize!=val.length) {
              this.newMessageMark=true
              this.getMessSize(val.length)
          }
        }
    }
}
</script>

<style>

</style>