package co.g2academy.StoreFront.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_CART")
public class Cart implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")    
    private Integer id;

    @ManyToOne(optional = false)
    private User user;
    
    @Column(name = "STATUS")
    private String status;

    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;
    
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "cart")
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

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public List<CartItems> getCartsItems() {
        return this.cartsItems;
    }

    public void setCartsItems(List<CartItems> cartsItems) {
        this.cartsItems = cartsItems;
    }


}
