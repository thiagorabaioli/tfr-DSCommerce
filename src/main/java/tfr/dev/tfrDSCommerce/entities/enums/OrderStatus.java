package tfr.dev.tfrDSCommerce.entities.enums;

import tfr.dev.tfrDSCommerce.entities.Order;

public enum OrderStatus {

    WAITING_PAYMENT(1, "Waiting Payment"),
    PAID(2, "Paid"),
    SHIPPED(3, "Shipped"),
    DELIVERED(4, "Delivered"),
    CANCELED(5, "Canceled");

    private int cod;
    private String description;

    private OrderStatus(int cod, String description){
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatus toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for(OrderStatus x : OrderStatus.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("invalid Id: " + cod);
    }
}
