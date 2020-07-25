package ua.ihor0k.heaven.service;

import org.springframework.core.io.Resource;

public interface ImageService {
    String upload(Resource image);

    Resource download(String name);

    void delete(String name);
}
