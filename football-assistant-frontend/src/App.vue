<template>
  <div id="app">
    <Navigation></Navigation>
    <PlayerTable></PlayerTable>
  </div>
</template>

<script>

import Navigation from "./components/Navigation.vue";
import PlayerTable from "./components/PlayerTable.vue";


export default {
  name:"App",
  components: {
    Navigation,
    PlayerTable
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