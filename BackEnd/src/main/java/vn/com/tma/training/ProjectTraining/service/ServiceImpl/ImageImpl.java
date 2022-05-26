package vn.com.tma.training.ProjectTraining.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import vn.com.tma.training.ProjectTraining.dto.ImageDTO;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;
import vn.com.tma.training.ProjectTraining.entity.ImageEntity;
import vn.com.tma.training.ProjectTraining.mapper.ImageMapper;
import vn.com.tma.training.ProjectTraining.repository.EmployeeRepository;
import vn.com.tma.training.ProjectTraining.repository.ImageRepository;
import vn.com.tma.training.ProjectTraining.service.ImageService;

import java.io.IOException;

@Controller
public class ImageImpl implements ImageService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageMapper mapper;

    @Override
    public void save(MultipartFile img, Integer employee_id) throws IOException {
        EmployeeEntity entity = employeeRepository.findById(employee_id).orElseThrow(() -> new IllegalArgumentException("Employee is not found!"));
        ImageEntity imageEntity = imageRepository.findByEmployee(entity);
        imageEntity.setContentType(img.getContentType());
        imageEntity.setData(img.getBytes());
        employeeRepository.save(entity);
    }

    @Override
    public ImageDTO getImg(Integer employee_id) {
        EmployeeEntity entity = employeeRepository.findById(employee_id)
                .orElseThrow(() -> new IllegalArgumentException("Employee is not found!"));
        ImageEntity imageEntity = imageRepository.findByEmployee(entity);
        if (imageEntity.getData() != null) {
            return mapper.toDTO(imageEntity);
        } else {
            throw new IllegalArgumentException("No Image");
        }
    }
}
