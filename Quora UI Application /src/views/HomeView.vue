<template>
  <div>
    <!-- {{ list }} -->
    <div style="float: right; padding-right: 10px">
      <!-- {{ adsList }} -->
      <div v-for="(adds, index) in adsList" :key="index">
        <img :src="adds.url" style="width: 250px; height: 250px" />
        <p>{{ adds.productName }}&nbsp; Rs.{{ adds.cost }}</p>
      </div>
    </div>
    <div v-for="(posts, index) in list" :key="index">
      <!-- {{ adsList }} -->
      <!-- <p>{{ posts.answerEntity.answerID }}</p> -->
      <div class="main">
        <div class="sub">
          <div class="userDetails">
            <div @click="emitPost(posts)">
              <p>
                <img
                  :src="posts.answerEntity.answerGiverImage"
                  style="width: 60px; padding-top: 10px"
                />&nbsp;&nbsp;
                <b>{{ posts.answerEntity.answerGiverName }}</b>
              </p>
              <p>
                <b>{{ posts.answerEntity.questionBody }}</b>
              </p>
              <!-- <p>{{ posts.answerEntity.answerID }}</p> -->
              <p>
                {{ posts.answerEntity.answerBody }}
              </p>
              <p>{{ posts.commentsList }}</p>
            </div>
            <div>
              <i class="fas fa-thumbs-up" @click="emitUpVote(posts)"></i>
              <!-- {{ likesList }} -->
              {{ posts.answerEntity.upVotersList.length }}
              <span style="visibility: hidden">R</span>
              <i class="fas fa-thumbs-down" @click="emitDownVote(posts)"></i>
              {{ posts.answerEntity.downVotersList.length }}
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
                @click="emitAllComment(posts)"
                style="cursor: pointer"
              ></p>
              <!-- {{ allComment[posts] }} -->
              <div v-if="allComments[posts.answerEntity.answerID]">
                <div
                  v-for="comment in allComments[posts.answerEntity.answerID]"
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
          @click="commenting(posts)"
          class="commentButton"
        >
          Comment
        </button>
      </div>
    </div>
  </div>
</template>

<script>
//import { createDecipheriv } from "crypto";
import axios from "axios";
export default {
  data() {
    return {
      answerId: localStorage.getItem("answerId"),
      userId: localStorage.getItem("userid"),
      userName: localStorage.getItem("name"),
      inputText: "",
      showComment: false,
      list: undefined,
      list1: undefined,
      list2: undefined,
      response: undefined,
      allComments: {},
      msg: "",
      isLiked: [],
      isDisLiked: [],
      showAllComment: false,
      likes: 0,
      dislikes: 0,
      likesList: [],
      dislikesList: [],
      activeUpVote: false,
      activeDownVote: false,
      ansId: "",
      adsList: [],
    };
  },
  computed: {
    usrId() {
      return localStorage.getItem("email");
    },
  },
  // async created() {
  //   await axios
  //     .get(`/api/question/getPostsbyUserId/${this.usrId}`)
  //     .then((res) => {
  //       this.list = res.data;
  //     });
  //   // console.log(this.list);
  //   // console.log(JSON.stringify(this.list));
  // },
  async created() {
    const responseOne = await axios
      .get(`/api/question/getPostsbyUserId/${this.usrId}`)
      .then((res) => {
        this.list = res.data;
      });
    console.log(responseOne);
    const responseTwo = await axios
      .get("ads/sendAds/" + localStorage.getItem("email"))
      .then((res) => {
        this.adsList = res.data;
      });
    // this.adsList = responseTwo.data;
    console.log(responseTwo);
    // console.log(this.list);
    // console.log(JSON.stringify(this.list));
  },
  methods: {
    //     async onUpVote(){
    //         await axios.get("http://10.20.3.153:8081/answer/upvote"+this.list)

    //     }
    async commenting(posts) {
      localStorage.setItem("answerID", posts.answerEntity.answerID);
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
    emitComment() {
      this.showComment = !this.showComment;
      console.log(this.inputText);
    },
    emitPost(posts) {
      localStorage.setItem("questionID", posts.questionId);
      this.$router.push("/postDescription");
    },
    changeUpvoteColor() {
      this.upvoteColor = this.upvoteColor === "#ccc" ? "#3f51b5" : "#ccc";
    },
    changeDownvoteColor() {
      this.downvoteColor = this.downvoteColor === "#ccc" ? "#3f51b5" : "#ccc";
    },
    async fetchPosts() {
      await axios.get("/api/question/getPosts").then((res) => {
        this.list = res.data;
      });
    },
    emitUpVote(posts) {
      this.activeUpVote = !this.activeUpVote;
      const isPostAlreadyLiked = this.isLiked.some(
        (currentPostId) => currentPostId === posts.answerEntity.answerID
      );
      if (isPostAlreadyLiked) {
        this.isLiked = this.isLiked.filter(
          (currentPostId) => currentPostId !== posts.answerEntity.answerID
        );
      } else {
        this.isLiked.push(posts.answerEntity.answerID);
      }
      // this.ansId: posts.answerEntity.answerID;
      axios
        .post(`/api/answer/upVote/${posts.answerEntity.answerID}/${this.usrId}`)
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
    emitDownVote(posts) {
      this.activeDownVote = !this.activeDownVote;
      const isPostAlreadyDisLiked = this.isDisLiked.some(
        (currentPostId) => currentPostId === posts.answerEntity.answerID
      );
      if (isPostAlreadyDisLiked) {
        this.isDisLiked = this.isDisLiked.filter(
          (currentPostId) => currentPostId !== posts.answerEntity.answerID
        );
      } else {
        this.isDisLiked.push(posts.answerEntity.answerID);
      }
      // this.ansId: posts.answerEntity.answerID;
      axios
        .post(
          `/api/answer/downVote/${posts.answerEntity.answerID}/${this.usrId}`
        )
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
    emitAllComment(posts) {
      this.showAllComment = !this.showAllComment;
      console.log("answer ID is ", posts.answerEntity.answerID);
      axios
        .get(`api/comment/getcommentbyanswer/${posts.answerEntity.answerID}`)
        .then((response) => {
          console.log(response);
          const allCommentsClone = { ...this.allComments };
          allCommentsClone[posts.answerEntity.answerID] =
            response.data.commentList;
          this.allComments = allCommentsClone;
          // this.commentsForPosts[postId] = response.data.comments;
          // this.commentsList = response.data.comments;
          console.log(
            "commentslist",
            this.allComments[posts.answerEntity.answerID]
          );
          // if (this.commentsList.length > 0) this.isComments = true;
        })
        .catch(function (error) {
          // handle error
          console.log(error);
        });
      // return this.allComments;
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
  width: 40%;
  align-content: center;
  margin-left: 26%;
  background-color: white;
  border-radius: 6px;
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
</style>
