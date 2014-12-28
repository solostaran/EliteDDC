package fr.jodev.elite;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DateNumberSerializer extends StdSerializer<Long> {
	
	public DateNumberSerializer() {
		super(Long.class);
	}

	@Override
	public void serialize(Long date, JsonGenerator json, SerializerProvider provider)
			throws IOException, JsonGenerationException {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String out = df.format(new Date(date));
		json.writeString(out);
	}
	
	public static String getDate() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return df.format(new Date());
	}
}
