package lk.ijse.gdse66.javaee.dao;

import lk.ijse.gdse66.javaee.dao.custom.impl.CustomerDAOImpl;

/**
 * @author : Kavithma Thushal
 * @project : JavaEE-POS
 * @since : 3:17 PM - 1/16/2024
 **/
public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        CUSTOMER, CUSTOM, ITEM, ORDERS, ORDERDETAILS
    }
}