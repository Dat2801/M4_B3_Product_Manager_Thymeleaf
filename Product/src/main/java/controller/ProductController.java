package controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.ProductService;

import javax.jws.WebParam;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    ProductService productService = new ProductService();

    @GetMapping("")
    public ModelAndView showList() {
        List<Product> listProduct = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("list", listProduct);
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView searchProduct(@RequestParam String search) {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Product> products = productService.findByName(search);
        modelAndView.addObject("list", products);
        return modelAndView;
    }

//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable int id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable int id, @ModelAttribute Product product) {
        product.setId(id);
        productService.edit(id, product);
        return new ModelAndView("list", "list", productService.findAll());
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("p", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@ModelAttribute Product p) {
        int id = productService.findAll().size();
        p.setId(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        productService.create(p);
        return modelAndView;
    }
    @GetMapping("delete/{id}")
    public ModelAndView deleteProduct(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        productService.delete(id);
        return modelAndView;
    }
}
