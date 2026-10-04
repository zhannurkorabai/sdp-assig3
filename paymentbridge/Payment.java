package paymentbridge;

public abstract class Payment {

  protected PaymentGateway gateway;

  public Payment(PaymentGateway gateway) {
    if (gateway == null) {
      throw new IllegalArgumentException("PaymentGateway cannot be null");
    }
    this.gateway = gateway;
  }

  public void setGateway(PaymentGateway gateway) {
    if (gateway == null) {
      throw new IllegalArgumentException("PaymentGateway cannot be null");
    }
    this.gateway = gateway;
  }

  public abstract void makePayment(String item, double amount);
}
