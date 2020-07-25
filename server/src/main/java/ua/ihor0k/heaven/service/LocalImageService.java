package ua.ihor0k.heaven.service;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
@Profile("image-local")
public class LocalImageService extends AbstractImageService {
    private File dir;

    public LocalImageService(@Value("${local.image.dir}") String localImageDir) {
        this.dir = new File(localImageDir);
    }

    @Override
    protected void save(String fileName, Resource image) throws IOException {
        File file = new File(dir, fileName);
        InputStream imageInputStream = image.getInputStream();
        FileUtils.copyInputStreamToFile(imageInputStream, file);
    }

    @Override
    protected boolean isFileExists(String fileName) {
        return new File(dir, fileName).exists();
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

}
