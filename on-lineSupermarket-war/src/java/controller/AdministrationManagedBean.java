/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.CategoryManagementRemote;
import ejb.ProductHasCustomerCartManagementRemote;
import ejb.ProductManagementRemote;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import persistence.Product;

/**
 *
 * @author pablo
 */
@Named(value = "administrationManagedBean")
@SessionScoped
public class AdministrationManagedBean implements Serializable {

    //@EJB
    private ProductHasCustomerCartManagementRemote producthascustomerCartManagement;

    //@EJB
    private CategoryManagementRemote categoryManagement;

    //@EJB
    private ProductManagementRemote productManagement;

    private ArrayList<Product> products;

    private String productName;

    private String productDescription;

    private double price;

    private String category;

    private UploadedFile file;

    private double marketCost;

    private StreamedContent fileDownload;

    private StreamedContent chart;
    
    private InitialContext context;

    public InitialContext getContext() {
        return context;
    }

    public void setContext(InitialContext context) {
        this.context = context;
    }

    public AdministrationManagedBean() {
        
        context=callEJB();
    }
    
    private InitialContext callEJB(){
        InitialContext c=null; 
        try {
             c= new InitialContext();
            categoryManagement = (CategoryManagementRemote) c.lookup("java:global/On-lineSupermarketEJB/CategoryManagement");
            producthascustomerCartManagement = (ProductHasCustomerCartManagementRemote) c.lookup("java:global/On-lineSupermarketEJB/ProductHasCustomerCartManagement");
            productManagement = (ProductManagementRemote) c.lookup("java:global/On-lineSupermarketEJB/ProductManagement");
        } catch (Exception e) {};
        return c;
    }

    public StreamedContent getChart() {
        
        try {

            //Chart
            JFreeChart jfreechart = ChartFactory.createPieChart("Customers category preferences (products sold per category / total products sold)", createDataset(), true, true, false);
            File chartFile = new File("dynamichart");
            ChartUtilities.saveChartAsPNG(chartFile, jfreechart, 375, 300);
            chart = new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chart;
    }

    public double getMarketCost() {
        return marketCost;
    }

    public void setMarketCost(double marketCost) {
        this.marketCost = marketCost;
    }

    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Product> getProducts() {
        downloadProducts();
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void downloadProducts() {
        products=new ArrayList<Product>();
        try {
            List<Product> list=productManagement.findAllProducts();
            products.addAll(list);
        } catch (NullPointerException e) {
        };
    }

    public void addProductToDB() throws IOException {
        
        if (file != null) {
            if (productManagement.productAlreadyExists(productName)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(productName + " already exists!"));
            } else if (category.equals("Diary") || category.equals("Meat&Fish") || category.equals("Bakery") || category.equals("Fruit&Veg")) {
                byte[] bytes = IOUtils.toByteArray(file.getInputstream());
                Product newProduct = new Product();
                newProduct.setName(productName);
                newProduct.setDescription(productDescription);
                newProduct.setPrice(price);
                newProduct.setMarketCost(marketCost);
                newProduct.setImage(bytes);
                newProduct.setCategoryId(categoryManagement.findCategoryByName(category));
                productManagement.registerNewProduct(newProduct);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product added"));
                downloadProducts();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Category not valid!"));
            }
        }
       

    }

    public double getSalesReturn() {
        return producthascustomerCartManagement.calculateSalesReturn();
    }

    public double getCostOfGoods() {
        return producthascustomerCartManagement.calculateCostOfGoods();
    }

    public double getNetSales() throws NamingException {
        System.out.println("entraaa net saleeeeeeeeeeeeeeeeeeeeeeeees");
        System.out.println(producthascustomerCartManagement.calculateNetSales());
        double total=producthascustomerCartManagement.calculateNetSales();
        return total;
    }

    public StreamedContent getImageAsStreamedContent() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String prodName = context.getExternalContext().getRequestParameterMap().get("productName");
            Product prod = productManagement.findProductByName(prodName);
            //Student student = studentService.find(Long.valueOf(studentId));
            return new DefaultStreamedContent(new ByteArrayInputStream(prod.getImage()));
        }

    }

    public void onRowEdit(RowEditEvent event) {
        if (categoryManagement.categoryExists(((Product) event.getObject()).getCategoryId().getName())) {

            //System.out.println(((Product) event.getObject()).getCategoryId().getId());
            //System.out.println(((Product) event.getObject()).getCategoryId().s);
            //new File("/resources/images/products/cheese.png").renameTo(new File("/resources/images/products/"+((Product) event.getObject()).getName()+".png"));
            try {
                ((Product) event.getObject()).setCategoryId(categoryManagement.findCategoryByName(((Product) event.getObject()).getCategoryId().getName()));
            } catch (NullPointerException e) {
            };
            productManagement.editProduct((Product) event.getObject());

            FacesMessage msg = new FacesMessage("Product Edited");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            //reset products with the values before the user tried to change them
            downloadProducts();
            FacesMessage msg = new FacesMessage("Category: " + ((Product) event.getObject()).getCategoryId().getName() + " does not exist!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onRowCancel(RowEditEvent event) {
        productManagement.removeProduct((Product) event.getObject());
        downloadProducts();
        System.out.println(products.size()+"((((((((((((((((((((((((((((((((((");
        FacesMessage msg = new FacesMessage("Product deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
/*
    @PostConstruct
    public void init() {
        downloadProducts();
        try {

            //Chart
            JFreeChart jfreechart = ChartFactory.createPieChart("Customers category preferences (products sold per category / total products sold)", createDataset(), true, true, false);
            File chartFile = new File("dynamichart");
            ChartUtilities.saveChartAsPNG(chartFile, jfreechart, 375, 300);
            chart = new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Diary", (double) producthascustomerCartManagement.calculatePercentagesProductsSoldPerCategory().get("Diary"));
        dataset.setValue("Meat&Fish", (double) producthascustomerCartManagement.calculatePercentagesProductsSoldPerCategory().get("Meat&Fish"));
        dataset.setValue("Fruit&Veg", (double) producthascustomerCartManagement.calculatePercentagesProductsSoldPerCategory().get("Fruit&Veg"));
        dataset.setValue("Bakery", (double) producthascustomerCartManagement.calculatePercentagesProductsSoldPerCategory().get("Bakery"));

        return dataset;
    }

}
