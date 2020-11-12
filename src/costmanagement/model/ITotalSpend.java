package costmanagement.model;

import il.ac.hit.costmanagement.dm.Incoming;
import il.ac.hit.costmanagement.dm.Spend;
import il.ac.hit.costmanagement.exception.CostManagementException;


public interface ITotalSpend {
    double getTotalIncomeByMonth(int id, int month) throws CostManagementException;
    double getTotalSpendByMonth(int id, int month) throws CostManagementException;
    double getTotalAmountByMonth(int id, int month) throws CostManagementException;
    double getTotalSpendByYear(int id, int year) throws CostManagementException;
    void addSpendToTotalSpend(Spend spend) throws CostManagementException;
    void addIncomeToTotalSpend(Incoming incoming) throws CostManagementException;
    void deleteSpendFromTotalSpend(Spend spend) throws CostManagementException;
    void deleteIncomeFromTotalSpend(Incoming incoming) throws CostManagementException;
}
