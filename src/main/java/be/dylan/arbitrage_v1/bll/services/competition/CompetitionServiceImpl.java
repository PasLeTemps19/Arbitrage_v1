package be.dylan.arbitrage_v1.bll.services.competition;

import be.dylan.arbitrage_v1.bll.mappers.CompetitionMapper;
import be.dylan.arbitrage_v1.bll.mappers.SeasonMapper;
import be.dylan.arbitrage_v1.bll.services.gymnasium.GymnasiumService;
import be.dylan.arbitrage_v1.bll.services.season.SeasonService;
import be.dylan.arbitrage_v1.dal.entities.Competition;
import be.dylan.arbitrage_v1.dal.entities.Gymnasium;
import be.dylan.arbitrage_v1.dal.entities.Season;
import be.dylan.arbitrage_v1.dal.repositories.CompetitionRepository;
import be.dylan.arbitrage_v1.pl.dtos.competition.CompetitionCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.competition.CompetitionIndexDto;
import be.dylan.arbitrage_v1.pl.dtos.competition.CompetitionUpdateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.gymnasium.GymnasiumIndexDto;
import be.dylan.arbitrage_v1.pl.dtos.season.SeasonIndexDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final SeasonService seasonService;
    private final GymnasiumService gymnasiumService;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository, SeasonService seasonService, GymnasiumService gymnasiumService) {
        this.competitionRepository = competitionRepository;
        this.seasonService = seasonService;
        this.gymnasiumService = gymnasiumService;
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Competition pas trouver"));
    }

    @Override
    public Competition addCompetition(CompetitionCreateFormDto competitionCreateFormDto) {
        Season season = seasonService.getSeasonById(competitionCreateFormDto.getSeasonId());
        Gymnasium gymnasium = gymnasiumService.getGymnasiumById(competitionCreateFormDto.getGymnasiumId());
        Competition competition = CompetitionMapper.convertToCompetition(competitionCreateFormDto, season, gymnasium);
        return competitionRepository.save(competition);
    }

    @Override
    public void deleteCompetitionById(Long id) {
        Competition competition = getCompetitionById(id);
        competitionRepository.delete(competition);
    }

    @Override
    public Competition updateCompetition(Long id, CompetitionUpdateFormDto competitionUpdateFormDto) {
        Competition competition = getCompetitionById(id);
        Season season = seasonService.getSeasonById(competitionUpdateFormDto.getSeasonId());
        Gymnasium gymnasium = gymnasiumService.getGymnasiumById(competitionUpdateFormDto.getGymnasiumId());
        Competition competitionUpdate = CompetitionMapper.convertUpdateToCompetition(competition, competitionUpdateFormDto, season, gymnasium);
        return competitionRepository.save(competitionUpdate);

    }
}
