package com.payetonkawa.order.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateCommande;
    private Long clientId;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getDateCommande() { return dateCommande; }
    public void setDateCommande(Date dateCommande) { this.dateCommande = dateCommande; }
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
}
