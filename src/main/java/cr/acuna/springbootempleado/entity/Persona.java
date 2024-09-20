package cr.acuna.springbootempleado.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
public class Persona {

    Long id;
    String nombre;
    int edad;
}
