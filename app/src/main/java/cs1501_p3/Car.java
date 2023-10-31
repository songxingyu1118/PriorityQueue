package cs1501_p3;

public class Car implements Car_Inter
{
    private String VIN;
    private String Make;
    private String Model;
    private String Color;
    private int Price;
    private int Mileage;

    public Car(String VIN, String Make, String Model, int Price, int Mileage, String Color)
    {
        this.VIN = VIN;
        this.Make = Make;
        this.Model = Model;
        setPrice(Price);
        setMileage(Mileage);
        setColor(Color);
    }

    public String getVIN()
    {
        return this.VIN;
    }

    public String getMake()
    {
        return this.Make;
    }

    public String getModel()
    {
        return this.Model;
    }

    public String getColor()
    {
        return this.Color;
    }

    public int getPrice()
    {
        return this.Price;
    }

    public int getMileage()
    {
        return this.Mileage;
    }

    public void setMileage(int Mileage)
    {
        this.Mileage = Mileage;
    }

    public void setPrice(int Price)
    {
        this.Price = Price;
    }

    public void setColor(String Color)
    {
        this.Color = Color;
    }
}