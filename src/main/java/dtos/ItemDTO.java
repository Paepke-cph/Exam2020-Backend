package dtos;

import entity.Item;

public class ItemDTO {
    private Long id;
    private String name;
    private int pricePrKilogram;

    public ItemDTO() {}

    public ItemDTO(Long id, String name, int pricePrKilogram) {
        this.id = id;
        this.name = name;
        this.pricePrKilogram = pricePrKilogram;
    }

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.pricePrKilogram = item.getPricePrKilogram();
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
