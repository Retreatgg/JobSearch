package com.example.demo.util;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileUtil {

    private static final String UPLOAD_DIR = "data";
    private final UserDao userDao;

    @SneakyThrows
    public String saveUploadedFile(MultipartFile file, String subDir) {
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "_" + file.getOriginalFilename();

        Path pathDir = Paths.get(UPLOAD_DIR + subDir);
        Files.createDirectories(pathDir);

        Path filePath = Paths.get(pathDir + "/" + resultFileName);
        if(!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        try(OutputStream os = Files.newOutputStream(filePath)) {
            os.write(file.getBytes());
        } catch (IOException e){
            log.error(e.getMessage());
        }

        return resultFileName;
    }

    public MultipartFile convertStringToMultipartFile(String avatarString) {
        if (StringUtils.isEmpty(avatarString)) {
            return null;
        }

        byte[] bytes = avatarString.getBytes();

        MultipartFile multipartFile = new MockMultipartFile("file", bytes) ;

        return multipartFile;
    }

    public User getUserByAuth(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String email = userDetails.getUsername();
        Optional<User> userOptional = userDao.getUserByEmail(email);
        return userOptional.orElseThrow(() -> new NoSuchElementException("User is not found"));
    }

    public String getAuthority(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.isEmpty() ? "" : authorities.iterator().next().getAuthority();
    }
}
