package dto;

public class LeagueDto {
    public Long id;
    public String name;
    public CountryDto country;

    public LeagueDto(Long id, String name, CountryDto country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public LeagueDto(String name, CountryDto country) {
        this.name = name;
        this.country = country;
    }
}
