module com.anonim.tree_builder {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.google.gson;

    opens com.anonim.tree_builder to javafx.fxml;
    exports com.anonim.tree_builder;
    exports com.anonim.tree_builder.Canvas;
    opens com.anonim.tree_builder.Canvas to javafx.fxml;
}