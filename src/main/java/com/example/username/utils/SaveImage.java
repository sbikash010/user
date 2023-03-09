package com.example.username.utils;

import com.example.username.constant.FieldErrorConstants;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Component
public class SaveImage {

    public String storeUserImages(MultipartFile multipartUserImage) throws Exception {
        String filename = null;
        if (multipartUserImage != null) {
            String imagePath = new StringBuilder().append(FieldErrorConstants.UPLOAD_DIR).append("images/user/profile/").toString();
            File file = new File(imagePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            try {
                /*check userImage and set to, if no image is found default(defaultUserImage.jpg) image is saved*/
                String tempFileName = System.currentTimeMillis() + multipartUserImage.getOriginalFilename();
                filename = imagePath + tempFileName;
                multipartUserImage.transferTo(new File(filename));
                return "images/user/profile/" + tempFileName;
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception(e.getMessage());
            }

        }
        throw new Exception(("user.image.notfound"));
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
        } else {
            throw new RuntimeException("file.not.found");
        }
    }
}
