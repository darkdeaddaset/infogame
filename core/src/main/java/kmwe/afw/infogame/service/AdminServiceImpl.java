package kmwe.afw.infogame.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.game.GameDTO;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.mapper.CompanyMapper;
import kmwe.afw.infogame.mapper.GameMapper;
import kmwe.afw.infogame.mapper.UserMapper;
import kmwe.afw.infogame.repository.CompanyRepository;
import kmwe.afw.infogame.repository.GameRepository;
import kmwe.afw.infogame.repository.UserRepository;
import kmwe.afw.infogame.service.abstracts.AbstractAdmin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdminServiceImpl extends AbstractAdmin implements AdminService {
    public AdminServiceImpl(UserRepository userRepository, UserMapper userMapper, ObjectMapper objectMapper, GameRepository gameRepository, CompanyRepository companyRepository, GameMapper gameMapper, CompanyMapper companyMapper, FileStorageService fileStorageService) {
        super(userRepository, userMapper, objectMapper, gameRepository, companyRepository, gameMapper, companyMapper, fileStorageService);
    }

    @Override
    public ResponseEntity<String> uploadGame(GameDTOFull gameDTOFull, MultipartFile logoGame) {
        return uploadGameInfo(gameDTOFull, logoGame);
    }

    @Override
    public ResponseEntity<String> uploadCompany(CompanyDTO companyDTO, MultipartFile logoCompany) {
        return uploadCompanyInfo(companyDTO, logoCompany);
    }

    @Override
    public ResponseEntity<String> deleteCompanyService(String name) {
        return deleteCompany(name);
    }

    @Override
    public ResponseEntity<String> deleteGameService(String name) {
        return deleteGame(name);
    }
}
