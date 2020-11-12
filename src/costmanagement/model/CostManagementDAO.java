package costmanagement.model;

import il.ac.hit.costmanagement.dm.Incoming;
import il.ac.hit.costmanagement.dm.Spend;
import il.ac.hit.costmanagement.dm.TotalSpend;
import il.ac.hit.costmanagement.dm.User;
import il.ac.hit.costmanagement.exception.CostManagementException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.sql.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class CostManagementDAO implements IIncomingDAO,
        ISpendDAO, ITotalSpend, IUserDAO
{
    private Session session;
    private SessionFactory factory;
    private Query hql;
    private String query;
    private double spend;
    private double income;
    private double total = 0;
    private TotalSpend totalSpend;
    private static CostManagementDAO instance = null;
    private int transactionId;
    private int month = new Date(Calendar.getInstance().getTimeInMillis()).toLocalDate().getMonth().getValue();
    private int year =  new Date(Calendar.getInstance().getTimeInMillis()).toLocalDate().getYear();

    private CostManagementDAO()  {
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        session.beginTransaction();
    }

    public static CostManagementDAO getInstance()  {
        return instance == null ? instance = new CostManagementDAO() : instance;
    }


    /**
     * Add a new income
     * @param incoming The object that represent the details of the incoming
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public void addIncoming(Incoming incoming) throws CostManagementException {

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try {
            session.save(incoming);
            session.getTransaction().commit();

            if (session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                if (session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }

            addIncomeToTotalSpend(incoming);

            System.out.println(incoming.toString() +"\nAction succeed\n");
        }

        catch (HibernateException | CostManagementException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
            if(session!=null)
                session.close();
        }
    }


    /**
     * Delete income by transaction id
     * @param id the transaction id of the incoming
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public void deleteIncoming(int id) throws CostManagementException {

        int rowsAffected;
        Incoming incoming;

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try {
            query = "from Incoming where transactionId = :id";
            hql = session.createQuery(query).setParameter("id", id);
            List<Incoming> incomingList = hql.list();


            query = "delete Incoming where transactionId = :id";
            hql = session.createQuery(query).setParameter("id",id);
            rowsAffected =  hql.executeUpdate();

            if(rowsAffected == 0)
                throw new CostManagementException("There is no transaction with that id",
                        new Throwable("Invalid id"));

            if(session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                if(session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }

            if(!incomingList.isEmpty()) {
                incoming = incomingList.get(0);
                deleteIncomeFromTotalSpend(incoming);
            }


            System.out.println("\nAction succeed\n");
        }

        catch (HibernateException | CostManagementException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
            if(session!=null)
                session.close();
        }
    }

    /**
     * Get an income by selected user and selected month
     * @param id the user id
     * @param month the selected month
     * @return income amount for the selected month
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public double getIncomeByMonth(int id, int month) throws CostManagementException {

        Incoming incoming;
        income = 0;

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }


        try{
            hql =session.createQuery("from Incoming where id = :id");
            hql.setParameter("id", id);
            List<Incoming> incomingList = hql.list();

            if (session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                if (session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }


            if(incomingList.isEmpty()) {
                return 0;
            }
            else {
                incoming = incomingList.get(0);
                Iterator<Incoming> iterator = incomingList.iterator();

                while (iterator.hasNext()) {
                    if (month == incoming.getDate().toLocalDate().getMonth().getValue()) {
                        income = income + incoming.getAmount();
                    }
                    incoming = iterator.next();
                }
                return income;
            }
        }

        catch (HibernateException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
            if(session!=null)
                session.close();
        }
    }

    /**
     * Add a new spend
     * @param spend the object the represent the spend info
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public void addSpend(Spend spend) throws CostManagementException {

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try {
            session.save(spend);
            session.getTransaction().commit();

            if(session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }

            addSpendToTotalSpend(spend);

            System.out.println(spend.toString() + "\nAction succeed\n");
        }

        catch (HibernateException | CostManagementException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
            if(session!=null)
                session.close();
        }
    }

    /**
     * Delete spend by transaction id
     * @param id the transaction id of the spend
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public void deleteSpend(int id) throws CostManagementException {

        Spend spend;
        int rowsAffected;

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try {
            query = "from Spend where transactionId = :id";
            hql = session.createQuery(query).setParameter("id", id);
            List<Spend> spendList = hql.list();


            query = "delete Spend where transactionId = :id";
            hql = session.createQuery(query).setParameter("id", id);
            rowsAffected =  hql.executeUpdate();

            if(rowsAffected == 0)
                throw new CostManagementException("There is no transaction with that id",
                        new Throwable("Invalid id"));

            if(session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                if(session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }

            if(!spendList.isEmpty()) {
                spend = spendList.get(0);
                deleteSpendFromTotalSpend(spend);
            }

            System.out.println("\nAction succeed\n");
        }

        catch (HibernateException | CostManagementException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
            if(session!=null)
                session.close();
        }

    }

    /**
     * Get the spend amount by category
     * @param userId the id of the selected user
     * @param category the name of the selected category
     * @return the amount spend by the selected category
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public double getSpendByCategory(int userId, String category) throws CostManagementException {

        //int userId = user.getId();
        double amountCategory;
        String cat;
        Date date;

        Integer id;
        double amount;

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try{
            hql = session.createQuery("from Spend where category = :category and id = :id");
            hql.setParameter("category", category).setParameter("id",userId);
            List<Spend> spendList = hql.list();


            if(session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                if(session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }

            if(spendList.isEmpty())
                return 0;


            else{

                Query q = session.createQuery("select e.id, e.amount , e.category , e.date from Spend e");
                List<Object[]> employees= (List<Object[]>)q.list();
                amountCategory =0;
                for(Object[] employee: employees){
                    id = (Integer)employee[0];
                    amount = (double)employee[1];
                    cat = (String)employee[2];
                    date = (Date)employee[3];

                    if(userId == id && category.equals(cat) &&  date.toLocalDate().getMonth().getValue() == month)
                        amountCategory = amountCategory + amount;

                }
            }

        }

        catch (HibernateException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
            if(session!=null)
                session.close();

        }
        return amountCategory;
    }

    /**
     * Get the spend by selected month
     * @param id the id of the user
     * @param month the selected month
     * @return the spend amount for month by the selected month
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public double getSpendByMonth(int id, int month) throws CostManagementException {

        spend = 0;
        Spend spendTable;

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try{
            hql =session.createQuery("from Spend where id = :id");
            hql.setParameter("id", id);
            List<Spend> spendList = hql.list();


            if (session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                if (session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }


            if(spendList.isEmpty()) {
                return 0;
            }

                spendTable = spendList.get(0);
                Iterator<Spend> iterator = spendList.iterator();
                while (iterator.hasNext()) {
                    if (month == spendTable.getDate().toLocalDate().getMonth().getValue()) {
                        spend = spend + spendTable.getAmount();
                    }
                    spendTable = iterator.next();

                }
                return spend;
        }


        catch (HibernateException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
           if(session!=null)
                session.close();
        }

    }

    /**
     * Get total income by month
     * @param id the id of the user
     * @param month thr selected month
     * @return the total income amount for the selected month
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public double getTotalIncomeByMonth(int id, int month) throws CostManagementException {
        spend = 0;
        income = 0;
        total = 0;
        TotalSpend totalIncome;

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try{
            hql = session.createQuery("from TotalSpend where month = :month and id = :id");
            hql.setParameter("month", month).setParameter("id",id);
            List<TotalSpend> totalIncomeList = hql.list();


            if(session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                if(session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }

            if(totalIncomeList.isEmpty())
                return 0;

            else{
                totalIncome = totalIncomeList.get(0);
                income = totalIncome.getAmountIncome();
            }

        }

        catch (HibernateException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
            if(session!=null)
                session.close();
        }

        return income;
    }

    /**
     * Get total spend by month
     * @param id the user id
     * @param month the selected month
     * @return the total spend amount for the selected month
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public double getTotalSpendByMonth(int id, int month) throws CostManagementException {
        spend = 0;

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try{
            hql = session.createQuery("from TotalSpend where month = :month and id = :id");
            hql.setParameter("month", month).setParameter("id",id);
            List<TotalSpend> totalSpendList = hql.list();


            if(session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                if(session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }

            if(totalSpendList.isEmpty())
                return 0;

            else{
                totalSpend = totalSpendList.get(0);
                spend = totalSpend.getAmountSpend();
            }

        }

        catch (HibernateException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
            if(session!=null)
                session.close();
        }

        return spend;
    }

    /**
     * Get total amount spend and income by month
     * @param id the user id
     * @param month the selected month
     * @return the total spend and income
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public double getTotalAmountByMonth(int id, int month) throws CostManagementException {

        spend = 0;
        income = 0;
        total = 0;

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try{
            hql = session.createQuery("from TotalSpend where month = :month and id = :id");
            hql.setParameter("month", month).setParameter("id",id);
            List<TotalSpend> totalSpendList = hql.list();


            if(session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                if(session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }

            if(totalSpendList.isEmpty())
                throw new CostManagementException("The selected month is invalid",
                        new Throwable("No spends recorded for this month"));

            else{
                totalSpend = totalSpendList.get(0);
                spend = totalSpend.getAmountSpend();
                income = totalSpend.getAmountIncome();
                total = Math.abs(income + spend);
            }

        }

        catch (HibernateException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
            if(session!=null)
                session.close();
        }

        return total;
    }

    /**
     * Get total amount spend by selected year
     * @param id the user id
     * @param year the selected ye
     * @return ar total amount spend by selected year
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public double getTotalSpendByYear(int id, int year) throws CostManagementException {

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try{
            hql = session.createQuery("FROM TotalSpend where year = :year and id = :id");
            hql.setParameter("year", year).setParameter("id",id);
            List<TotalSpend> totalSpendList = hql.list();

            if(session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                if(session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }

            if(totalSpendList.isEmpty())
                throw new CostManagementException("The selected year is invalid",
                        new Throwable("No spends recorded for this year"));

            else{
                Iterator<TotalSpend> iterator = totalSpendList.iterator();

                /* Retrieve from database all the rows for the selected year
                   and calculates all the spend and income for the selected year
                   and returns the total cost for this year. */

                while(iterator.hasNext()){
                    totalSpend = iterator.next();
                    spend = spend + totalSpend.getAmountSpend();
                    income = income + totalSpend.getAmountIncome();
                }
                total = income - spend;
            }

        }

        catch (HibernateException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
            if(session!=null)
                session.close();
        }

        return total;
    }

    /**
     * Add spend to the total spend in month
     * @param spend represent the spend information
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public void addSpendToTotalSpend(Spend spend) throws CostManagementException {

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try {
            transactionId = spend.getId();
            session.beginTransaction();
            hql = session.createQuery("from TotalSpend where month = :month and id = :id");
            hql.setParameter("month", month).setParameter("id", transactionId);
            List<TotalSpend> totalSpendList = hql.list();

            if(session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                if(session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }


             /* Check if there is already month with spend amount on db.
             if yes the list.size return value 1 else it returns value 0
             and then we create a new row for this month.
             Each row has specific month. */

            if (totalSpendList.isEmpty()) {
                totalSpend = new TotalSpend(spend.getId(), spend.getAmount(), 0, year, month);
                session.save(totalSpend);
                session.getTransaction().commit();


                if (session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                    session.getTransaction().rollback();
                    throw new CostManagementException("Action failed",
                            new Throwable("The transaction was not committed"));
                }
            } else {
                totalSpend = totalSpendList.get(0);
                double newAmount = spend.getAmount() + totalSpend.getAmountSpend();
                hql = session.createQuery("UPDATE TotalSpend spend SET spend.amountSpend = :newAmount WHERE spend.month = :month");
                hql.setParameter("newAmount", newAmount).setParameter("month", month);
                hql.executeUpdate();
            }

        }
        catch (HibernateException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
            if(session!=null)
                session.close();
        }
    }

    /**
     * Add income to toal income
     * @param incoming represent the income information
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public void addIncomeToTotalSpend(Incoming incoming) throws CostManagementException {

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try {
            transactionId = incoming.getId();
             session.beginTransaction();
            hql = session.createQuery("from TotalSpend where month = :month and id = :id");
            hql.setParameter("month", month).setParameter("id", transactionId);
            List<TotalSpend> totalSpendList = hql.list();


             /* Check if there is already month with spend amount on db.
             if yes the list.size return value 1 else it returns value 0
             and then we create a new row for this month.
             Each row has specific month. */

            if (totalSpendList.isEmpty()) {
                totalSpend = new TotalSpend(incoming.getId(), 0, incoming.getAmount(), year, month);
                session.save(totalSpend);
                session.getTransaction().commit();


                if (session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                    session.getTransaction().rollback();
                    throw new CostManagementException("Action failed",
                            new Throwable("The transaction was not committed"));
                }
            } else {
                totalSpend = totalSpendList.get(0);
                double newAmount = incoming.getAmount() + totalSpend.getAmountIncome();
                hql = session.createQuery("UPDATE TotalSpend spend SET spend.amountIncome = :newAmount WHERE spend.month = :month");
                hql.setParameter("newAmount", newAmount).setParameter("month", month);
                hql.executeUpdate();
            }
        }
        catch (HibernateException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
            if(session!=null)
                session.close();
        }
    }

    /**
     * Delete spend from total spend
     * @param spend represent the spend information
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public void deleteSpendFromTotalSpend(Spend spend) throws CostManagementException {

        transactionId = spend.getId();

        int month = spend.getDate().toLocalDate().getMonth().getValue();

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try {
            hql = session.createQuery("from TotalSpend where month = :month and id = :id");
            hql.setParameter("month", month).setParameter("id", transactionId);
            List<TotalSpend> totalSpendList = hql.list();

            totalSpend = totalSpendList.get(0);
            double newAmount = totalSpend.getAmountSpend() - spend.getAmount();
            hql = session.createQuery("UPDATE TotalSpend spend SET spend.amountSpend = :newAmount WHERE spend.month = :month");
            hql.setParameter("newAmount", newAmount).setParameter("month", month);
            hql.executeUpdate();

            if (session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }
        }
        catch (HibernateException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
            if(session!=null)
                session.close();
        }

    }

    /**
     * Delete income from total spend
     * @param incoming represent the spend information
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public void deleteIncomeFromTotalSpend(Incoming incoming) throws CostManagementException {

        transactionId = incoming.getId();
        int month = incoming.getDate().toLocalDate().getMonth().getValue();

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try {

            hql = session.createQuery("from TotalSpend where month = :month and id = :id");
            hql.setParameter("month", month).setParameter("id", transactionId);
            List<TotalSpend> totalSpendList = hql.list();

            totalSpend = totalSpendList.get(0);
            double newAmount = totalSpend.getAmountIncome() - incoming.getAmount();
            hql = session.createQuery("UPDATE TotalSpend spend SET spend.amountIncome = :newAmount WHERE spend.month = :month");
            hql.setParameter("newAmount", newAmount).setParameter("month", month);
            hql.executeUpdate();


            if (session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }
        }
        catch (HibernateException e){
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }

        finally {
            if(session!=null)
                session.close();
        }

    }

    /**
     * Register a new user
     * @param user represent the information of the user
     * @return true for succeed , otherwise false
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public boolean registerUser(User user) throws CostManagementException {

        String userName = user.getUserName();

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try {
            hql = session.createQuery("from User where userName = :userName");
            hql.setParameter("userName", userName);
            List<User> userList = hql.list();

            /* Check if the user name is already exists in database
            by checking the size of the list. if the list is not empty
            there is a user with this user name and then we throw an exception.
            other wise we insert the user to database. */

            if(userList.isEmpty()){
                session.save(user);
                session.getTransaction().commit();
            }

            else{
                if(session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("User name is already exists",
                        new Throwable("The email that entered is already exists in DB"));
            }

            if(session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                if(session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }

            return true;

        } catch (HibernateException e) {
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }
         finally {
            if (session!=null){
                session.close();
            }
        }
    }

    /**
     * Authenticate the user
     * @param userName the email of the user
     * @param password the password of the user
     * @return true if succeed, otherwise false
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public boolean userAuthentication(String userName, String password) throws CostManagementException {

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try {
            hql = session.createQuery("from User where userName = :userName");
            hql.setParameter("userName", userName);
            List<User> userList = hql.list();

            /* Check if the user name is already exists in database
            by checking the size of the list. if the list is not empty
            there is a user with this user name and then we check the password
            if the password are match - authentication succeed
            other wise authentication failed. */

            if(!userList.isEmpty()){
                String passFromDB = userList.get(0).getPassword();
                if(passFromDB.equals(password)){
                    System.out.println("Authentication succeed");
                    return true;
                }
                else{
                    if(session.getTransaction().isActive())
                        session.getTransaction().rollback();
                    throw new CostManagementException("Username or password is incorrect",
                            new Throwable("Password is incorrect"));
                }
            }

            else{
                if(session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("Username or password is incorrect",
                        new Throwable("Wrong username"));
            }


        } catch (HibernateException e) {
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        }
        finally {
            if (session!=null){
                session.close();
            }
        }
    }

    /**
     * Get the current user that currently login
     * @param userName the email of the user
     * @return the id of the user
     * @throws CostManagementException if there is an exception in Hibernate
     */
    @Override
    public int getCurrentUser(String userName) throws CostManagementException {

        if(!session.isOpen()){
            session = factory.openSession();
            session.beginTransaction();
        }

        try {

            hql = session.createQuery("from User where userName = :userName");
            hql.setParameter("userName", userName);
            List<User> userList = hql.list();

            /* Check if the user name is already exists in database
            by checking the size of the list. if the list is not empty
            there is a user with this user name and then we check the password
            if the password are match - authentication succeed
            other wise authentication failed. */

            if(userList.isEmpty()){
                    if(session.getTransaction().isActive())
                        session.getTransaction().rollback();
                    throw new CostManagementException("Username is incorrect",
                            new Throwable("The user name is not exists"));
                }
            if(session.getTransaction().getStatus() == TransactionStatus.FAILED_COMMIT) {
                if(session.getTransaction().isActive())
                    session.getTransaction().rollback();
                throw new CostManagementException("Action failed",
                        new Throwable("The transaction was not committed"));
            }

            return userList.get(0).getId();

        } catch (HibernateException e) {
            if(session.getTransaction().isActive())
                session.getTransaction().rollback();
            throw new CostManagementException(e.getMessage());
        } finally {

            if (session!=null){
                session.close();
            }
        }
    }
}


