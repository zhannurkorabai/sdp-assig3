package paymentbridge;

public class Main {

  public static void main(String[] args) {
    System.out.println("--- STEP 2 TEST ---");
    PaymentGateway stripe = new StripeGateway();
    stripe.connect();
    stripe.processTransaction("Burger", 10.0);
  }
}
