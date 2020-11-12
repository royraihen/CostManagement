package costmanagement.model;

import il.ac.hit.costmanagement.dm.Spend;
import il.ac.hit.costmanagement.exception.CostManagementException;


public interface ISpendDAO {
    void addSpend(Spend spend) throws CostManagementException;
    void deleteSpend(int id) throws CostManagementException;
    double getSpendByCategory(int userId, String category) throws CostManagementException;
    double getSpendByMonth(int id, int month) throws CostManagementException;
}
