package paymentbridge;

public class SubscriptionPayment extends Payment {

  public SubscriptionPayment(PaymentGateway gateway) {
    super(gateway);
  }

  @Override
  public void makePayment(String item, double amount) {
    System.out.println("\n--- Setting up Subscription payment ---");
    gateway.connect();
    System.out.println(">> Registering recurring token for future billing...");
    gateway.processTransaction(item, amount);
    System.out.println(
      ">> Subscription has been actived! Next billing in 30 days."
    );
  }
}
