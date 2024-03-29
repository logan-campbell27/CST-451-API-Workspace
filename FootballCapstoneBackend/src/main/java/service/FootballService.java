package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import models.Player;

@Service
public class FootballService {

	public List<Player> getPlayers() {
		
		// URL and Key to access API
		String FantasyURL = "https://api.sportsdata.io/api/nfl/fantasy/json/PlayerSeasonStats/2023";
		String key = "afef772de80a4dc18df8a05edef44227";
		
		//Create the builder to make API call
		WebClient.Builder builder = WebClient.builder();
		
		//Generate list of players from API
		List<Player> PlayerList = (List<Player>) builder.build().
				get()
				.uri(FantasyURL)
				.header("Ocp-Apim-Subscription-Key", key)
				.retrieve()
				.bodyToFlux(Player.class)
				.collectList()
				.block();
		
		//Filter players down to only positions and required minimum point values
		List<Player> FilteredPlayers = new ArrayList<Player>();
		this.FilterPlayers(PlayerList, 100f).forEach(p -> FilteredPlayers.add(p));
		System.out.println(FilteredPlayers.size()+" Players");
		return FilteredPlayers;
	}
	
	
	
	//Method to filter players down to only those above the set point value
	public List<Player> FilterPlayers(List<Player> AllPlayers, Float PointParameter){
		
		//Create list of players to be edited and returned
		ArrayList<Player> FilteredPlayers = new ArrayList<Player>();
		
		//List of positions
		ArrayList<String> Positions = new ArrayList<String>();
		Positions.add("QB");
		Positions.add("RB");
		Positions.add("TE");
		Positions.add("WR");
		
		//Iterate through list of all players and get only those who meet the position and point requirement
		for(Player PosPlayer: AllPlayers) {
			if(Positions.indexOf(PosPlayer.getPosition()) >=0 && PosPlayer.getPointsPerReception() >= PointParameter) {
				FilteredPlayers.add(PosPlayer);
			}
		}
		return FilteredPlayers;
	}

}
