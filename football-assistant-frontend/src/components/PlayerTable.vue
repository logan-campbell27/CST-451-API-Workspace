<template>
<div>
            <h1>Player List</h1>
            <div>Team: {{ team }}</div>
            <div>Position: {{ position }}</div>

            <select v-model="position">
                <option value="">Position</option>
                <option value="QB">QB</option>
                <option value="RB">RB</option>
                <option value="WR">WR</option>
                <option value="TE">TE</option>
            </select>
            <select v-model="team">
                <option value="">Team</option>
                <option value="CLE">CLE</option>
                <option value="DET">DET</option>
            </select>
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
        <th v-if="position !=='QB' || !position">Receiving Yards</th>
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
    import { ref, onMounted} from 'vue';
    import axios from 'axios';
   
    export default {
    name: 'PlayerTable',

  data() {
    return {
      players: undefined,
      team: ref(''),
      position: ref(''),
    };
  },
  computed: {
    filteredPlayers() {
      if (!this.players) return [];

      return this.players.filter((player) => {
        return (
          (player.Position === this.position || !this.position) &&
          (player.Team === this.team || !this.team)
        );
      });
    },
  },
  mounted() {
    axios
      .get('/api/players')
      .then((resp) => {
        console.warn(resp.data);
        this.players = resp.data;
      })
      .catch((error) => {
        console.error(error);
      });
  },
};
    </script>
    
    <style>
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
    </style>
    