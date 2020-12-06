////////////////////////////////////////////////////////////////////
// [Alessio] [Trevisan] [1187399]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Throwable{

    private String except;

    public TakeAwayBillException(String e){
        this.except = e;
    }
    
    public String getException(){
        return except;
    }
}