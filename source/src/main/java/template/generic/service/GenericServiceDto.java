package template.generic.service;

import template.generic.model.EntidadPersistente;

public class GenericServiceDto<E extends EntidadPersistente,ID> extends GenericService {


    public EntidadPersistente save(EntidadPersistente entity) {
        return super.save(entity);

    }
}
