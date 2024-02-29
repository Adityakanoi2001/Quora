<template>
  <div class="profile-page">
    <header>
      <div class="header-content">
        <img
          class="profile-image"
          :src="getProfileAnotherUser.img"
          alt="Profile Image"
        />
        <div class="profile-info">
          <h1 class="profile-name">{{ getProfileAnotherUser.userName }}</h1>
          <div v-if="!isFollower">
            <button class="follow-button">Following</button>
          </div>
          <div v-else>
            <button class="follow-button" @click="followPerson">Follow</button>
          </div>
          <p class="profile-bio">{{ getProfileAnotherUser.bio }}</p>
          <p>User score: {{ getProfileAnotherUser.score }}</p>
          <p>Classification: {{ getProfileAnotherUser.classification }}</p>
        </div>
      </div>
      <!-- User profile{{ getProfileAnotherUser }} -->
    </header>
    <div style="display: contents">
      <div class="statistics">
        <router-link
          to="/profileanother/questionsanother"
          style="text-decoration: none; color: inherit"
        >
          <div class="statistic">
            <p class="statistic-value">{{ questionsCount }}</p>
            <b><p class="statistic-label">Questions</p></b>
          </div>
        </router-link>
        <router-link
          to="/profileanother/answersanother"
          style="text-decoration: none; color: inherit"
        >
          <div class="statistic">
            <!-- <p class="statistic-value">{{ answersCount }}</p> -->
            <b><p class="statistic-label">Answers</p></b>
          </div>
        </router-link>
        <router-link
          to="/profileanother/followersanother"
          style="text-decoration: none; color: inherit"
        >
          <div class="statistic">
            <!-- <p class="statistic-value">{{ followersCount }}</p> -->
            <b><p class="statistic-label">Followers</p></b>
          </div>
        </router-link>
        <router-link
          to="/profileanother/followinganother"
          style="text-decoration: none; color: inherit"
        >
          <div class="statistic">
            <!-- <p class="statistic-value">{{ followingCount }}</p> -->
            <b><p class="statistic-label">Following</p></b>
          </div>
        </router-link>
      </div>
      <router-view />
    </div>
    <!-- {{ getProfileAnotherUser }} -->
  </div>
</template>

<script>
// import { computed } from "@vue/runtime-core";
import axios from "axios";
import { mapGetters, mapActions } from "vuex";
export default {
  data() {
    return {
      profileImage: "https://via.placeholder.com/150",
      isFollower: false,
    };
  },
  computed: {
    ...mapGetters(["getProfileAnotherUser"]),
    userId() {
      return localStorage.getItem("email");
    },
    userSearch() {
      return localStorage.getItem("userSearch");
    },
  },
  methods: {
    ...mapActions(["getProfileAnotherUserApi"]),
    followPerson() {
      axios.post(`/api/User/follow/${this.userId}/${this.userSearch}`);
      window.location.href = "/profileanother";
    },
  },
  created() {
    this.$store.dispatch("getProfileAnotherUserApi", {
      id: localStorage.getItem("userSearch"),
    });
    axios
      .get(`/api/User/showFollowButton/${this.userId}/${this.userSearch}`)
      .then((response) => {
        console.log("true or false response", response);
        if (response.data) {
          this.isFollower = true;
        }
      })
      .then((error) => {
        console.log(error);
      });
  },
};
</script>

<style>
.profile-page {
  display: flex;
  flex-direction: column;
  align-items: center;
}

header {
  width: 100%;
  background-color: #f4f4f4;
  padding: 20px;
  text-align: center;
}

.header-content {
  display: flex;
  align-items: center;
  justify-content: center;
}

.profile-image {
  border-radius: 50%;
  width: 150px;
  height: 150px;
  margin-right: 20px;
}

.profile-info {
  text-align: left;
}

.profile-name {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 10px;
}
.profile-bio {
  font-size: 16px;
  color: #333;
}
p {
  color: black;
}
main {
  width: 100%;
  padding: 20px;
}

.statistics {
  display: flex;
  justify-content: space-between;
  align-content: center;
  width: 100%;
  margin-bottom: 20px;
}

.statistic {
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  cursor: pointer;
}

.statistic-value {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 10px;
}

.statistic-label {
  font-size: 16px;
  color: #333;
}

.activity {
  width: 100%;
}

.activity-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;
}
f .activity-list {
  list-style: none;
  padding: 0;
}
.follow-button {
  background-color: #0084ff;
  color: #fff;
  border: none;
  padding: 8px 12px;
  border-radius: 24px;
  cursor: pointer;
  font-size: 16px;
  margin-right: 20px;
}
.activity-item {
  font-size: 16px;
  color: #333;
  margin-bottom: 10px;
}
</style>
