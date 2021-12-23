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
    private PersonaJuridicaDAOSQL personaJuridicaDAO = null;
    private OcupacionDAOSQL ocupacionDAO = null;
    private ConsumoDAOSQL consumoDAO = null;
    private ReservaDAOSQL reservaDAO = null;
    private FueraDeServicioDAOSQL fueraDeServicioDAO = null;
    private FacturaDAOSQL facturaDAO = null;
    private ItemDAOSQL itemDAO = null;

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

    public PersonaJuridicaDAOSQL getPersonaJuridicaDAO() {
        if (this.personaJuridicaDAO == null) {
            this.personaJuridicaDAO = new PersonaJuridicaDAOSQL(wrapper.getConnection());
        }
        return personaJuridicaDAO;
    }

    public OcupacionDAOSQL getOcupacionDAO() {
        if (this.ocupacionDAO == null) {
            this.ocupacionDAO = new OcupacionDAOSQL(wrapper.getConnection());
        }
        return ocupacionDAO;
    }

    public ConsumoDAOSQL getConsumoDAO() {
        if (this.consumoDAO == null) {
            this.consumoDAO = new ConsumoDAOSQL(wrapper.getConnection());
        }
        return consumoDAO;
    }

    public ReservaDAOSQL getReservaDAO() {
        if (this.reservaDAO == null) {
            this.reservaDAO = new ReservaDAOSQL(wrapper.getConnection());
        }
        return reservaDAO;
    }

    public FueraDeServicioDAOSQL getFueraDeServicioDAO() {
        if (this.fueraDeServicioDAO == null) {
            this.fueraDeServicioDAO = new FueraDeServicioDAOSQL(wrapper.getConnection());
        }
        return fueraDeServicioDAO;
    }

    public FacturaDAOSQL getFacturaDAO() {
        if (this.facturaDAO == null) {
            this.facturaDAO = new FacturaDAOSQL(wrapper.getConnection());
        }
        return facturaDAO;
    }

    public ItemDAOSQL getItemDAO() {
        if (this.itemDAO == null) {
            this.itemDAO = new ItemDAOSQL(wrapper.getConnection());
        }
        return itemDAO;
    }
}
