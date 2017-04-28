package controller;


import ejb.CategoryManagementRemote;
import ejb.CustomerManagementRemote;
import ejb.ProductManagementRemote;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.naming.InitialContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import persistence.Category;
import persistence.Customer;
import persistence.Product;

/**
 *
 * @author pablo
 */
@Named(value = "selectCategoryManagedBean")
@SessionScoped
public class SelectCategoryManagedBean implements Serializable {

    //@EJB
    private CategoryManagementRemote categoryManagement;
    private ProductManagementRemote productManagement;
    
    /**
     * Property boolean that saves true if the user is correctly logged in
     */
    private Boolean loggedIn;
    
    private Category categorySelected ;
    
    private ArrayList<Category> categories;
    
    private ArrayList<Product> productsInCategory;
    

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public ArrayList<Product> getProductsInCategory() {
        return productsInCategory;
    }

    public void setProductsInCategory(ArrayList<Product> productsInCategory) {
        this.productsInCategory = productsInCategory;
    }

    public Category getCategorySelected() {
        return categorySelected;
    }

    public void setCategorySelected(Category categorySelected) {
        this.categorySelected = categorySelected;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    /**
     * Creates a new instance of selectCategoryManagedBean
     */
    public SelectCategoryManagedBean() {
      try {
            InitialContext c = new InitialContext();
            categoryManagement = (CategoryManagementRemote) c.lookup("java:global/On-lineSupermarketEJB/CategoryManagement");
            productManagement = (ProductManagementRemote) c.lookup("java:global/On-lineSupermarketEJB/ProductManagement");
      } catch (Exception e) {};
    }
    
    public String displayCategoryPage(Category category){
        try{
            categorySelected=category;
            productsInCategory=categoryManagement.getProductCollectionGivenCategory(categorySelected.getName());
            //productsInCategory.addAll(categoryManagement.getProductsGivenCategory(categorySelected));
        } catch (NullPointerException e) {}
        return "storeCategory.xhtml";
    }
    
    public void updateCategoriesAvailable (){
        try{
            categories=new ArrayList<Category>();
            productsInCategory=new ArrayList<Product>();
            categories.addAll(categoryManagement.getAllCategories());
        } catch (NullPointerException e) {}
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
    
    
    
}
