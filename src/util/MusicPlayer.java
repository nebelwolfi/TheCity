package util;

import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import main.Main;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 * AboutState
 * 
 * @author Dennis Hasenstab
 * @version 1.0
 */
public class MusicPlayer {
	public static Clip clip;
	public static Music bg;
	private static String soundDir = "assets/sounds/";

	public static void playBG() {
		if (!Main.data.musicIsPlaying && Main.options.musicEnabled) {
			try {
			    bg = new Music("sounds/bg.xm");
			    bg.loop();
				Main.data.musicIsPlaying = true;
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void playRandomBG() {
		if (!Main.data.musicIsPlaying && Main.options.musicEnabled) {
			try {
			    Random rand = new Random();
				File bag = new File(soundDir);
				String ur = bag.list()[rand.nextInt(bag.list().length)];
			    bg = new Music(soundDir+ur);
			    bg.loop();
				Main.data.musicIsPlaying = true;
			    System.out.println("Now playing: "+soundDir+ur);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void stopBG() {
		bg.stop();
	}
	
	public static void playEffect(String eff) {
    	if (Main.options.sfxEnabled) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(eff).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
    	} catch(Exception ex) {
    		System.out.println("Error with playing sound.");
    		ex.printStackTrace();
    	}
    	}
	}
}
