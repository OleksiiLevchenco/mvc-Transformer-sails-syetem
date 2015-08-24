package com.levchenko.transformerShop.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Alexey Levchhenko
 */
@Component
public class FileUploadValidator implements Validator {

    private Integer maxUploadFileSize;

    @Override
    public boolean supports(Class<?> aClass) {
        return MultipartFile.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {


        MultipartFile file = (MultipartFile) o;

        if (file.getSize() > maxUploadFileSize) {
            errors.rejectValue("imgUrl", "OverSize.employeeForm.imgUrl");
        }

        String[] formats = {"image/jpeg", "image/png", "image/gif"};
        boolean wrongFormat = true;
        for (String format : formats) {
            if (file.getContentType().equals(format)) {
                wrongFormat = false;
                break;
            }
        }
        if (wrongFormat) {
            errors.rejectValue("imgUrl", "WrongFormat.employeeForm.imgUrl");
        }


    }



// getters and setters
    public Integer getMaxUploadFileSize() {
        return maxUploadFileSize;
    }

    public void setMaxUploadFileSize(Integer maxUploadFileSize) {
        this.maxUploadFileSize = maxUploadFileSize;
    }
}

