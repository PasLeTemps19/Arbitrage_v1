package be.dylan.arbitrage_v1.bll.services.gymnasium;

import be.dylan.arbitrage_v1.bll.mappers.GymnasiumMapper;
import be.dylan.arbitrage_v1.dal.entities.Gymnasium;
import be.dylan.arbitrage_v1.dal.repositories.GymnasiumRepository;
import be.dylan.arbitrage_v1.pl.dtos.gymnasium.GymnasiumCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.gymnasium.GymnasiumUpdateFormDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymnasiumServiceImpl implements GymnasiumService {

    private final GymnasiumRepository gymnasiumRepository;

    public GymnasiumServiceImpl(GymnasiumRepository gymnasiumRepository) {
        this.gymnasiumRepository = gymnasiumRepository;
    }

    @Override
    public Gymnasium addGymnasium(GymnasiumCreateFormDto gymnasiumCreateFormDto) {
        Gymnasium gymnasium = GymnasiumMapper.convertToGymnasium(gymnasiumCreateFormDto);
        return gymnasiumRepository.save(gymnasium);
    }

    @Override
    public List<Gymnasium> getAllGymnasiums() {
        return gymnasiumRepository.findAll();
    }

    @Override
    public Gymnasium getGymnasiumById(Long id) {
        return gymnasiumRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Gymnasium id " + id + " pas trouver"));
    }

    @Override
    public Gymnasium updateGymnasium(Long id, GymnasiumUpdateFormDto gymnasiumUpdateFormDto) {
        Gymnasium gymnasium = getGymnasiumById(id);
        Gymnasium gymnasiumUpdate = GymnasiumMapper.convertUpdateToGymnasium(gymnasium, gymnasiumUpdateFormDto);
        return  gymnasiumRepository.save(gymnasiumUpdate);
    }

    @Override
    public void deleteGymnasiumById(Long id) {
        Gymnasium gymnasium = getGymnasiumById(id);
        gymnasiumRepository.delete(gymnasium);

    }
}
