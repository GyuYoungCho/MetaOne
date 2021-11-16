<template>
    <div id="unity-data">
        <div id="unity-character" hidden></div>
        <div id="unity-room" hidden></div>
        <div id="unity-room-population" hidden></div>
        <div id="unity-education-name" hidden></div>
        <div id="unity-education-time" hidden></div>
        <div id="unity-education-auth" hidden></div>
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
    },
    async mounted(){                                // 테스트를 위해 페이지 로딩 시 axois 수행해보기
        
        this.unityCharacter = document.getElementById("unity-character").innerHTML
        this.unityRoom = document.getElementById("unity-room").innerHTML
        this.unityRoomPopulation = document.getElementById("unity-room-population").innerHTML
        this.unityEducationName = document.getElementById("unity-education-name").innerHTML     // fire, earthquake, corona, typhoon
        this.unityEducationTime = document.getElementById("unity-education-time").innerHTML
        this.unityEducationAuth = document.getElementById("unity-education-auth").innerHTML


        console.log("unity-data")
    },
    methods:{
        ...mapActions('unity', ['setCharacter', 'setRoom', 'setRoomPopulation', 'setEducationTime', 'setEducationAuth']),
        ...mapActions('education',['getEdunum']),
        async setCharacterMethod(){
            await this.$store.commit('unity/SET_UNITY_CHARACTER', this.unityCharacter)        // 파일명 저장
            await this.setCharacter()
        },
        async setRoomMethod(){
            await this.$store.commit('unity/SET_UNITY_ROOM', this.unityRoom) 
            await this.setRoom()
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
        ...mapState('user', ['nickname', 'email'])
    },
    watch:{
        unityObject(val){
            switch (val) {
                case "guestbook":
                    this.$router.push({name : 'GuestBook'});
                    break;
                case "rank1":
                    this.getEdunum(1);
                    this.$router.push({name : 'Rank'});
                    break;
                case "rank2":
                    this.getEdunum(2);
                    this.$router.push({name : 'Rank'});
                    break;
                
                default:
                    break;
            }
        },
        unityCharacter(val){
            console.log(val)
            if(val) this.setCharacterMethod()
        },
        unityRoom(val){               // 방 이름
            console.log(val)
            if(val) this.setRoomMethod()
        },
        unityEducationName(val){           // 교육 명 : Vuex 저장
            console.log(val)
            if(val) this.$store.commit('unity/SET_UNITY_EDUCATIONNAME', this.unityEducationName)
        },
        unityEducationTime(val){           // 시험 통과 시간
            console.log(val)
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