package com.levchenko.tss.dao.hibernate;

import com.levchenko.tss.dao.EmployeeDao;
import com.levchenko.tss.domain.Employee;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Alexey Levchhenko
 */

@Component
public class ImageDao {

    @Autowired
    private String imageFolder;
    @Autowired
    private String defaultImageUrl;
    @Autowired
    private EmployeeDao employeeDao;

    public String saveImage(MultipartFile file, Integer employeeId) {

        if (!file.isEmpty()) {

            String ext = FilenameUtils.getExtension(file.getOriginalFilename());

            File employeesDir = new File(imageFolder);
            if (!employeesDir.exists()) {
                employeesDir.mkdirs();
            }

            StringBuilder fileNameBuilder = new StringBuilder();
            fileNameBuilder
                    .append(File.separator)
                    .append(employeeId)
                    .append('.')
                    .append(ext);

            try {
                byte[] bytes = file.getBytes();

                File serveFile = new File(employeesDir.getAbsolutePath() + fileNameBuilder.toString());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serveFile));
                stream.write(bytes);
                stream.close();

            } catch (IOException e) {
                e.printStackTrace();
                return "-1";
            }

            String imageUrl = ("/images" + fileNameBuilder.toString()).replaceAll("[\\\\]", "/");
            Employee employee = employeeDao.getById(employeeId);
            employee.setImgUrl(imageUrl);

            return imageUrl;
        }

        return "-1";
    }

    public boolean deleteImage(Integer id) {
        final String imgUrl = employeeDao.getById(id).getImgUrl();
        if ( imgUrl != null && ! imgUrl.equals(defaultImageUrl)) {
            File file = new File(imageFolder + imgUrl.replace("/images", ""));
            return file.delete();
        } else return true;
    }
}
