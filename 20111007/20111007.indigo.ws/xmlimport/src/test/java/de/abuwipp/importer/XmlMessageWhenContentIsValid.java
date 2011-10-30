package de.abuwipp.importer;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class XmlMessageWhenContentIsValid {
	private static List<XmlMessage> messages;

	@BeforeClass
	public static void given() {
		messages = Arrays.asList(new XmlMessage("id4", "value4"),
				new XmlMessage("otherId", "otherValue"));
	}

	@Test
	public void shouldReadFields() throws Exception {
		for (XmlMessage message : messages) {
			assertEquals(message.id, XmlMessage.read(message.asXml()).id);
			assertEquals(message.value, XmlMessage.read(message.asXml()).value);
		}
	}

}
