<template>
  <div>
    <div style="display: flex; flex-direction: column; align-items: center">
      <div
        v-for="(question, index) in list"
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
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      list: [],
    };
  },
  async created() {
    await axios.get("/api/question/getAllQuestions").then((res) => {
      this.list = res.data;
    });
  },
  methods: {
    emitQuestion(question) {
      localStorage.setItem("questionID", question.questionId);
      this.$router.push("/postdescription");
    },
    async fetchPosts() {
      await axios.get("/api/question/getAllQuestions").then((res) => {
        this.list = res.data;
      });
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
