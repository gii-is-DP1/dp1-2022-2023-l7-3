package org.springframework.monopoly.property;

import java.util.List;
import java.util.Map;

public class AuxBuildings {
    
    private List<Boolean> hotels;
    private List<String> names;
    private List<Integer> quantity;
    static final Map<String, List<String>> propertiesByColor = initMap();

    private static StreetRepository streetRepository;

    public static Map<String, List<String>> initMap() {
        List<String> keys = streetRepository.findAllColor();
        for(String key: keys) {
            propertiesByColor.put(key, streetRepository.findByColor(key));
        }
        return propertiesByColor;
    }

    public List<Boolean> getHotels() {
        return hotels;
    }
    public void setHotels(List<Boolean> hotels) {
        this.hotels = hotels;
    }
    public List<String> getNames() {
        return names;
    }
    public void setNames(List<String> names) {
        this.names = names;
    }
    public List<Integer> getQuantity() {
        return quantity;
    }
    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }
    
}
