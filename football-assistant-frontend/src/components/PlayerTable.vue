<template>
<div>
            <h1>Player List</h1>
            <div>Team: {{ team }}</div>
            <div>Position: {{ position }}</div>

            <select v-model="position">
                <option value="undefined">Position</option>
                <option value="QB">QB</option>
                <option value="RB">RB</option>
                <option value="WR">WR</option>
                <option value="TE">TE</option>
            </select>
            <select v-model="team">
                <option value="undefined">Team</option>
                <option>CLE</option>
                <option>DET</option>
            </select>
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
                    <div v-if="(player.Position == position || position == undefined ) && (player.Team == team || team == undefined)">
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
                        </div>
                    

                </tr>
            </table>
        </div>
    </template>
    
    <script>
    import { ref, onMounted} from 'vue';
    import axios from 'axios';

    const team = ref('')
    const position = ref('')

        export default {
            name:"PlayerTable",
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
    