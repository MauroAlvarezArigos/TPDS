package Servicios.Mappers;

import DAO.ItemDAOSQL;
import DAO.utils.DAOManager;
import DTO.UnidadesDTO;
import Dominio.Unidades;

import java.util.ArrayList;
import java.util.List;

public class MapperUnidades {

    DAOManager daoManager;
    ItemDAOSQL itemDAO;


    public UnidadesDTO toDTO(Unidades dominio){
        UnidadesDTO dto = new UnidadesDTO();
        dto.setNombre(dominio.getItemConsumo().getNombre());
        dto.setCantidad(dominio.getCantidad());
        dto.setCostoUnitario(dominio.getItemConsumo().getCosto());
        dto.setFechaConsumo(dominio.getFechaConsumo());
        dto.setId_item(dominio.getItemConsumo().getId_item());
        dto.setSeccionConsumo(dominio.getItemConsumo().getSeccionConsumo().getSeccion());
        dto.setId_seccionConsumo(dominio.getItemConsumo().getSeccionConsumo().getId_categoria());

        return dto;
    }

    public List<UnidadesDTO> toDTO(List<Unidades> LDominio){
        if(LDominio == null){
            List<UnidadesDTO> Unidades = new ArrayList<>();
            return Unidades;
        }else {
            List<UnidadesDTO> Ldto = new ArrayList<>();
            for (Unidades u : LDominio) {
                Ldto.add(toDTO(u));
            }
            return Ldto;
        }
    }

    public Unidades toDomain (UnidadesDTO dto){
        Unidades dominio = new Unidades();
        dominio.setCantidad(dto.getCantidad());
        dominio.setFechaConsumo(dto.getFechaConsumo());
        dominio.setValorTotal(dto.getValorTotal());
        dominio.setItemConsumo(itemDAO.getItem(dto.getId_item()));

        return dominio;
    }

    public List<Unidades> toDomain (List<UnidadesDTO> Ldto){
        if(Ldto != null) {
            daoManager = new DAOManager();
            itemDAO = daoManager.getItemDAO();
            List<Unidades> LDominio = new ArrayList<>();

            daoManager.begin();
            for (UnidadesDTO u : Ldto) {
                LDominio.add(toDomain(u));
            }

            return LDominio;
        }else{
            return new ArrayList<>();
        }
    }

}
