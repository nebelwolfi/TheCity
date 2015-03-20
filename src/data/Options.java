package data;

import java.awt.Dimension;
import java.io.*;

public class Options {

	public boolean musicEnabled;
	public boolean sfxEnabled;
	private static final String OPTION_FILE = "settings.ini";
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;
	public Dimension resolution;

	public Options() {
		musicEnabled = true;
		sfxEnabled = true;
		load();
	}
	
	/**
	 * Save option-data.
	 */
	public void save() {
		updateOptionsFile();
	}

	/**
	 * Load option-data
	 */
	public void load() {
		loadOptionFile();
	}
	
	/**
	 * Loads data from the <code>OPTION_FILE</code>.
	 */
	public void loadOptionFile() {
		try {
			inputStream = new ObjectInputStream(new FileInputStream(
					OPTION_FILE));
			String[] temp = (String[]) inputStream.readObject();
			musicEnabled = temp[0].equals("true");
			sfxEnabled = temp[1].equals("true");
			resolution = new Dimension(Integer.parseInt(temp[2]),Integer.parseInt(temp[3]));
		} catch (FileNotFoundException e) {
			System.out.println("[Laad] FNF Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Laad] IO Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[Laad] CNF Error: " + e.getMessage());
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Laad] IO Error: " + e.getMessage());
			}
		}
	}
	
	/**
	 * Updates data in the <code>OPTION_FILE</code>.
	 */
	public void updateOptionsFile() {
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(
					OPTION_FILE));
			String[] temp = new String[10];
			temp[0] = musicEnabled?"true":"false";
			temp[1] = sfxEnabled?"true":"false";
			temp[2] = ""+(int)resolution.getWidth();
			temp[3] = ""+(int)resolution.getHeight();
			outputStream.writeObject(temp);
		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error: " + e.getMessage()
					+ ",the program will try and make a new file");
		} catch (IOException e) {
			System.out.println("[Update] IO Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}
}
