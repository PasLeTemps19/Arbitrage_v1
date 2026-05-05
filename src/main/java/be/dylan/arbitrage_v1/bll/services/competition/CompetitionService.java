package be.dylan.arbitrage_v1.bll.services.competition;

import be.dylan.arbitrage_v1.dal.entities.Competition;
import be.dylan.arbitrage_v1.pl.dtos.competition.CompetitionCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.competition.CompetitionUpdateFormDto;

import java.util.List;

public interface CompetitionService {
    List<Competition> getAllCompetitions();
    Competition getCompetitionById(Long id);
    Competition addCompetition(CompetitionCreateFormDto competitionCreateFormDto);
    void deleteCompetitionById(Long id);
    Competition updateCompetition(Long id, CompetitionUpdateFormDto competitionUpdateFormDto);
}
