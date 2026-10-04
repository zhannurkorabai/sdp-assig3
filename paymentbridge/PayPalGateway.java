package paymentbridge;

public class PayPalGateway implements PaymentGateway {

  @Override
  public void connect() {
    System.out.println(">> Redirecting to PayPal secure server...");
  }

  @Override
  public void processTransaction(String item, double amount) {
    System.out.println(
      ">> [PayPal]: Deducting $" + amount + " for " + item + " from wallet."
    );
  }
}
