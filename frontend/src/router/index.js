import Vue from "vue";
import VueRouter from "vue-router";
import Auth from "@/components/Auth.vue";
import Login from "@/view/Login.vue";
import Join from "@/view/Join.vue";
import EducateList from "@/view/EducateList.vue";
import Certificate from "@/view/Certificate.vue";
import Guestbook from "@/view/Guestbook.vue";
import MessageSend from "@/view/MessageSend.vue";
import MessageRecv from "@/view/MessageRecv.vue";
import NotFound from "@/view/errorpages/404.vue";

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
  // {
  //   path: "/my-page",
  //   name: "Mypage",
  //   component: Mypage,
  // },
  // {
  //   path: "/find-pw",
  //   name: "FindPw",
  //   component: FindPw,
  // },
  {
    path: "/educate-list",
    name: "EducateList",
    component: EducateList,
  },
  {
    path: "/certificate",
    name: "Certificate",
    component: Certificate,
  },
  // {
  //   path: "/character-select",
  //   name: "Character",
  //   component: Character,
  // },
  // {
  //   path: "/room-select",
  //   name: "Room",
  //   component: Room,
  // },
  // {
  //   path: "/room-create",
  //   name: "Room",
  //   component: Room,
  // },
  {
    path: "/guestbook",
    name: "Guestbook",
    component: Guestbook,
  },
  {
    path: "/message-send",
    name: "MessageSend",
    component: MessageSend,
  },
  {
    path: "/message-recv",
    name: "MessageRecv",
    component: MessageRecv,
  },
  // {
  //   path: "/unity-map",
  //   name: "UnityMap",
  //   component: UnityMap,
  // },
  {
    path: "/404",
    name: "NotFound",
    component: NotFound,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
