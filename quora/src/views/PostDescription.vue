<template>
  <div>
    <div v-if="!list.length">
      <button class="answerThis" @click="showInput = true">Answer This</button>
      <center>
        <div v-if="showInput" style="align-items: center">
          <textarea
            type="text"
            class="form-control"
            v-model="inputValue"
            style="width: 350px; height: 150px"
          />
          <button class="answerThis" @click="submitInput">Submit</button>
        </div>
      </center>
    </div>
    <div v-else>
      <p>
        <br />
        <b>{{ list[0].questionBody }}</b>
      </p>
      <div>
        <button class="answerThis" @click="showInput = true">
          Answer This
        </button>
        <center>
          <div v-if="showInput" style="align-items: center">
            <textarea
              type="text"
              class="form-control"
              v-model="inputValue"
              style="width: 350px; height: 150px"
            />
            <button class="answerThis" @click="submitInput">Submit</button>
          </div>
        </center>
      </div>
      <div v-for="(answer, index) in list" :key="index">
        <!-- {{ answer }} -->
        <div class="main">
          <div class="sub">
            <div class="userDetails">
              <p>
                <img
                  :src="answer.answerGiverImage"
                  style="width: 50px; padding-top: 10px"
                />
                <b>{{ answer.answerGiverName }}</b>
              </p>
              <!-- <p>{{ answer.answerID }}</p> -->
              <p>{{ answer.answerBody }}</p>
              <div>
                <i class="fas fa-thumbs-up" @click="emitUpVote(answer)"></i>
                <!-- {{ likesList }} -->
                {{ answer.upVotersList.length }}
                <span style="visibility: hidden">R</span>
                <i class="fas fa-thumbs-down" @click="emitDownVote(answer)"></i>
                {{ answer.downVotersList.length }}
                <span style="visibility: hidden">R</span>
                <span style="visibility: hidden">R</span>
                <p
                  class="fas fa-comment"
                  @click="emitComment"
                  style="cursor: pointer"
                ></p>
                <span style="visibility: hidden">R</span
                ><span style="visibility: hidden">R</span>
                <p
                  class="fas fa-comments"
                  @click="emitAllComment(answer)"
                  style="cursor: pointer"
                ></p>

                <div v-if="allComments[answer.answerID]">
                  <div
                    v-for="comment in allComments[answer.answerID]"
                    :key="comment.commentId"
                  >
                    <div class="showcomments" v-if="showAllComment">
                      <div class="column">
                        <img
                          :src="comment.commenterImage"
                          style="width: 50px"
                        />&nbsp;
                        <b>{{ comment.userName }}</b>
                      </div>
                      <p style="margin-left: 10%">{{ comment.commentBody }}</p>
                      <hr class="my-4" />
                    </div>
                  </div>
                </div>
              </div>
              <!-- <p>{{ answer.questionBody }}</p> -->
              <!-- <p>{{ answer.questionId }}</p> -->
              <!-- <button>Upvote</button> -->
            </div>
          </div>
        </div>
        <div style="padding-top: 5px">
          <input
            v-if="showComment"
            v-model="inputText"
            type="text"
            class="form-group"
          />&nbsp;
          <button
            v-if="showComment"
            @click="commenting(answer)"
            class="commentButton"
          >
            Comment
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
//import { defineComponent } from '@vue/composition-api'
import axios from "axios";

