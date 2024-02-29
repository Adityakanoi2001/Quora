<template>
  <div style="margin-left: 30%; margin-right: 30%; margin-top: 2%">
    <div
      class="main"
      v-for="(users, index) in getUserList"
      :key="index"
      @click="searchInput(users)"
    >
      <div class="row">
        <div class="column" style="padding-top: 5px">
          <img :src="users.img" style="width: 80px" />
        </div>
        <div class="column">
          <b
            ><p style="padding-top: 25px">{{ users.userName }}</p></b
          >
        </div>
        <div class="column">
          <p style="padding-top: 25px">{{ users.bio }}</p>
        </div>
      </div>
      <!-- {{ getUserList }} -->
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters(["getUserList"]),
  },
  data() {
    return {
      searchText: this.$route.params.searchText,
    };
  },
  created() {
    this.$store.dispatch("SEARCH_USER_LIST", {
      userName: this.searchText,
    });
  },
  methods: {
    ...mapActions(["SEARCH_USER_LIST"]),
    searchInput(users) {
      localStorage.setItem("userSearch", users.userId);
      this.$router.push("/profileanother");
    },
  },
};
</script>
<style scoped>
.main {
  border: 1.4px #ced5da solid;
  margin-top: 10px;
  background-color: white;
  border-radius: 6px;
}
.column {
  float: left;
  width: 33.33%;
  height: 90px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}
</style>
