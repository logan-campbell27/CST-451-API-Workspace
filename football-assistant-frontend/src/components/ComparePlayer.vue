<template>
<div>
    <div id="player-container">
      <div class="card" v-for="player in comparedPlayers" :key="player.index">
          <div class="content">
              <h2 class="player-name">{{ player.Name }}</h2>
              <p>Position: {{ player.Position }}</p>
              <p>Team: {{ player.Team }}</p>
              <p>PPR Points: {{ player.FantasyPointsPPR }}</p>
              <p>Standard Points: {{ player.FantasyPoints }}</p>
              <p v-if="player.Position === 'QB'">Completion Percentage: {{ player.PassingCompletionPercentage }}</p>
              <p v-if="player.Position === 'QB' || !player.Position">Passing Yards: {{ player.PassingYards }}</p>
              <p v-if="player.Position === 'QB' || !player.Position">Passing Touchdowns: {{ player.PassingTouchdowns }}</p>
              <p v-if="player.Position === 'QB'">Interceptions: {{ player.PassingInterceptions }}</p>
              <p v-if="player.Position === 'RB'">Rushing Attempts: {{ player.RushingAttempts }}</p>
              <p v-if="player.Position === 'RB' || !player.Position">Rushing Yards: {{ player.RushingYards }}</p>
              <p v-if="player.Position === 'RB' || !player.Position">Rushing Touchdowns: {{ player.RushingTouchdowns }}</p>
              <p v-if="player.Position !== 'QB' && player.Position">Targets: {{ player.ReceivingTargets }}</p>
              <p v-if="player.Position !== 'QB' && player.Position">Receptions: {{ player.Receptions }}</p>
              <p v-if="player.Position !== 'QB' || !player.Position">Receiving Yards: {{ player.ReceivingYards }}</p>
              <p v-if="player.Position !== 'QB' || !player.Position">Receiving Touchdowns: {{ player.ReceivingTouchdowns }}</p>
              <p>Fumbles: {{ player.FumblesLost }}</p>
              <button @click="DeselectPlayer(player.Name)">Deselect Player</button>
          </div>
      </div>
    </div>
    <h2>Compare Players</h2>

    <select v-model="position">
      <option value="">Position</option>
      <option value="QB">QB</option>
      <option value="RB">RB</option>
      <option value="WR">WR</option>
      <option value="TE">TE</option>
    </select>
    <select v-model="team">
      <option value="">Team</option>
      <option value="ARI">ARI</option>
      <option value="ATL">ATL</option>
      <option value="BAL">BAL</option>
      <option value="BUF">BUF</option>
      <option value="CAR">CAR</option>
      <option value="CHI">CHI</option>
      <option value="CIN">CIN</option>
      <option value="CLE">CLE</option>
      <option value="DAL">DAL</option>
      <option value="DEN">DEN</option>
      <option value="DET">DET</option>
      <option value="GB">GB</option>
      <option value="HOU">HOU</option>
      <option value="IND">IND</option>
      <option value="JAX">JAX</option>
      <option value="KC">KC</option>
      <option value="LAC">LAC</option>
      <option value="LAR">LAR</option>
      <option value="LV">LV</option>
      <option value="MIA">MIA</option>
      <option value="MIN">MIN</option>
      <option value="NE">NE</option>
      <option value="NO">NO</option>
      <option value="NYG">NYG</option>
      <option value="NYJ">NYJ</option>
      <option value="PHI">PHI</option>
      <option value="PIT">PIT</option>
      <option value="SF">SF</option>
      <option value="SEA">SEA</option>
      <option value="TB">TB</option>
      <option value="TEN">TEN</option>
      <option value="WAS">WAS</option>
    </select>
    <select v-model="sort" @change="sortPlayers">
      <option value="">Sort By</option>
      <option value="Name">Name</option>
      <option value="FantasyPointsPPR">Points</option>
      <option v-if="position === 'QB' || !position" value="PassingYards">Passing Yards</option>
      <option v-if="position === 'QB'" value="PassingCompletionPercentage">Completion Percentage</option>
      <option v-if="position !== 'QB' || !position" value="ReceivingYards">Receiving Yards</option>
      <option v-if="position === 'RB' || !position" value="RushingYards">Rushing Yards</option>
      <option v-if="position !== 'QB' || !position" value="ReceivingTouchdowns">Receiving Touchdowns</option>
      <option v-if="position === 'RB' || !position" value="RushingTouchdowns">Rushing Touchdowns</option>
      <option v-if="position === 'QB' || !position" value="PassingTouchdowns">Passing Touchdowns</option>
      <option v-if="position !== 'QB'" value="Receptions">Receptions</option>
      <option v-if="position !== 'QB'" value="ReceivingTargets">Targets</option>
      <option v-if="position === 'RB'" value="RushingAttempts">Rushing Attempts</option>
      <option v-if="position === 'QB' || !position" value="PassingInterceptions">Interceptions</option>
      <option value="FumblesLost">Fumbles</option>
    </select>
    <input type="text" v-model="SearchText" id="SearchPlayer" @input="filterPlayers()" placeholder="Search For Player">
    <div id="player-container">
        <div class="card" v-for="player in filteredPlayers" :key="player.id">
            <div class="content">
            <h2 class="player-name">{{ player.Name }}</h2>
            <p>Position: {{ player.Position }}</p>
            <p>Team: {{ player.Team }}</p>
            <p>PPR Points: {{ player.FantasyPointsPPR }}</p>
            <p>Standard Points: {{ player.FantasyPoints }}</p>
            <button @click="SelectPlayer(player.Name)">View More Information</button>
        </div>
    </div>
