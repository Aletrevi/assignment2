////////////////////////////////////////////////////////////////////
// [Alessio] [Trevisan] [1187399]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;
import java.time.LocalTime;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.User;
import it.unipd.tos.model.MenuItem;
public interface TakeAwayBill {
  double getOrderPrice(List<MenuItem> itemsOrdered, User user, LocalTime time) throws TakeAwayBillException;
}