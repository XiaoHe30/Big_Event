package com.xiaohe.validation;

import com.xiaohe.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State, String> {
    /*
    * s：将来要校验的数据
    * return：如果返回true，则校验通过，返回false，则校验不通过
    * */

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验规则
        if (s == null) {
            return false;
        }

        if (s.equals("已发布") || s.equals("草稿")) {
            return true;
        }
        return false;
    }
}
