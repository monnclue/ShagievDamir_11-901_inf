package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Address {
    private Long accountId;
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String street;
    private String postcode;
    private String phone;
}
