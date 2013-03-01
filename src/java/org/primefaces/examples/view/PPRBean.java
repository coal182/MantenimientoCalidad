package org.primefaces.examples.view;  
  
import java.io.Serializable;  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
@ManagedBean
@RequestScoped  
public class PPRBean implements Serializable {  
  
    private String firstname;  
      
    public String getFirstname() {  
        return firstname;  
    }  
  
    public void setFirstname(String firstname) {  
        this.firstname = firstname;  
    }  
} 