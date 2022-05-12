module com.libsysfrontendcustomer.libsysfrontendcustomer {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.google.gson;

	exports com.libsysfrontendcustomer.libsysfrontendcustomer;
	opens com.libsysfrontendcustomer.libsysfrontendcustomer to javafx.fxml, com.google.gson;
}