module com.example.as3_tp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires mysql.connector.j;

    //opens com.example.as3_tp to javafx.fxml;
    //exports com.example.as3_tp;
    exports com.example.as3_tp.Presentation;
    opens com.example.as3_tp.Presentation to javafx.fxml;
}