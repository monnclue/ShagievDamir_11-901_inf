package ru.itis.models;

import lombok.*;

/**
 * 23.09.2020
 * 04. Html Servlet
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class User {
    private Long id;
    private String email;
    private String hashPassword;
    private String firstName;
    private String lastName;
}
