package be.dylan.arbitrage_v1.pl.controllers;

import be.dylan.arbitrage_v1.bll.mappers.UserRankMapper;
import be.dylan.arbitrage_v1.bll.services.userRank.UserRankService;
import be.dylan.arbitrage_v1.dal.entities.UserRank;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankDetailsDto;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankIndexDto;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankPromoteFormDto;
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
    public ResponseEntity<List<UserRankDetailsDto>> findById(@PathVariable Long userId) {
        List<UserRankDetailsDto> userRankDetailsDtos = userRankService.getUserRanks(userId)
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

    @GetMapping
    public ResponseEntity<List<UserRankIndexDto>> findAll() {
        List<UserRankIndexDto> ranks = userRankService.getAllActiveRanks()
                .stream()
                .map(UserRankMapper::convertToUserRankIndexDto)
                .toList();
        return ResponseEntity.ok(ranks);
    }

    @PostMapping("/promote")
    public ResponseEntity<UserRankDetailsDto> promote(@RequestBody @Valid UserRankPromoteFormDto dto) {
        UserRank result = userRankService.promoteRank(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserRankMapper.convertToUserRankDetailsDto(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userRankService.deleteRankUser(id);
        return ResponseEntity.noContent().build();
    }

}
