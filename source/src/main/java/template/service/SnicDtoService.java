package template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import template.dto.SnicDto;
import template.dto.UserDto;
import template.exception.GenericException;
import template.model.Snic;
import template.model.SnicDelito;
import template.model.User;
import template.repository.SnicRepository;

@Service
public class SnicDtoService extends GenericDtoService<SnicDto,Snic, Long> {
    @Autowired
    private SnicRepository snicRepository;

    @Override
    protected JpaRepository<Snic, Long> getRepository() {
        return snicRepository;
    }

    @Override
    public Snic mapperDtoToEntity(SnicDto snicDto) {
        return modelMapper.map(snicDto,Snic.class);
    }

    @Override
    public SnicDto mapperEntityToDto(Snic snic) {
        return modelMapper.map(snic,SnicDto.class);
    }


    @Override
    public void applyBusinessRules(SnicDto snicDto){
        //- Validar permiso del usuario contra la seccional
        // Validar Consistencia de datos
      //  No se deben duplicar los tipos de delitos informados
/*

        snicDto.getSnicDelitos().stream()
                .forEach(snicDelito -> {
                    Integer totalVictimas = 0;
                    Integer totalDenuncias = 0;
                    Integer totalGenero = 0;

                    totalDenuncias = snicDelito.getPorDenunciaParticular() + snicDelito.getPorIntervencionPolicial() + snicDelito.getPorOrdenJudicial()
                            + snicDelito.getOtroNoConsta();
                    totalVictimas = snicDelito.getVictimasFemenino() + snicDelito.getVictimasMasculino() + snicDelito.getVictimasNoConsta();
                    totalGenero = snicDelito.getGeneroSd() + snicDelito.getGeneroOtro() + snicDelito.getMujer()+ snicDelito.getMujerTrans()
                            + snicDelito.getVaron() + snicDelito.getVaronTrans();

                    if ( totalDenuncias != totalVictimas )
                        throw new GenericException("Tipo Delito '"+snicDelito.getCodigoTipoDelitoSnic()+"': El total de Víctimas debe ser igual al total de Hechos");

                    if ( totalVictimas != totalGenero )
                        throw  new GenericException("Tipo Delito '"+snicDelito.getCodigoTipoDelitoSnic()+": 'El total de Víctimas debe ser igual al total de Género");
                });
*/

    }

    private void validarConsistenciaDatosTiposDelitos(){


    }



    /*@Autowired
    private BarrioRepository barrioRepository;

    @Override
    protected GenericServiceEntity2<Barrio, Long> getService() {
        return null;
    }

    @Override
    protected JpaRepository<Barrio, Long> getRepository() {
        return barrioRepository;
    }*/
}
