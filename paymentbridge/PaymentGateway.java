package paymentbridge;

public interface PaymentGateway {
  void connect();
  void processTransaction(String item, double amount);
}
