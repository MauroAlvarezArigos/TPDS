package DAO.utils;

import DAO.*;

//from http://tutorials.jenkov.com/java-persistence/dao-manager.html
//from http://tutorials.jenkov.com/java-persistence/dao-design-problems.html

//Mirar https://stackoverflow.com/questions/31563537/how-to-avoid-duplicate-codes-that-is-common-for-all-methods-of-a-class
//https://www.baeldung.com/jsf-spring-boot-controller-service-dao


public class DAOManager {
    ConnectionWrapper wrapper;

    private IDTypeDAOSQL iDTypeDAO = null;
    private PasajeroDAOSQL pasajeroDAO = null;
    private PosIVADAOSQL posIVADAO = null;
    private UbicacionDAOSQL ubicacionDAO = null;
    private HabitacionDAOSQL habitacionDAO = null;

    public DAOManager(){
        this.wrapper = new ConnectionWrapper();
    }

    public void begin(){
        wrapper.begin();
    }

    public void disconnect(){
        wrapper.destroy();
    }

    public void commit(){
        wrapper.commit();
    }

    public void rollback(){
        wrapper.rollback();
    }

    public IDTypeDAOSQL getIDTypeDAO(){
        if(this.iDTypeDAO == null){
            this.iDTypeDAO = new IDTypeDAOSQL(wrapper.getConnection());
        }
        return this.iDTypeDAO;
    }

    public PasajeroDAOSQL getPasajeroDAO(){
        if(this.pasajeroDAO == null){
            this.pasajeroDAO = new PasajeroDAOSQL(wrapper.getConnection());
        }
        return pasajeroDAO;
    }

    public PosIVADAOSQL getPosIVADAO(){
        if(this.posIVADAO == null){
            this.posIVADAO = new PosIVADAOSQL(wrapper.getConnection());
        }
        return posIVADAO;
    }

    public UbicacionDAOSQL getUbicacionDAO(){
        if(this.ubicacionDAO == null){
            this.ubicacionDAO = new UbicacionDAOSQL(wrapper.getConnection());
        }
        return ubicacionDAO;
    }

    public HabitacionDAOSQL getHabitacionDAO(){
        if(this.habitacionDAO == null){
            this.habitacionDAO = new HabitacionDAOSQL(wrapper.getConnection());
        }
        return habitacionDAO;
    }

}
