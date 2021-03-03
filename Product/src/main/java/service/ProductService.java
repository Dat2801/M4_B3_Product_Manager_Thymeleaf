package service;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {
    public static Map<Integer, Product> list = new HashMap<>();

    static {
        list.put(1, new Product(1, "Iphone", 10000, "hang xin lam"));
        list.put(2, new Product(2, "Iphone 11", 20000, "hang xach tay"));
        list.put(3, new Product(3, "sp3", 30000, "mo ta sp3"));
        list.put(4, new Product(4, "sp4", 40000, "mo ta sp4"));
        list.put(5, new Product(5, "sp5", 50000, "mo ta sp5"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(list.values());
    }

    @Override
    public Product edit(int id, Product product) {
        list.put(id, product);
        return product;
    }

    @Override
    public Product create(Product product) {
        list.put(product.getId(), product);
        return product;
    }

    @Override
    public Product findById(int id) {
        return list.get(id);
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> listName = new ArrayList<>();
        for (Product product : list.values()) {
            if (product.getName().equals(name)) {
                listName.add(product);
            }
        }
        return listName;
    }

    @Override
    public void delete(int id) {
        list.remove(id);
    }
}
