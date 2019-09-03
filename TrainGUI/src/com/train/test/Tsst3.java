package com.train.test;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;

import javax.swing.JDialog;

public class Tsst3 {
	public static void main(String[] args) {
		/*
		// 最小化Eclipse窗口
		robot.mouseMove(1245, 14);
		robot.delay(1000);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.delay(100);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		*/
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int xMax = screenSize.width;
		int yMax = screenSize.height;
		System.out.println("Screen Resolution: " + xMax + " * " + yMax);
	}

	private static void mouseClick(int mouseKey) throws IllegalArgumentException {
		try {
			if (mouseKey != InputEvent.BUTTON1_MASK && mouseKey != InputEvent.BUTTON2_MASK && mouseKey != InputEvent.BUTTON3_MASK) {
				throw new IllegalArgumentException("Invalid mouse key code!");
			}
			else {
				Robot robot = new Robot();
				robot.mousePress(mouseKey);
				robot.delay(100);
				robot.mouseRelease(mouseKey);
			}
		} catch (AWTException ae) {
			ae.printStackTrace();
		}
	}
}
