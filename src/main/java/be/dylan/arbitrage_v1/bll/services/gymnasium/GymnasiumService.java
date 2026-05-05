package be.dylan.arbitrage_v1.bll.services.gymnasium;

import be.dylan.arbitrage_v1.dal.entities.Gymnasium;
import be.dylan.arbitrage_v1.pl.dtos.gymnasium.GymnasiumCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.gymnasium.GymnasiumUpdateFormDto;

import java.util.List;

public interface GymnasiumService {
    Gymnasium addGymnasium(GymnasiumCreateFormDto gymnasiumCreateFormDto);
    List<Gymnasium> getAllGymnasiums();
    Gymnasium getGymnasiumById(Long id);
    Gymnasium updateGymnasium(Long id, GymnasiumUpdateFormDto  gymnasiumUpdateFormDto);
    void  deleteGymnasiumById(Long id);
}
