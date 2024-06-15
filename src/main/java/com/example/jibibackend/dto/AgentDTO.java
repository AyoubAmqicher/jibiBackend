package com.example.jibibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgentDTO {

    private String id;

    private String lastName;
    private String firstName;
    private String email;
    private String phone;

    private String agence;
}
