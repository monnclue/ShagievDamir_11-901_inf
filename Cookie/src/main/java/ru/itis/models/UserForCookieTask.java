package ru.itis.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UserForCookieTask {
    private String login;
    private String password;
    private String uuid;
}
