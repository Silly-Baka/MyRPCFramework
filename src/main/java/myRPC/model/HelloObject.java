package myRPC.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Date: 2022/5/26
 * Time: 21:00
 *
 * @Author SillyBaka
 * Description：
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloObject implements Serializable {
    private Integer id;
    private String name;
}
