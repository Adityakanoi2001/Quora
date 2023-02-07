<template>
  <div>
    <div
      v-for="following in getFollowing"
      :key="following.userId"
      style="justify-content: left; display: flex; flex-direction: column"
    >
      <img :src="following.img" style="width: 50px" />
      <p>{{ following.userName }}</p>
    </div>
    Total no of following are {{ getFollowing.length }}
  </div>
</template>
<script>
// import ProfileView from "@/views/ProfileView.vue";
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["getFollowing"]),
  },
  methods: {
    ...mapActions(["getFollowingApi"]),
  },
  created() {
    this.$store.dispatch("getFollowingApi", {
      id: localStorage.getItem("userSearch"),
    });
  },
};
</script>
<style></style>
