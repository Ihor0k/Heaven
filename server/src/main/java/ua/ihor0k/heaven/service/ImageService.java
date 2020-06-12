package ua.ihor0k.heaven.service;

import org.springframework.core.io.Resource;

public interface ImageService {
    String upload(Resource file);

    Resource download(String name);

    void delete(String name);
}
