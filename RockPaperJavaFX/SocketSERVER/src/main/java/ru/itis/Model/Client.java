package ru.itis.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@Getter
@Setter
public class Client {
    private String name;
    private boolean isBusy;


    public Client(String name, boolean isBusy) {
        this.name = name;
        this.isBusy = isBusy;
    }
}

