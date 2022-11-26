package kmwe.afw.infogame.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.mapper.CompanyMapper;
import kmwe.afw.infogame.mapper.GameMapper;
import kmwe.afw.infogame.mapper.UserMapper;
import kmwe.afw.infogame.repository.CompanyRepository;
import kmwe.afw.infogame.repository.GameRepository;
import kmwe.afw.infogame.repository.UserRepository;
import kmwe.afw.infogame.service.abstracts.AbstractAdmin;
import kmwe.afw.infogame.service.abstracts.AdminFunction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends AbstractAdmin implements AdminService {
    public AdminServiceImpl(UserRepository userRepository, UserMapper userMapper, ObjectMapper objectMapper, GameRepository gameRepository, CompanyRepository companyRepository, GameMapper gameMapper, CompanyMapper companyMapper) {
        super(userRepository, userMapper, objectMapper, gameRepository, companyRepository, gameMapper, companyMapper);
    }

    @Override
    public ResponseEntity<String> uploadGame(GameDTOFull gameDTOFull) {
        return uploadGameInfo(gameDTOFull);
    }

    @Override
    public ResponseEntity<String> uploadCompany(CompanyDTOFull companyDTOFull) {
        return uploadCompanyInfo(companyDTOFull);
    }
}
