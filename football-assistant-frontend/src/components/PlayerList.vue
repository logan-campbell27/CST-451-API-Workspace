<template>
    <div>
        <h1>Player List</h1>
    </div>
</template>

<script>
import { ref, onMounted} from 'vue';
import axios from 'axios';
    export default {
        name:"PlayerList",

        mounted(){
            axios
            .get('/api/players')
            .then((resp)=>{
                console.warn(resp)
            })
            .catch((error)=>{
                console.error(error)
            });
        }
    }
</script>