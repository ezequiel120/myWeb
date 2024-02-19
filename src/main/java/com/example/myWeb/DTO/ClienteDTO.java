package com.example.myWeb.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteDTO extends PersonaDTO {

    long id;

    String sede;

}
