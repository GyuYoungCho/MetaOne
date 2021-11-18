<template>
    <div>
        <div id="naverIdLogin" style="display: none"></div>
    </div>
</template>

<script>
import { getKakaoToken, getKakaoUserInfo } from "@/common/login.js";
import { mapActions } from 'vuex';
export default {
    name: 'Auth',
    created() {

        if (this.$route.query.code) {
            this.setKakaoToken(this.$route.query.code);
        }
    },
    methods: {
        ...mapActions('user',['kakaoLogin']),
        async setKakaoToken (code) {
            const { data } = await getKakaoToken(code);
            if (data.error) {
                alert('카카오톡 로그인 오류입니다.');
                this.$router.replace('/');
                return;
            }
            window.Kakao.Auth.setAccessToken(data.access_token);
            await this.setUserInfo();
        },
        async setUserInfo () {
            const res = await getKakaoUserInfo();
            const userInfo = {
                email: res.kakao_account.email,
                name: res.kakao_account.profile.nickname,
            };
            this.$store.commit("user/SET_JOIN_NAME", userInfo.name);
            this.$store.commit("user/SET_JOIN_EMAIL", userInfo.email);
            await this.kakaoLogin()
        },
    }
}
</script>