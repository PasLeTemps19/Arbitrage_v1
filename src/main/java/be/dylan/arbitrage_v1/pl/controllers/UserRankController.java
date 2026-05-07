package be.dylan.arbitrage_v1.pl.controllers;

import be.dylan.arbitrage_v1.bll.mappers.UserRankMapper;
import be.dylan.arbitrage_v1.bll.services.userRank.UserRankService;
import be.dylan.arbitrage_v1.dal.entities.UserRank;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankDetailsDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usersRanks")
@RequiredArgsConstructor
public class UserRankController {
    private final UserRankService userRankService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserRankDetailsDto>> findById(@PathVariable Long id) {
        List<UserRankDetailsDto> userRankDetailsDtos = userRankService.getUserRanks(id)
                .stream()
                .map(UserRankMapper::convertToUserRankDetailsDto)
                .toList();
        return ResponseEntity.ok(userRankDetailsDtos);
    }

    @PostMapping("/create")
    public ResponseEntity<UserRankDetailsDto> create(@RequestBody @Valid UserRankCreateFormDto userRankCreateFormDto) {
        UserRank userRankCreate = userRankService.assignRank(userRankCreateFormDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserRankMapper.convertToUserRankDetailsDto(userRankCreate));
    }
}
