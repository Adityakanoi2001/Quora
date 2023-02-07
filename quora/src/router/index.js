import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import ProfileView from "../views/ProfileView.vue";
import ProfileAnotherPerson from "../components/ProfileAnotherPerson.vue";
import AnswersPosted from "../components/AnswersPosted.vue";
import FollowersComponent from "../components/FollowersComponent.vue";
import FollowingComponent from "../components/FollowingComponent.vue";
import QuestionsAsked from "../components/QuestionsAsked.vue";
import UserRegistration from "../components/UserRegistration.vue";
import UserLogin from "../components/UserLogin.vue";
import QuestionDescription from "../views/QuestionDescription.vue";
import PostDescription from "../views/PostDescription.vue";
import PostQuestion from "../components/PostQuestion.vue";
import AnswerQuestion from "../components/AnswerQuestion.vue";
import SearchView from "../views/SearchView.vue";
import QuestionsAskedAnotherPerson from "../components/QuestionsAskedAnotherPerson.vue";
import AnswersPostedAnotherPerson from "../components/AnswersPostedAnotherPerson.vue";
import FollowersComponentAnotherPerson from "../components/FollowersComponentAnotherPerson.vue";
import FollowingComponentAnotherPerson from "../components/FollowingComponentAnotherPerson.vue";
import SerachViewUser from "../views/SerachViewUser.vue";
import AnswerAnyQuestion from "../components/AnswerAnyQuestion.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/home",
    name: "home",
    component: HomeView,
  },
  {
    path: "/search/:searchText",
    name: "search",
    component: SearchView,
  },
  {
    path: "/searchUser/:searchText",
    name: "searchUser",
    component: SerachViewUser,
  },
  {
    path: "/userregistration",
    name: "userregistration",
    component: UserRegistration,
  },
  {
    path: "/postdescription",
    name: "postdescription",
    component: PostDescription,
  },
  {
    path: "/answerquestion",
    name: "answerquestion",
    component: AnswerQuestion,
  },
  {
    path: "/answeranyquestion",
    name: "answeranyquestion",
    component: AnswerAnyQuestion,
  },
  {
    path: "/questiondescription",
    name: "questiondescription",
    component: QuestionDescription,
  },
  {
    path: "/postquestion",
    name: "postquestion",
    component: PostQuestion,
  },
  {
    path: "/",
    name: "userlogin",
    component: UserLogin,
  },
  {
    path: "/profile",
    name: "profile",
    component: ProfileView,
    children: [
      {
        path: "questions",
        name: "questions",
        component: QuestionsAsked,
      },
      {
        path: "answers",
        name: "answers",
        component: AnswersPosted,
      },
      {
        path: "followers",
        name: "followers",
        component: FollowersComponent,
      },
      {
        path: "following",
        name: "following",
        component: FollowingComponent,
      },
    ],
  },
  {
    path: "/profileanother",
    name: "ProfileAnotherUser",
    component: ProfileAnotherPerson,
    children: [
      {
        path: "questionsanother",
        name: "questionsanother",
        component: QuestionsAskedAnotherPerson,
      },
      {
        path: "answersanother",
        name: "answersanother",
        component: AnswersPostedAnotherPerson,
      },
      {
        path: "followersanother",
        name: "followersanother",
        component: FollowersComponentAnotherPerson,
      },
      {
        path: "followinganother",
        name: "followinganother",
        component: FollowingComponentAnotherPerson,
      },
    ],
  },
  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
