package ru.itis.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class AddressForm {
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String street;
    private String postcode;
    private String phone;
}
