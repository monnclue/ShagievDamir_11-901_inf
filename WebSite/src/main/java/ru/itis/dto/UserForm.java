package ru.itis.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 26.10.2020
 * 05. Skeleton
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Data
@AllArgsConstructor
@Builder
public class UserForm {
    @NotEmpty
    @Pattern(regexp =
            "([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}")
    private String email;
    @NotEmpty
    @Pattern(regexp = "[A-Z][a-z]*")
    @Length(min = 2, max = 20)
    private String firstName;
    @NotEmpty
    @Length(min = 2, max = 20)
    @Pattern(regexp = "[A-Z][a-z]*")
    private String lastName;
    @NotEmpty
    @Length(min = 8, max = 20)
    private String password;
}
