//package com.example.username.utils;
//
//import com.example.username.dto.userImage.UserImageRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.tika.Tika;
//import org.apache.tika.exception.TikaException;
//import org.apache.tika.io.TikaInputStream;
//import org.apache.tika.mime.MimeTypes;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@Component
//@Slf4j
//public class ApacheTikaFileUtil {
//    //    public void fileSizeAndTypeValidation(MultipartFile file, DocumentDetailPojo documentDetailPojo) throws TikaException, IOException {
////        final HttpHeaders headers = new HttpHeaders();
////        TikaInputStream tikaStream = TikaInputStream.get(file.getBytes());
////        Tika tika = new Tika();
////        String mimeType = tika.detect(tikaStream);
////    headers.setContentType(MediaType.valueOf(mimeType));
////    MimeTypes defaultMimeTypes = MimeTypes.getDefaultMimeTypes();
////    String extension = defaultMimeTypes.forName(mimeType).getExtension().substring(1);
////        if (extension == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, customMessageSource.get("not.found", "Extension"));
////    long fileSize = file.getSize();
////    List<String> definedExtensionTypes = Arrays.asList(documentDetailPojo.getFileExtension().split(","));
////        if (!definedExtensionTypes.contains(extension.toLowerCase())) {
////        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, customMessageSource.get("file.type.validation", extension));
////    }
////        if (fileSize > documentDetailPojo.getFileSize() * 1000000) {
////        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, customMessageSource.get("file.size.validation"));
////    }
////}
//    public String fileSizeAndTypeValidation(UserImageRequest userImageRequest) throws TikaException, IOException {
//        MultipartFile file = userImageRequest.getMultipartFile();
//        final HttpHeaders headers = new HttpHeaders();
//        TikaInputStream tikaStream = TikaInputStream.get(file.getBytes());
//        Tika tika = new Tika();
//        String mimeType = tika.detect(tikaStream);
//        headers.setContentType(MediaType.valueOf(mimeType));
//        MimeTypes defaultMimeTypes = MimeTypes.getDefaultMimeTypes();
//        String extension = defaultMimeTypes.forName(mimeType).getExtension().substring(1);
//        if (extension == null) throw new RuntimeException("extension is not found !!!");
//        long fileSize = file.getSize();
////    List<String> definedExtensionTypes = Arrays.asList(file.split(","));
////    if (!definedExtensionTypes.contains(extension.toLowerCase())) {
////        throw new RuntimeException("extension is not found !!!");
////    }
//        if (fileSize > 200000000) {
//            throw new RuntimeException("fileSize is greater than 200 mb !!!");
//        }
//        return null;
//    }
//}
