<template>
  <div id="app">
    <nav>
      <a @click="navigateTo('playertable')">All Players</a>
      <a @click="navigateTo('compareplayer')">Compare Players</a>
    </nav>

    <div v-if="currentComponent === 'playertable'">      
      <PlayerTable></PlayerTable>
    </div>

    <div v-if="currentComponent === 'compareplayer'">
      <ComparePlayer></ComparePlayer>
    </div>
  </div>
</template>

<script>

import Navigation from "./components/Navigation.vue";
import PlayerTable from "./components/PlayerTable.vue";
import ComparePlayer from "./components/ComparePlayer.vue";

export default {
  name:"App",
  components: {
    Navigation,
    PlayerTable,
    ComparePlayer
  },
  data(){
    return {
      currentComponent: 'playertable',
  };
  },
  methods: {
    navigateTo(component) {
      this.currentComponent = component;
    }
  }
};
</script>

<style>
#app {
  font-family: Arial, Helvetica, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>