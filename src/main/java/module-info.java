module com.libsysfrontendcustomer.libsysfrontendcustomer {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.google.gson;

	opens com.libsysfrontendcustomer.libsysfrontendcustomer.models to javafx.fxml, com.google.gson;
	exports com.libsysfrontendcustomer.libsysfrontendcustomer.models;
	opens com.libsysfrontendcustomer.libsysfrontendcustomer to javafx.fxml;
	exports com.libsysfrontendcustomer.libsysfrontendcustomer;
}