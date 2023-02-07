import axios from "axios";

export default {
  state: {
    userList: [],
    questionList: [],
  },
  getters: {
    getUserList: (state) => state.userList,
    getQuestionList: (state) => state.questionList,
  },
  mutations: {
    setUserList: (state, value) => {
      state.userList = value;
    },
    setQuestionList: (state, value) => {
      state.questionList = value;
    },
  },
  actions: {
    SEARCH_QUESTION_LIST: ({ commit }, { questionsearch }) => {
      axios
        .get("/api/question/searchQuestion/" + questionsearch)
        .then((res) => {
          console.log(res);
          commit("setQuestionList", res.data);
        });
    },
    SEARCH_USER_LIST: ({ commit }, { userName }) => {
      axios.get("/api/User/searchUser/" + userName).then((res) => {
        console.log(res);
        commit("setUserList", res.data);
      });
    },
  },
};
