package ru.itis.springbootdemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootdemo.dto.UserForm;
import ru.itis.springbootdemo.models.Product;
import ru.itis.springbootdemo.services.ProductService;

@Controller
public class ShopController {

    private ProductService productService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/shop")
    public String getShopPage(Model model){
        //model.add
        return "shop_page";
    }

    @RequestMapping(value="shop", method = RequestMethod.POST)
    public void postShopPage(@RequestParam("action") String type){
        switch (type) {
            case "name":
                Product product = objectMapper.readValue(req.getReader(), Product.class);
                String productsAsJson = objectMapper
                        .writeValueAsString(productService.getAllByName(product.getName()));
                break;
        }
    }

}
