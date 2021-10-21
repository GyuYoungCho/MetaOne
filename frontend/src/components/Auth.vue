<template>
    <div>
        <div id="naverIdLogin" style="display: none"></div>
    </div>
</template>

<script>
import { getKakaoToken, getKakaoUserInfo, setKakaoUserInfo } from "@/common/login.js";
export default {
    name: 'Auth',
    created() {
        console.log("음?")

        if (this.$route.query.code) {
            console.log("오나요?1")
            this.setKakaoToken(this.$route.query.code);
        }
    },
    methods: {
        async setKakaoToken (code) {
            console.log("오나요?")
            console.log('카카오 인증 코드', code);
            const { data } = await getKakaoToken(code);
            if (data.error) {
                alert('카카오톡 로그인 오류입니다.');
                this.$router.replace('/');
                return;
            }
            console.log("토큰", data)
            window.Kakao.Auth.setAccessToken(data.access_token);
            await this.setUserInfo();
            this.$router.replace('/');
        },
        async setUserInfo () {
            console.log("왔나?")
            const res = await getKakaoUserInfo();
            const userInfo = {
                email: res.kakao_account.email,
                name: res.kakao_account.profile.nickname,
            };

            console.log("유저정보", userInfo)
            setKakaoUserInfo(userInfo);
            
            // this.$store.commit('setUser', userInfo);
        },
    }
}
</script>