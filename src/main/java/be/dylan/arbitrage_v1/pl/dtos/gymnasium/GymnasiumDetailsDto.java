package be.dylan.arbitrage_v1.pl.dtos.gymnasium;

public record GymnasiumDetailsDto(
        Long id,
        String name,
        String street,
        Integer number,
        int postCode,
        String city,
        String responsable,
        String description
) {
}
