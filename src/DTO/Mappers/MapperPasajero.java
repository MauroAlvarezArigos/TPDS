package DTO.Mappers;

import DTO.PasajeroDTO;
import Dominio.Pasajero;

public class MapperPasajero {
    public PasajeroDTO toDTO(Pasajero unPasajero){
        PasajeroDTO unPasajeroDTO = new PasajeroDTO();

        unPasajeroDTO.setNombre(unPasajero.getNombre());
        unPasajeroDTO.setApellido(unPasajero.getApellido());
        unPasajeroDTO.setNdoc(unPasajero.getNdoc());
        unPasajeroDTO.setTipodoc(unPasajero.getTipodoc().getTipoDeID());
        unPasajeroDTO.setOcupacion(unPasajero.getOcupacion());
        unPasajeroDTO.setFechanacimiento(unPasajero.getFechanacimiento().toString());
        unPasajeroDTO.setNacionalidad(unPasajero.getNacionalidad().getNacionalidad());
        unPasajeroDTO.setTelefono(unPasajero.getTelefono());
        unPasajeroDTO.setEmail(unPasajero.getEmail());
        unPasajeroDTO.setCuit_cif(unPasajero.getCuit_cif());
        unPasajeroDTO.setCalle(unPasajero.getCalle());
        unPasajeroDTO.setAltura(unPasajero.getAltura());
        unPasajeroDTO.setIVA(unPasajero.getIVA().getTipo());
        //unPasajeroDTO.setPais();
        //unPasajeroDTO.setProvincia(unPasajero.getLocalidad().getProvincia());
        unPasajeroDTO.setLocalidad(unPasajero.getLocalidad().getNombre());

        return unPasajeroDTO;
    }
}
