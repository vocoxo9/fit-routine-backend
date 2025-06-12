package kr.co.khedu.fitroutine.storage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public final class FileStorageService {
    private final Path location;

    public FileStorageService(
            @Value("${file.storage.location}") String location
    ) {
        this.location = Paths.get(location).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.location);
        } catch (IOException exception) {
            throw new RuntimeException("저장 공간을 초기화할 수 없습니다.", exception);
        }
    }

    public String store(MultipartFile file) {
        if (file.getOriginalFilename() == null) {
            throw new IllegalArgumentException("파일 이름이 유효하지 않습니다.");
        }

        String oldName = StringUtils.cleanPath(file.getOriginalFilename());
        String newName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(oldName);

        Path target = location.resolve(newName);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            throw new RuntimeException("파일을 저장하는 도중 오류가 발생했습니다.", exception);
        }

        return newName;
    }

    public void delete(String filename) {
        try {
            Files.deleteIfExists(location.resolve(filename));
        } catch (IOException exception) {
            throw new RuntimeException("파일을 저장하는 도중 오류가 발생했습니다.", exception);
        }
    }
}
