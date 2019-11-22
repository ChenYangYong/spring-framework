package yancy;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

public class String2DateConverter  implements Converter<String, Date> {
	@Override
	public Date convert(String source) {
		System.out.println("进入到String2DateConverter，source="+source);
		try {
			return DateUtils.parseDate(source,new String [] { "yyyy-MM-dd"} );
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
