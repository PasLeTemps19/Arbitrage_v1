package be.dylan.arbitrage_v1.bll.services.userRank;

import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.dal.entities.UserRank;
import be.dylan.arbitrage_v1.dal.repositories.UserRankRepository;
import org.springframework.stereotype.Service;



@Service
public class UserRankServiceImpl  implements UserRankService {
    private final UserRankRepository userRankRepository;
    public UserRankServiceImpl(UserRankRepository userRankRepository) {
        this.userRankRepository = userRankRepository;
    }



}
