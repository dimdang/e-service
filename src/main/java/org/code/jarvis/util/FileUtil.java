package org.code.jarvis.util;

import org.apache.commons.codec.binary.Base64;
import org.code.jarvis.model.core.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KimChheng on 5/9/2017.
 */
public final class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);
    private static String homeDir = Constant.HOME_DIRECTORY;

    public static List<Image> saveFiles(Long id, MultipartFile[] multipartFiles) {
        List<Image> list = new ArrayList<>();
        File file;
        try {
            String dir = (id != null && id > 0) ? homeDir + File.separator + id : homeDir;
            if (multipartFiles != null) {
                file = new File(dir);
                if (!file.exists())
                    file.mkdirs();
                for (MultipartFile multipartFile : multipartFiles) {
                    file = new File(dir + File.separator + multipartFile.getOriginalFilename());
                    multipartFile.transferTo(file);
                    list.add(new Image(file.getName(), file.getParentFile().getName() + File.separator + file.getName()));
                    log.info("file name[" + multipartFile.getOriginalFilename() + "],size[" + multipartFile.getSize() + "]. save to directory[" + file.getAbsolutePath() + "] successful");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return list;
    }

    public static String saveFile(Long id, MultipartFile multipartFile) {
        File file;
        try {
            String dir = (id != null && id > 0) ? homeDir + File.separator + id : homeDir;
            if (multipartFile != null) {
                file = new File(dir);
                if (!file.exists())
                    file.mkdirs();
                file = new File(dir + File.separator + multipartFile.getOriginalFilename());
                multipartFile.transferTo(file);
                log.info("file name[" + multipartFile.getOriginalFilename() + "],size[" + multipartFile.getSize() + "]. save to directory[" + file.getAbsolutePath() + "] successful");
                return file.getPath();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    public static String encodeImage(String path) {
        String imageString = null;
        FileInputStream fis = null;
        try {
            if (path != null && !path.isEmpty()) {
                File file = new File(path);
                if (file.exists()) {
                    fis = new FileInputStream(file);
                    byte byteArray[] = new byte[(int) file.length()];
                    fis.read(byteArray);
                    imageString = Base64.encodeBase64String(byteArray);
                } else
                    log.warn("file does not exists[" + path + "]");
            } else log.warn("file path can not be empty");
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error(e.getMessage());
                }
        }
        return imageString;
    }

    public static boolean decodeImage(String imageString, String fileName, String saveToDir) {
        FileOutputStream fos = null;
        File file;
        try {
            if (saveToDir == null || saveToDir.isEmpty()) {
                file = new File(homeDir);
                if (!file.exists())
                    file.mkdirs();
                saveToDir = homeDir;
            }
            if (imageString != null && !imageString.isEmpty()) {
                fos = new FileOutputStream(saveToDir + File.separator + fileName);
                byte byteArray[] = Base64.decodeBase64(imageString);
                fos.write(byteArray);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            if (fos != null)
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error(e.getMessage());
                }
        }
        return false;
    }
}
