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
            unityRoomPopulation: "",
            unityEducationName: "",
            unityEducationTime: "",
            unityEducationAuth: "",
            
        }
    },
    async mounted(){                                // 테스트를 위해 페이지 로딩 시 axois 수행해보기
        
        this.unityCharacter = document.getElementById("unity-character").innerHTML
        this.unityRoom = document.getElementById("unity-room").innerHTML
        this.unityRoomPopulation = document.getElementById("unity-room-population")
        this.unityEducationName = document.getElementById("unity-education-name")     // fire, earthquake, corona, typhoon
        this.unityEducationTime = document.getElementById("unity-education-time")
        this.unityEducationAuth = document.getElementById("unity-education-auth")


        console.log("unity-data")
        // this.unityCharacter = "test_character_file";
        // await this.setCharacterMethod();
    },
    methods:{
        ...mapActions('unity', ['setCharacter', 'setRoom', 'setRoomPopulation', 'setEducationTime', 'setEducationAuth']),
        async setCharacterMethod(){
            await this.$store.commit('unity/SET_UNITY_CHARACTER', this.unityCharacter)        // 파일명 저장
            await this.setCharacter()
        },
        async setRoomMethod(){
            await this.$store.commit('unity/SET_UNITY_ROOM', this.unityRoom) 
            await this.setRoom()
        },
        async setRoomPopulationMethod(){
            await this.$store.commit('unity/SET_UNITY_ROOMPOPULATION', this.unityRoomPopulation)
            await this.setRoomPopulation()
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
                case "award":
                    this.$router.push({name : 'Rank'});
                    break;
                case "myedu": 
                    this.$router.push({name : 'EducationList'});
                    break;
                
                default:
                    break;
            }
        },
        unityCharacter(){               // 캐릭터 파일 ID
            this.setCharacterMethod()
        },
        unityRoom(){                    // 방 이름
            this.setRoomMethod()
        },
        unityRoomPopulation(){          // 방 최대 인원
            this.setRoomPopulationMethod()
        },
        unityEducationName(){           // 교육 명 : Vuex 저장
            this.$store.commit('unity/SET_UNITY_EDUCATIONNAME', this.unityEducationName)
        },
        unityEducationTime(){           // 시험 통과 시간
            this.setEducationTimeMethod()
        },
        unityEducationAuth(){           // 교육 수강 여부
            this.setEducationAuthMethod()
        },
    }
}
</script>

<style>

</style>