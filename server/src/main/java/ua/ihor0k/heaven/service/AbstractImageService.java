package ua.ihor0k.heaven.service;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Random;

public abstract class AbstractImageService implements ImageService {
    private static final String extension = "png";
    private static final int fileNameLength = 10;
    private static final String fileNameAlphabet = "abcdefghijklmnopqrstuvwxyz0123456789";

    private final Random random;

    public AbstractImageService() {
        this.random = new Random();
    }

    @Override
    public String upload(Resource image) {
        String fileName = randomUniqFileName();
        try {
            save(fileName, image);
        } catch (IOException e) {
            // TODO
            throw new RuntimeException(e);
        }
        return fileName;
    }

    protected abstract void save(String fileName, Resource image) throws IOException;

    private String randomUniqFileName() {
        String fileName;
        do {
            fileName = randomString() + "." + extension;
        } while (isFileExists(fileName));
        return fileName;
    }

    private String randomString() {
        char[] chars = new char[fileNameLength];
        for (int i = 0; i < chars.length; i++) {
            int randomIndex = random.nextInt(fileNameAlphabet.length());
            chars[i] = fileNameAlphabet.charAt(randomIndex);
        }
        return new String(chars);
    }

    protected abstract boolean isFileExists(String fileName);
}
