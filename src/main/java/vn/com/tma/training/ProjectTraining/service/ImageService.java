package vn.com.tma.training.ProjectTraining.service;

import org.springframework.web.multipart.MultipartFile;
import vn.com.tma.training.ProjectTraining.dto.ImageDTO;

import java.io.IOException;

public interface ImageService {
    public void save(MultipartFile img, Integer employee_id) throws IOException;

    public ImageDTO getImg(Integer id);
}
