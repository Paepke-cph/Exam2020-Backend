package entity;

import utils.EMF_Creator;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    @Column(name = "item_name", unique = true, nullable = false)
    private String name;
    @Column(name = "item_pricePrKilogram", nullable = false)
    private int pricePrKilogram;

    public Item() {
    }

    public Item(Long id, String name, int pricePrKilogram) {
        this.id = id;
        this.name = name;
        this.pricePrKilogram = pricePrKilogram;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPricePrKilogram() {
        return pricePrKilogram;
    }

    public void setPricePrKilogram(int pricePrKilogram) {
        this.pricePrKilogram = pricePrKilogram;
    }
}
