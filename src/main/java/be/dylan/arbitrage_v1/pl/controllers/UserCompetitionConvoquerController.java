package be.dylan.arbitrage_v1.pl.controllers;

import be.dylan.arbitrage_v1.bll.mappers.UserCompetitionConvoquerMapper;
import be.dylan.arbitrage_v1.bll.services.userCompetitionConvoquer.UserCompetitionConvoquerService;
import be.dylan.arbitrage_v1.dal.entities.UserCompetitionConvoquer;
import be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer.UserCompetitionConvoquerCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer.UserCompetitionConvoquerDetailsDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/convocation")
public class UserCompetitionConvoquerController {

    private final UserCompetitionConvoquerService userCompetitionConvoquerService;

   @PostMapping("/create")
    public ResponseEntity<UserCompetitionConvoquerDetailsDto> create(@RequestBody @Valid UserCompetitionConvoquerCreateFormDto userCompetitionConvoquerCreateFormDto) {
       UserCompetitionConvoquer userCompetitionConvoquer = userCompetitionConvoquerService.addConvocation(userCompetitionConvoquerCreateFormDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(UserCompetitionConvoquerMapper.convertToUserCompetitionConvoquerDetailsDto(userCompetitionConvoquer));
   }

   @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserCompetitionConvoquerDetailsDto>> findByUser(@PathVariable Long userId) {
       List<UserCompetitionConvoquerDetailsDto>   userCompetitionConvoquerDetailsDtos = userCompetitionConvoquerService.getConvocationsByUser(userId)
               .stream()
               .map(UserCompetitionConvoquerMapper::convertToUserCompetitionConvoquerDetailsDto)
               .toList();
       return ResponseEntity.ok(userCompetitionConvoquerDetailsDtos);
   }

    @GetMapping("/competition/{competitionId}")
    public ResponseEntity<List<UserCompetitionConvoquerDetailsDto>> findByCompetition(@PathVariable Long competitionId) {
       List<UserCompetitionConvoquerDetailsDto> userCompetitionConvoquerDetailsDtos = userCompetitionConvoquerService.getConvocationsByCompetition(competitionId)
               .stream()
               .map(UserCompetitionConvoquerMapper::convertToUserCompetitionConvoquerDetailsDto)
               .toList();
       return  ResponseEntity.ok(userCompetitionConvoquerDetailsDtos);
    }

    @DeleteMapping("/{userId}/{competitionId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long competitionId) {
       userCompetitionConvoquerService.deleteConvocation(userId, competitionId);
       return ResponseEntity.noContent().build();
    }



}
