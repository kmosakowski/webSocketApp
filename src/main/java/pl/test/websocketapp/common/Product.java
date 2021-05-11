package pl.test.websocketapp.common;

public enum Product {
    BTCUSD("BTC-USD"),
    BTCEUR("BTC-EUR"),
    ETHUSD("ETH-USD"),
    ETHEUR("ETH-EUR");

    private final String productId;

    Product(String productId){
        this.productId = productId;
    }

    public String getProductId(){
        return productId;
    }

    public static Product whtchOne(String product){
        for (Product pr : Product.values()) {
            if (pr.productId.equalsIgnoreCase(product)) {
                return pr;
            }
        }
        return null;
    }
}
