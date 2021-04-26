package dto;

public class CountryDto {
    public Long id;
    public String name;
    public String shortName;

    public CountryDto(Long id, String name, String shortName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
    }

    public CountryDto(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }
}
