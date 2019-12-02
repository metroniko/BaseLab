package reflection;

public class InjectorExeption extends Exception {
    InjectorExeption(Exception exep) {
        super(exep.getMessage());
    }
}

