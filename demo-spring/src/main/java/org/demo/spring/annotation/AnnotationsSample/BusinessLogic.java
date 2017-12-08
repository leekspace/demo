/*
 * This Class demonstrates use of Todo annotation defined in Todo.java
 * 
 * @author Yashwant Golecha (ygolecha@gmail.com)
 * @version 1.0
 * 
 */

package org.demo.spring.annotation.AnnotationsSample;

import org.demo.spring.annotation.AnnotationsSample.Todo.Priority;
import org.demo.spring.annotation.AnnotationsSample.Todo.Status;

public class BusinessLogic {
    public BusinessLogic() {
        super();
    }
    
    public void compltedMethod() {
        System.out.println("This method is complete");
    }    
    
    @Todo(priority = Todo.Priority.HIGH)
    public void notYetStartedMethod() {

    }
    
    @Todo(priority = Todo.Priority.MEDIUM, author = "Uday",  status = Todo.Status.STARTED)
    public void incompleteMethod1() {
       
    }

    @Todo(priority = Todo.Priority.LOW, status = Todo.Status.STARTED )
    public void incompleteMethod2() {
   
    }
    
    @Todo(author="1",priority=Priority.HIGH,status=Status.NOT_STARTED)
    public void test(){
        
    }
}
