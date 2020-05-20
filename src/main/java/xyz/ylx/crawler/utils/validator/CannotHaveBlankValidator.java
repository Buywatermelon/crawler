package xyz.ylx.crawler.utils.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class CannotHaveBlankValidator implements ConstraintValidator<CannotHaveBlank, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s != null && s.contains(" ")) {
            // 获取默认提示信息
            String messageTemplate = constraintValidatorContext.getDefaultConstraintMessageTemplate();
            log.error("default message: " + messageTemplate);
            return false;
        }
        return true;
    }
}
