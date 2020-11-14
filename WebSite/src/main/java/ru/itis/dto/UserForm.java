package ru.itis.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
