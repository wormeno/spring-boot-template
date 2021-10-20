package service.rest.businessRule;

import org.springframework.stereotype.Component;
import service.rest.model.EntidadPersistente;

@Component
public interface  ReglaDeNegocio<E extends EntidadPersistente> {

 //   public abstract void applyBusinessRules(E entity, Action action);

    default void delete(E entity){}
    default void put(E entity){}
    default void patch(E entity){}
    default void post(E entity){}

}
