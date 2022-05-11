package org.hcpss.pwdgen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

class App {

  private final Dictionary CHARS = new Dictionary("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM");
  private final Dictionary NUMS = new Dictionary("1234567890");
  private final Dictionary SPECIALS = new Dictionary("(){}[]|!£$%^&*\"<>:;#~_-+=,@\\/.' ");
  private Dictionary dict = new Dictionary();
  private Dictionary add = new Dictionary();
  private Dictionary sub = new Dictionary();

  private int length = 10;
  private boolean doChars = false;
  private boolean doNums = false;
  private boolean doSpecials = false;
  private String password = "";
  private File dictSave;
  
  public static void main(String[] args) {
    new App();
  }

  public App() {
    try {
      dictSave = new File(this.getClass().getResource("/dict.dat").toURI());
      FileInputStream fis = new FileInputStream(dictSave);
      ObjectInputStream ois = new ObjectInputStream(fis);
      add = (Dictionary) ois.readObject();
      ois.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    new Window(this);
  }

  public void setDoChars(boolean bool) {
      doChars = bool;
  }
  public void setDoNums(boolean bool) {
    doNums = bool;
  }
  public void setDoSpecials(boolean bool) {
    doSpecials = bool;
  }

  public void setAdd(String s) {
      add.reset();
      add.add(s);
  }

  public void setSub(String s) {
      sub.reset();
      sub.add(s);
  }

  public void setLen(int l) {
      if (l >= 5) {
        length = l;
      }
  }

  public String getDict() {
      updateDict();
      return dict.toString();
  }

  public String getPass() {
      genPass();
      return password;
  }

  private void updateDict() {
      dict.reset();
      dict.add(add.getTokens());
      if (doChars) {
          dict.add(CHARS.getTokens());
      }
      if (doNums) {
          dict.add(NUMS.getTokens());
      }
      if (doSpecials) {
          dict.add(SPECIALS.getTokens());
      }
      dict.sub(sub.getTokens());
      try {
        FileOutputStream fos = new FileOutputStream(dictSave);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(dict);
        oos.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
  }

  private void genPass() {
      password = "";
      for (int i = 0; i < length; i++) {
          password += dict.getRandomToken();
      }
  }
}