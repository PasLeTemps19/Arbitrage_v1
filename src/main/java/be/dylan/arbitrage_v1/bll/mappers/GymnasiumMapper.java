package be.dylan.arbitrage_v1.bll.mappers;

import be.dylan.arbitrage_v1.dal.entities.Gymnasium;
import be.dylan.arbitrage_v1.pl.dtos.gymnasium.GymnasiumCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.gymnasium.GymnasiumDetailsDto;
import be.dylan.arbitrage_v1.pl.dtos.gymnasium.GymnasiumIndexDto;
import be.dylan.arbitrage_v1.pl.dtos.gymnasium.GymnasiumUpdateFormDto;

public class GymnasiumMapper {

    public static Gymnasium convertToGymnasium(GymnasiumCreateFormDto gymnasiumCreateFormDto) {
        Gymnasium gymnasium = new Gymnasium();
        gymnasium.setName(gymnasiumCreateFormDto.getName());
        gymnasium.setStreet(gymnasiumCreateFormDto.getStreet());
        gymnasium.setNumber(gymnasiumCreateFormDto.getNumber());
        gymnasium.setPostCode(gymnasiumCreateFormDto.getPostCode());
        gymnasium.setCity(gymnasiumCreateFormDto.getCity());
        gymnasium.setResponsable(gymnasiumCreateFormDto.getResponsable());
        gymnasium.setDescription(gymnasiumCreateFormDto.getDescription());
        return gymnasium;
    }

    public static GymnasiumIndexDto convertToGymnasiumIndexDto(Gymnasium gymnasium) {
        return  new GymnasiumIndexDto(
                gymnasium.getId(),
                gymnasium.getName(),
                gymnasium.getCity()
        );
    }

    public static GymnasiumDetailsDto convertToGymnasiumDetailsDto(Gymnasium gymnasium) {
        return  new GymnasiumDetailsDto(
                gymnasium.getId(),
                gymnasium.getName(),
                gymnasium.getStreet(),
                gymnasium.getNumber(),
                gymnasium.getPostCode(),
                gymnasium.getCity(),
                gymnasium.getResponsable(),
                gymnasium.getDescription()

        );
    }
    public static Gymnasium convertUpdateToGymnasium(Gymnasium gymnasium, GymnasiumUpdateFormDto gymnasiumUpdateFormDto) {
        gymnasium.setName(gymnasiumUpdateFormDto.getName());
        gymnasium.setStreet(gymnasiumUpdateFormDto.getStreet());
        gymnasium.setNumber(gymnasiumUpdateFormDto.getNumber());
        gymnasium.setPostCode(gymnasiumUpdateFormDto.getPostCode());
        gymnasium.setCity(gymnasiumUpdateFormDto.getCity());
        gymnasium.setResponsable(gymnasiumUpdateFormDto.getResponsable());
        gymnasium.setDescription(gymnasiumUpdateFormDto.getDescription());
        return gymnasium;
    }
}
