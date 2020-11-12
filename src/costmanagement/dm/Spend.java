package costmanagement.dm;

import javax.persistence.Id;
import java.sql.Date;

public class Spend {


    private int id;
    private double amount;
    private Date date;
    private String category;
    private boolean permanentSpend;
    private String comment;
    @Id
    private int transactionId;

    public Spend(){}

    public Spend(int id, double amount, Date date, String category, boolean permanentSpend, String comment, int transactionId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.permanentSpend = permanentSpend;
        this.comment = comment;
        this.transactionId = transactionId;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isPermanentSpend() {
        return permanentSpend;
    }

    public void setPermanentSpend(boolean permanentSpend) {
        this.permanentSpend = permanentSpend;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "Spend{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", permanentSpend=" + permanentSpend +
                ", comment='" + comment + '\'' +
                ", transactionId=" + transactionId +
                '}';
    }
}
