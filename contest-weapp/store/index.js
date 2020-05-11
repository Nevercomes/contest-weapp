import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import search from './modules/search.js'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    user,
	search
  },
  getters
})

export default store