export default {
  data() {
    return {
      list: undefined,
      showInput: false,
      inputValue: "",
      showComment: false,
      allComments: {},
      showAllComment: false,
      inputText: "",
      isLiked: [],
      isDisLiked: [],
      // showAllComment: false,
      likes: 0,
      dislikes: 0,
      likesList: [],
      dislikesList: [],
      activeUpVote: false,
      activeDownVote: false,
      ansId: "",
    };
  },
  computed: {
    usrId() {
      return localStorage.getItem("email");
    },
  },
  methods: {
    submitInput() {
      // const requestbody = {
      //   answerBody: this.inputValue,
      //   answerUserId: localStorage.getItem("email"),
      //   questionId: localStorage.getItem("questionID"),
      // };
      const params = `?answerBody=${
        this.inputValue
      }&answerUserId=${localStorage.getItem(
        "email"
      )}&questionId=${localStorage.getItem("questionID")}`;
      // axios.post("/api/answer/addAnswer", requestbody);
      axios.get("/api/answer/addAnswer1" + params);
      this.showInput = false;
      this.inputValue = "";
      window.location.href = "/postDescription";
    },
    async commenting(answer) {
      console.log("In commenting");
      localStorage.setItem("answerID", answer.answerID);
      const payload = {
        answerId: localStorage.getItem("answerID"),
        commentBody: this.inputText,
        parentId: "null",
        userId: localStorage.getItem("email"),
        userName: "ram",
      };
      console.log(payload);
      if (this.inputText.length > 1) {
        console.log(this.inputText);
        await axios.post("/api/comment/addcomment", payload).then((res) => {
          console.log(res);
        });
      }
      this.showComment = !this.showComment;
      // this.showAllComment = !this.showAllComment;
    },
    emitUpVote(answer) {
      this.activeUpVote = !this.activeUpVote;
      const isPostAlreadyLiked = this.isLiked.some(
        (currentPostId) => currentPostId === answer.answerID
      );
      if (isPostAlreadyLiked) {
        this.isLiked = this.isLiked.filter(
          (currentPostId) => currentPostId !== answer.answerID
        );
      } else {
        this.isLiked.push(answer.answerID);
      }
      // this.ansId: posts.answerEntity.answerID;
      axios
        .post(`/api/answer/upVote/${answer.answerID}/${this.usrId}`)
        .then((response) => {
          this.likesList = response.data.message;
          console.log("upvote response is ", this.likesList);
          console.log("response is ", response);
          // location.reload();
          this.activeUpVote = !this.activeUpVote;
        })
        .catch(function (error) {
          console.log(error);
        });
      location.reload();
    },
    emitDownVote(answer) {
      this.activeDownVote = !this.activeDownVote;
      const isPostAlreadyDisLiked = this.isDisLiked.some(
        (currentPostId) => currentPostId === answer.answerID
      );
      if (isPostAlreadyDisLiked) {
        this.isDisLiked = this.isDisLiked.filter(
          (currentPostId) => currentPostId !== answer.answerID
        );
      } else {
        this.isDisLiked.push(answer.answerID);
      }
      // this.ansId: posts.answerEntity.answerID;
      axios
        .post(`/api/answer/downVote/${answer.answerID}/${this.usrId}`)
        .then((response) => {
          this.dislikesList = response.data.message;
          console.log("upvote response is ", this.dislikesList);
          console.log("response is ", response);
          // location.reload();
          this.activeDownVote = !this.activeDownVote;
        })
        .catch(function (error) {
          console.log(error);
        });
      location.reload();
    },
    emitComment() {
      this.showComment = !this.showComment;
      console.log(this.inputText);
    },
    emitAllComment(answer) {
      this.showAllComment = !this.showAllComment;
      console.log("answer ID is ", answer.answerID);
      axios
        .get(`api/comment/getcommentbyanswer/${answer.answerID}`)
        .then((response) => {
          console.log(response);
          const allCommentsClone = { ...this.allComments };
          allCommentsClone[answer.answerID] = response.data.commentList;
          this.allComments = allCommentsClone;
          // this.commentsForPosts[postId] = response.data.comments;
          // this.commentsList = response.data.comments;
          console.log("commentslist", this.allComments[answer.answerID]);
          // if (this.commentsList.length > 0) this.isComments = true;
        })
        .catch(function (error) {
          // handle error
          console.log(error);
        });
      // return this.allComments;
    },
  },
  async created() {
    await axios
      .get("/api/answer/findAllAnswers/" + localStorage.getItem("questionID"))
      .then((res) => {
        this.list = res.data;
      });
    console.log(this.list);
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
  width: 40%;
  align-content: center;
  margin-left: 26%;
  background-color: white;
  border-radius: 6px;
}
.fa-thumbs-up,
.fa-thumbs-down {
  font-size: 20px;
  cursor: pointer;
}
.fa-comment,
.fa-comments {
  font-size: 18px;
  cursor: pointer;
  color: #2c3f51;
}
.sub {
  margin-left: 2%;
}
.commentButton {
  background-color: #b92b28;
  color: #fff;
  /* padding: 10px 20px; */
  border-radius: 5px;
  cursor: pointer;
  border: none;
  font-size: 16px;
  height: 24px;
  align-content: center;
}
.answerThis {
  background-color: #b92b28;
  color: #fff;
  /* padding: 10px 20px; */
  border-radius: 5px;
  cursor: pointer;
  border: none;
  font-size: 16px;
  height: 30px;
}
</style>
