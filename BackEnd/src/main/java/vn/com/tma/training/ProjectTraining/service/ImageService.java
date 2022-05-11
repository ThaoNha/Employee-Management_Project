package vn.com.tma.training.ProjectTraining.service;

import org.springframework.web.multipart.MultipartFile;
import vn.com.tma.training.ProjectTraining.response.ImageResponse;

import java.io.IOException;

public interface ImageService {
    public void save(MultipartFile img, Integer employee_id) throws IOException;

    public ImageResponse getImg(Integer id);
}
