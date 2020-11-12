package costmanagement.rest;


import il.ac.hit.costmanagement.dm.User;
import il.ac.hit.costmanagement.exception.CostManagementException;
import il.ac.hit.costmanagement.model.CostManagementDAO;
import il.ac.hit.costmanagement.model.IUserDAO;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 *
 *
 * This class is the user service for base actions of the user
 * using RESTful web services
 *
 */

@Path("/users")
public class UsersService {

    IUserDAO dao = CostManagementDAO.getInstance();

    /**
     *
     * Checking user authentication
     * @param jsonUser the current user that has login
     * @return true if succeed , otherwise false
     *
     */
    @POST
    @Path("/authentication")
    public Response checkAuthentication(String jsonUser) {

        JSONObject userAuthentication = new JSONObject(jsonUser);

        try {
            String email = (String) userAuthentication.get("email");
            String password = (String) userAuthentication.get("password");
            dao.userAuthentication(email, password);
            userAuthentication.put("status","OK");
            return Response.status(Response.Status.OK).entity(userAuthentication.toString()).build();
        }
        catch (CostManagementException e){
            e.printStackTrace();
            userAuthentication.put("status","ERROR");
            userAuthentication.put("errorMessage",e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(userAuthentication.toString()).build();

        }
    }

    /**
     *
     * Getting the current user for making actions in the program
     * @param jsonUser JSON object that represent the user
     * @return JSON object of the user
     *
     */
    @GET
    @Path("/user/{email}")
    public Response getUser(@PathParam("email") String jsonUser) {
        JSONObject currentUser = new JSONObject();

        try {
            currentUser.put("currentUser", dao.getCurrentUser(jsonUser));
            currentUser.put("status","OK");
            return Response.status(Response.Status.OK).entity(currentUser.toString()).build();
        }
        catch (CostManagementException e){
            e.printStackTrace();
            currentUser.put("status","ERROR");
            currentUser.put("errorMessage",e.getMessage());
            return  Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /**
     *
     * Register a new user
     * @param jsonUser JSON object that represent the user name and password of the register page
     * @return true if succeed , otherwise false
     *
     */
    @POST
    @Path("/register")
    @Produces("application/json")
    @Consumes({"application/json"})
    public Response registerUser(String jsonUser) {

        JSONObject userRegistration = new JSONObject(jsonUser);

        try {
            String email = userRegistration.getString("email");
            String password = userRegistration.getString("password");
            int id = 0;
            User user = new User(email,password,id);

            userRegistration.put("isRegistrationSucceed", dao.registerUser(user));

            userRegistration.put("status","OK");
            return Response.status(Response.Status.OK).entity(userRegistration.toString()).build();

        }
        catch (CostManagementException e){
            e.printStackTrace();
            userRegistration.put("status","ERROR");
            userRegistration.put("errorMessage",e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(userRegistration.toString()).build();

        }
    }

}
