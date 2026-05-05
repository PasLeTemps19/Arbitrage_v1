package be.dylan.arbitrage_v1.bll.services.season;

import be.dylan.arbitrage_v1.dal.entities.Season;
import be.dylan.arbitrage_v1.pl.dtos.season.SeasonCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.season.SeasonUpdateFormDto;

import java.util.List;

public interface SeasonService {
    List<Season> getAllSeasons();
    Season getSeasonById(Long id);
    Season addSeason(SeasonCreateFormDto seasonCreateFormDto);
    void deleteSeasonById(Long id);
    Season updateSeason(Long id, SeasonUpdateFormDto seasonUpdateFormDto);
}
