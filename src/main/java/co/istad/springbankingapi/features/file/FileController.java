package co.istad.springbankingapi.features.file;

import co.istad.springbankingapi.features.file.dto.FileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    @PostMapping
    FileResponse upload(@RequestPart MultipartFile file) throws IOException {
        return fileService.upload(file);
    }
}
