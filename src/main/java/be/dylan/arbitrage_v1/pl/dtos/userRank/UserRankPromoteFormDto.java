package be.dylan.arbitrage_v1.pl.dtos.userRank;

import be.dylan.arbitrage_v1.dal.enums.RankStyle;
import be.dylan.arbitrage_v1.dal.enums.RankType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class UserRankPromoteFormDto {
    @NotNull(message = "L'utilisateur est obligatoire.")
    private Long userId;
    @NotNull(message = "Le style est obligatoire.")
    private RankStyle rankStyle;
    @NotNull(message = "Le type est obligatoire.")
    private RankType rankType;
    private LocalDate obtentionDate;
}