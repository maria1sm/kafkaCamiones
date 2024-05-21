package org.babel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Camion {

    private String matricula;
    private float km;
    private Timestamp timestamp;

}
