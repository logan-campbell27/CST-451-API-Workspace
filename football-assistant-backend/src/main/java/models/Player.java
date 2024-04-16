package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {
	
	@JsonProperty("PlayerID")
	int playerID;
	
	@JsonProperty("Name")
	String name;
	
	@JsonProperty("FantasyPointsPPR")
	Float pointsPerReception;
	
	@JsonProperty("Position")
	String position;
	
	@JsonProperty("Team")
	String team;
	
	@JsonProperty("Number")
	int number;
	
	@JsonProperty("PassingYards")
	int passingYards;
	
	@JsonProperty("PassingCompletionPercentage")
	Float passingCompletionPercentage;
	
	@JsonProperty("PassingInterceptions")
	int passingInterceptions;
	
	@JsonProperty("PassingTouchdowns")
	int passingTouchdowns;
	
	@JsonProperty("RushingAttempts")
	int rushingAttempts;
	
	@JsonProperty("RushingYards")
	double rushingYards;
	
	@JsonProperty("RushingTouchdowns")
	int rushingTouchdowns;
	
	@JsonProperty("ReceivingTargets")
	int receivingTargets;
	
	@JsonProperty("Receptions")
	int receptions;
	
	@JsonProperty("ReceivingYards")
	double receivingYards;
	
	@JsonProperty("ReceivingTouchdowns")
	int receivingTouchdowns;
	
	@JsonProperty("FumblesLost")
	int fumblesLost;
	
	@JsonProperty("FantasyPoints")
	Float fantasyPoints;
	
	

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPassingYards() {
		return passingYards;
	}

	public void setPassingYards(int passingYards) {
		this.passingYards = passingYards;
	}

	public Float getCompletionPercentage() {
		return passingCompletionPercentage;
	}

	public void setCompletionPercentage(Float completionPercentage) {
		this.passingCompletionPercentage = completionPercentage;
	}

	public int getPassingInterceptions() {
		return passingInterceptions;
	}

	public void setPassingInterceptions(int passingInterceptions) {
		this.passingInterceptions = passingInterceptions;
	}

	public int getPassingTouchdowns() {
		return passingTouchdowns;
	}

	public void setPassingTouchdowns(int passingTouchdowns) {
		this.passingTouchdowns = passingTouchdowns;
	}

	public double getRushingYards() {
		return rushingYards;
	}

	public void setRushingYards(double rushingYards) {
		this.rushingYards = rushingYards;
	}

	public int getRushingTouchdowns() {
		return rushingTouchdowns;
	}

	public void setRushingTouchdowns(int rushingTouchdowns) {
		this.rushingTouchdowns = rushingTouchdowns;
	}

	public int getReceivingTargets() {
		return receivingTargets;
	}

	public void setReceivingTargets(int receivingTargets) {
		this.receivingTargets = receivingTargets;
	}

	public int getReceptions() {
		return receptions;
	}

	public void setReceptions(int receptions) {
		this.receptions = receptions;
	}

	public double getReceivingYards() {
		return receivingYards;
	}

	public void setReceivingYards(double receivingYards) {
		this.receivingYards = receivingYards;
	}

	public int getReceivingTouchdowns() {
		return receivingTouchdowns;
	}

	public void setReceivingTouchdowns(int receivingTouchdowns) {
		this.receivingTouchdowns = receivingTouchdowns;
	}

	public int getFumblesLost() {
		return fumblesLost;
	}

	public void setFumblesLost(int fumblesLost) {
		this.fumblesLost = fumblesLost;
	}

	public Float getFantasyPoints() {
		return fantasyPoints;
	}

	public void setFantasyPoints(Float fantasyPoints) {
		this.fantasyPoints = fantasyPoints;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String Position) {
		position = Position;
	}

	public Float getPointsPerReception() {
		return pointsPerReception;
	}

	public void setPointsPerReception(Float PointsPerReception) {
		pointsPerReception = PointsPerReception;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String Name) {
		name = Name;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int PlayerID) {
		playerID = PlayerID;
	}

	@Override
	public String toString() {
		return "Player [PlayerID=" + playerID + ", Name=" + name + ", PointsPerReception=" + pointsPerReception
				+ ", Position=" + position + "]";
	}
	
	
}
