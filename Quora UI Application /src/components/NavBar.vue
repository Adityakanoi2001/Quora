<template>
  <nav class="navbar">
    <div class="left-section">
      <router-link class="nav-link" to="/home">
        <img
          src="https://www.quora.com/favicon.ico"
          style="height: 53px"
          alt="Quora Logo"
        />
      </router-link>
      <form class="search-form">
        <input
          type="text"
          class="search-input"
          v-model="searchText"
          placeholder="Search Quora"
        />
        <button type="submit" class="search-button" @click="submit">
          <i class="fa fa-search"></i>
        </button>
      </form>
    </div>
    <div class="right-section">
      <ul class="nav-links">
        <router-link to="/home" style="text-decoration: none; color: inherit">
          <li class="nav-link">Home</li></router-link
        >
        <!-- <li class="nav-link"><a href="#">Topics</a></li>
        <li class="nav-link"><a href="#">Notifications</a></li>
        <li class="nav-link"><a href="#">Messages</a></li> -->
      </ul>
      <ul class="nav-links">
        <router-link
          to="/answeranyquestion"
          style="text-decoration: none; color: inherit"
        >
          <li class="nav-link">Answer a Question</li></router-link
        >
      </ul>
      <div class="user-actions">
        <router-link
          to="/postquestion"
          style="text-decoration: none; color: inherit"
        >
          <button class="write-question-button">
            Write a Question
          </button></router-link
        >
        <img
          src="../assets/user.png"
          alt="User Avatar"
          class="user-avatar"
          style="padding-top: 7px"
          @click="goprofile()"
        />
      </div>
    </div>
    <!-- <router-view /> -->
  </nav>
</template>
<script>
export default {
  methods: {
    submit() {
      this.$router.push(`/search/${this.searchText}`);
    },
    goprofile() {
      // localStorage.setItem("email", "63dc9b4ccfd535353bd2e883");
      localStorage.setItem("name", "user");
      this.$router.push("/profile");
      console.log(this.$router);
    },
  },
};
</script>
<style scoped>
.navbar {
  background-color: white;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
}

.left-section {
  display: flex;
  align-items: center;
}

.logo {
  display: flex;
  align-items: center;
}

.logo img {
  height: 40px;
}

.search-form {
  display: flex;
  align-items: center;
  background-color: #fff;
  border-radius: 24px;
  border: 1px gray solid;
  padding: 0 10px;
}

.search-input {
  border: none;
  padding: 8px 12px;
  font-size: 16px;
  width: 400px;
  outline: none;
  border-radius: 8px;
}

.search-button {
  background-color: #b92b28;
  border: none;
  color: #fff;
  padding: 8px 12px;
  border-radius: 24px;
  cursor: pointer;
  font-size: 16px;
}

.right-section {
  display: flex;
  align-items: center;
}

.nav-links {
  list-style: none;
  margin: 0;
  padding: 0;
  display: flex;
}

.nav-link {
  margin-right: 20px;
}

.nav-link a {
  color: #333;
  text-decoration: none;
  font-size: 16px;
}

.user-actions {
  display: flex;
  align-items: center;
}

.write-question-button {
  background-color: #b92b28;
  color: #fff;
  border: none;
  padding: 8px 12px;
  border-radius: 24px;
  cursor: pointer;
  font-size: 16px;
  margin-right: 20px;
}

.user-avatar {
  height: 40px;
  width: 40px;
  border-radius: 50%;
  cursor: pointer;
}
</style>
