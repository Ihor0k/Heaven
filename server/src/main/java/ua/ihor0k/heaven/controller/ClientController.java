package ua.ihor0k.heaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ihor0k.heaven.dto.ParameterDTO;
import ua.ihor0k.heaven.service.ImageService;
import ua.ihor0k.heaven.service.ParameterService;

import java.util.List;

@RestController
@CrossOrigin    // TODO
@RequestMapping("/")
public class ClientController {
    private ParameterService parameterService;
    private ImageService imageService;

    @GetMapping("/parameters")
    public List<ParameterDTO> getParameters() {
        return parameterService.getParameters();
    }

    @GetMapping("/image/{name}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String name) {
        Resource image = imageService.download(name);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(image);
    }

    @Autowired
    public void setAdminService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
}
