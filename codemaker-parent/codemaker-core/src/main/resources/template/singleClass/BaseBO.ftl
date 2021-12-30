package ${package}.domain.bo;

import lombok.ToString;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO基类
 */
@ToString
@Data
public class BaseBO implements Serializable {
   /**
    * 修改时间
    */
   private Date dateUpdate;

   /**
    * 创建时间
    */
   private Date dateCreate;

   /**
    * 修改人
    */
   private Long updateUserId;

   /**
    * 创建人
    */
   private  Long createUserId;

  /**
   * 请求号
   */
   private String requestNo;


}
