package paymentbridge;

public class OneTimePayment extends Payment {

  public OneTimePayment(PaymentGateway gateway) {
    super(gateway);
  }

  @Override
  public void makePayment(String item, double amount) {
    System.out.println("\n--- Initiating One-Time Payment ---");
    gateway.connect();
    gateway.processTransaction(item, amount);
    System.out.println(
      ">> Payment complete. The receipt has been sent to customer."
    );
  }
}
