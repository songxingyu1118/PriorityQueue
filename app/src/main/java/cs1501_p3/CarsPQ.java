package cs1501_p3;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;

public class CarsPQ implements CarsPQ_Inter
{
    private Car[] PH; //This is heap for price(Price Heap).
    private Car[] MH; //This is heap for Mileage(Mileage Heap).
    private int index; //This index is pointing to the place which is waiting to be added.

    public CarsPQ()
    {
        this.PH = new Car[8];
        this.MH = new Car[8];
        this.index = 0;
    }

    public CarsPQ(String filename)
    {
        this();
        File f = new File(filename);
        Scanner scan = null;
        try {scan = new Scanner(f);}
        catch(Exception e) {}
        if(scan != null)
        {
            scan.nextLine();
            while(scan.hasNextLine())
            {
                String[] line = scan.nextLine().split(":");
                if(line.length == 6) this.add(new Car(line[0], line[1], line[2], Integer.parseInt(line[3]), Integer.parseInt(line[4]), line[5]));
            }
        }
    }

    private void resize()
    {
        Car[] NPH = new Car[PH.length * 2];
        Car[] NMH = new Car[MH.length * 2];
        for(int i = 0; i < PH.length; i++)
        {
            NPH[i] = PH[i];
            NMH[i] = MH[i];
        }
        this.PH = NPH;
        this.MH = NMH;
    }

    public void add(Car c) throws IllegalStateException
    {
        for(int i = 0; i < index; i++) if(PH[i].getVIN().equals(c.getVIN())) throw new IllegalStateException();
        PH[index] = c;
        MH[index] = c;
        if((++index) >= PH.length) resize();
        rankPrice(index - 1);
        rankMileage(index - 1);
    }

    public Car get(String vin) throws NoSuchElementException
    {
        for(int i = 0; i < index; i++) if(PH[i].getVIN().equals(vin)) return PH[i];
        throw new NoSuchElementException();
    }

    public void updatePrice(String vin, int newPrice) throws NoSuchElementException
    {
        for(int i = 0; i < index; i++) if(PH[i].getVIN().equals(vin))
        {
            PH[i].setPrice(newPrice);
            rankPrice(i);
            return;
        }
        throw new NoSuchElementException();
    }

    public void updateMileage(String vin, int newMileage) throws NoSuchElementException
    {
        for(int i = 0; i < index; i++) if(MH[i].getVIN().equals(vin))
        {
            MH[i].setMileage(newMileage);
            rankMileage(i);
            return;
        }
        throw new NoSuchElementException();
    }

    public void updateColor(String vin, String newColor) throws NoSuchElementException
    {
        for(int i = 0; i < index; i++) if(PH[i].getVIN().equals(vin))
        {
            PH[i].setColor(newColor);
            return;
        }
        throw new NoSuchElementException();
    }

    public void remove(String vin) throws NoSuchElementException
    {
        int PI = 0;
        int MI = 0;
        boolean meetP = false;
        boolean meetM = false;
        for(int i = 0; i < index; i++)
        {
            if(PH[i].getVIN().equals(vin))
            {
                meetP = true;
                PI = i;
            }
            if(MH[i].getVIN().equals(vin))
            {
                meetM = true;
                MI = i;
            }
            if(meetP && meetM) break;
        }
        if(meetP && meetM)
        {
            swap(PI, (index - 1), PH);
            swap(MI, (index - 1), MH);
            index--;
            rankPrice(PI);
            rankMileage(MI);
        }
        else throw new NoSuchElementException();
    }

    public Car getLowPrice()
    {
        if(index <= 0) return null;
        else return PH[0];
    }

    public Car getLowMileage()
    {
        if(index <= 0) return null;
        else return MH[0];
    }

    public Car getLowPrice(String make, String model)
    {
        Car getLowPrice = null;
        for(int i = 0; i < index; i++)
        {
            if(PH[i].getMake().equals(make) && PH[i].getModel().equals(model))
            {
                getLowPrice = PH[i];
                int p = 1;
                int q = 0;
                while(q < i)
                {
                    q += p;
                    p *= 2;
                }
                for(int o = i; o <= q && o < index; o++) if((PH[o].getPrice() < getLowPrice.getPrice()) && (PH[o].getMake().equals(make) && PH[o].getModel().equals(model))) getLowPrice = PH[o];
                break;
            }
        }
        return getLowPrice;
    }

