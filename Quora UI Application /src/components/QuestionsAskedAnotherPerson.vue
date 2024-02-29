<template>
  <div>
    <p>Questions asked: {{ getQuestions.questionEntityList.length }}</p>
    <div style="display: flex; flex-direction: column-reverse">
      <div
        v-for="(question, index) in getQuestions.questionEntityList"
        :key="index"
        @click="emitQuestion(question)"
        style="cursor: pointer"
      >
        <!-- <p>{{ question }}</p> -->
        <div class="main">
          <div class="sub">
            <div class="userDetails" style="padding: 8px; padding-top: 14px">
              <b
                ><p>{{ question.questionBody }}</p></b
              >
            </div>
          </div>
        </div>
      </div>
      <!-- {{ getQuestions }} -->
    </div>
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["getQuestions"]),
  },
  methods: {
    ...mapActions(["getQuestionsApi"]),
    emitQuestion(question) {
      localStorage.setItem("questionID", question.questionId);
      this.$router.push("/questiondescription");
    },
  },
  created() {
    this.$store.dispatch("getQuestionsApi", {
      id: localStorage.getItem("userSearch"),
    });
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
