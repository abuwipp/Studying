package de.abuwipp.importer;

public class Store {

	private XmlMessage xmlMessage;

	public void importMessage(XmlMessage xmlMessage) {
		this.xmlMessage = xmlMessage;
	}

	public String getValue(String string) {
		return xmlMessage.value;
	}

}
