/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dashboard.components.model;

import query.tool.model.Product;
import query.tool.model.ProductCategory;

/**
 *
 * @author Admin
 */
public class MProduct {
    private Product product;
    private ProductCategory proCategory;

    public MProduct(Product product, ProductCategory proCategory) {
        this.product = product;
        this.proCategory = proCategory;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductCategory getProCategory() {
        return proCategory;
    }

    public void setProCategory(ProductCategory proCategory) {
        this.proCategory = proCategory;
    }
    
    
}
