
package costmanagement.rest;

import il.ac.hit.costmanagement.dm.Incoming;
import il.ac.hit.costmanagement.dm.Spend;
import il.ac.hit.costmanagement.exception.CostManagementException;
import il.ac.hit.costmanagement.model.CostManagementDAO;
import il.ac.hit.costmanagement.model.IIncomingDAO;
import il.ac.hit.costmanagement.model.ISpendDAO;
import il.ac.hit.costmanagement.model.ITotalSpend;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * This class is for action services of the user
 * using RESTful web services
 */


@Path("/actions")
public class ActionsService {

    ISpendDAO spendDAO = CostManagementDAO.getInstance();
    IIncomingDAO incomingDAO = CostManagementDAO.getInstance();
    ITotalSpend totalSpendDAO = CostManagementDAO.getInstance();

    /**
     * Adding a new spend
     * @param newSpend an object that represent the new spend
     * @return JSON object response if the action succeed or not
     */
    @POST
    @Path("/addspend")
    public Response addSpend(String newSpend) {

        JSONObject response = new JSONObject(newSpend);

        try {
            int userId = response.getInt("userId");
            double amount = response.getDouble("amount");
            Date date = new Date(Calendar.getInstance().getTimeInMillis());
            String category = response.getString("category");
            boolean permanentSpend = (boolean) response.get("permanentSpend");
            String comment = response.getString("comment");

            Spend spend = new Spend(userId,amount,date,category,permanentSpend,comment,0);
            spendDAO.addSpend(spend);
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
     * Adding a new income
     * @param newIncome an object the represent the new income
     * @return Response object if the action succeed or not
     */
    @POST
    @Path("/addincome")
    public Response addIncome(String newIncome) {

        JSONObject response = new JSONObject(newIncome);

        try {
            int userId = response.getInt("userId");
            double amount = response.getDouble("amount");
            Date date = new Date(Calendar.getInstance().getTimeInMillis());
            boolean permanentIncome = response.getBoolean("permanentIncome");
            String comment = response.getString("comment");
            Incoming income = new Incoming(userId,amount,date,permanentIncome,comment,0);
            System.out.println("userId: " + userId + "\n amount: " + amount + "\n permanentIncome: " + permanentIncome + "\ncomment: " + comment);
            incomingDAO.addIncoming(income);

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
     * Getting the total amount spend by selected month
     * @param id the id of the selected user
     * @param selectedMonth the value of the month
     * @return total amount spend for selected month
     */
    @GET
    @Path("/spend/month/{userId}/{selectedmonth}")
    @Consumes({"application/json"})
    public Response getTotalSpendBySelectedMonth(@PathParam("userId") int id,@PathParam("selectedmonth") int selectedMonth){
        JSONObject response = new JSONObject(selectedMonth);

        try{
            double spendByMonth = totalSpendDAO.getTotalSpendByMonth(id,selectedMonth);
            response.put("spendByMonth",spendByMonth);
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
     * Getting the total amount income by selected month
     * @param id the id of the selected user
     * @param selectedMonth the value of the month
     * @return total amount income for selected month
     */
    @GET
    @Path("/income/month/{userId}/{selectedmonth}")
    @Consumes({"application/json"})
    public Response getTotalIncomeBySelectedMonth(@PathParam("userId") int id, @PathParam("selectedmonth") int selectedMonth){
        JSONObject response = new JSONObject(selectedMonth);

        try{
            double incomeByMonth = totalSpendDAO.getTotalIncomeByMonth(id,selectedMonth);
            response.put("incomeByMonth",incomeByMonth);
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
}
