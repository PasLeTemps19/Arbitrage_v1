package be.dylan.arbitrage_v1.bll.mappers;

import be.dylan.arbitrage_v1.dal.entities.Competition;
import be.dylan.arbitrage_v1.dal.entities.ScheduledEmail;
import be.dylan.arbitrage_v1.pl.dtos.ScheduledEmail.ScheduledEmailCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.ScheduledEmail.ScheduledEmailDetailsDto;


public class ScheduledEmailMapper {

    public static ScheduledEmail convertToScheduledEmail(Competition competition, ScheduledEmailCreateFormDto dto) {
        ScheduledEmail scheduledEmail = new ScheduledEmail();
        scheduledEmail.setCompetition(competition);
        scheduledEmail.setSendAt(dto.getSendAt());
        scheduledEmail.setSubject(dto.getSubject());
        scheduledEmail.setMessage(dto.getMessage());
        scheduledEmail.setIntroMessage(dto.getIntroMessage());
        scheduledEmail.setTargetStatus(dto.getTargetStatus());
        return scheduledEmail;
    }

    public static ScheduledEmailDetailsDto convertToScheduledEmailDetailsDto(ScheduledEmail s) {
        return new ScheduledEmailDetailsDto(
                s.getId(),
                s.getCompetition().getId(),
                s.getCompetition().getName(),
                s.getSendAt(),
                s.getSubject(),
                s.getMessage(),
                s.getIntroMessage(),
                s.getTargetStatus().name(),
                s.getStatus().name()
        );
    }
}