package be.dylan.arbitrage_v1.pl.controllers;

import be.dylan.arbitrage_v1.bll.mappers.CompetitionMapper;
import be.dylan.arbitrage_v1.bll.services.competition.CompetitionService;
import be.dylan.arbitrage_v1.dal.entities.Competition;
import be.dylan.arbitrage_v1.pl.dtos.competition.CompetitionCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.competition.CompetitionDetailsDto;
import be.dylan.arbitrage_v1.pl.dtos.competition.CompetitionIndexDto;
import be.dylan.arbitrage_v1.pl.dtos.competition.CompetitionUpdateFormDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competitions")
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;

    @GetMapping
    public ResponseEntity<List<CompetitionIndexDto>> findAll() {
        List<CompetitionIndexDto> competitions = competitionService.getAllCompetitions()
                .stream()
                .map(CompetitionMapper::convertToCompetitionIndexDto)
                .toList();
        return ResponseEntity.ok(competitions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDetailsDto> findById(@PathVariable Long id) {
        CompetitionDetailsDto competitionDetailsDto = CompetitionMapper.convertToCompetitionDetailsDto(competitionService.getCompetitionById(id));
        return ResponseEntity.ok(competitionDetailsDto);
    }

    @PostMapping("/create")
    public ResponseEntity<CompetitionDetailsDto> create(@RequestBody @Valid CompetitionCreateFormDto competitionCreateFormDto) {
        Competition competitionCreate = competitionService.addCompetition(competitionCreateFormDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CompetitionMapper.convertToCompetitionDetailsDto(competitionCreate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitionDetailsDto> update(@PathVariable Long id, @RequestBody @Valid CompetitionUpdateFormDto competitionUpdateFormDto) {
        Competition competitionUpdate = competitionService.updateCompetition(id, competitionUpdateFormDto);
        return ResponseEntity.ok(CompetitionMapper.convertToCompetitionDetailsDto(competitionUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        competitionService.deleteCompetitionById(id);
        return ResponseEntity.noContent().build();
    }
}