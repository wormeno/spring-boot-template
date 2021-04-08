package template.generic.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import template.generic.response.ResponseModel;
import template.generic.response.ResponseModelBuilder;
import template.generic.response.ResponseModelBuilderPaginator;
import template.generic.exeption.GenericException;
import template.generic.model.EntidadPersistente;
import template.generic.service.GenericServiceEntity2;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

public abstract class RestGenericController<E  extends EntidadPersistente,ID> implements GenericController<E>{

    protected abstract GenericServiceEntity2<E, ID> getService();

    @GetMapping("/{pageIndex}/{pageSize}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> findAllPageable(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        Page<E> result = null;
        result = getService().findAllPageable(pageIndex, pageSize);
        ResponseModelBuilderPaginator<E> builder = new ResponseModelBuilderPaginator<E>();

        result.getContent().forEach(e -> {
            builder.addRow(e);
        });

        builder.setPageCount(result.getTotalPages())
                .setPageIndex(result.getNumber())
                .setPageSize(result.getNumberOfElements());

        return ResponseEntity.ok(builder.setResponseCode("OK").buildPaginator());
    }

    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> findAll() {
        List<E> result = getService().findAllList();
        return ResponseEntity.ok(result);
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> save( @Valid @RequestBody E entity){

        entity = getService().save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{i}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
        /*
        try {
            entity = getService().save(entity);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{i}").buildAndExpand(entity.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (GenericException e) {
            return ResponseEntity.ok(new ResponseModelBuilder<E>().setResponseCode("ERROR").setResponseMessage(e.getLocalizedMessage()).build()) ;
        } catch (DuplicateKeyException e) {
            return ResponseEntity.ok(new ResponseModelBuilder<E>().setResponseCode("ERROR").setResponseMessage("Ya existe el/la " + entity.getClass().getSimpleName() + " con los datos ingresados.").build()) ;
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok(new ResponseModelBuilder<E>().setResponseCode("ERROR").setResponseMessage("Datos erroneos. Causa: " + e.getLocalizedMessage()).build()) ;
        } catch (Exception e) {
           System.out.println(e.getMessage());
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage() );
            e.printStackTrace();
//            return ResponseEntity.ok(new ResponseModelBuilder<E>().setResponseCode("ERROR").setResponseMessage("Error inesperado. Causa: " + e.getLocalizedMessage()).build()) ;
        }*/
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> update(@Valid @RequestBody E entity){
        try {
            entity = getService().save(entity);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{i}").buildAndExpand(entity.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (GenericException e) {
            return ResponseEntity.ok(new ResponseModelBuilder<E>().setResponseCode("ERROR").setResponseMessage(e.getLocalizedMessage()).build()) ;
        } catch (DuplicateKeyException e) {
            return ResponseEntity.ok(new ResponseModelBuilder<E>().setResponseCode("ERROR").setResponseMessage("Ya existe el/la " + entity.getClass().getSimpleName() + " con los datos ingresados.").build()) ;
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok(new ResponseModelBuilder<E>().setResponseCode("ERROR").setResponseMessage("Datos erroneos. Causa: " + e.getLocalizedMessage()).build()) ;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ResponseModelBuilder<E>().setResponseCode("ERROR").setResponseMessage("Error inesperado. Causa: " + e.getLocalizedMessage()).build()) ;
        }
    }


    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> find(@PathVariable ID id){
        Optional<E> entity = getService().find(id);

        if (!entity.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(entity) ;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResponseModel<Void> remove(@PathVariable ID id) {
        try {
            this.getService().remove(id);
            return new ResponseModelBuilder<Void>().setResponseCode("OK").build();
        } catch (GenericException e) {
            return new ResponseModelBuilder<Void>().setResponseCode("ERROR").setResponseMessage(e.getLocalizedMessage()).build();
        } catch (Exception e) {
            return new ResponseModelBuilder<Void>().setResponseCode("ERROR").setResponseMessage("Error inesperado. Causa: " + e.getLocalizedMessage()).build();
        }
    }
}
