package costmanagement.model;

import il.ac.hit.costmanagement.dm.Incoming;
import il.ac.hit.costmanagement.exception.CostManagementException;

public interface IIncomingDAO {
    void addIncoming(Incoming incoming) throws CostManagementException;
    void deleteIncoming(int id) throws CostManagementException;
    double getIncomeByMonth(int id, int month) throws CostManagementException;
}
