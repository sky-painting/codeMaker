package ${package}.domain.support.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MqHandler {

   /**
    * 前缀
    */
   String prefixKey() default Constant.TRADE_CENTER_CORE_LOCK_PREFIX;
/**
 * 锁
 */
String key() default "orderId";

/**
 * 默认 uuid
 */
String requestId() default "requestId";


/**
 * 提示消息
 */
String msg() default "请不要重复提交";


}
