package tulling.mybatis.v4.entity;

import lombok.Data;
import lombok.ToString;

/**
 * Created by smlz on 2019/3/22.
 */
@Data
@ToString
public class Employee {

    private Integer id;

    private String lastName;

    private String email;

    private Integer gender;

    private Integer deptId;

}
