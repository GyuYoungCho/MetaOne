import Vue from "vue";
import VueRouter from "vue-router";
import Auth from "@/components/Auth.vue";
import Login from "@/view/Login.vue";
import Join from "@/view/Join.vue";
import FindPw from "@/view/FindPw.vue";
import MyPage from "@/view/MyPage.vue";
import SelectCharacter from "@/view/SelectCharacter.vue";
import SelectRoom from "@/view/SelectRoom.vue";
import SettingRoom from "@/view/SettingRoom.vue";
import EducateList from "@/view/EducateList.vue";
import Certificate from "@/view/Certificate.vue";
import Guestbook from "@/view/Guestbook.vue";
import MessageRecv from "@/view/MessageRecv.vue";
import UnityMap from "@/view/UnityMap.vue";
import Rank from "@/view/Rank.vue";
import UnityData from "@/components/unity/UnityData.vue";
import NotFound from "@/view/errorpages/404.vue";

import store from "@/store/";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    alias: ["/login"],
    name: "Login",
    component: Login,
  },
  {
    path: "/auth",
    name: "Auth",
    component: Auth,
  },
  {
    path: "/join",
    name: "Join",
    component: Join,
  },
  {
    path: "/my-page",
    name: "MyPage",
    component: MyPage,
    meta: { requireAuth: true },
  },
  {
    path: "/find-pw",
    name: "FindPw",
    component: FindPw,
  },
  {
    path: "/educate-list",
    name: "EducateList",
    component: EducateList,
    meta: { requireAuth: true },
  },
  {
    path: "/certificate",
    name: "Certificate",
    component: Certificate,
    meta: { requireAuth: true },
  },
  {
    path: "/select-room",
    name: "SelectRoom",
    component: SelectRoom,
    meta: { requireAuth: true },
  },
  {
    path: "/select-character",
    name: "SelectCharacter",
    component: SelectCharacter,
    meta: { requireAuth: true },
  },
  {
    path: "/setting-room",
    name: "SettingRoom",
    component: SettingRoom,
    meta: { requireAuth: true },
  },
  {
    path: "/guestbook",
    name: "Guestbook",
    component: Guestbook,
    meta: { requireAuth: true },
  },
  {
    path: "/message-recv",
    name: "MessageRecv",
    component: MessageRecv,
    meta: { requireAuth: true },
  },
  {
    path: "/unity-map",
    name: "UnityMap",
    component: UnityMap,
    meta: { requireAuth: true },
  },
  {
    path: "/404",
    name: "NotFound",
    component: NotFound,
  },
  {
    path: "/rank",
    name: "Rank",
    component: Rank,
  },
  {
    path: "/unity-data",
    name: "UnityData",
    component: UnityData,
  },
  {
    path: "*",
    redirect: "/404",
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach(function (to, from, next) {
  if (
    to.matched.some(function (routeInfo) {
      return routeInfo.meta.requireAuth;
    })
  ) {
    if (!store.state.user.isLogin) {
      next("/");
    } else {
      next();
    }
  } else {
    if (to.name === "Login") {
      if (store.state.user.isLogin) {
        next("/select-character");
      } else {
        next();
      }
    } else {
      next();
    }
  }
});

export default router;
