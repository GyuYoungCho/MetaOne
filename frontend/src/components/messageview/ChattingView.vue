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
         <div class="msg_history">
            <div v-for="(message, index) in messages" :key="index" :message="message">
              <div v-if="isme(message.name)" class="incoming_msg">
              <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                <div class="received_msg">
                    <div class="received_withd_msg">
                    <p>{{message.content}}</p>
                    <span class="time_date"> 11:01 AM    |    June 9</span>
                    </div>
                </div>
              </div>
              <div v-else class="outgoing_msg">
                <div class="sent_msg">
                <p>message.content</p>
                <span class="time_date"> 11:01 AM    |    June 9</span> </div>
              </div>
            </div>


            <div class="incoming_msg">
              <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
              <div class="received_msg">
                <div class="received_withd_msg">
                  <p>Test, which is a new approach to have</p>
                  <span class="time_date"> 11:01 AM    |    Yesterday</span></div>
              </div>
            </div>
            <div class="outgoing_msg">
              <div class="sent_msg">
                <p>Apollo University, Delhi, India Test</p>
                <span class="time_date"> 11:01 AM    |    Today</span> </div>
            </div>
            <div class="incoming_msg">
              <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
              <div class="received_msg">
                <div class="received_withd_msg">
                  <p>We work directly with our designers and suppliers,
                    and sell direct to you, which means quality, exclusive
                    products, at a price anyone can afford.</p>
                  <span class="time_date"> 11:01 AM    |    Today</span></div>
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
import { getDatabase, ref, set, get, child, query,orderByChild } from "firebase/database";
export default {
    data(){
        return{
            content:'',
            name:'jo',
            messages:null,
        }
    },
    computed:{
    },
    methods:{
        sendMessage(){
            const db = getDatabase();
            set(ref(db, 'users/' + this.name), {
                username: this.name,
                content:this.content,
                createdAt : String(new Date()),
                title:"들어가라"
            });
            this.content=''
        },

        fetchMessages(){
            try {
                const db = getDatabase()
                const dbRef = query(ref(db, 'users'), orderByChild('createdAt'));
                
                get(dbRef).then((snapshot) => {
                // get(query(ref(dbRef, 'users/' + this.name), orderByChild('createAt'))).then((snapshot) => {
                    if (snapshot.exists()) {
                        const messages = snapshot.val()
                        this.messages = messages
                        console.log(messages)
                    } else {
                        console.log("No data available");
                    }
                }).catch((error) => {
                    console.error(error);
                });
            } catch (e) {
                throw e
            }
        },

        isme(name){
            return name=='jo' 
        }
    },

    created(){
        this.fetchMessages()
        console.log(this.messages)
    }
}
</script>

<style>

</style>