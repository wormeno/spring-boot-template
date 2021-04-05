package template.generic.service;

import org.springframework.data.jpa.repository.JpaRepository;

public class GenericServiceDto extends GenericService {

    @Override
    protected GenericService getService() {
        return null;
    }

    @Override
    protected JpaRepository getRepository() {
        return null;
    }
}
