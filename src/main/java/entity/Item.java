package entity;

import dtos.ItemDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "items")
@NamedQueries({
        @NamedQuery(
                name = "Item.dropAll",
                query = "delete from Item"
        ),
        @NamedQuery(
                name = "Item.getAll",
                query = "select i from Item i"
        )
})
public class Item implements Serializable {
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

    public Item(ItemDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.pricePrKilogram = dto.getPricePrKilogram();
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
