package logic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Aleksandr Konstantinovitch
 * @version 1.0
 * @date 25/01/2015
 * {@link http://javaxblog.ru/article/java-hibernate-1/}
 * {@link http://www.developer.am/j2eetutorial/hibernate/?page=load_hibernate_cfg_xml_from_different_directory}
 * {@link http://perrohunter.com/org-hibernate-hibernateexception-hibernate-cfg-xml-not-found-in-intellij-project/}
 *********************************************************************
 * SQL> CREATE TABLE bookstore.Student
 * (
 *   id INT NOT NULL,
 *   name VARCHAR(255),
 *   age INT NOT NULL,
 *   PRIMARY KEY ( id )
 * );
 */
@Entity
@Table(name="Student")
public class Student {
    
    private Long id;    
    private String name;    
    private Long age;
    
    public Student(){
        name = null;
    }
    
    public Student(Student s){
        name = s.getName();
    }   
    
	@Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    public Long getId() {
        return id;
    }
    
	@Column(name="name")
    public String getName(){
        return name;
    }
    
	@Column(name="age")
    public Long getAge(){
        return age;
    }
    
    public void setId(Long i){
        id = i;     
    }
    
    public void setName(String s){
        name = s;
    }   
    
    public void setAge(Long l){
        age = l;
    }   
}