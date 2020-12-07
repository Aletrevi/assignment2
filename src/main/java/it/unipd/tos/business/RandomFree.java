////////////////////////////////////////////////////////////////////
// [Alessio] [Trevisan] [1187399]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;
import java.util.Random;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import it.unipd.tos.model.User;

public class RandomFree {

    private int num=10;
    private boolean eligible=false;
    Optional<Boolean> r = Optional.empty();
    List<User> AlreadySelected = new ArrayList<User>();
    public boolean chosen;

    public boolean checktime(LocalTime t) {
       boolean time_eligible=false;
       LocalTime inf=LocalTime.of(18,0);
       LocalTime sup=LocalTime.of(19,0);

    if(t.isBefore(sup) && t.isAfter(inf)) { 
    time_eligible=true;
     }
    return time_eligible;
    }
    
   public boolean checkage(User user){
          boolean age_eligible=false;
              if(user.getAge()<18)
                    {age_eligible=true;}
          return age_eligible;
    }
    public boolean is_eligible(User user, LocalTime time) {
         boolean is_eligible=false;
         if(checkage(user)&&checktime(time)) {
           is_eligible=true;
       }
       return is_eligible;
    }
    public boolean checkGift(User user, LocalTime time) {

     eligible=is_eligible(user,time);
     
     Random rnd = new Random();
     chosen=r.isPresent() ? r.get() : rnd.nextBoolean();
     
     if(chosen && eligible && (!AlreadySelected.contains(user)) && num != 0) {
        num--;
        AlreadySelected.add(user);
        return eligible;
    } 
     else 
       {return false;}
    }
    public void setROptionalValue(Boolean v) {
        r = Optional.of(v);
    }
}