    public Car getLowMileage(String make, String model)
    {
        Car getLowMileage = null;
        for(int i = 0; i < index; i++)
        {
            if(MH[i].getMake().equals(make) && MH[i].getModel().equals(model))
            {
                getLowMileage = MH[i];
                int p = 1;
                int q = 0;
                while(q < i)
                {
                    q += p;
                    p *= 2;
                }
                for(int o = i; o <= q && o < index; o++) if((MH[o].getMileage() < getLowMileage.getMileage()) && (MH[o].getMake().equals(make) && MH[o].getModel().equals(model))) getLowMileage = MH[o];
                break;
            }
        }
        return getLowMileage;
    }

    private void rankPrice(int i)
    {
        boolean up = false;
        while(i > 0 && PH[i].getPrice() < PH[parent(i)].getPrice())
        {
            up = true;
            swap(i, parent(i), PH);
            i = parent(i);
        }
        if(!up)
        {
            while(leftChild(i) < index || rightChild(i) < index)
            {
                if((leftChild(i) < index && rightChild(i) < index) && (PH[i].getPrice() > PH[leftChild(i)].getPrice() && PH[i].getPrice() > PH[rightChild(i)].getPrice()))
                {
                    if(PH[leftChild(i)].getPrice() < PH[rightChild(i)].getPrice())
                    {
                        swap(i, leftChild(i), PH);
                        i = leftChild(i);
                        continue;
                    }
                    else
                    {
                        swap(i, rightChild(i), PH);
                        i = rightChild(i);
                        continue;
                    }
                }
                if(leftChild(i) < index && PH[i].getPrice() > PH[leftChild(i)].getPrice())
                {
                    swap(i, leftChild(i), PH);
                    i = leftChild(i);
                    continue;
                }
                if(rightChild(i) < index && PH[i].getPrice() > PH[rightChild(i)].getPrice())
                {
                    swap(i, rightChild(i), PH);
                    i = rightChild(i);
                    continue;
                }
                if((leftChild(i) < index && rightChild(i) < index) && (PH[i].getPrice() < PH[leftChild(i)].getPrice() && PH[i].getPrice() < PH[rightChild(i)].getPrice())) break;
                if(leftChild(i) >= index && PH[i].getPrice() < PH[rightChild(i)].getPrice()) break;
                if(rightChild(i) >= index && PH[i].getPrice() < PH[leftChild(i)].getPrice()) break;
            }
        }
    }

    private void rankMileage(int i)
    {
        boolean up = false;
        while(i > 0 && MH[i].getMileage() < MH[parent(i)].getMileage())
        {
            up = true;
            swap(i, parent(i), MH);
            i = parent(i);
        }
        if(!up)
        {
            while(leftChild(i) < index || rightChild(i) < index)
            {
                if((leftChild(i) < index && rightChild(i) < index) && (MH[i].getMileage() > MH[leftChild(i)].getMileage() && MH[i].getMileage() > MH[rightChild(i)].getMileage()))
                {
                    if(MH[leftChild(i)].getMileage() < MH[rightChild(i)].getMileage())
                    {
                        swap(i, leftChild(i), MH);
                        i = leftChild(i);
                        continue;
                    }
                    else
                    {
                        swap(i, rightChild(i), MH);
                        i = rightChild(i);
                        continue;
                    }
                }
                if(leftChild(i) < index && MH[i].getMileage() > MH[leftChild(i)].getMileage())
                {
                    swap(i, leftChild(i), MH);
                    i = leftChild(i);
                    continue;
                }
                if(rightChild(i) < index && MH[i].getMileage() > MH[rightChild(i)].getMileage())
                {
                    swap(i, rightChild(i), MH);
                    i = rightChild(i);
                    continue;
                }
                if((leftChild(i) < index && rightChild(i) < index) && (MH[i].getMileage() < MH[leftChild(i)].getMileage() && MH[i].getMileage() < MH[rightChild(i)].getMileage())) break;
                if(leftChild(i) >= index && MH[i].getMileage() < MH[rightChild(i)].getMileage()) break;
                if(rightChild(i) >= index && MH[i].getMileage() < MH[leftChild(i)].getMileage()) break;
            }
        }
    }

    private void swap(int i, int p, Car[] h)
    {
        Car temp = h[p];
        h[p] = h[i];
        h[i] = temp;
    }

    private static int parent(int i) {return (int)(((float)i - 1.0) / 2.0);}

    private static int leftChild(int i) {return ((2 * i) + 1);}

    private static int rightChild(int i) {return ((2 * i) + 2);}
}