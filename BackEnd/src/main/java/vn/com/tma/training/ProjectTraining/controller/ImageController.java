package vn.com.tma.training.ProjectTraining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.tma.training.ProjectTraining.common.MessageResponse;
import vn.com.tma.training.ProjectTraining.dto.ImageDTO;
import vn.com.tma.training.ProjectTraining.service.ImageService;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/{employee_id}")
    public ResponseEntity<?> getImg(@PathVariable Integer employee_id) {
        try {
            ImageDTO imageDTO = imageService.getImg(employee_id);
            return ResponseEntity.ok().header("Content-Type", imageDTO.getContentType()).body(imageDTO.getData());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }
    }

    @PostMapping("/upload/{employee_id}")
    public ResponseEntity<?> upload(@RequestBody MultipartFile img, @PathVariable Integer employee_id) {
        String contentType=img.getContentType();
        String s= contentType != null ? contentType.substring(0, 5) : null;
        if(!s.equals("image")){
            return ResponseEntity.badRequest().body(MessageResponse.builder().message("File is not matched value!").build());
        }
        String message = "";
        try {
            imageService.save(img, employee_id);
            message = "Uploaded the Image successfully: " + img.getOriginalFilename();
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
