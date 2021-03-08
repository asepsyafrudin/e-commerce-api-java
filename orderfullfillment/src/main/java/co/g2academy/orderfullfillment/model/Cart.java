package co.g2academy.orderfullfillment.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Cart implements Serializable {
 
    private Integer id;

    private User user;

    private String status;

    private Date transactionDate;
    
    private List<CartItems> cartsItems;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(Date date) {
        this.transactionDate = transactionDate;
    }

    public List<CartItems> getCartsItems() {
        return this.cartsItems;
    }

    public void setCartsItems(List<CartItems> cartsItems) {
        this.cartsItems = cartsItems;
    }


}
