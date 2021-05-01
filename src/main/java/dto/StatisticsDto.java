package dto;

public class StatisticsDto {
    public Long id;
    public int ballPossession;
    public int totalShots;
    public int shotsOnGoals;
    public int freeKicks;
    public int corners;
    public int fouls;
    public int offsides;
    public int yellowCards;
    public int redCards;

    public StatisticsDto(Long id, int ballPossession, int totalShots, int shotsOnGoals, int freeKicks, int corners, int fouls, int offsides, int yellowCards, int redCards) {
        this.id = id;
        this.ballPossession = ballPossession;
        this.totalShots = totalShots;
        this.shotsOnGoals = shotsOnGoals;
        this.freeKicks = freeKicks;
        this.corners = corners;
        this.fouls = fouls;
        this.offsides = offsides;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
    }

    public StatisticsDto(int ballPossession, int totalShots, int shotsOnGoals, int freeKicks, int corners, int fouls, int offsides, int yellowCards, int redCards) {
        this.ballPossession = ballPossession;
        this.totalShots = totalShots;
        this.shotsOnGoals = shotsOnGoals;
        this.freeKicks = freeKicks;
        this.corners = corners;
        this.fouls = fouls;
        this.offsides = offsides;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
    }

    public StatisticsDto(Long id) {
        this.id = id;
    }
}
