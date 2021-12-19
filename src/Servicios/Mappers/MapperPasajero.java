package Servicios.Mappers;

import DTO.PasajeroDTO;
import Dominio.Pasajero;
import Servicios.IDTypeServicio;
import Servicios.IVAServicio;
import Servicios.UbicacionServicio;

import java.sql.Date;
import java.util.List;

public class MapperPasajero {

    UbicacionServicio ubicacionServicio;
    IVAServicio IVAServicio;
    IDTypeServicio IDServicio;

    public PasajeroDTO toDTO(Pasajero unPasajero){
        PasajeroDTO unPasajeroDTO = new PasajeroDTO();

        unPasajeroDTO.setNombre(unPasajero.getNombre());
        unPasajeroDTO.setApellido(unPasajero.getApellido());
        unPasajeroDTO.setNdoc(unPasajero.getNdoc());
        unPasajeroDTO.setTipodoc(unPasajero.getTipodoc().getTipoDeID());
        unPasajeroDTO.setOcupacion(unPasajero.getOcupacion());
        unPasajeroDTO.setFechanacimiento(unPasajero.getFechanacimiento());
        unPasajeroDTO.setNacionalidad(unPasajero.getNacionalidad().getNacionalidad());
        unPasajeroDTO.setTelefono(unPasajero.getTelefono());
        unPasajeroDTO.setEmail(unPasajero.getEmail());
        unPasajeroDTO.setCuit_cif(unPasajero.getCuit_cif());
        unPasajeroDTO.setCalle(unPasajero.getCalle());
        unPasajeroDTO.setAltura(unPasajero.getAltura());
        unPasajeroDTO.setIVA(unPasajero.getIVA().getTipo());
        unPasajeroDTO.setPais(unPasajero.getLocalidad().getProvincia().getPais().getNombre());
        unPasajeroDTO.setProvincia(unPasajero.getLocalidad().getProvincia().getNombre());
        unPasajeroDTO.setLocalidad(unPasajero.getLocalidad().getNombre());

        return unPasajeroDTO;
    }

    public Pasajero toDomain(PasajeroDTO unPasajeroDTO){
        Pasajero unPasajero = new Pasajero();
        ubicacionServicio = new UbicacionServicio();
        IVAServicio = new IVAServicio();
        IDServicio = new IDTypeServicio();

        unPasajero.setNombre(unPasajeroDTO.getNombre());
        unPasajero.setApellido(unPasajeroDTO.getApellido());
        unPasajero.setNdoc(unPasajeroDTO.getNdoc());
        unPasajero.setTipodoc(IDServicio.getIDType(unPasajeroDTO.getTipodoc()));
        unPasajero.setOcupacion(unPasajeroDTO.getOcupacion());
        unPasajero.setFechanacimiento(unPasajeroDTO.getFechanacimiento());
        unPasajero.setNacionalidad(ubicacionServicio.getNacionalidad(unPasajeroDTO.getNacionalidad()));
        unPasajero.setTelefono(unPasajeroDTO.getTelefono());
        unPasajero.setEmail(unPasajeroDTO.getEmail());
        unPasajero.setCuit_cif(unPasajeroDTO.getCuit_cif());
        unPasajero.setCalle(unPasajeroDTO.getCalle());
        unPasajero.setAltura(unPasajeroDTO.getAltura().toString());
        unPasajero.setIVA(IVAServicio.getIVA(unPasajeroDTO.getIVA()));
        unPasajero.setLocalidad(ubicacionServicio.getLocalidadNombre(unPasajeroDTO.getLocalidad(),unPasajeroDTO.getProvincia(),unPasajeroDTO.getPais()));

        return unPasajero;
    }
}
