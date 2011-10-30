package de.abuwipp.importer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class XmlMessage {

	private static final String ID_TAG = "id";
	private static final String VALUE_TAG = "value";
	String id;
	String value;

	public XmlMessage(String id, String value) {
		this.id = id;
		this.value = value;
	}

	public static XmlMessage read(InputStream in) {
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLEventReader eventReader;
			eventReader = inputFactory.createXMLEventReader(in);
			String id = null;
			String value = null;
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					String tag = event.asStartElement().getName()
							.getLocalPart();
					if (ID_TAG.equals(tag)) {
						event = eventReader.nextEvent();
						id = event.asCharacters().getData();
						continue;
					}
					if (VALUE_TAG.equals(tag)) {
						event = eventReader.nextEvent();
						value = event.asCharacters().getData();
						continue;
					}
				}
			}
			return new XmlMessage(id, value);
		} catch (XMLStreamException e) {
			throw new IllegalStateException(e);
		}
	}

	public InputStream asXml() {
		String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>"
				+ buildBlock(ID_TAG, id) + buildBlock(VALUE_TAG, value)
				+ "</root>";
		InputStream in = new ByteArrayInputStream(xmlString.getBytes());
		return in;
	}

	private String buildBlock(String tagName, String data) {
		return "<" + tagName + ">" + data + "</" + tagName + ">";
	}

}
