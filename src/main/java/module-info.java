module com.libsysfrontendcustomer.libsysfrontendcustomer {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.libsysfrontendcustomer.libsysfrontendcustomer to javafx.fxml;
	exports com.libsysfrontendcustomer.libsysfrontendcustomer;
}