</div>

</div>
</template>
<script>
import { ref, onMounted} from 'vue';
import axios from 'axios';
import Player from './Player';

export default{
    
    name: "ComparePlayer",
        data(){
            return {
                players:[],
                comparedPlayers: [],
                team: ref(''),
                position: ref(''),
                sort: ref(''),
                SearchText: ref('')
            }
        },
        computed: {
            // Dynamically filter players based on drop down selection
    filteredPlayers() {
      if (!this.players) return [];
      return this.players.filter((player) => {
        return (
          (player.Position === this.position || !this.position) &&
          (player.Team === this.team || !this.team) &&
          player.Name.toLowerCase().includes(this.SearchText.toLowerCase())
        );
      });
    },
  },
        mounted() {
            //API call to populate data
    axios
      .get('/api/players')
      .then((resp) => {
        console.warn(resp.data);
        this.players = resp.data.map((playerData) => new Player(playerData));
        this.players = this.players.sort((a, b) => {
       return a.Name.localeCompare(b.Name);
      });
      })
      .catch((error) => {
        console.error(error);
      });
  },
        methods: {
            // Method to view details of a player by clicking select button
            SelectPlayer(Name) {
                this.players.forEach((player) => {
                    if (player.Name === Name && this.comparedPlayers.length < 5) {
                        this.comparedPlayers.push(player);
                    }
              });       
            },
            // Method to deselect a player and remove them from the compare screen at the top
            DeselectPlayer(Name){
                let tempList = [];
                this.comparedPlayers.forEach((player) =>{
                    if(player.Name !== Name){
                        tempList.push(player);
                    }
                })
                this.comparedPlayers = tempList;

            },
            // This will sort players based on selected parameter
    sortPlayers(){
      if (!this.players || !this.sort) return;

this.players = this.players.sort((a, b) => {
  // Checks if it is sorted by name to make sure it does the proper algorithm
  if (this.sort === 'Name' || this.sort === "") {
    return a.Name.localeCompare(b.Name);
  } else {
    //Sorts on any other attribute and its numberic value
    return b[this.sort] - a[this.sort];
  }
});

console.log(this.players);
return this.players;
  },
        }
}
</script>
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