package co.istad.springbankingapi.features.file;

import co.istad.springbankingapi.features.file.dto.FileResponse;
import co.istad.springbankingapi.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService{

    @Value("${file-server.server-path}")
    private String serverPath;

    @Value("${file-server.base-uri}")
    private String baseUri;

    @Override
    public FileResponse upload(MultipartFile file) throws IOException {
        String newName = FileUtil.generateFileName(file.getOriginalFilename());
        String extension = FileUtil.extractExtension(file.getOriginalFilename());

        Path path = Path.of(serverPath + newName);
        Files.copy(file.getInputStream(), path);
        return FileResponse
                .builder()
                .name(newName)
                .extension(extension)
                .size(file.getSize())
                .contentType(file.getContentType())
                .extension(extension + newName)
                .uri(baseUri + newName)
                .build();
    }
}
