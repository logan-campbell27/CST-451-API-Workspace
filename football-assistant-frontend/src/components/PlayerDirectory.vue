<template>

<div id="app">
    <h1>Player Directory</h1>
<div id="player-container">
    <div class="card" v-for="player in players" :key="player.id">
        <div class="content">
            <h2 class="player-name">{{ player.Name }}</h2>
            <p>Position: {{ player.Position }}</p>
            <p>Team: {{ player.Team }}</p>
            <p>PPR Points: {{ player.FantasyPointsPPR }}</p>
            <p>Standard Points: {{ player.FantasyPoints }}</p>
            <button>View More Information</button>
        </div>
    </div>
</div>
</div>
   <!-- <div>
        <h1>Player List</h1>
        <table id="players">
            <tr>
                <td>Name</td>
                <td>Position</td>
                <td>Team</td>
                <td>Fantasy Points</td>
                <td>Passing Yards</td>
                <td>Passing Touchdowns</td>
                <td>Rushing Yards</td>
                <td>Rushing Touchdowns</td>
                <td>Receiving Yards</td>
                <td>Receiving Touchdowns</td>
            </tr>
            <tr id="player" v-for="player in players" v-bind:key="player.id">
                <td>{{player.Name}}</td>
                <td>{{player.Position}}</td>
                <td>{{player.Team}}</td>
                <td>{{player.FantasyPointsPPR}}</td>
                <td>{{player.PassingYards}}</td>
                <td>{{player.PassingTouchdowns}}</td>
                <td>{{player.RushingYards}}</td>
                <td>{{player.RushingTouchdowns}}</td>
                <td>{{player.ReceivingYards}}</td>
                <td>{{player.ReceivingTouchdowns}}</td>
            </tr>
        </table>
    </div> -->
</template>

<script>
import { ref, onMounted} from 'vue';
import axios from 'axios';
    export default {
        name:"PlayerDirectory",
        data(){
            return {players:undefined}
        },
        mounted(){
            axios
            .get('/api/players')
            .then((resp)=>{
                console.warn(resp.data)
                this.players = resp.data
            })
            .catch((error)=>{
                console.error(error)
            });
        }
    }
</script>

<!--<style>
    #players{
        width: 80%;
        text-align: center;
        margin-left: auto;
        margin-right: auto;
        font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
        color: black;
        font-size: 12px;
    }
    #player:hover{
        background: lightgrey;
    }
    table{
        border-collapse: collapse;
        width: 100%;
    }
    tr, th{
        border: 2px solid rgb(0, 150, 244);
        padding: 8px;
    }
</style>-->
<style>
body {
  background: #20262E;
  padding: 20px;
  font-family: Helvetica;
}

#app {
  background: #fff;
  border-radius: 4px;
  padding: 20px;
  transition: all 0.2s;
}
h2 {
  font-weight: bold;
  margin-bottom: 15px;
}

h3 {
  font-weight: 600;
  font-size: 16px;
}

#player-container {
  display: flex;
  flex-wrap: wrap;
  justify-items: center;
}

.card {
  border-radius: 5px;
  box-shadow: rgba(0, 0, 0, 0.3) 0 5px 10px;
  margin: 10px;
  width: 18%;
}

.content {
  padding: 30px;
}

.recipte-title {
  font-size: 18px;
  font-weight: 600;
}


.ingredient-title {
  font-size: 16px;
  font-weight: 600;
  margin-top: 20px;
}

.recipe-image {
  width: 100%;
  max-height: 200px;
  padding: -10px -10px;;
}

#sort-bar {
  width: 80%;
  height: 80px;
  margin-left: 10px;
  background-color: #f2e0c1;
  display: flex;
  flex-wrap: wrap;
  padding: 10px;
}

.sort-button {
  background-color: rgba(0,0,0,0);
  border: none;
 height: 50px;
  height: 100%;
  width: 50px;
}
 
  #sort-label {
    font-size: 12px;
  }

#ascending-icon {
  height: 30px;
  height: 100%;
  width: 30px;
}

#select {
  background-color: rgba(0,0,0, 0);
  border: none;
}
  
  #cooking-time-input {
    width: 30px;
    margin-right: 10px;
  }

  #search-input {
    margin-right: 10px;
  }
</style>