import Vue from "vue";
import Vuex from "vuex";
import profileView from "./modules/profileView";
import homeView from "./modules/homeView";
import searchView from "./modules/searchView";
Vue.use(Vuex);

export default new Vuex.Store({
  modules: { profileView, homeView, searchView },
});
