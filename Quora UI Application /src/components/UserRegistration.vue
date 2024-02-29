<template>
  <div class="bg">
    <div class="container">
      <img
        src="../assets/quora-ar21-removebg-preview.png"
        style="width: 250px"
      />
      <h2 class="text-center">User Registration</h2>
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="username">Username</label>
          <input
            type="text"
            class="form-control"
            id="username"
            v-model="username"
            required
          />
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            type="password"
            class="form-control"
            id="password"
            v-model="password"
            required
          />
        </div>
        <div class="form-group">
          <label for="firstName">First Name</label>
          <input
            type="text"
            class="form-control"
            id="firstName"
            v-model="firstName"
            required
          />
        </div>
        <div class="form-group">
          <label for="lastName">Last Name</label>
          <input
            type="text"
            class="form-control"
            id="lastName"
            v-model="lastName"
            required
          />
        </div>
        <div class="form-group">
          <label for="phoneNumber">Phone Number</label>
          <input
            type="text"
            class="form-control"
            id="phoneNumber"
            v-model="phoneNumber"
            required
          />
        </div>
        <div class="form-group">
          <label for="email">Email</label>
          <input
            type="email"
            class="form-control"
            id="email"
            v-model="email"
            required
          />
        </div>
        <div class="form-group">
          <label for="dob">Date of Birth</label>
          <input
            type="text"
            class="form-control"
            id="dob"
            v-model="dob"
            required
          />
        </div>
        <div class="form-group">
          <label for="platformId">Platform ID</label>
          <input
            type="text"
            class="form-control"
            id="platformId"
            v-model="platformId"
            required
          />
        </div>
        <div class="form-group form-check">
          <input
            type="checkbox"
            class="form-check-input"
            id="isPrivate"
            v-model="isPrivate"
          />
          <label class="form-check-label" for="isPrivate">Is private?</label>
        </div>
        <div class="form-group">
          <label for="categories">Categories</label>
          <select
            multiple
            class="form-control"
            id="categories"
            v-model="categories"
          >
            <option v-for="category in categoriesList" :key="category">
              {{ category }}
            </option>
          </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <!-- <button type="button" class="btn btn-secondary">Reset</button> -->
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
      firstName: "",
      lastName: "",
      phoneNumber: "",
      email: "",
      dob: "",
      platformId: "quora",
      isPrivate: false,
      categories: [],
      categoriesList: [
        "Technology",
        "Business",
        "Science",
        "Politics",
        "Sports",
      ],
    };
  },
  computed: {
    ...mapGetters(["getRegister"]),
  },
  methods: {
    ...mapActions(["REGISTER"]),
    submitForm() {
      const payloadData = {
        username: this.username,
        password: this.password,
        firstName: this.firstName,
        lastName: this.lastName,
        phoneNumber: this.phoneNumber,
        email: this.email,
        dob: this.dob,
        platformId: this.platformId,
        isPrivate: this.isPrivate,
        categories: this.categories,
      };
      this.$store.dispatch("REGISTER", { payload: payloadData });
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 500px;
  margin: 0 auto;
  margin: 3%;
  padding: 30px;
  text-align: center;
  background-color: rgb(236, 241, 241);
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
}
.form-check-label {
  font-weight: normal;
}

.btn {
  width: 100%;
}
</style>
