package costmanagement.rest;

import il.ac.hit.costmanagement.exception.CostManagementException;
import il.ac.hit.costmanagement.model.CostManagementDAO;
import il.ac.hit.costmanagement.model.ISpendDAO;
import il.ac.hit.costmanagement.model.ITotalSpend;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 *
 * This class is for home services of the user
 * using RESTful web services
 *
 */


@Path("/home")
public class HomeService {

    ISpendDAO spendDAO = CostManagementDAO.getInstance();
    ITotalSpend totalSpendDAO = CostManagementDAO.getInstance();

    /**
     * Getting all the costs of the current year
     // * @param allYearCost JSON object that represent data from request and the data of response
     * @return JSON object that represent the costs of each month of the currently year
     */
    @GET
    @Path("/yearcosts/{userId}")
    @Consumes({"application/json"})
    public Response getAllYearCosts(@PathParam("userId") int id) {

        JSONObject response = new JSONObject();

        int month = new Date(Calendar.getInstance().getTimeInMillis()).toLocalDate().getMonth().getValue();

        String totalSpendForMonth = "0";
        String totalIncomeForMonth = "0";


        String januarySpend = "0";
        String februarySpend = "0";
        String marchSpend = "0";
        String aprilSpend = "0";
        String maySpend = "0";
        String juneSpend = "0";
        String julySpend = "0";
        String augustSpend = "0";
        String septemberSpend = "0";
        String octoberSpend = "0";
        String novemberSpend = "0";
        String decemberSpend = "0";

        String[] monthsSpend = {januarySpend, februarySpend, marchSpend, aprilSpend,
                maySpend, juneSpend, julySpend, augustSpend, septemberSpend, octoberSpend,
                novemberSpend, decemberSpend};


        String januaryIncome = "0";
        String februaryIncome = "0";
        String marchIncome = "0";
        String aprilIncome = "0";
        String mayIncome = "0";
        String juneIncome = "0";
        String julyIncome = "0";
        String augustIncome = "0";
        String septemberIncome = "0";
        String octoberIncome = "0";
        String novemberIncome = "0";
        String decemberIncome = "0";

        String[] monthIncome = {januaryIncome, februaryIncome, marchIncome, aprilIncome,
                mayIncome, juneIncome, julyIncome, augustIncome, septemberIncome, octoberIncome,
                novemberIncome, decemberIncome};

        try {

            for (int i = 0; i < monthsSpend.length; i++) {
                monthsSpend[i] = String.valueOf(totalSpendDAO.getTotalSpendByMonth(id, i+1));
                if (i+1 == month)
                    totalSpendForMonth = monthsSpend[i];
            }


            for (int currentMonth = 0; currentMonth < monthIncome.length; currentMonth++) {
                monthIncome[currentMonth] = String.valueOf(totalSpendDAO.getTotalIncomeByMonth(id, currentMonth+1));
                if (currentMonth+1 == month)
                    totalIncomeForMonth = monthIncome[currentMonth];
            }


            response.put("januarySpend",  monthsSpend[0]);
            response.put("februarySpend", monthsSpend[1]);
            response.put("marchSpend", monthsSpend[2]);
            response.put("aprilSpend", monthsSpend[3]);
            response.put("maySpend", monthsSpend[4]);
            response.put("juneSpend", monthsSpend[5]);
            response.put("julySpend", monthsSpend[6]);
            response.put("augustSpend", monthsSpend[7]);
            response.put("septemberSpend", monthsSpend[8]);
            response.put("octoberSpend", monthsSpend[9]);
            response.put("novemberSpend", monthsSpend[10]);
            response.put("decemberSpend", monthsSpend[11]);

            response.put("totalSpendForMonth", totalSpendForMonth);

            response.put("januaryIncome",  monthIncome[0]);
            response.put("februaryIncome",  monthIncome[1]);
            response.put("marchIncome",  monthIncome[2]);
            response.put("aprilIncome",  monthIncome[3]);
            response.put("mayIncome",  monthIncome[4]);
            response.put("juneIncome",  monthIncome[5]);
            response.put("julyIncome",  monthIncome[6]);
            response.put("augustIncome",  monthIncome[7]);
            response.put("septemberIncome",  monthIncome[8]);
            response.put("octoberIncome",  monthIncome[9]);
            response.put("novemberIncome",  monthIncome[10]);
            response.put("decemberIncome",  monthIncome[11]);

            response.put("totalIncomeForMonth", totalIncomeForMonth);

            response.put("status","OK");
            return Response.status(Response.Status.OK).entity(response.toString()).build();
        }
        catch (CostManagementException e){
            e.printStackTrace();
            response.put("status","ERROR");
            response.put("errorMessage",e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(response.toString()).build();
        }

    }


    /**
     * Getting all the monthly spend per each category
     * @param userId the id of the selected user
     * @return the amount spend of each category
     */
    @GET
    @Path("/categories/{userid}")
    @Consumes({"application/json"})
    public Response getAllCategories(@PathParam("userid") int userId) {

        JSONObject response = new JSONObject();
        try {

            String shopping = String.valueOf(spendDAO.getSpendByCategory(userId, "Shopping"));
            String transport = String.valueOf(spendDAO.getSpendByCategory(userId, "Transport"));
            String restaurant = String.valueOf(spendDAO.getSpendByCategory(userId, "Restaurant"));
            String health = String.valueOf(spendDAO.getSpendByCategory(userId, "Health"));
            String family = String.valueOf(spendDAO.getSpendByCategory(userId, "Family"));
            String groceries = String.valueOf(spendDAO.getSpendByCategory(userId, "Groceries"));
            String leisure = String.valueOf(spendDAO.getSpendByCategory(userId, "Leisure"));
            String government = String.valueOf(spendDAO.getSpendByCategory(userId, "Government"));
            String food = String.valueOf(spendDAO.getSpendByCategory(userId, "Food"));

            response.put("Shopping", shopping);
            response.put("Transport", transport);
            response.put("Restaurant", restaurant);
            response.put("Health", health);
            response.put("Family", family);
            response.put("Groceries", groceries);
            response.put("Leisure", leisure);
            response.put("Government", government);
            response.put("Food", food);

            response.put("status","OK");
            return Response.status(Response.Status.OK).entity(response.toString()).build();
        }
        catch (CostManagementException e){
            e.printStackTrace();
            response.put("status","ERROR");
            response.put("errorMessage",e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(response.toString()).build();
        }
    }

    /**
     * Getting spend amount for currently month
     * @param spend JSON object the represent the current user and the selected month
     * @return the spend amount of the current month
     */
    @GET
    @Path("/getspendformonth")
    public Response getSpendForMonth(String spend) {

        JSONObject totalSpend = new JSONObject(spend);
        try {
            int userId = (int) totalSpend.get("currentUser");
            int month = (Integer) totalSpend.get("month");

            String spendForMonth = String.valueOf(totalSpendDAO.getTotalSpendByMonth(userId, month));
            System.out.println("double total spend: " + spendForMonth);
            totalSpend.put("spendForMonth", spendForMonth);
            totalSpend.put("status","OK");
            System.out.println("total: " + totalSpend.toString());
            return Response.status(Response.Status.OK).entity(totalSpend.toString()).build();

        }
        catch (CostManagementException e){
            e.printStackTrace();
            totalSpend.put("status","ERROR");
            totalSpend.put("errorMessage",e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(totalSpend.toString()).build();
        }
    }

    /**
     * Getting the income amount of the current month
     * @param income JSON object the represent the current user and the selected month
     * @return income amount for the current month
     *
     */
    @GET
    @Path("/monthincome")
    @Consumes({"application/json"})
    public Response getIncomeForMonth(String income)  {
        JSONObject totalIncome = new JSONObject(income);
        try {
            int  userId = (int) totalIncome.get("currentUser");
            int month = (Integer) totalIncome.get("month");

            String incomeForMonth = String.valueOf(totalSpendDAO.getTotalIncomeByMonth(userId, month));

            totalIncome.put("incomeForMonth", incomeForMonth);
            totalIncome.put("status","OK");
            return Response.status(Response.Status.OK).entity(totalIncome.toString()).build();
        }
        catch (CostManagementException e){
            e.printStackTrace();
            totalIncome.put("status","ERROR");
            totalIncome.put("errorMessage",e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(totalIncome.toString()).build();
        }
    }

    /**
     * Getting total costs amount for the current month
     * @param amount JSON object the represent the current user and selected month
     * @return total costs amount for the current month
     */
    @GET
    @Path("/totalamount")
    public Response getTotalAmount(String amount) {
        JSONObject totalAmount = new JSONObject(amount);
        try {
            int userId =totalAmount.getInt("currentUser");
            int month = totalAmount.getInt("month");

            String totalAmountForMonth = String.valueOf(totalSpendDAO.getTotalAmountByMonth(userId, month));

            totalAmount.put("totalAmountForMonth", totalAmountForMonth);
            totalAmount.put("status","OK");
            return Response.status(Response.Status.OK).entity(totalAmount.toString()).build();
        }
        catch (CostManagementException e){
            e.printStackTrace();
            totalAmount.put("status","ERROR");
            totalAmount.put("errorMessage",e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(totalAmount.toString()).build();
        }
    }

}
