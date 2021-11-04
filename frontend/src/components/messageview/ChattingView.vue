<template>
  <div>
    <section class="Chatting">
      <button class="icon-btn" data-bs-toggle="offcanvas" data-bs-target="#chatting" aria-controls="chatting"
            @click="newMessageMark=false">
        <i class="far fa-comments fa-5x"></i>
      </button>
      <i class="fas fa-exclamation-circle fa-lg" v-if="newMessageMark"></i>
      <div class="offcanvas offcanvas-start" tabindex="-1" id="chatting" aria-labelledby="chattingLabel">
        <div class="offcanvas-header">
          <h5 class="offcanvas-title" id="chattingLabel">채팅창</h5>
          <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
      <div class="offcanvas-body">
        <div>
         <div class="msg_history">
          <div class="incoming_msg">
            <div class="col incoming_info">
              <div class="row incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
              <span class="incoming_name">아ff</span>
            </div>
            <div class="received_msg">
              <div class="received_withd_msg">
                <p>Test, which is a new approach to have</p>
                <span class="time_date"> 11:01 AM    |    Yesterday</span></div>
            </div>
          </div>
            
            <div v-for="(message, index) in messages" :key="index" :message="message">
              <div v-if="isme(message.nickname)" class="incoming_msg">
                <div class="col incoming_info">
                  <div class="row incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                  <span class="incoming_name">{{message.nickname}}</span>
                </div>
                  <div class="received_msg">
                    <div class="received_withd_msg">
                    <p>{{message.content}}</p>
                    <span class="time_date"> {{register_time(message.sendDate)}}</span>
                  </div>
                </div>
              </div>
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
            roomid:1,
            newMessageMark:false,
        }
    },
    computed:{
      ...mapState('user', ['nickname']),
      ...mapState('message', ['messSize'])
    },
    methods:{
        ...mapActions('message', ['getMessSize']),
        sendMessage(){
            let today = new Date()
            let ymd = moment(today).format("yyyyMMDD")
            let newData = firebase.database().ref(ymd + '/'+this.roomid+'/chats').push();
            newData.set({
                nickname: this.nickname,
                content: this.content,
                sendDate: Date()
            }).then(()=>{
                this.scrollToBottom()
            })
            this.content=''
        },

        fetchMessages(){
            try {
              let today = new Date()
              let ymd = moment(today).format("yyyyMMDD")
              firebase.database().ref(ymd + '/'+ this.roomid+'/chats').on('value', (snapshot) => {
                  
                  if (snapshot.exists()) {
                      this.messages = [];
                      snapshot.forEach((doc) => {
                          let item = doc.val()
                          item.key = doc.key
                          this.messages.push(item)
                      });
                      
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
        
    },

    created(){
        this.fetchMessages()
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