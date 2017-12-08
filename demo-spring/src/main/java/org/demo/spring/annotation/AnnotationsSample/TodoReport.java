/*
 * This Class demonstrates use of annotations using reflection.
 * 
 * @author Yashwant Golecha (ygolecha@gmail.com)
 * @version 1.0
 * 
 */

package org.demo.spring.annotation.AnnotationsSample;

import java.lang.reflect.Method;

public class TodoReport {
    public TodoReport() {
        super();
    }

    public static void main(String[] args) {
        getTodoReportForBusinessLogic();
    }

    /**
     * 
     * 使用反射机制，人class得到注解的内容。
     */
    private static void getTodoReportForBusinessLogic() {
        
        Class businessLogicClass = BusinessLogic.class;
        for(Method method : businessLogicClass.getMethods()) {
            Todo todoAnnotation = (Todo)method.getAnnotation(Todo.class);
            if(todoAnnotation != null) {
                System.out.println(" Method Name : " + method.getName());
                System.out.println(" Author : " + todoAnnotation.author());
                System.out.println(" Priority : " + todoAnnotation.priority());
                System.out.println(" Status : " + todoAnnotation.status());
                System.out.println(" --------------------------- ");
            }
        }
    }
}
