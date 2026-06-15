package be.dylan.arbitrage_v1.pl.dtos.ScheduledEmail;

import java.time.LocalDateTime;

public record ScheduledEmailDetailsDto(
        Long id,
        Long competitionId,
        String competitionName,
        LocalDateTime sendAt,
        String subject,
        String message,
        String introMessage,
        String targetStatus,
        String status
) {}