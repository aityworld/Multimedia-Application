package com.mul.laf;

import com.mul.laf.EaSynthPopup;
import java.awt.Component;

import javax.swing.Popup;
import javax.swing.PopupFactory;

public class EaSynthPopupFactory extends javax.swing.PopupFactory {
	
	private static EaSynthPopupFactory popupFactory = new EaSynthPopupFactory();

	private static PopupFactory backupPopupFactory;

	public static void install() {
		if (backupPopupFactory == null) {
			backupPopupFactory = getSharedInstance();
			setSharedInstance(popupFactory);
		}
	}

	public static void uninstall() {
		if (backupPopupFactory == null) {
			return;
		}
		setSharedInstance(backupPopupFactory);
		backupPopupFactory = null;
	}
	
	public Popup getPopup(Component owner, Component contents, int ownerX, int ownerY) {
		final Popup realPopup = super.getPopup(owner, contents, ownerX, ownerY);
		return new EaSynthPopup(owner, contents, ownerX, ownerY, realPopup);
	}
}