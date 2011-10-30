package de.abuwipp.importer;

import junit.framework.TestCase;

public class StoreGetValueWhenMessageWasImported extends TestCase {

	private Store store;
	private XmlMessage xmlMessage;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		store = new Store();
		xmlMessage = new XmlMessage("id2", "value2");
		store.importMessage(xmlMessage);
	}

	public void testReturnsImportedValue() throws Exception {
		assertEquals(xmlMessage.value, store.getValue(xmlMessage.id));
	}
}
