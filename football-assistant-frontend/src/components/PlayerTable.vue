<template>
  <div>
    <h1>Player List</h1>

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

    <table id="players">
      <tr>
        <th>Name</th>
        <th>Position</th>
        <th>Team</th>
        <th>Fantasy Points</th>
        <th v-if="position === 'QB'">Completion Percentage</th>
        <th v-if="position === 'QB' || !position">Passing Yards</th>
        <th v-if="position === 'QB' || !position">Passing Touchdowns</th>
        <th v-if="position === 'QB'">Interceptions</th>
        <th v-if="position === 'RB'">Rushing Attempts</th>
        <th v-if="position === 'RB' || !position">Rushing Yards</th>
        <th v-if="position === 'RB' || !position">Rushing Touchdowns</th>
        <th v-if="position !== 'QB' && position">Targets</th>
        <th v-if="position !== 'QB' && position">Receptions</th>
        <th v-if="position !== 'QB' || !position">Receiving Yards</th>
        <th v-if="position !== 'QB' || !position">Receiving Touchdowns</th>
        <th>Fumbles</th>
      </tr>
      <tr v-for="player in filteredPlayers" :key="player.id" id="player">
        <td>{{ player.Name }}</td>
        <td>{{ player.Position }} </td>
        <td>{{ player.Team }}</td>
        <td>{{ player.FantasyPointsPPR }}</td>
        <td v-if="position === 'QB'">{{ player.PassingCompletionPercentage }}</td>
        <td v-if="position === 'QB' || !position">{{ player.PassingYards }}</td>
        <td v-if="position === 'QB' || !position">{{ player.PassingTouchdowns }}</td>
        <td v-if="position === 'QB'">{{ player.PassingInterceptions }}</td>
        <td v-if="position === 'RB'">{{ player.RushingAttempts }}</td>
        <td v-if="position === 'RB' || !position">{{ player.RushingYards }}</td>
        <td v-if="position === 'RB' || !position">{{ player.RushingTouchdowns }}</td>
        <td v-if="position !== 'QB' && position">{{ player.ReceivingTargets }}</td>
        <td v-if="position !== 'QB' && position">{{ player.Receptions }}</td>
        <td v-if="position !== 'QB' || !position">{{ player.ReceivingYards }}</td>
        <td v-if="position !== 'QB' || !position">{{ player.ReceivingTouchdowns }}</td>
        <td>{{ player.FumblesLost }}</td>
      </tr>
    </table>
  </div>
</template>
    
<script>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import Player from './Player';

export default {
  name: 'PlayerTable',

  data() {
    return {
      players: undefined,
      team: ref(''),
      position: ref(''),
      SearchText: '',
      sort: ref('')
    };
  },
  computed: {
    // Will dynamically filter players as user selects filter options
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
    sortPlayers(){
      if (!this.players || !this.sort) return;

this.players = this.players.sort((a, b) => {
  if (this.sort === 'Name' || this.sort === "") {
    return a.Name.localeCompare(b.Name);
  } else {
    return b[this.sort] - a[this.sort];
  }
});

console.log(this.players);
return this.players;
  },
  },
  mounted() {
    // Populating data from the API
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
};
</script>
    
<style>

#players {
  text-align: center;
  margin-left: auto;
  margin-right: auto;
  font-family: Verdana, sans-serif;
  color: black;
  font-size: 12px;

}
table {
  width: 80%;
  border-collapse: collapse;
}
tr, th {
  border: 2px solid rgb(0, 150, 244);

}

#players th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: rgb(0,150,244);
  color: white;
}
#players td{
  padding-top: 6px;
  padding-bottom: 6px;
}

#player{
background-color: lightgrey;
}

#player:hover {
  background: grey;
}
</style>
    