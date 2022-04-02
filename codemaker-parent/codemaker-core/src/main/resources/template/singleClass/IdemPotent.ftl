package ${package}.domain.support.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shenshuai
 * @see
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IdemPotent {

    /**
     * 接口幂等的请求号
     */
    String key() default "requestNo";

/**
* 消息幂等的消息id 多个需要 _msgId,xxNo,sfdsdf
* 使用需要指定 msgId属性
*/
String msgKey() default "_msgId";

}
