package costmanagement.model;

import il.ac.hit.costmanagement.dm.User;
import il.ac.hit.costmanagement.exception.CostManagementException;


public interface IUserDAO {
    boolean registerUser(User user) throws CostManagementException;
    boolean userAuthentication(String userName, String password) throws CostManagementException;
    int getCurrentUser(String userName) throws CostManagementException;
}
