////////////////////////////////////////////////////////////////////
// [Alessio] [Trevisan] [1187399]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;
import java.time.LocalTime;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.business.RandomFree;
public class Calc implements TakeAwayBill{

private boolean selected;
public RandomFree ra=new RandomFree();

public double getOrderPrice(List<MenuItem> itemsOrdered, User user, LocalTime time) throws TakeAwayBillException{


        double total = 0.0;
        double lessExpensive = Integer.MAX_VALUE;
        int numGelati = 0;

        for(MenuItem it: itemsOrdered){
        total += it.getPrice();
        
          if(it.getItemName() == MenuItem.items.Gelato){
     
          numGelati++;
          lessExpensive = lessExpensive > it.getPrice() ? it.getPrice() : lessExpensive;
            }
        }

        if(numGelati > 5)
        {total -= lessExpensive * 0.5;}
        
        double fiftycheck=0;
        for (int i=0;i<itemsOrdered.size();i++)
        {if (itemsOrdered.get(i).getItemName()!=MenuItem.items.Bevanda) {
             fiftycheck+=itemsOrdered.get(i).getPrice();
             }
        }
        if(fiftycheck > 50) { 
        total *= 0.9;
        }
        
        if(itemsOrdered.size() > 30)
        {throw new TakeAwayBillException("Error: Non è possibile ordinare più di 30 elementi nello stesso ordine");}
        
        
        if(total < 10)
          {total += 0.50;}
        
        selected=ra.checkGift(user, time);
        
        if (selected) {
            return 0;
        }
        return total;
   }
};