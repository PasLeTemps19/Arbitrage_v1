package be.dylan.arbitrage_v1.bll.mappers;

import be.dylan.arbitrage_v1.dal.entities.Competition;
import be.dylan.arbitrage_v1.dal.entities.Gymnasium;
import be.dylan.arbitrage_v1.dal.entities.Season;
import be.dylan.arbitrage_v1.pl.dtos.competition.CompetitionCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.competition.CompetitionDetailsDto;
import be.dylan.arbitrage_v1.pl.dtos.competition.CompetitionIndexDto;
import be.dylan.arbitrage_v1.pl.dtos.competition.CompetitionUpdateFormDto;

public class CompetitionMapper {

    public static CompetitionIndexDto convertToCompetitionIndexDto(Competition competition) {
        return new CompetitionIndexDto(
                competition.getId(),
                competition.getName(),
                competition.getDate()
        );
    }

    public static CompetitionDetailsDto convertToCompetitionDetailsDto(Competition competition) {
        return new CompetitionDetailsDto(
                competition.getId(),
                competition.getName(),
                competition.getDate(),
                competition.getTime(),
                competition.getDescription(),
                competition.getSeason().getId(),
                competition.getSeason().getName(),
                competition.getGymnasium().getId(),
                competition.getGymnasium().getName()
        );
    }

    public static Competition convertToCompetition(CompetitionCreateFormDto dto, Season season, Gymnasium gymnasium) {
        Competition competition = new Competition();
        competition.setName(dto.getName());
        competition.setDate(dto.getDate());
        competition.setTime(dto.getTime());
        competition.setDescription(dto.getDescription());
        competition.setSeason(season);
        competition.setGymnasium(gymnasium);
        return competition;
    }

    public static Competition convertUpdateToCompetition(Competition competition, CompetitionUpdateFormDto dto, Season season, Gymnasium gymnasium) {
        competition.setName(dto.getName());
        competition.setDate(dto.getDate());
        competition.setTime(dto.getTime());
        competition.setDescription(dto.getDescription());
        competition.setSeason(season);
        competition.setGymnasium(gymnasium);
        return competition;
    }
}