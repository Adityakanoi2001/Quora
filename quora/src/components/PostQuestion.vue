<template>
  <div>
    <main>
      <h1>Ask a Question</h1>
      <form @submit.prevent="submitQuestion">
        <div class="form-group">
          <label for="question-category">Question Category</label><br />
          <select id="question-category" v-model="questionCategory">
            <option value="food">Food</option>
            <option value="music">Music</option>
            <option value="sports">Sports</option>
            <option value="travel">Travel</option>
            <option value="entertainment">Entertainment</option>
            <option value="fashion">Fashion</option>
          </select>
        </div>
        <div class="form-group">
          <label for="question-body">Question Body</label><br />
          <textarea id="question-body" v-model="questionBody"></textarea>
        </div>
        <button>Submit Question</button>
      </form>
    </main>
  </div>
</template>

<script>
import axios from "axios";
// import { log } from "console";
export default {
  data() {
    return {
      questionCategory: "",
      questionBody: "",
      userId: localStorage.getItem("email"),
      accessToken: localStorage.getItem("accessToken"),
    };
  },
  methods: {
    submitQuestion() {
      const payload = {
        questionBody: this.questionBody,
        questionCategory: this.questionCategory,
        questionUserID: this.userId,
      };
      const params = `?questionBody=${this.questionBody}&questionCategory=${this.questionCategory}&questionUserID=${this.userId}`;
      console.log("request body sent is ", payload);
      this.apiCall(payload, params);
    },
    apiCall(payload, params) {
      console.log("yes");
      console.log(payload);
      //   axios.post("/api/merchant/addNewItem", requestbody);
      //   debugger;
      //   axios.post("/api/question/addQuestion", payload, {
      //     headers: { Authorization: this.accessToken },
      //   });
      axios.get("/api/question/param" + params);
      console.log("after response");
      window.location.href = "/home";
      // window.location.href("/");
    },
  },
};
</script>

<style scoped>
main {
  width: 80%;
  margin: 0 auto;
  padding: 20px;
}

.form-group {
  margin-bottom: 20px;
}

label {
  font-size: 16px;
}

select {
  width: 50%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-sizing: border-box;
  margin-top: 10px;
}

textarea {
  width: 50%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-sizing: border-box;
  margin-top: 10px;
}

button {
  background-color: #0f77b7;
  color: #fff;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  border: none;
  font-size: 16px;
}
</style>
