package paymentbridge;

public class StripeGateway implements PaymentGateway {

  @Override
  public void connect() {
    System.out.println(">> Connecting to Stripe API...");
  }

  @Override
  public void processTransaction(String item, double amount) {
    System.out.println(
      ">> [Stripe]: Processing charge of $" + amount + " for " + item
    );
  }
}
