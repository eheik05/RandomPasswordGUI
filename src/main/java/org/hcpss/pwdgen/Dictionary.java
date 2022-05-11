package org.hcpss.pwdgen;

import java.util.LinkedHashSet;
import java.io.Serializable;
import java.security.SecureRandom;

public final class Dictionary implements Serializable {
  private LinkedHashSet<Character> tokens = new LinkedHashSet<>();

  private transient SecureRandom rand = new SecureRandom();

  public Dictionary(String tokens) {
    this.tokens = stringToSet(tokens);
  }

  public Dictionary(LinkedHashSet<Character> tokens) {
    this.tokens = tokens;
  }

  public Dictionary() {}

  private LinkedHashSet<Character> stringToSet(String str) {
    LinkedHashSet<Character> result = new LinkedHashSet<>();
    for (char c : str.toCharArray()) {
      result.add(c);
    }
    return result;
  }

  public void add(String other) {
    tokens.addAll(stringToSet(other));
  }

  public void sub(String other) {
    tokens.removeAll(stringToSet(other));
  }

  public void add(LinkedHashSet<Character> other) {
    tokens.addAll(other);
  }

  public void sub(LinkedHashSet<Character> other) {
    tokens.removeAll(other);
  }

  public LinkedHashSet<Character> getTokens() {
    return tokens;
  }

  public String getRandomToken() {
    if (tokens.size() <= 0) {
      return "";
    }
    int item = rand.nextInt(tokens.size());
    return tokens.toArray(new Character[0])[item].toString();
  }

  public void reset() {
    tokens.clear();
  }

  @Override
  public String toString() {
    String result = "";
    for (Character ch : tokens) {
      result += ch;
    }
    return result;
  }
}