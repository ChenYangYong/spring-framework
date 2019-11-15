package yancy;


import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatePropertyEditor extends PropertyEditorSupport {
	private String format = "yyyy-MM-dd";

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			setValue(df.parse(text));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
