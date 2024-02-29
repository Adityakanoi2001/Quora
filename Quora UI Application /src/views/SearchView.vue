<template>
  <div>
    <div style="display: flex; flex-direction: column; align-items: center">
      <div
        v-for="(question, index) in getQuestionList"
        :key="index"
        @click="emitQuestion(question)"
        style="cursor: pointer"
      >
        <div class="main">
          <div class="sub">
            <div class="userDetails" style="padding: 8px; padding-top: 14px">
              <b
                ><p>{{ question.questionBody }}</p></b
              >
            </div>
          </div>
        </div>
        <!-- {{ question }} -->
      </div>
    </div>
    <!-- {{ list }} -->
    <!-- {{ getQuestionList }} -->
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters(["getQuestionList"]),
  },
  data() {
    return {
      searchText: this.$route.params.searchText,
    };
  },
  created() {
    this.$store.dispatch("SEARCH_QUESTION_LIST", {
      questionsearch: this.searchText,
    });
  },
  methods: {
    ...mapActions(["SEARCH_QUESTION_LIST"]),
    emitQuestion(question) {
      localStorage.setItem("questionID", question.questionId);
      this.$router.push("/postdescription");
    },
  },
};
</script>

<style scoped>
.main {
  display: flex;
  /* justify-content: center; */
  flex-direction: row;
  text-align: left;
  border: 1.4px #ced5da solid;
  margin-top: 10px;
  width: 40vw;
  align-content: center;
  /* margin-left: 26%; */
  background-color: white;
  border-radius: 6px;
}
.sub {
  margin-left: 2%;
}
</style>
