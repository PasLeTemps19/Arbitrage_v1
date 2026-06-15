package be.dylan.arbitrage_v1.pl.controllers;

import be.dylan.arbitrage_v1.bll.mappers.ScheduledEmailMapper;
import be.dylan.arbitrage_v1.bll.services.scheduledEmail.ScheduledEmailService;
import be.dylan.arbitrage_v1.dal.entities.ScheduledEmail;
import be.dylan.arbitrage_v1.pl.dtos.ScheduledEmail.ScheduledEmailCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.ScheduledEmail.ScheduledEmailDetailsDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduled-emails")
public class ScheduledEmailController {

    private final ScheduledEmailService scheduledEmailService;

    @PostMapping("/create")
    public ResponseEntity<ScheduledEmailDetailsDto> create(@RequestBody @Valid ScheduledEmailCreateFormDto dto) {
        ScheduledEmail scheduledEmail = scheduledEmailService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ScheduledEmailMapper.convertToScheduledEmailDetailsDto(scheduledEmail));
    }

    @GetMapping("/competition/{competitionId}")
    public ResponseEntity<List<ScheduledEmailDetailsDto>> getByCompetition(@PathVariable Long competitionId) {
        return ResponseEntity.ok(
                scheduledEmailService.getByCompetition(competitionId)
                        .stream()
                        .map(ScheduledEmailMapper::convertToScheduledEmailDetailsDto)
                        .toList()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduledEmailService.delete(id);
        return ResponseEntity.noContent().build();
    }
}