<template>
  <div>
    <!-- In Followers Component -->
    <div
      v-for="follower in getFollowers"
      :key="follower.userId"
      style="justify-content: left; display: flex; flex-direction: column"
    >
      <p>
        <img :src="follower.img" style="width: 50px" /> {{ follower.userName }}
      </p>
    </div>
    <p>Total no of followers are {{ getFollowers.length }}</p>
    <!-- {{ getFollowers }} -->
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
// import axios from "axios";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["getFollowers"]),
  },
  methods: {
    ...mapActions(["getFollowersApi"]),
  },
  created() {
    this.$store.dispatch("getFollowersApi", {
      id: localStorage.getItem("email"),
    });
  },
};
</script>
<style></style>
