package hellofx;

import java.io.IOException;
import java.text.ParseException;

import javax.naming.directory.InvalidAttributesException;

import org.curiousworks.BlueMarble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlueMarbleController {

	@FXML
	private ImageView image;

	@FXML
	private DatePicker datePicker;

	@FXML
	private Button enhanceButton;

	@FXML
	private Button colorAdjustButton;

	@FXML
	void enhanceImage(ActionEvent event) {
		BlueMarble blueMarble = new BlueMarble();
		blueMarble.setDate(datePicker.getValue().getYear() + "-0" + datePicker.getValue().getMonthValue() + "-"
				+ datePicker.getValue().getDayOfMonth());
		blueMarble.setEnhanced(true);
		image.setImage(new Image(blueMarble.getImage()));
	}

	@FXML
	void colorAdjust(ActionEvent event) throws IOException {
		BlueMarble blueMarble = new BlueMarble();
		blueMarble.setDate(datePicker.getValue().getYear() + "-0" + datePicker.getValue().getMonthValue() + "-"
				+ datePicker.getValue().getDayOfMonth());
		image.setImage(new Image(blueMarble.getImage()));
		ColorAdjust desaturate = new ColorAdjust();
		desaturate.setSaturation(-1);
		image.setEffect(desaturate);
	}

	@FXML
	void updateDate(ActionEvent event) throws InvalidAttributesException, ParseException {

		BlueMarble blueMarble = new BlueMarble();
		enhanceButton.setVisible(false);
		blueMarble.setDate(datePicker.getValue().getYear() + "-0" + datePicker.getValue().getMonthValue() + "-"
				+ datePicker.getValue().getDayOfMonth());
		ColorAdjust normalize = new ColorAdjust();
		normalize.setSaturation(0);
		image.setEffect(normalize);
		if (blueMarble.hasEnhanced()) {
			enhanceButton.setVisible(true);
		}
		if (blueMarble.isPastDate()) {
			System.out.println("Date ok.");
			image.setImage(new Image(blueMarble.getImage()));
			colorAdjustButton.setVisible(true);
		} else {
			image.setImage(new Image(blueMarble.getSadImage()));
			throw new InvalidAttributesException("Invalid date");
		}

//		blueMarble.setDate("2018-0" + datePicker.getValue().getMonthValue() + "-" + datePicker.getValue().getDayOfMonth());
//		Image value = new Image(BlueMarble.getMostRecentImage());

	}

}
