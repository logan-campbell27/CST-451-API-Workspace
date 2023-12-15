package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Player;
import service.FootballService;

@RestController
public class FootballController {
	
	private FootballService playerService;
	
	@Autowired
	public FootballController(FootballService footballService) {
		this.playerService = footballService;
	}
	
	
	//Getting all the players that we will be using
	@GetMapping("/players")
	public List<Player> getPlayers(){
		return this.playerService.getPlayers();
	}
}
