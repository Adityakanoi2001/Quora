<template>
  <div class="profile-page">
    <div
      style="
        float: right;
        display: flex;
        justify-content: end;
        margin-left: 70%;
        padding-top: 10px;
      "
    >
      <input type="text" v-model="searchText" placeholder="Search User" />&nbsp;
      <button class="user-search" @click="submit">Search</button>
      <button class="user-signout" @click="signout">Log Out</button>
    </div>
    <header>
      <div class="header-content">
        <img class="profile-image" :src="getProfile.img" alt="Profile Image" />
        <div class="profile-info">
          <h1 class="profile-name">{{ getProfile.userName }}</h1>
          <p class="profile-bio">{{ getProfile.bio }}</p>
          <p>User score: {{ getProfile.score }}</p>
          <p>Classification: {{ getProfile.classification }}</p>
        </div>
      </div>
      <!-- User profile{{ getProfile }} -->
    </header>
    <div style="display: contents">
      <div class="statistics">
        <router-link
          to="/profile/questions"
          style="text-decoration: none; color: inherit"
        >
          <div class="statistic">
            <p class="statistic-value">{{ questionsCount }}</p>
            <b><p class="statistic-label">Questions</p></b>
          </div>
        </router-link>
        <router-link
          to="/profile/answers"
          style="text-decoration: none; color: inherit"
        >
          <div class="statistic">
            <!-- <p class="statistic-value">{{ answersCount }}</p> -->
            <b><p class="statistic-label">Answers</p></b>
          </div>
        </router-link>
        <router-link
          to="/profile/followers"
          style="text-decoration: none; color: inherit"
        >
          <div class="statistic">
            <!-- <p class="statistic-value">{{ followersCount }}</p> -->
            <b><p class="statistic-label">Followers</p></b>
          </div>
        </router-link>
        <router-link
          to="/profile/following"
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
  </div>
</template>

<script>
// import { computed } from "@vue/runtime-core";
import { mapGetters, mapActions } from "vuex";
export default {
  data() {
    return {
      profileImage: "https://via.placeholder.com/150",
      name: localStorage.getItem("name"),
      bio: "A sample bio about him",
      userId: localStorage.getItem("email"),
    };
  },
  computed: {
    ...mapGetters(["getProfile"]),
  },
  methods: {
    ...mapActions(["getProfileApi"]),
    submit() {
      this.$router.push(`/searchUser/${this.searchText}`);
    },
    signout() {
      localStorage.removeItem("email"),
        // localStorage.removeItem("questionID"),
        // localStorage.removeItem("firebaseToken"),
        // localStorage.removeItem("accessToken"),
        this.$router.push("/");
    },
  },
  created() {
    this.$store.dispatch("getProfileApi", {
      id: localStorage.getItem("email"),
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
.user-search {
  background-color: #b92b28;
  color: #fff;
  border: none;
  padding: 8px 12px;
  border-radius: 12px;
  cursor: pointer;
  font-size: 16px;
  margin-right: 20px;
}
.user-signout {
  background-color: #b92b28;
  color: #fff;
  border: none;
  padding: 8px 12px;
  border-radius: 12px;
  cursor: pointer;
  font-size: 16px;
  margin-right: 23px;
  width: 90px;
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

.activity-item {
  font-size: 16px;
  color: #333;
  margin-bottom: 10px;
}
</style>
