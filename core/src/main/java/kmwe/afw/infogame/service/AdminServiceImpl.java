package kmwe.afw.infogame.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.mapper.CompanyMapper;
import kmwe.afw.infogame.mapper.GameMapper;
import kmwe.afw.infogame.mapper.UserMapper;
import kmwe.afw.infogame.payload.CompanyInfo;
import kmwe.afw.infogame.repository.CompanyRepository;
import kmwe.afw.infogame.repository.GameRepository;
import kmwe.afw.infogame.repository.UserRepository;
import kmwe.afw.infogame.service.abstracts.AbstractAdmin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Service
public class AdminServiceImpl extends AbstractAdmin implements AdminService {
    public AdminServiceImpl(UserRepository userRepository, UserMapper userMapper, ObjectMapper objectMapper, GameRepository gameRepository, CompanyRepository companyRepository, GameMapper gameMapper, CompanyMapper companyMapper, FileStorageService fileStorageService) {
        super(userRepository, userMapper, objectMapper, gameRepository, companyRepository, gameMapper, companyMapper, fileStorageService);
    }

    @Override
    public ResponseEntity<CompanyInfo> getInfoCompany(String name, HttpServletRequest request) {
        return getCompany(name, request);
    }

    @Override
    public ResponseEntity<String> uploadGame(GameDTOFull gameDTOFull) {
        return uploadGameInfo(gameDTOFull);
    }

    @Override
    public ResponseEntity<String> uploadCompany(String name, MultipartFile logo) {
        return uploadCompanyInfo(name, logo);
    }
}
