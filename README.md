# Assignment 3 - Bridge Pattern (Payment Processing System)

## Clean Code Principles Applied

### 1. Prefer Composition Over Inheritance (Avoid Class Explosion)

- **Why:** If we used inheriance to combine payment types and gateways, our class hierachy would grow exponentially. By using composition (the Bridge), we prevent combinatorial explosion and keep the system scalable.

> **Before:**

```java
// We would have to create a new class for EVERY combination
public class StripeOneTimePayment extends StripePayment { ... }
public class PayPalOneTimePayment extends PayPalPayment { ... }
public class StripeSubscriptionPayment extends StripePayment { ... }
```

> **After:**

```java
// The Abstraction (Payment) simply holds a reference to the Implementor (Gateway)
public abstract class Payment {
    protected PaymentGateway gateway;
}
// We only need 2 Payment classes and 2 Gateway classes, totally separate.
```

---

### 2. Clear Separation of responsibilities (Single Responsibility Principle)

- **Why:** Classes shouldn't mix high-level business rules with low-level infrastructure code. The Bridge pattern splits these perfectly.

> **Before:**

```java
public class Payment {
    public void processStripe(String item) {
        // Mixing business logic (subscription rules)
        // with low-level API connection logic inside one class
    }
}
```

> **After:**

```java
public class SubscriptionPayment extends Payment {
    // ONLY handles the business logic (recurring tokens, 30 days logic)
}

public class StripeGateway implements PaymentGateway {
    // ONLY handles the infrastructure logic (HTTP API connections)
}
```

---

### 3. Backward-compatible Design (Open/Closed Principle)

- **Why:** We should be able to add new features without changing existing code. Because of the Bridge pattern, adding a new payment gateway does not require touching the abstraction.

> **Before:**

```java
public void makePayment(String type) {
    if (type.equals("Stripe")) { connectStripe(); }
    else if (type.equals("PayPal")) { connectPayPal(); }
    // If we add Crypto, we have to open and modify this file
}
```

> **After:**

```java
// To add Crypto, we just create one new class.
// We DO NOT have to modify Payment, OneTimePayment, or SubscriptionPayment.
public class CryptoGateway implements PaymentGateway {
    public void connect() { ... }
    public void processTransaction(String item, double amount) { ... }
}
```

---

### 4. No duplicated logic (DRY - Don't Repeat Yourself)

- **Why:** Copying and pasting the exact same logic across multiple classes makes the system hard to maintain.

> **Before:**

```java
public class StripeSubscription {
    // Register token, charge first month, set 30-day timer
}
public class PayPalSubscription {
    // Exactly the same logic: Register token, charge first month, set 30-day timer
}
```

> **After:**

```java
public class SubscriptionPayment extends Payment {
    // The subscription logic is written EXACTLY ONCE here.
    // It delegates the actual charging to whatever gateway is passed in.
}
```

---

### 5. Validated construction (Fail Fast)

- **Why:** We should never let an object be created in a broken or incomplete state. Checking for `null` right inside the constructor stops bugs immediately before they cause hidden crashes later.

> **Before:**

```java
public Payment(PaymentGateway gateway) {
    // if gateway is null, it crashes later with a vague "NullPointerException"
    this.gateway = gateway;
}
```

> **After:**

```java
public Payment(PaymentGateway gateway) {
    if (gateway == null) {
        throw new IllegalArgumentException("PaymentGateway cannot be null");
    }
    this.gateway = gateway;
}
```
