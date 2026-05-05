package be.dylan.arbitrage_v1.pl.controllers;

import be.dylan.arbitrage_v1.bll.mappers.GymnasiumMapper;
import be.dylan.arbitrage_v1.bll.services.gymnasium.GymnasiumService;
import be.dylan.arbitrage_v1.dal.entities.Gymnasium;
import be.dylan.arbitrage_v1.pl.dtos.gymnasium.GymnasiumCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.gymnasium.GymnasiumDetailsDto;
import be.dylan.arbitrage_v1.pl.dtos.gymnasium.GymnasiumIndexDto;
import be.dylan.arbitrage_v1.pl.dtos.gymnasium.GymnasiumUpdateFormDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gymnasium")
@RequiredArgsConstructor
public class GymnasiumController {

    private final GymnasiumService gymnasiumService;

    @GetMapping
    public ResponseEntity<List<GymnasiumIndexDto>> findAll()
    {
        List<GymnasiumIndexDto> gymnasiums = gymnasiumService.getAllGymnasiums()
                .stream()
                .map(GymnasiumMapper::convertToGymnasiumIndexDto)
                .toList();
        return ResponseEntity.ok(gymnasiums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymnasiumDetailsDto> findByid(@PathVariable Long id)
    {
        GymnasiumDetailsDto gymnasiumDetailsDto = GymnasiumMapper.convertToGymnasiumDetailsDto(gymnasiumService.getGymnasiumById(id));
        return ResponseEntity.ok(gymnasiumDetailsDto);
    }

    @PostMapping("/create")
    public ResponseEntity<GymnasiumDetailsDto> create(@RequestBody @Valid GymnasiumCreateFormDto gymnasiumCreateFormDto){
        Gymnasium gymnasiumCreate = gymnasiumService.addGymnasium(gymnasiumCreateFormDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GymnasiumMapper.convertToGymnasiumDetailsDto(gymnasiumCreate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        gymnasiumService.deleteGymnasiumById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GymnasiumDetailsDto> update(@PathVariable Long id, @RequestBody @Valid GymnasiumUpdateFormDto updateFormDto){
        Gymnasium gymnasiumUpdate = gymnasiumService.updateGymnasium(id, updateFormDto);
        return ResponseEntity.ok(GymnasiumMapper.convertToGymnasiumDetailsDto(gymnasiumUpdate));
    }

}
