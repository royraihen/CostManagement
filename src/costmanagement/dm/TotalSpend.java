package costmanagement.dm;

public class TotalSpend {
    private int id;
    private double amountSpend;
    private double amountIncome;
    private int year;
    private int month;


    public TotalSpend(){}

    public TotalSpend(int id, double amountSpend, double amountIncome, int year, int month) {
        this.id = id;
        this.amountSpend = amountSpend;
        this.amountIncome = amountIncome;
        this.year = year;
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmountSpend() {
        return amountSpend;
    }

    public void setAmountSpend(double amountSpend) {
        this.amountSpend = amountSpend;
    }

    public double getAmountIncome() {
        return amountIncome;
    }

    public void setAmountIncome(double amountIncome) {
        this.amountIncome = amountIncome;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }


    @Override
    public String toString() {
        return "TotalSpend{" +
                "id=" + id +
                ", amountSpend=" + amountSpend +
                ", amountIncome=" + amountIncome +
                ", year=" + year +
                ", month=" + month +
                '}';
    }
}
