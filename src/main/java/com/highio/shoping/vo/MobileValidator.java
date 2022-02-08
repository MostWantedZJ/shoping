package com.highio.shoping.vo;


import com.highio.shoping.validator.IsMobile;
import freemarker.template.utility.StringUtil;
import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<IsMobile,String> {


    private boolean required = true;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(required){
            if(s.equals("")){
                return false;
            }else if(s.length() != 11){
                return false;
            }else{
                return true;
            }
        }else{
            if(StringUtils.isEmpty(s)){
                return true;
            }else{
                if(s.length() != 11){
                    return false;
                }else{
                    return true;
                }
            }
        }
    }
}
