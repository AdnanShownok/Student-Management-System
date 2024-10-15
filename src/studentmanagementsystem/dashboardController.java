/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
//import java.sql.Date;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import static javafx.stage.StageStyle.TRANSPARENT;

/**
 *
 * @author TUF GAMING
 */
public class dashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private FontAwesomeIcon close;

    @FXML
    private Button home_Btn;

    @FXML
    private Button AddStudents_Btn;

    @FXML
    private Button AvailableCourses_Btn;

    @FXML
    private Button GradesofStudents_Btn;

   @FXML
    private Button Signout_Btn;

    @FXML
    private AnchorPane home;

    @FXML
    private Label home_toltalEnrolled;

    @FXML
    private Label home_totalFemale;

    @FXML
    private Label home_totalMale;

    @FXML
    private BarChart<?, ?> home_totalEnrolledChart;

    @FXML
    private AreaChart<?, ?> home_totalFemaleChart;

    @FXML
    private LineChart<?, ?> home_totalMaleChart;

    @FXML
    private AnchorPane addStudents_form;

    @FXML
    private TextField addStudents_search;

    @FXML
    private TableView<studentData> addstudents_tableview;

    @FXML
    private TableColumn<studentData, String> addStudents_col_studentName;

    @FXML
    private TableColumn<studentData, String> addStudents_col_year;

    @FXML
    private TableColumn<studentData, String> addStudents_col_course;

    @FXML
    private TableColumn<studentData, String> addStudents_col_firstName;

    @FXML
    private TableColumn<studentData, String> addStudents_col_lastName;

    @FXML
    private TableColumn<studentData, String> addStudents_col_gender;

    @FXML
    private TableColumn<studentData, String> addStudents_col_birthDate;

    @FXML
    private TableColumn<studentData, String> addStudents_col_status;

    @FXML
    private TextField addStudents_studentName;

    @FXML
    private ComboBox<?> addStudents_year;

    @FXML
    private ComboBox<?> addStudents_course;

    @FXML
    private TextField addStudents_firstName;

    @FXML
    private TextField addStudents_lastName;

    @FXML
    private ComboBox<?> addStudents_gender;

    @FXML
    private DatePicker addStudents_birthDate;

    @FXML
    private ComboBox<?> addStudents_status;

    @FXML
    private ImageView addStudents_imageView;

    @FXML
    private Button addStudents_insertBtn;

    @FXML
    private Button addStudents_addBtn;

    @FXML
    private Button addStudents_updateBtn;

    @FXML
    private Button addStudents_deleteBtn;

    @FXML
    private Button addStudents_clearBtn;

    @FXML
    private AnchorPane availableCourse_form;

    @FXML
    private TextField availableCourse_course;

    @FXML
    private TextField availableCourse_description;

    @FXML
    private TextField availableCourse_degree;

    @FXML
    private Button availableCourse_addBtn;

    @FXML
    private Button availableCourse_updateBtn;

    @FXML
    private Button availableCourse_clearBtn;

    @FXML
    private Button availableCourse_deleteBtn;

    @FXML
    private TableView<courseData> availableCourse_tableview;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_course;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_description;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_degree;

    @FXML
    private AnchorPane studentGrade_form;

    @FXML
    private TextField studentGrade_studentName;

    @FXML
    private Label studentGrade_year;

    @FXML
    private Label studentGrade_course;

    @FXML
    private TextField studentGrade_firstSem;

    @FXML
    private TextField studentGrade_secondSem;

    @FXML
    private Button studentGrade_updateBtn;

    @FXML
    private Button studentGrade_clearBtn;

    @FXML
    private TableView<studentData> studentGrade_tableview;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_studentName;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_year;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_course;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_firstSem;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_secondSem;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_final;

    @FXML
    private TextField studentGrade_search;
    private Connection connect ;
    private PreparedStatement prepare;
    private Statement statement ;
    private ResultSet result ;
    private Image image;
    
    
    public void homeDisplayTotalEnrolledStudent()
    {
        String sql="SELECT COUNT(id) FROM student";
        connect= database.connectDb();
        int countEnrolled=0;
        try{
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();
            if(result.next())
            {
                countEnrolled= result.getInt("COUNT(id)");
            }
            home_toltalEnrolled.setText(String.valueOf(countEnrolled));
            
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }
    public void homeDisplayFemaleEnrolled()
    {
        String sql="SELECT COUNT(id) FROM student WHERE gender = 'Female' and status = 'Enrolled'";
        connect=database.connectDb();
        try
        {
           int countFemale=0;
           prepare=connect.prepareStatement(sql);
           result=prepare.executeQuery();
           if(result.next())
           {
               countFemale=result.getInt("COUNT(id)");
           }
           home_totalFemale.setText(String.valueOf(countFemale));
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }
    
    
    public void homeDisplayMaleEnrolled()
    {
         String sql= "SELECT COUNT(id) FROM student WHERE gender = 'Male' and status = 'Enrolled' ";
         connect=database.connectDb();
         try{
             
             int countMale = 0;
             prepare=connect.prepareStatement(sql);
             result=prepare.executeQuery();
             if(result.next())
             {
                 countMale=result.getInt("COUNT(id)");
             }
             home_totalMale.setText(String.valueOf(countMale));
             
             
         }
         catch(Exception e )
         {
             e.printStackTrace();
         }
    }
    
    public void homeDisplayTotalEnrolledChart()
    {
        home_totalEnrolledChart.getData().clear();
        String sql="SELECT date, COUNT(id) FROM student WHERE status = 'Enrolled' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";
        connect=database.connectDb();
        try{
            XYChart.Series chart= new XYChart.Series();
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();
            while(result.next())
            {
                chart.getData().add(new XYChart.Data(result.getString(1),result.getInt(2)));
            }
            home_totalEnrolledChart.getData().add(chart);
            
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        
    }
    public void homeDisplayFemaleEnrolledChart()
    {
        home_totalFemaleChart.getData().clear();
        String sql="SELECT date, COUNT(id) FROM student WHERE status = 'Enrolled' and gender = 'Female' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";
        connect= database.connectDb();
        try{
            XYChart.Series chart= new XYChart.Series();
           prepare=connect.prepareStatement(sql);
           result=prepare.executeQuery();
           while(result.next())
           {
               chart.getData().add(new XYChart.Data(result.getString(1),result.getInt(2)));
           }
           home_totalFemaleChart.getData().add(chart);
            
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }
    
    
    
    
    public void homeDisplayMaleEnrolledChart()
    {
        home_totalMaleChart.getData().clear();
        String sql="SELECT date, COUNT(id) FROM student WHERE status = 'Enrolled' and gender = 'Male' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";
        connect=database.connectDb();
        try{
            
            XYChart.Series chart= new XYChart.Series();
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();
             while(result.next())
             {
                 chart.getData().add(new XYChart.Data(result.getString(1),result.getInt(2)));
                 
             }
             home_totalMaleChart.getData().add(chart);
            
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }
    
    public void addStudentsAdd()
    {
        String InsertData= "INSERT INTO student"
                +"(studentNum,year,course,firstName,lastName,gender,birth,status,image,date) "
                +"VALUES(?,?,?,?,?,?,?,?,?,?) ";
        
        connect= database.connectDb();
        try{
            Alert alert;
            if(addStudents_studentName.getText().isEmpty()
                ||addStudents_year.getSelectionModel().getSelectedItem()==null
                ||addStudents_course.getSelectionModel().getSelectedItem()==null
                ||addStudents_firstName.getText().isEmpty()
                ||addStudents_lastName.getText().isEmpty()
                ||addStudents_gender.getSelectionModel().getSelectedItem()==null
                ||addStudents_birthDate.getValue()==null
                ||addStudents_status.getSelectionModel().getSelectedItem()==null
                ||getData.path==null || getData.path=="")
            {
                 alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message ");
                alert.setHeaderText(null);
                alert.setContentText("Please Fillup all the blank fields ");
                alert.showAndWait();
               
            }
            else
            {
                
                String checkData="SELECT studentNum FROM student WHERE studentNum= '"
                        +addStudents_studentName.getText()+"'";
                statement=connect.createStatement();
                result=statement.executeQuery(checkData);
                if(result.next())
                { 
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message ");
                alert.setHeaderText(null);
                alert.setContentText("Student # "+addStudents_studentName.getText()+" was already exist!!");
                alert.showAndWait();
                    
                }
                else
                {
                   prepare=connect.prepareStatement(InsertData);
                prepare.setString(1,addStudents_studentName.getText() );
                prepare.setString(2,(String)addStudents_year.getSelectionModel().getSelectedItem());
                prepare.setString(3, (String)addStudents_course.getSelectionModel().getSelectedItem());
                prepare.setString(4, addStudents_firstName.getText());
                prepare.setString(5, addStudents_lastName.getText());
                prepare.setString(6,(String)addStudents_gender.getSelectionModel().getSelectedItem());
                prepare.setString(7, String.valueOf(addStudents_birthDate.getValue()));
                prepare.setString(8, (String)addStudents_status.getSelectionModel().getSelectedItem());
                String uri=getData.path;
                uri= uri.replace("\\","\\\\");
                prepare.setString(9, uri);
                Date date= new Date();
                java.sql.Date sqlDate= new java.sql.Date(date.getTime());
                prepare.setString(10, String.valueOf(sqlDate));
                prepare.executeUpdate();
                
                String insertStudentGrade="INSERT INTO student_grade "
                        +"(studentNum,year,course,first_sem,second_sem,final) "
                        +"VALUES(?,?,?,?,?,?)";
                prepare=connect.prepareStatement(insertStudentGrade);
                 prepare.setString(1,addStudents_studentName.getText() );
                 prepare.setString(2,(String)addStudents_year.getSelectionModel().getSelectedItem() );
                 prepare.setString(3,(String)addStudents_course.getSelectionModel().getSelectedItem() );
                 prepare.setString(4,"0");
                 prepare.setString(5,"0");
                 prepare.setString(6,"0");
                 prepare.executeUpdate();
                
                
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message ");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added ");
                alert.showAndWait();
                addStudentsShowListData();
                addStudentsClear();
                }
                
                
            }
            
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
                
    }
    
    public void addStudentsUpdate()
    {
        String uri=getData.path;
        uri=uri.replace("\\", "\\\\");
        
        String updateData= "UPDATE student SET "
                +"year = '"+addStudents_year.getSelectionModel().getSelectedItem()
                +"', course = '"+addStudents_course.getSelectionModel().getSelectedItem()
                +"', firstName = '"+addStudents_firstName.getText()
                +"', lastName = '"+addStudents_lastName.getText()
                +"', gender = '"+addStudents_gender.getSelectionModel().getSelectedItem()
                +"', birth = '"+addStudents_birthDate.getValue()
                +"', status = '"+addStudents_status.getSelectionModel().getSelectedItem()
                +"', image = '"+uri+"' WHERE studentNum = '"+addStudents_studentName.getText()+"'";
        
        connect= database.connectDb();
        try{
            
            Alert alert;
            if(addStudents_studentName.getText().isEmpty()
                ||addStudents_year.getSelectionModel().getSelectedItem()==null
                ||addStudents_course.getSelectionModel().getSelectedItem()==null
                ||addStudents_firstName.getText().isEmpty()
                ||addStudents_lastName.getText().isEmpty()
                ||addStudents_gender.getSelectionModel().getSelectedItem()==null
                ||addStudents_birthDate.getValue()==null
                ||addStudents_status.getSelectionModel().getSelectedItem()==null
                ||getData.path==null || getData.path=="")
            {
                 alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message ");
                alert.setHeaderText(null);
                alert.setContentText("Please Fillup all the blank fields ");
               
                        alert.showAndWait();
                        
                        
                        
                
               
            }
            else
            {
                
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message ");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student #"+ addStudents_studentName.getText()+" ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addStudentsShowListData();
                    // TO CLEAR THE FIELDS
                    addStudentsClear();

                } else {
                    return;
                }
            }
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        
        
        
        
    }
    public void addStudentsDelete()
    {
        String deleteData="DELETE FROM student WHERE studentNum = '"
                +addStudents_studentName.getText()+"'";
        connect= database.connectDb();
        
        try{
            
            Alert alert;
            if(addStudents_studentName.getText().isEmpty()
                ||addStudents_year.getSelectionModel().getSelectedItem()==null
                ||addStudents_course.getSelectionModel().getSelectedItem()==null
                ||addStudents_firstName.getText().isEmpty()
                ||addStudents_lastName.getText().isEmpty()
                ||addStudents_gender.getSelectionModel().getSelectedItem()==null
                ||addStudents_birthDate.getValue()==null
                ||addStudents_status.getSelectionModel().getSelectedItem()==null
                ||getData.path==null || getData.path=="")
            {
                 alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message ");
                alert.setHeaderText(null);
                alert.setContentText("Please Fillup all the blank fields ");
               
                        alert.showAndWait();
                        
                        
                        
                
               
            }
            else
            {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Student # "+addStudents_studentName.getText()+" ?");
                Optional<ButtonType> option =alert.showAndWait();
                if(option.get().equals(ButtonType.OK))
                {
                    statement=connect.createStatement();
                    statement.executeUpdate(deleteData);
                    
                    String checkData="SELECT studentNum FROM student_grade "
                            +"WHERE studentNum = '"+addStudents_studentName.getText()+"'";
                    prepare=connect.prepareStatement(checkData);
                    result=prepare.executeQuery();
                    
                    if(result.next())
                    {
                        String deleteGrade="DELETE FROM student_grade WHERE "
                            +"studentNum = '"+addStudents_studentName.getText()+"'";
                        statement=connect.createStatement();
                        statement.executeUpdate(deleteGrade);
                        
                    }
                            
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addStudentsShowListData();
                    // TO CLEAR THE FIELDS
                    addStudentsClear();
                }
                else
                {
                    return ;
                }
                
            }
            
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }
    public void addStudentsClear()
    {
        addStudents_studentName.setText("");
         addStudents_year.getSelectionModel().clearSelection();
         addStudents_course.getSelectionModel().clearSelection();
         addStudents_firstName.setText("");
        addStudents_lastName.setText("");
        addStudents_gender.getSelectionModel().clearSelection();
        addStudents_birthDate.setValue(null);
        addStudents_status.getSelectionModel().clearSelection();
        addStudents_imageView.setImage(null);
        getData.path="";
    }
    
    public void addStudentsInsertImage()
    {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image File","*jpg","*png"));
        File file= open.showOpenDialog(home.getScene().getWindow());
        if(file!=null)
        {
             image = new Image(file.toURI().toString(),120,149,false,true);
             addStudents_imageView.setImage(image);
        }
        getData.path=file.getAbsolutePath();
        
       
    }
    
    
    
   public  void addStudentsSearch()
   {
       FilteredList<studentData>filter = new FilteredList<>(addStudentsListD,e->true);
       addStudents_search.textProperty().addListener((Observable,oldValue,newValue)->{
           
           filter.setPredicate(predicateStudentData->{
               if(newValue==null||newValue.isEmpty())
               {
                   return true ;
               }
               String searchkey=newValue.toLowerCase();
               if(predicateStudentData.getStudentNum().toString().contains(searchkey))
               {
                   return true;
                   
               }
               else if(predicateStudentData.getYear().toLowerCase().contains(searchkey))
               {
                   return true;
                   
               }
               else if(predicateStudentData.getCourse().toLowerCase().contains(searchkey))
               {
                   return true;
               }
               else if (predicateStudentData.getFirstName().toLowerCase().contains(searchkey))
               {
                   return true;
                   
               }
               else if(predicateStudentData.getLastName().toLowerCase().contains(searchkey))
               {
                   return true ;
               }
               else if(predicateStudentData.getGender().toLowerCase().contains(searchkey))
               {
                   return true;
               }
               else if(predicateStudentData.getBirth().toString().contains(searchkey))
               {
                   return true ;
               }
               else if (predicateStudentData.getStatus().toLowerCase().contains(searchkey))
               {
                   return true;
                   
               }
               else
               {
                     return false;
               }
             
           });
       });
       
       SortedList<studentData> sortlist= new SortedList<>(filter);
       sortlist.comparatorProperty().bind(addstudents_tableview.comparatorProperty());
       addstudents_tableview.setItems(sortlist);
       
   }
    public String[] yearlist={"First Year","Second Year","Third Year","Fourth Year"};
    public void addStudentsYearList()
    {
        List<String> yearl=new ArrayList<>();
        for(String data: yearlist)
        {
            yearl.add(data);
        }
        ObservableList Oblist= FXCollections.observableArrayList(yearl);
        addStudents_year.setItems(Oblist);
        
    }
    
    
    public void addStudentsCourseList()
    {
        String listCourse="SELECT * FROM course"; 
        connect= database.connectDb();
        try{
            ObservableList listC=FXCollections.observableArrayList();
            prepare=connect.prepareStatement(listCourse);
            result=prepare.executeQuery();
            while(result.next())
            {
                listC.add(result.getString("course"));
            }
            addStudents_course.setItems(listC);
            
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }
    private String[] genderlist={"Male","Female","Others"};
    public void addGenderList()
    {
        List<String> genderl=new ArrayList<>();
        for(String data: genderlist)
        {
            genderl.add(data);
        }
        ObservableList Oblist= FXCollections.observableArrayList(genderl);
        addStudents_gender.setItems(Oblist);
    }
    
    private String[] statusList={"Enrolled","Not Enrolled","Inactive"};
    public void addStatusList()
    {
        List<String> statusl=new ArrayList<>();
        for(String data: statusList)
        {
            statusl.add(data);
        }
        ObservableList Oblist= FXCollections.observableArrayList(statusl);
        addStudents_status.setItems(Oblist);
    }
    public ObservableList<studentData> addStudentsListData()
    {
        ObservableList<studentData> listStudents= FXCollections.observableArrayList();
        String sql="SELECT * FROM student";
        connect= database.connectDb();
        try{
            
            studentData studentD;
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();
            while(result.next())
            {
                studentD= new studentData(result.getInt("studentNum"),
                        result.getString("year"),
                        result.getString("course"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getDate("birth"),
                        result.getString("status"),
                        result.getString("image"));
                listStudents.add(studentD);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return listStudents;
        
    }
    
    private ObservableList<studentData> addStudentsListD;
    public void addStudentsShowListData()
    {
        addStudentsListD= addStudentsListData();
        addStudents_col_studentName.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudents_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addStudents_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addStudents_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addStudents_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addStudents_col_birthDate.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addStudents_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        addstudents_tableview.setItems(addStudentsListD);
        
        
        
    }
    public void addStudentsSelect()
    {
        studentData studentD= addstudents_tableview.getSelectionModel().getSelectedItem();
        int num=addstudents_tableview.getSelectionModel().getSelectedIndex();
        if((num-1)<-1)
        {
            return;
        }
        addStudents_studentName.setText(String.valueOf(studentD.getStudentNum()));
        addStudents_firstName.setText(studentD.getFirstName());
        addStudents_lastName.setText(studentD.getLastName());
        addStudents_birthDate.setValue(LocalDate.parse(String.valueOf(studentD.getBirth())));
        String uri="file:" + studentD.getImage();
        image = new Image(uri,120,149,false,true);
        addStudents_imageView.setImage(image);
        
        getData.path=studentD.getImage();
        
    }
    public void availableCourseAdd()
    {
        String insertdata="INSERT INTO course(course,description,degree) VALUES(?,?,?)";
        connect=database.connectDb();
        try{
            
            Alert alert;
            if(availableCourse_course.getText().isEmpty()||availableCourse_description.getText().isEmpty()||availableCourse_degree.getText().isEmpty())
            {
                alert= new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blank fills ");
                alert.showAndWait();
            }
            else
            {
                String checkData="SELECT course From course WHERE course = '" + availableCourse_course.getText()+"'";
                statement=connect.createStatement();
                result =statement.executeQuery(checkData);
                
                
                if(result.next())
                {
                    alert= new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Course "+ availableCourse_course.getText()+"was already exist!");
                alert.showAndWait();
                }
                else
                {
                    prepare =connect.prepareStatement(insertdata);
                    prepare.setString(1, availableCourse_course.getText());
                    prepare.setString(2, availableCourse_description.getText());
                    prepare.setString(3, availableCourse_degree.getText());
                    prepare.executeUpdate();
                alert= new Alert(AlertType.INFORMATION);
                alert.setTitle("Informtion Message");
                alert.setHeaderText(null);
                alert.setContentText("Sucessfully Added! ");
                alert.showAndWait();
                availableCourseShowListData();
                availableCourseClear();
                }
            }
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }
    
    public void availableCourseUpdate()
    {
        String updateData=" UPDATE course SET description = '"
                +availableCourse_description.getText()+"', degree= '"
                +availableCourse_degree.getText()+"' WHERE course = '"
                +availableCourse_course.getText()+"'";
        connect =database.connectDb();
        try{
            Alert alert;
            
            if(availableCourse_course.getText().isEmpty()||availableCourse_description.getText().isEmpty()||availableCourse_degree.getText().isEmpty())
            {
                alert= new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blank fills ");
                alert.showAndWait();
            }
            else
            {  
                
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want UPDATE course "+ availableCourse_course.getText()+" ?");
                Optional<ButtonType>option=alert.showAndWait();
                if(option.get().equals(ButtonType.OK))
                {
                    
                
                statement=connect.createStatement();
                statement.executeUpdate(updateData);
              
            
                alert= new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated ");
                alert.showAndWait();
               availableCourseShowListData();
                availableCourseClear();
                }
                else
                {
                    return;
                }
            
            }
            
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        
        
        
        
        
        
    }
     public void availableCourseDelete()
     {
         String deleteData= "DELETE FROM course WHERE course = '"
         +availableCourse_course.getText()+"'";
         connect=database.connectDb();
         try{
             
             Alert alert;
             if(availableCourse_course.getText().isEmpty()||availableCourse_description.getText().isEmpty()||availableCourse_degree.getText().isEmpty())
            {
                alert= new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blank fills ");
                alert.showAndWait();
            }
            else
             {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want DELETE course "+ availableCourse_course.getText()+" ?");
                Optional<ButtonType>option=alert.showAndWait();
                if(option.get().equals(ButtonType.OK))
                {
                    
                
                statement=connect.createStatement();
                statement.executeUpdate(deleteData);
              
            
                alert= new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted ");
                alert.showAndWait();
                availableCourseShowListData();
                availableCourseClear();}
                else
                {
                    return;
                }
                 
                 
             }
         }
         catch(Exception e )
         {
             e.printStackTrace();
         }
         
     }
    public void availableCourseClear()
    {
        availableCourse_course.setText("");
        availableCourse_description.setText("");
        availableCourse_degree.setText("");
    }
    
    public ObservableList<courseData> availableCourseListData()
    {
        ObservableList<courseData> listData=FXCollections.observableArrayList();
        String sql="SELECT * FROM course";
        connect=database.connectDb();
        try{
            
          courseData courseD;
          prepare=connect.prepareStatement(sql);
          result=prepare.executeQuery();
          while(result.next())
          { 
              courseD= new courseData(result.getString("course"),result.getString("description"),result.getString("degree"));
              listData.add(courseD);
          }
          
              
          
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return listData;
    }
    
    public ObservableList<courseData> availablecourselist;
    public void availableCourseShowListData()
    {
        availablecourselist= availableCourseListData();
        availableCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        availableCourse_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        availableCourse_col_degree.setCellValueFactory(new PropertyValueFactory<>("degree"));
        availableCourse_tableview.setItems(availablecourselist);
        
        
    }
    public void availableCourseSelect()
    {
        courseData courseD=availableCourse_tableview.getSelectionModel().getSelectedItem();
        int num=availableCourse_tableview.getSelectionModel().getSelectedIndex();
        if((num-1)<-1)
        {
            return;
            
        }
        availableCourse_course.setText(courseD.getCourse());
        availableCourse_description.setText(courseD.getDescription());
        availableCourse_degree.setText(courseD.getDegree());
    }
    
    public void studentGradesUpdate()
    {
        double finalCheck1=0;
        double finalCheck2=0;
        String checkData= " SELECT * FROM student_grade WHERE studentNum = '"
                +studentGrade_studentName.getText()+"'";
       
        connect=database.connectDb();
        double finalresult=0;
        
        
        try{
            
            prepare=connect.prepareStatement(checkData);
            result=prepare.executeQuery();
            
            if(result.next())
            {
                finalCheck1=result.getDouble("first_sem");
                finalCheck2=result.getDouble("second_sem");
            }
            if(finalCheck1==0 ||finalCheck2==0)
            {
                finalresult=0;
            }
            else
            {
                finalresult=(Double.parseDouble(studentGrade_firstSem.getText())+Double.parseDouble(studentGrade_secondSem.getText()))/2;
            }
            String updateData= "UPDATE student_grade SET "
                +" year = '"+studentGrade_year.getText()
                +"', course = '"+studentGrade_course.getText()
                +"', first_sem = '"+studentGrade_firstSem.getText()
                +"', second_sem = '"+studentGrade_secondSem.getText()
                +"', final = '"+finalresult+"' WHERE studentNum = '"
                +studentGrade_studentName.getText()+"'";
            
            Alert alert;
            if(studentGrade_studentName.getText().isEmpty()||
                    studentGrade_year.getText().isEmpty()||
                    studentGrade_course.getText().isEmpty())
            {
                alert= new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fillup all the blank fields");
                alert.showAndWait();
            }
            else
            {
               alert= new Alert(AlertType.CONFIRMATION);
               alert.setTitle("Confirmation Message ");
               alert.setHeaderText(null);
               alert.setContentText("Are you sure you want to UPDATE student #: "+studentGrade_studentName.getText()+" ?");
               Optional<ButtonType> option =alert.showAndWait();
               if(option.get().equals(ButtonType.OK))
               {
                    statement=connect.createStatement();
                statement.executeUpdate(updateData);
                
                alert= new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!   ");
                alert.showAndWait();
                studentGradesShowListData();
                
               }
               else
               {
                   return ;
               }
                
               
            }
            
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }
    public void studentsGradeClear()
    {
        studentGrade_studentName.setText("");
        studentGrade_year.setText("");
        studentGrade_course.setText("");
        studentGrade_firstSem.setText("");
        studentGrade_secondSem.setText("");
         
    }
    public ObservableList<studentData> studentGradeListData()
    {
        
       ObservableList<studentData> listData= FXCollections.observableArrayList();
       String sql= "SELECT * FROM student_grade";
       connect= database.connectDb();
       try{
           
           studentData studentD;
           prepare=connect.prepareStatement(sql);
           result=prepare.executeQuery();
           while(result.next())
           {
               studentD=new studentData(result.getInt("studentNum"),
                       result.getString("year"),
                       result.getString("course"),
                       result.getDouble("first_sem"),
                       result.getDouble("second_sem"),
                       result.getDouble("final"));
           
           listData.add(studentD);
           }
           
       }
       
      catch(Exception e )
      {
          e.printStackTrace();
      }
      return listData;  
        
    }
    private ObservableList<studentData> studentGradeList;
    public void studentGradesShowListData()
    {
        studentGradeList=studentGradeListData();
        studentGrade_col_studentName.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        studentGrade_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        studentGrade_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        studentGrade_col_firstSem.setCellValueFactory(new PropertyValueFactory<>("firstsem"));
        studentGrade_col_secondSem.setCellValueFactory(new PropertyValueFactory<>("secondsem"));
        studentGrade_col_final.setCellValueFactory(new PropertyValueFactory<>("finals"));
        studentGrade_tableview.setItems(studentGradeList);
        
    }
    public void studentGradesSelect()
    {
        
        studentData studentD= studentGrade_tableview.getSelectionModel().getSelectedItem();
        int num=studentGrade_tableview.getSelectionModel().getSelectedIndex();
        if((num-1)<-1)
        {
            return;
        }
        studentGrade_studentName.setText(String.valueOf(studentD.getStudentNum()));
        studentGrade_year.setText(studentD.getYear());
        studentGrade_course.setText(studentD.getCourse());
        studentGrade_firstSem.setText(String.valueOf(studentD.getFirstsem()));
        studentGrade_secondSem.setText(String.valueOf(studentD.getSecondsem()));
        
    }
    
    public void studentGradesSearch()
    {
        
        FilteredList<studentData> filter= new FilteredList<>(studentGradeList,e->true);
        studentGrade_search.textProperty().addListener((ObservableValue<? extends String> Observable,String oldValue,String newValue)->{
            
            
            filter.setPredicate(predicateStudentData ->{
                
            if(newValue.isEmpty()||newValue==null)
            {
                return true;
            }
            String searchkey= newValue.toLowerCase();
            
            if(predicateStudentData.getStudentNum().toString().contains(searchkey))
            {
                
                return true;
            }
            else if(predicateStudentData.getYear().toLowerCase().contains(searchkey))
            {
               return true; 
            }
            else if(predicateStudentData.getCourse().toLowerCase().contains(searchkey))
            {
               return true; 
            }
            else
                
            {return false; }    
                
            
            
                
              
                
                
              
                
          
            });
            
            
        });
        
        SortedList<studentData> sortlist= new SortedList<>(filter);
        sortlist.comparatorProperty().bind(studentGrade_tableview.comparatorProperty());
        studentGrade_tableview.setItems(sortlist);
        
        }
    
    
    
    
    
    
    
    private double x = 0;
    private double y = 0;

    public void logout() throws IOException {

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                
                Signout_Btn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {

                    x = event.getSceneX();
                    y = event.getSceneY();

                });
                root.setOnMouseDragged((MouseEvent event) -> {

                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                    stage.setOpacity(.8);

                });
                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);

                });

                stage.initStyle(TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    

    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_Btn) {
            home.setVisible(true);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);

            home_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right,#3f82ae,#26bf7d)");
            AddStudents_Btn.setStyle("-fx-background-color:transparent");
            AvailableCourses_Btn.setStyle("-fx-background-color:transparent");
            GradesofStudents_Btn.setStyle("-fx-background-color:transparent");
            homeDisplayTotalEnrolledStudent();
            homeDisplayMaleEnrolled();
            homeDisplayFemaleEnrolled();
            homeDisplayMaleEnrolledChart();
            homeDisplayFemaleEnrolledChart();
            homeDisplayTotalEnrolledChart();
        } else if (event.getSource() == AddStudents_Btn) {
            home.setVisible(false);
            addStudents_form.setVisible(true);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);

            home_Btn.setStyle("-fx-background-color:transparent");
            AddStudents_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right,#3f82ae,#26bf7d);");
            AvailableCourses_Btn.setStyle("-fx-background-color:transparent");
            GradesofStudents_Btn.setStyle("-fx-background-color:transparent");
            addStudentsShowListData();
            addStudentsYearList();
            addGenderList();
            addStatusList();
            addStudentsCourseList();
            addStudentsSearch();

        } else if (event.getSource() == AvailableCourses_Btn) {
            home.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(true);
            studentGrade_form.setVisible(false);

            home_Btn.setStyle("-fx-background-color:transparent");
            AddStudents_Btn.setStyle("-fx-background-color:transparent");
            AvailableCourses_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right,#3f82ae,#26bf7d);");
            GradesofStudents_Btn.setStyle("-fx-background-color:transparent");
            
            availableCourseShowListData();

        } else if (event.getSource() == GradesofStudents_Btn) {
            home.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(true);

            home_Btn.setStyle("-fx-background-color:transparent");
            AddStudents_Btn.setStyle("-fx-background-color:transparent");
            AvailableCourses_Btn.setStyle("-fx-background-color:transparent");
            GradesofStudents_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right,#3f82ae,#26bf7d);");
            
            studentGradesShowListData();
            studentGradesSearch();

        }

    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        homeDisplayTotalEnrolledStudent();
        homeDisplayMaleEnrolled();
        homeDisplayFemaleEnrolled();
        homeDisplayMaleEnrolledChart();
        homeDisplayFemaleEnrolledChart();
        homeDisplayTotalEnrolledChart();
        addStudentsShowListData();
        addStudentsYearList();
        addGenderList();
        addStatusList();
        addStudentsCourseList();
        availableCourseShowListData();
        studentGradesShowListData();
    }

}
