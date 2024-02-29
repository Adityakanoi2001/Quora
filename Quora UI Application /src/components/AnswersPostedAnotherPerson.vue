<template>
  <div style="display: flex; flex-direction: column-reverse">
    <div v-for="answers in getAnswers" :key="answers.answerId">
      <div class="main">
        <div class="sub">
          <b>{{ answers.questionBody }}</b> <br />
          <p style="padding-top: 6px">{{ answers.answerBody }}</p>
        </div>
      </div>
    </div>
    <!-- {{ getAnswers }} -->
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["getAnswers"]),
  },
  methods: {
    ...mapActions(["getAnswersApi"]),
    // emitAnswer(answers) {
    //   localStorage.setItem("answerID", answers.answerID);
    //   this.$router.push("/answerdescription");
    // },
  },
  created() {
    this.$store.dispatch("getAnswersApi", {
      id: localStorage.getItem("userSearch"),
    });
  },
};
</script>

<style scoped>
.main {
  display: flex;
  /* justify-content: center; */
  flex-direction: column;
  text-align: left;
  border: 1.4px #ced5da solid;
  margin-top: 10px;
  width: 40%;
  align-content: center;
  margin-left: 26%;
  background-color: white;
  border-radius: 6px;
}
.sub {
  margin-left: 2%;
  padding-top: 4px;
}
</style>
