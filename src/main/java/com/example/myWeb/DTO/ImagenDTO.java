package com.example.myWeb.DTO;

import lombok.*;

import java.beans.Transient;

@Data
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class ImagenDTO {
    private int id;
    private String name;
    private String imagenUrl;
    private String imagenId;
}
