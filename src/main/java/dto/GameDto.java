package dto;

public class GameDto {

    public Long id;
    public TeamDto homeTeam;
    public TeamDto guestTeam;
    public int homeCount;
    public int guestCount;
    public StatisticsDto homeStatistics;
    public StatisticsDto guestStatistics;

    public GameDto(Long id, TeamDto homeTeam, TeamDto guestTeam, int homeCount, int guestCount, StatisticsDto homeStatistics, StatisticsDto guestStatistics) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.homeCount = homeCount;
        this.guestCount = guestCount;
        this.homeStatistics = homeStatistics;
        this.guestStatistics = guestStatistics;
    }

    public GameDto(TeamDto homeTeam, TeamDto guestTeam, int homeCount, int guestCount, StatisticsDto homeStatistics, StatisticsDto guestStatistics) {
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.homeCount = homeCount;
        this.guestCount = guestCount;
        this.homeStatistics = homeStatistics;
        this.guestStatistics = guestStatistics;
    }
}
