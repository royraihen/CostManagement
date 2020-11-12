package costmanagement.dm;

import java.sql.Date;


public class Incoming {


    private int id;
    private double amount;
    private Date date;
    private boolean permanentIncoming;
    private String comment;
    private int transactionId;

    public Incoming(){}

    public Incoming(int id, double amount, Date date, boolean permanentIncoming, String comment, int transactionId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.permanentIncoming = permanentIncoming;
        this.comment = comment;
        this.transactionId = transactionId;
    }


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

    public boolean isPermanentIncoming() {
        return permanentIncoming;
    }

    public void setPermanentIncoming(boolean permanentIncoming) {
        this.permanentIncoming = permanentIncoming;
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
        return "Incoming{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", permanentIncoming=" + permanentIncoming +
                ", comment='" + comment + '\'' +
                ", transactionId=" + transactionId +
                '}';
    }
}
