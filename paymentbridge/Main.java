package paymentbridge;

public class Main {

  public static void main(String[] args) {
    PaymentGateway stripe = new StripeGateway();
    PaymentGateway paypal = new PayPalGateway();

    // initial payment with Stripe
    Payment myPurchase = new OneTimePayment(stripe);
    myPurchase.makePayment("Laptop", 300.00);

    // change to PayPal without changing abstraction
    System.out.println(
      "\n>> [!] User switches preferred payment method to PayPal..."
    );
    myPurchase.setGateway(paypal);
    myPurchase.makePayment("Wireless Mouse", 21.00);

    //  test subscription abstraction
    Payment netflixSub = new SubscriptionPayment(paypal);
    netflixSub.makePayment(">> Streaming service [1 Month]", 16.99);
  }
}
