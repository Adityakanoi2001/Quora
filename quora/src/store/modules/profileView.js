import axios from "axios";

export default {
  state: {
    followers: [],
    profile: [],
    following: [],
    answers: [],
    questions: [],
    register: "",
    login: "",
    profileAnotherUser: [],
    // allQuestions: [],
  },
  getters: {
    getFollowing: (state) => state.following,
    getFollowers: (state) => state.followers,
    getProfile: (state) => state.profile,
    getAnswers: (state) => state.answers,
    getQuestions: (state) => state.questions,
    getProfileAnotherUser: (state) => state.profileAnotherUser,
    // getAllQuestions: (state) => state.allQuestions,
    getRegister: (state) => {
      return state.register;
    },
    getLogin: (state) => {
      return state.login;
    },
  },
  mutations: {
    setFollowers: (state, value) => {
      state.followers = value;
    },
    setFollowing: (state, value) => {
      state.following = value;
    },
    setProfile: (state, value) => {
      state.profile = value;
    },
    setAnswers: (state, value) => {
      state.answers = value;
    },
    setQuestions: (state, value) => {
      state.questions = value;
    },
    setRegister: (state, value) => {
      state.register = value;
    },
    setProfileAnotherUser: (state, value) => {
      state.profileAnotherUser = value;
    },
    // setAllQuestions: (state, value) => {
    //   state.allQuestions = value;
    // },
    setLogin: (state, value) => {
      state.login = value;
    },
  },
  actions: {
    getFollowersApi: ({ commit }, { id }) => {
      fetch(`/api/User/findFollowerByUserId/${id}`)
        .then((response) => response.json())
        .then((res) => {
          console.log(res);
          commit("setFollowers", res);
        });
    },
    getFollowingApi: ({ commit }, { id }) => {
      fetch(`/api/User/findFollowingByUserId/${id}`)
        .then((response) => response.json())
        .then((res) => {
          console.log(res);
          commit("setFollowing", res);
        });
    },
    getAnswersApi: ({ commit }, { id }) => {
      fetch(`/api/answer/getAllAnswerByAnswerUserId/${id}`)
        .then((response) => response.json())
        .then((res) => {
          console.log(res);
          commit("setAnswers", res);
        });
    },
    getQuestionsApi: ({ commit }, { id }) => {
      fetch(`/api/question/getQuestionByQuestionUserId/${id}`)
        .then((response) => response.json())
        .then((res) => {
          console.log(res);
          commit("setQuestions", res);
        });
    },
    getProfileApi: ({ commit }, { id }) => {
      // console.log("Id in API is ", id),
      fetch(`/api/User/findUserById/${id}`)
        .then((response) => response.json())
        .then((res) => {
          console.log(res);
          commit("setProfile", res);
        });
    },
    getProfileAnotherUserApi: ({ commit }, { id }) => {
      console.log("id in console log", id);
      fetch(`/api/User/findUserById/${id}`)
        .then((response) => response.json())
        .then((res) => {
          console.log(res);
          commit("setProfileAnotherUser", res);
        });
    },
    // getAllQuestionsApi: ({ commit }) => {
    //   // console.log("id in console log", id);
    //   fetch("/api/question/getAllQuestions")
    //     .then((response) => response.json())
    //     .then((res) => {
    //       console.log(res);
    //       commit("setAllQuestions", res);
    //     });
    // },
    async REGISTER({ commit }, { payload }) {
      const response = await axios.post("/oauth/api/auth/register", payload);
      console.log(response);
      commit("setRegister", response);
    },
    async LOGIN({ commit }, { payload, success }) {
      await axios
        .post("/oauth/api/auth/login", payload)
        .then((response) => {
          commit("setLogin", response);
          console.log(response);
          localStorage.setItem("email", response.data.userId);
          localStorage.setItem("accessToken", response.data.accessToken);
          if (response.statusText === "OK") {
            success();
          }
        })
        .catch((err) => {
          console.log(err);
          alert("Invalid credentials");
        });
      // console.log("Status text is ", response.statusText);
    },
  },
};
