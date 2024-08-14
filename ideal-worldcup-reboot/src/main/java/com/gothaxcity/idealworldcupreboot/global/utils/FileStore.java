package com.gothaxcity.idealworldcupreboot.global.utils;

import com.gothaxcity.idealworldcupreboot.post.domain.PostImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public List<PostImage> storeFiles(List<MultipartFile> files) throws IOException {
        List<PostImage> storeFileResult = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                storeFileResult.add(storeFile(file));
            }
        }
        return storeFileResult;
    }

    public PostImage storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        String originalFilename = file.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);

        file.transferTo(new File(getFullPath(storeFileName)));
        return new PostImage(originalFilename, storeFileName);
    }

    private String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    private String createStoreFileName(String originalFilename) {
        String extension = extractExtension(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + extension;

    }

    private String extractExtension(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos+1);
    }

}
