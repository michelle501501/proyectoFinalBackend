package com.portfolio.proyecto.Controller;

import com.portfolio.proyecto.Dto.dtoPersona;
import com.portfolio.proyecto.Entity.Persona;
import com.portfolio.proyecto.Interface.IpersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
    
@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class PersonaController {
    @Autowired IpersonaService ipersonaService;
    
    @GetMapping("personas/traer")
    public List<Persona> getPersona(){
        return ipersonaService.getPersona();
    }
    
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "La persona fue creada correctamente";
    }
    
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
       ipersonaService.deletePersona(id);
       return "La persona fue eliminada correctamente";
    }
    
    /*
    @PutMapping("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                               @RequestParam("nombre") String nuevoNombre,
                               @RequestParam("apellido") String nuevoApellido,
                               @RequestParam("img") String nuevoImg,
                               @RequestParam("bio") String nuevaBio,
                               @RequestParam("puesto") String nuevoPuesto
    ){
        Persona persona = ipersonaService.findPersona(id);

        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevoImg);
        persona.setBio(nuevaBio);
        persona.setPuesto(nuevoPuesto);
    
        ipersonaService.savePersona(persona);
        return persona;
    }
*/
    @PutMapping("/personas/editar/{id}")
    public ResponseEntity<?> editPersona(@PathVariable Long id,@RequestBody dtoPersona dtopersona
    ){
        Persona persona = ipersonaService.findPersona(id);

        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setImg(dtopersona.getImg());
        persona.setBio(dtopersona.getBio());
        persona.setPuesto(dtopersona.getPuesto());
    
        ipersonaService.savePersona(persona);
        //return persona;
        return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }
    


    @GetMapping("personas/traer/perfil")
    public Persona findPersonas(){
        return ipersonaService.findPersona((long)1);
    }
}