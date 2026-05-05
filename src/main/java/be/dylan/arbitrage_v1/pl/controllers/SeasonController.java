package be.dylan.arbitrage_v1.pl.controllers;

import be.dylan.arbitrage_v1.bll.mappers.SeasonMapper;
import be.dylan.arbitrage_v1.bll.services.season.SeasonService;
import be.dylan.arbitrage_v1.dal.entities.Season;
import be.dylan.arbitrage_v1.pl.dtos.season.SeasonCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.season.SeasonDetailsDto;
import be.dylan.arbitrage_v1.pl.dtos.season.SeasonIndexDto;
import be.dylan.arbitrage_v1.pl.dtos.season.SeasonUpdateFormDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seasons")
@RequiredArgsConstructor
public class SeasonController {
    private final SeasonService seasonService;

    @GetMapping
    public ResponseEntity<List<SeasonIndexDto>> findAll() {
        List<SeasonIndexDto> seasons = seasonService.getAllSeasons()
                .stream()
                .map(SeasonMapper::convertToSeasonIndexDto)
                .toList();
        return ResponseEntity.ok(seasons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeasonDetailsDto> findById(@PathVariable Long id) {
        SeasonDetailsDto seasonDetailsDto = SeasonMapper.convertToSeasonDetailsDto(seasonService.getSeasonById(id));
        return ResponseEntity.ok(seasonDetailsDto);

    }
    @PostMapping("/create")
    public ResponseEntity<SeasonDetailsDto> create(@RequestBody  @Valid  SeasonCreateFormDto seasonCreateFormDto) {
        Season seasonCreate = seasonService.addSeason(seasonCreateFormDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(SeasonMapper.convertToSeasonDetailsDto(seasonCreate));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        seasonService.deleteSeasonById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<SeasonDetailsDto> update(@PathVariable Long id, @RequestBody @Valid SeasonUpdateFormDto seasonUpdateFormDto) {
        Season seasonUpdate = seasonService.updateSeason(id, seasonUpdateFormDto);
        return  ResponseEntity.ok(SeasonMapper.convertToSeasonDetailsDto(seasonUpdate));
    }
}
