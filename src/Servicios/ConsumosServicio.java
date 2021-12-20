package Servicios;

import DAO.ConsumoDAOSQL;
import DAO.HabitacionDAOSQL;
import DAO.utils.DAOManager;
import Dominio.Consumo;
import Dominio.Ocupacion;
import Servicios.Mappers.MapperHabitacion;
import Servicios.Mappers.MapperOcupacion;
import utils.Converter;

public class ConsumosServicio {
    private Converter converter = new Converter();

    DAOManager daoManager;
    ConsumoDAOSQL consumoDAO;

    public Consumo getConsumosOcupacion(Ocupacion unOcupacion){
        Consumo unConsumo = new Consumo();
        daoManager = new DAOManager();
        consumoDAO = daoManager.getConsumoDAO();

        daoManager.begin();

        unConsumo = consumoDAO.getConsumoOcupacion(unOcupacion);
        daoManager.commit();
        daoManager.disconnect();
        return unConsumo;
    }
}
