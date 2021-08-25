package template.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import template.generic.response.ResponseModel;
import javax.validation.Valid;

@Validated
public interface GenericController<E,ID> {

   // public ResponseEntity<?> findAllPageable(@PathVariable Integer pageIndex, @PathVariable Integer pageSize);
  //  public ResponseEntity<?> findAll();
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> save(@Valid @RequestBody E entity);

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> update(@Valid @PathVariable ID id,@RequestBody E entity);

    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> partialUpdate(@Valid  @PathVariable ID id, @RequestBody E entity );

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> find();

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody ResponseModel<Void> delete(@Valid @PathVariable ID id);

}
