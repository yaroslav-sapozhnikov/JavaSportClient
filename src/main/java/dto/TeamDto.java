package dto;

public class TeamDto {
    public Long id;
    public String name;
    public String shortName;
    public LeagueDto league;

    public TeamDto(String name, String shortName, LeagueDto leagueDto) {
        this.name = name;
        this.shortName = shortName;
        this.league = leagueDto;
    }

    public TeamDto(Long id, String name, String shortName, LeagueDto leagueDto) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.league = leagueDto;
    }

    public TeamDto(String s, LeagueDto leagueDto) {
    }
}
