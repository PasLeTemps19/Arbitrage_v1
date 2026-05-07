package be.dylan.arbitrage_v1.bll.services.season;

import be.dylan.arbitrage_v1.bll.mappers.SeasonMapper;
import be.dylan.arbitrage_v1.dal.entities.Season;
import be.dylan.arbitrage_v1.dal.repositories.SeasonRepository;
import be.dylan.arbitrage_v1.pl.dtos.season.SeasonCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.season.SeasonUpdateFormDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonServiceImpl implements SeasonService {
    private final SeasonRepository seasonRepository;

    public SeasonServiceImpl(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }


    @Override
    public List<Season> getAllSeasons() {

        return seasonRepository.findAll();
    }

    @Override
    public Season getSeasonById(Long id) {
        return seasonRepository.findById(id).orElseThrow(() -> new RuntimeException("Saison pas trouvé"));
    }

    @Override
    public Season addSeason(SeasonCreateFormDto seasonCreateFormDto) {
        if(seasonCreateFormDto.getStartDate().isAfter(seasonCreateFormDto.getEndDate())) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin.");
        }
        Season season = SeasonMapper.convertToSeason(seasonCreateFormDto);
        return seasonRepository.save(season);
    }

    @Override
    public void deleteSeasonById(Long id) {
        Season season = getSeasonById(id);
        seasonRepository.delete(season);

    }

    @Override
    public Season updateSeason(Long id,SeasonUpdateFormDto seasonUpdateFormDto) {
        Season season =getSeasonById(id);
        Season seasonUpdate = SeasonMapper.convertUpdateToSeason(season, seasonUpdateFormDto);
        return seasonRepository.save(seasonUpdate);


    }
}
