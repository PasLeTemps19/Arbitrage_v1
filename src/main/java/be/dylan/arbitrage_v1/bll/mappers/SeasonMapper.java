package be.dylan.arbitrage_v1.bll.mappers;

import be.dylan.arbitrage_v1.dal.entities.Season;
import be.dylan.arbitrage_v1.pl.dtos.season.SeasonCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.season.SeasonDetailsDto;
import be.dylan.arbitrage_v1.pl.dtos.season.SeasonIndexDto;
import be.dylan.arbitrage_v1.pl.dtos.season.SeasonUpdateFormDto;

public class SeasonMapper {

    public static Season convertToSeason(SeasonCreateFormDto seasonCreateFormDto) {
        Season season = new Season();
        season.setName(seasonCreateFormDto.getName());
        season.setStartDate(seasonCreateFormDto.getStartDate());
        season.setEndDate(seasonCreateFormDto.getEndDate());
        return season;
    }

    public static SeasonIndexDto convertToSeasonIndexDto(Season season) {
        return new SeasonIndexDto(
                season.getId(),
                season.getName()
        );
    }

    public static SeasonDetailsDto convertToSeasonDetailsDto(Season season) {
        return new SeasonDetailsDto(
                season.getId(),
                season.getName(),
                season.getStartDate(),
                season.getEndDate()
        );
    }

    public static Season convertUpdateToSeason(Season season, SeasonUpdateFormDto seasonUpdateFormDto) {
        season.setName(seasonUpdateFormDto.getName());
        season.setStartDate(seasonUpdateFormDto.getStartDate());
        season.setEndDate(seasonUpdateFormDto.getEndDate());
        return season;
    }

}