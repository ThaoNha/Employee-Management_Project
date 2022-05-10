package vn.com.tma.training.ProjectTraining.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;
import vn.com.tma.training.ProjectTraining.repository.EmployeeRepository;
import vn.com.tma.training.ProjectTraining.service.ImageService;

import java.io.IOException;

@Controller
public class ImageImpl implements ImageService {
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public void save(MultipartFile img, Integer employee_id) throws IOException {
        EmployeeEntity entity = employeeRepository.findById(employee_id).orElseThrow(() -> new IllegalArgumentException("Employee is not found!"));
        entity.setImage(img.getBytes());
        employeeRepository.save(entity);
    }

    @Override
    public byte[] getImg(Integer employee_id) {
        EmployeeEntity entity = employeeRepository.findById(employee_id)
                .orElseThrow(() -> new IllegalArgumentException("Employee is not found!"));
        return entity.getImage();
    }
}
