package com.train.gui;

import javax.swing.JComboBox;

public class JComboBoxPlus<E> extends JComboBox<E> {
	
	private static final long serialVersionUID = 1L;

	/**
	 * ÕÅ´¨¿¸°Ñ×Ó
	 * @param item The item needed to be checked
	 * @return True if JComboBox contains this item
	 */
	public boolean isContainsItem(Object item) {
		int i = 0;
		for (i = 0; i < this.getItemCount(); i++) {
			if (this.getItemAt(i).equals(item)) {
				break;
			}
		}
		return i < this.getItemCount();
	}
	
}
