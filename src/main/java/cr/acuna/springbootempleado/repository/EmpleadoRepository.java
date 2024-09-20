package cr.acuna.springbootempleado.repository;

import cr.acuna.springbootempleado.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
