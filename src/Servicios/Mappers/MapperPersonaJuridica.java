package Servicios.Mappers;

import DAO.PersonaJuridicaDAOSQL;
import DAO.PosIVADAOSQL;
import DAO.UbicacionDAOSQL;
import DAO.utils.DAOManager;
import DTO.PersonaJuridicaDTO;
import Dominio.PersonaJuridica;

public class MapperPersonaJuridica {

    MapperLocalidad mapperLocalidad;
    PersonaJuridicaDAOSQL personaJuridicaDAO;
    PosIVADAOSQL posIVADAO;
    UbicacionDAOSQL ubicacionDAO;
    DAOManager daoManager;

    public PersonaJuridicaDTO toDTO(PersonaJuridica dominio){
        PersonaJuridicaDTO dto = new PersonaJuridicaDTO();

        mapperLocalidad = new MapperLocalidad();
        if(dominio != null) {
            dto.setAltura(dominio.getAltura());
            dto.setCalle(dominio.getCalle());
            dto.setCuit_cif(dominio.getCuit_cif());
            dto.setEmail(dominio.getEmail());
            dto.setIdpersona(dominio.getIdpersona());
            dto.setPosIVA(dominio.getIVA().getTipo());
            dto.setTelefono(dominio.getTelefono());
            dto.setDomicilioFiscal(dominio.getDomicilioFiscal());
            dto.setRazonSocial(dominio.getRazonSocial());
            dto.setLocalidad(mapperLocalidad.toDTO(dominio.getLocalidad()));
            return dto;
        }else{
            return null;
        }

    }

    public PersonaJuridica toDomain(PersonaJuridicaDTO dto){
       PersonaJuridica personaJuridica;

       daoManager = new DAOManager();
       posIVADAO = daoManager.getPosIVADAO();
       ubicacionDAO = daoManager.getUbicacionDAO();

            personaJuridica = new PersonaJuridica();
            personaJuridica.setIdpersona(dto.getIdpersona());
            personaJuridica.setIVA(posIVADAO.getIVA(dto.getPosIVA()));
            personaJuridica.setLocalidad(ubicacionDAO.buscarLocalidad(dto.getLocalidad().getCodigo()));
            personaJuridica.setTelefono(dto.getTelefono());
            personaJuridica.setCalle(dto.getCalle());
            personaJuridica.setEmail(dto.getEmail());
            personaJuridica.setCuit_cif(dto.getCuit_cif());
            personaJuridica.setAltura(dto.getAltura());
            personaJuridica.setDomicilioFiscal(dto.getDomicilioFiscal());
            personaJuridica.setRazonSocial(dto.getRazonSocial());


        return personaJuridica;
    }

}
