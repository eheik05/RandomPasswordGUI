package org.hcpss.pwdgen;

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

  private final Window WINDOW;
  
  public static void main(String[] args) {
    new App();
  }

  public App() {
    WINDOW = new Window(this);
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
      add.add(s);
  }

  public void setSub(String s) {
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
  }

  private void genPass() {
      password = "";
      for (int i = 0; i < length; i++) {
          password += dict.getRandomToken();
      }
  }
}