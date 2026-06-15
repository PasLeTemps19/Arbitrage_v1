package be.dylan.arbitrage_v1.pl.controllers;

import be.dylan.arbitrage_v1.bll.mappers.UserCompetitionConvoquerMapper;
import be.dylan.arbitrage_v1.bll.services.userCompetitionConvoquer.UserCompetitionConvoquerService;
import be.dylan.arbitrage_v1.dal.entities.UserCompetitionConvoquer;
import be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer.UserCompetitionConvoquerCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer.UserCompetitionConvoquerDetailsDto;
import be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer.UserCompetitionConvoquerUpdateStatusDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/convocation")
public class UserCompetitionConvoquerController {

    private final UserCompetitionConvoquerService userCompetitionConvoquerService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserCompetitionConvoquerDetailsDto> create(
            @RequestPart("data") @Valid UserCompetitionConvoquerCreateFormDto dto,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) {

        List<File> attachments = new ArrayList<>();
        if (files != null) {
            for (MultipartFile file : files) {
                try {
                    File tempFile = File.createTempFile("attach_", "_" + file.getOriginalFilename());
                    file.transferTo(tempFile);
                    tempFile.deleteOnExit();
                    attachments.add(tempFile);
                } catch (IOException e) {
                    throw new RuntimeException("Erreur lors du traitement du fichier", e);
                }
            }
        }

        UserCompetitionConvoquer convoquer = userCompetitionConvoquerService.addConvocation(dto, attachments);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserCompetitionConvoquerMapper.convertToUserCompetitionConvoquerDetailsDto(convoquer));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserCompetitionConvoquerDetailsDto>> findByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(
                userCompetitionConvoquerService.getConvocationsByUser(userId)
                        .stream()
                        .map(UserCompetitionConvoquerMapper::convertToUserCompetitionConvoquerDetailsDto)
                        .toList()
        );
    }

    @GetMapping("/competition/{competitionId}")
    public ResponseEntity<List<UserCompetitionConvoquerDetailsDto>> findByCompetition(@PathVariable Long competitionId) {
        return ResponseEntity.ok(
                userCompetitionConvoquerService.getConvocationsByCompetition(competitionId)
                        .stream()
                        .map(UserCompetitionConvoquerMapper::convertToUserCompetitionConvoquerDetailsDto)
                        .toList()
        );
    }

    @GetMapping("/respond/{token}")
    public ResponseEntity<Void> respond(@PathVariable String token, @RequestParam String response) {
        userCompetitionConvoquerService.respondToConvocation(token, response);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("http://localhost:4200/convocation-response?status=" + response))
                .build();
    }

    @PatchMapping("/{userId}/{competitionId}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long userId,
            @PathVariable Long competitionId,
            @RequestBody @Valid UserCompetitionConvoquerUpdateStatusDto dto) {
        userCompetitionConvoquerService.updateStatus(userId, competitionId, dto.getStatus());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/{competitionId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long competitionId) {
        userCompetitionConvoquerService.deleteConvocation(userId, competitionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/track/{token}")
    public ResponseEntity<byte[]> trackOpen(@PathVariable String token) {
        userCompetitionConvoquerService.trackEmailOpen(token);

        // Pixel transparent 1x1
        byte[] pixel = Base64.getDecoder().decode(
                "R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7"
        );

        return ResponseEntity.ok()
                .header("Content-Type", "image/gif")
                .header("Cache-Control", "no-cache, no-store, must-revalidate")
                .body(pixel);
    }
}