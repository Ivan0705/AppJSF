import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;


@ManagedBean(name = "category", eager = true)
@SessionScoped
public class Category {
    private String categoryName;
    private String id;

    public Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String s1_no) {
        this.id = s1_no;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public void addCategory() {
        try {
            Connection connection;
            DB_connection db_connection = new DB_connection();
            connection = db_connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO categories( category_name) VALUE('" + categoryName + "')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public ArrayList<Category> getAllCategory() {
        ArrayList listOfCategories = new ArrayList();
        Connection connection;
        try {
            DB_connection dbConnection = new DB_connection();
            connection = dbConnection.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM categories");
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryName(rs.getString("category_name"));
                category.setId(rs.getString("id"));
                listOfCategories.add(category);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listOfCategories;
    }

    public String deleteCategory() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String field_sl_no = params.get("action");
        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM categories WHERE id=?");
            ps.setString(1, field_sl_no);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/main.xhtml?faces-redirect=true";
    }
}
