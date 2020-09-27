package dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
}
