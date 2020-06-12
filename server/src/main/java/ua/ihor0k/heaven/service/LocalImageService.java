package ua.ihor0k.heaven.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class LocalImageService implements ImageService {
    private static final int fileNameLength = 10;
    private static final String fileNameAlphabet = "abcdefghijklmnopqrstuvwxyz0123456789";

    private final File dir;
    private final Random random;

    public LocalImageService(String dir) {
        this.dir = new File(dir);
        this.random = new Random();
    }

    @Override
    public String upload(Resource image) {
        String extension = FilenameUtils.getExtension(image.getFilename());
        extension = extension == null ? "" : "." + extension.toLowerCase();
        File file = randomFile(extension);
        try {
            InputStream imageInputStream = image.getInputStream();
            FileUtils.copyInputStreamToFile(imageInputStream, file);
        } catch (IOException e) {
            // TODO
            throw new RuntimeException(e);
        }
        return file.getName();
    }

    @Override
    public Resource download(String name) {
        return new FileSystemResource(new File(dir, name));
    }

    @Override
    public void delete(String name) {
        File file = new File(dir, name);
        if (!file.exists()) {
            // TODO
        } else {
            file.delete();
        }
    }

    private File randomFile(String extension) {
        File file;
        do {
            String fileName = randomString() + extension;
            file = new File(dir, fileName);
        } while (file.exists());
        return file;
    }

    private String randomString() {
        char[] chars = new char[fileNameLength];
        for (int i = 0; i < chars.length; i++) {
            int randomIndex = random.nextInt(fileNameAlphabet.length());
            chars[i] = fileNameAlphabet.charAt(randomIndex);
        }
        return new String(chars);
    }
}
