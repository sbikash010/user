package com.example.username.utils;

import com.example.username.dto.userImage.UserImageRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@Slf4j
public class FIleStorageUtils {
    public String storeFile(UserImageRequest userImageRequest) {
        MultipartFile multipartFile = userImageRequest.getMultipartFile();
        Long size = multipartFile.getSize();
        try {
            if (multipartFile == null)
                throw new RuntimeException(("file Not Found!!"));

            if (validateImageSize(multipartFile)) {
                String fileDir = System.getProperty("user.dir") + File.separator + "image";


                File fileDirectory = new File(fileDir);
                if (!fileDirectory.exists()) {
                    boolean mkdir = fileDirectory.mkdir();
                } else {
                    log.info("file is already exist!!");
                }
                UUID uuid = UUID.randomUUID();
                if (multipartFile.getOriginalFilename() != null) {
                    String filepath = fileDir + File.separator + uuid + "_" + multipartFile.getOriginalFilename();
                    fileDirectory = new File(filepath);
                    try (FileOutputStream outputStream = new FileOutputStream(fileDirectory)) {
                        outputStream.write(multipartFile.getBytes());
                        return filepath;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return filepath;
                } else {
                    return null;
                }
            } else {
                throw new RuntimeException("image size is greater than 200 Mb");
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean validateImageSize(MultipartFile multipartFile) {
        return multipartFile.getSize() <= (200000000);
    }

    public void getImage(String filePath, HttpServletResponse response) throws IOException {
        File file = new File(filePath);
        String fileName = filePath.replaceFirst("(?i)^.*filename=\"?([^\"]+)\"?.*$", "$1");
        if (file.exists()) {
            response.setContentType("image/" + FilenameUtils.getExtension(fileName));
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + file.getName() + "\"");
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } else throw new RuntimeException("file.not.found");
    }

    public void getPDF(String filePath, HttpServletResponse response) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            response.setContentType("application/pdf");
            OutputStream out = response.getOutputStream();
            byte[] b = Files.readAllBytes(Paths.get(filePath));
            out.write(b, 0, b.length);
            out.close();
        } else throw new RuntimeException("file.not.found");
    }
}
