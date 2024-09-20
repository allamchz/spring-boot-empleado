package cr.acuna.springbootempleado.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PuestoRepository extends JpaRepository<Empleado, Long> {
}
