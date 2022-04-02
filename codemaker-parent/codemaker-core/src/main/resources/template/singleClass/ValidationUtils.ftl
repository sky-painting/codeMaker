package ${package}.support;

package com.oyo.inventory.common.utils;


import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * @author kermit.liu on 2018/3/15
 */
public class ValidationUtils {
/**
* 使用hibernate的注解来进行验证
*/
private static Validator validator = Validation.byProvider(HibernateValidator.class)
.configure().failFast(true).buildValidatorFactory().getValidator();

/**
* 功能描述: <br>
* 〈注解验证参数〉
*
* @param obj obj
*/
    public static <T> String validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 检验不合格处理
        if (CollectionUtils.isNotEmpty(constraintViolations)) {
            List<String> tipList = Lists.newArrayList();
            constraintViolations.forEach(constraintViolationImpl -> tipList.add(constraintViolationImpl.getMessage()));
                return StringUtils.join(tipList, ",");
        } else {
            return "";
        }
    }
}