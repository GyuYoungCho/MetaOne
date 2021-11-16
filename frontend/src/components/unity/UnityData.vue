<template>
    <div id="unity-data">
        <input id="unity-character" style="display:none" v-model="unityCharacter" >
        <input id="unity-room" style="display:none" v-model="unityRoom">
        <input id="unity-education-name" style="display:none" v-model="unityEducationName">
        <input id="unity-education-time" style="display:none" v-model="unityEducationTime">
        <input id="unity-education-auth" style="display:none" v-model="unityEducationAuth">
        <input type="text" id="unity-object" style="display:none" v-model="unityObject">
    </div>

</template>

<script>
import { mapActions, mapState } from 'vuex'

export default {
    name: "UnityData",
    data(){
        return{
            unityObject: "",
            unityCharacter: "",
            unityRoom: "",
            unityEducationName: "",
            unityEducationTime: "",
            unityEducationAuth: "",
        }
    },
    created(){
        this.$store.commit('unity/SET_UNITY_CHARACTER', "") 
        this.$store.commit('unity/SET_UNITY_ROOM', "")
        this.$store.commit('unity/SET_UNITY_EDUCATIONNAME', "")
        this.$store.commit('unity/SET_UNITY_EDUCATIONTIME', 0)
        this.$store.commit('unity/SET_UNITY_EDUCATIONAUTH', false)
        this.$store.commit('unity/SET_UNITY_OBJECT','')
    },
    async mounted(){                                // 테스트를 위해 페이지 로딩 시 axois 수행해보기
        this.interval = setInterval(()=>{
            this.unityObject = document.getElementById("unity-object").value
            this.unityCharacter = document.getElementById("unity-character").value
            this.unityRoom = document.getElementById("unity-room").value
            this.unityEducationName = document.getElementById("unity-education-name").value    // fire, earthquake, corona, typhoon
            this.unityEducationTime = document.getElementById("unity-education-time").value
            this.unityEducationAuth = document.getElementById("unity-education-auth").value

        },1000)
        console.log( document.getElementById("unity-character"))
    },
    destroyed(){
        clearInterval(this.interval);
    },
    methods:{
        ...mapActions('unity', ['setCharacter', 'setRoom',  'setEducationTime', 'setEducationAuth']),
        ...mapActions('education',['getEdunum']),
        async setCharacterMethod(){
            await this.$store.commit('unity/SET_UNITY_CHARACTER', this.unityCharacter)        // 파일명 저장
            await this.setCharacter()
        },
        async setRoomMethod(){
            await this.$store.commit('unity/SET_UNITY_ROOM', this.unityRoom)
            // await this.setRoom()
        },
        async setEducationTimeMethod(){
            await this.$store.commit('unity/SET_UNITY_EDUCATIONTIME', this.unityEducationTime)
            await this.setEducationTime()
        },
        async setEducationAuthMethod(){
            await this.$store.commit('unity/SET_UNITY_EDUCATIONAUTH', this.unityEducationAuth)
            await this.setEducationAuth()
        },
    },
    computed:{
        ...mapState('user', ['nickname', 'email']),
        
    },
    watch:{
        unityObject(val){
            console.log(val)
            switch (val) {
                case "guestbook":
                    document.getElementById("unity-object").value= "";
                    this.unityObject=""
                    this.$router.push({name : 'Guestbook'});
                    break;
                case "rank1":
                    this.getEdunum(1);
                    document.getElementById("unity-object").value= "";
                    this.unityObject=""
                    this.$router.push({name : 'Rank'});
                    break;
                case "rank2":
                    this.getEdunum(2);
                    document.getElementById("unity-object").value= "";
                    this.unityObject=""
                    this.$router.push({name : 'Rank'});
                    break;
                
                default:
                    break;
            }
        },
        unityCharacter(val){
            if(val) this.setCharacterMethod()
        },
        unityRoom(val){               // 방 이름
            if(val) this.setRoomMethod()
        },
        unityEducationName(val){           // 교육 명 : Vuex 저장
            if(val) this.$store.commit('unity/SET_UNITY_EDUCATIONNAME', this.unityEducationName)
        },
        unityEducationTime(val){           // 시험 통과 시간
            if(val) this.setEducationTimeMethod()
        },
        unityEducationAuth(val){           // 교육 수강 여부
            if(val) this.setEducationAuthMethod()
        },
    }
}
</script>

<style>

</style>