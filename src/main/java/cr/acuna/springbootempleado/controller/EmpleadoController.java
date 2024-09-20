package cr.acuna.springbootempleado.controller;


import cr.acuna.springbootempleado.entity.Empleado;
import cr.acuna.springbootempleado.entity.Persona;
import cr.acuna.springbootempleado.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class EmpleadoController {



    @Autowired
    EmpleadoRepository empleadoRepository;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public EmpleadoController(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
        restClient = restClientBuilder.build();
    }

    @GetMapping("/empleado")
    ResponseEntity <List<Empleado>> getEmpleados(){

        return ResponseEntity.ok(empleadoRepository.findAll());
    }
    @PostMapping("/empleado")
    ResponseEntity<Empleado> saveEmpleado(@RequestBody Empleado empleado){

        ServiceInstance serviceInstance =  discoveryClient.getInstances("SPRING-BOOT-AOP-MAVEN").get(0);;
        Persona persona = restClient.get()
                .uri(serviceInstance.getUri()+"/api/persona/" + empleado.getPersona())
                .headers(headers -> headers.setBasicAuth("admin", "admin"))
                .retrieve()
                .body(Persona.class);

        return ResponseEntity.ok(empleadoRepository.save(empleado));
    }
    @PutMapping ("empleado")
    ResponseEntity<Empleado> updatePersona(@RequestBody  Empleado empleado){
        return ResponseEntity.ok(empleadoRepository.save(empleado));
    }
    @DeleteMapping("/empleado/{id}")
    ResponseEntity<Void> deletePersona(@PathVariable("id") Long id ){
        empleadoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/persona/{id}")
    ResponseEntity<Empleado> getPersona(@PathVariable("id") Long id) {


        Optional<Empleado> optional = empleadoRepository.findById(id);
        return optional.map((empleado)->ResponseEntity.ok(empleado))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

}
