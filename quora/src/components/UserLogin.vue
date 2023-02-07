<template>
  <div class="bg">
    <div class="container">
      <img
        src="../assets/quora-ar21-removebg-preview.png"
        style="width: 250px"
      />
      <h2 class="text-center">Login</h2>
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="username">Username</label>
          <input
            type="text"
            class="form-control"
            id="username"
            v-model="username"
          />
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            type="password"
            class="form-control"
            id="password"
            v-model="password"
          />
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <hr class="my-4" />
        <router-link
          class="nav-link d-block text-center mt-2 small link-primary"
          to="/userregistration"
          >Don't have an account? Register !</router-link
        >
      </form>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  computed: {
    ...mapGetters(["getLogin"]),
  },
  methods: {
    ...mapActions(["LOGIN"]),
    submitForm() {
      const payloadData = {
        username: this.username,
        password: this.password,
      };
      this.$store.dispatch("LOGIN", {
        payload: payloadData,
        success: () => {
          this.$router.push("/home");
          // localStorage.setItem("email",)
        },
      });
    },
  },
};
</script>
<style scoped>
.container {
  /* display: flex; */
  max-width: 500px;
  margin: 0 auto;
  padding: 30px;
  top: 200px;
  height: 530px;
  text-align: center;
  background-color: rgb(236, 241, 241);
  border-radius: 10px;
}

h2 {
  margin-bottom: 30px;
}

label {
  font-weight: 600;
}

.form-control {
  margin-bottom: 15px;
}
.bg {
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url("https://qph.cf2.quoracdn.net/main-qimg-b5faebde65208b4e09af0a85b3893ddd-lq");
  width: 100%;
  height: 100vh;
}
.btn {
  width: 100%;
}
</style>
