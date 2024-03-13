export default class Player {
    CompletionPercentage: number;
    PlayerID: number;
    Name: string;
    FantasyPointsPPR: number;
    Position: string;
    Team: string;
    Number: number;
    PassingYards: number;
    PassingCompletionPercentage: number;
    PassingInterceptions: number;
    PassingTouchdowns: number;
    RushingAttempts: number;
    RushingYards: number;
    RushingTouchdowns: number;
    ReceivingTargets: number;
    Receptions: number;
    ReceivingYards: number;
    ReceivingTouchdowns: number;
    FumblesLost: number;
    FantasyPoints: number;
  
    constructor(data: any) {
      this.CompletionPercentage = data.completionPercentage;
      this.PlayerID = data.PlayerID;
      this.Name = data.Name;
      this.FantasyPointsPPR = data.FantasyPointsPPR;
      this.Position = data.Position;
      this.Team = data.Team;
      this.Number = data.Number;
      this.PassingYards = data.PassingYards;
      this.PassingCompletionPercentage = data.PassingCompletionPercentage;
      this.PassingInterceptions = data.PassingInterceptions;
      this.PassingTouchdowns = data.PassingTouchdowns;
      this.RushingAttempts = data.RushingAttempts;
      this.RushingYards = data.RushingYards;
      this.RushingTouchdowns = data.RushingTouchdowns;
      this.ReceivingTargets = data.ReceivingTargets;
      this.Receptions = data.Receptions;
      this.ReceivingYards = data.ReceivingYards;
      this.ReceivingTouchdowns = data.ReceivingTouchdowns;
      this.FumblesLost = data.FumblesLost;
      this.FantasyPoints = data.FantasyPoints;
    }
  }
  