package vn.com.tma.training.ProjectTraining.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    public void save(MultipartFile img, Integer employee_id) throws IOException;

    public byte[] getImg(Integer id);
}
