package ua.ihor0k.heaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.ihor0k.heaven.dto.OptionDTO;
import ua.ihor0k.heaven.dto.ParameterDTO;
import ua.ihor0k.heaven.service.ImageService;
import ua.ihor0k.heaven.service.ParameterService;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin    // TODO
@RequestMapping("/admin")
public class AdminController {
    private ParameterService parameterService;
    private ImageService imageService;

    @GetMapping("/parameters")
    public List<ParameterDTO> getParameters() {
        return parameterService.getParameters();
    }

    @PostMapping("/parameters")
    @ResponseStatus(HttpStatus.CREATED)
    public ParameterDTO createParameter(@RequestBody ParameterDTO parameter) {
        return parameterService.createParameter(parameter);
    }

    @PatchMapping("/parameters/{id}")
    public void updateParameter(@PathVariable Long id, @RequestBody ParameterDTO parameter) {
        parameterService.updateParameter(id, parameter);
    }

    @DeleteMapping("/parameters/{id}")
    public void deleteParameter(@PathVariable Long id) {
        parameterService.deleteParameter(id);
    }

    @PostMapping("/parameters/{id}/move")
    public void moveParameter(@PathVariable Long id, @RequestParam Integer position) {
        parameterService.moveParameter(id, position);
    }

    @PostMapping("/parameters/{id}/options")
    @ResponseStatus(HttpStatus.CREATED)
    public OptionDTO createOption(@PathVariable Long id, @RequestBody OptionDTO option) {
        return parameterService.createOption(id, option);
    }

    @DeleteMapping("/parameters/{parameterId}/options/{optionId}")
    public void deleteOption(@PathVariable Long parameterId, @PathVariable Long optionId) {
        parameterService.deleteOption(parameterId, optionId);
    }

    @PostMapping("/parameters/{parameterId}/options/{optionId}/move")
    public void moveOption(@PathVariable Long parameterId, @PathVariable Long optionId, @RequestParam Integer position) {
        parameterService.moveOption(parameterId, optionId, position);
    }

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {
        if (!Objects.equals(image.getContentType(), MediaType.IMAGE_PNG_VALUE)) {
            return ResponseEntity
                    .badRequest()
                    .body("File extension should be png");
        }
        String fileName = imageService.upload(image.getResource());
        return ResponseEntity
                .ok(fileName);
    }

    @GetMapping("/image/{name}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String name) {
        Resource image = imageService.download(name);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(image);
    }

    @DeleteMapping("/image/{name}")
    public void deleteImage(@PathVariable String name) {
        imageService.delete(name);
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
