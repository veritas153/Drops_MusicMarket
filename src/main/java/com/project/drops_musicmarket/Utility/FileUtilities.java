//package com.project.drops_musicmarket.Utility;
//
//import org.springframework.util.FileCopyUtils;
//
//import java.io.File;
//import java.util.UUID;
//
//public class FileUtilities {
//
//    String os = System.getProperty("os.name").toLowerCase();
//
//    public static String uploadFile(String uploadPath, String originalName, byte[]
//            fileData)throws Exception{
//        UUID uid = UUID.randomUUID();
//        String savedName = uid.toString() +"_" + originalName;
//        String savedPath = calcPath(uploadPath);
//        File target = new File(uploadPath + savedPath, savedName);
//        FileCopyUtils.copy(fileData, target);
//        String uploadFileName = makeIcon(uploadPath, savedPath, savedName);
//        return uploadFileName;
//    }
//
//}
