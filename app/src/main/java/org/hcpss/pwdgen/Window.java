package org.hcpss.pwdgen;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Window extends JFrame{
    
  private final JLabel LABEL_CHARS = new JLabel("Characters");
  private final JLabel LABEL_NUMS = new JLabel("Numbers");
  private final JLabel LABEL_SPECIAL = new JLabel("Special");
  private final JLabel LABEL_SUB = new JLabel("Remove");
  private final JLabel LABEL_ADD = new JLabel("Add");
  private final JLabel LABEL_LEN = new JLabel("Length");
  private final JLabel LABEL_DICT = new JLabel("Dictionary");

  private final JButton BUTTON_PASS = new JButton("Password");

  private final JTextArea TEXT_SUB = new JTextArea();
  private final JTextArea TEXT_ADD = new JTextArea();
  private final JTextArea TEXT_PASS = new JTextArea();
  private final JTextArea TEXT_DICT = new JTextArea();

  private final JScrollPane SCROLL_SUB = new JScrollPane(TEXT_SUB, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
  private final JScrollPane SCROLL_ADD = new JScrollPane(TEXT_ADD, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
  private final JScrollPane SCROLL_PASS = new JScrollPane(TEXT_PASS, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
  private final JScrollPane SCROLL_DICT = new JScrollPane(TEXT_DICT, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

  

  private final SpinnerNumberModel SPIN_LEN_MOD = new SpinnerNumberModel(10, 5, 100, 1);

  private final JSpinner SPIN_LEN = new JSpinner(SPIN_LEN_MOD);

  private final JCheckBox CHECK_CHARS = new JCheckBox();
  private final JCheckBox CHECK_NUMS = new JCheckBox();
  private final JCheckBox CHECK_SPECIALS = new JCheckBox();

  private final GridLayout LAYOUT = new GridLayout(9, 2);

  private final App APP;

  public Window(App app) {
    super("PwdGenGUI");
    APP = app;
    TEXT_ADD.setEditable(true);
    TEXT_SUB.setEditable(true);
    TEXT_PASS.setEditable(false);
    TEXT_DICT.setEditable(false);
    CHECK_CHARS.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            APP.setDoChars(CHECK_CHARS.isSelected());
            TEXT_DICT.setText(APP.getDict());
        }
    });
    CHECK_NUMS.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            APP.setDoNums(CHECK_NUMS.isSelected());
            TEXT_DICT.setText(APP.getDict());
        }
    });
    CHECK_SPECIALS.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            APP.setDoSpecials(CHECK_SPECIALS.isSelected());
            TEXT_DICT.setText(APP.getDict());
        }
    });
    TEXT_ADD.addCaretListener(new CaretListener() {
      @Override
      public void caretUpdate(CaretEvent e) {
        APP.setAdd(TEXT_ADD.getText());
        TEXT_DICT.setText(APP.getDict());
      }
    });
    TEXT_SUB.addCaretListener(new CaretListener() {
      @Override
      public void caretUpdate(CaretEvent e) {
        APP.setSub(TEXT_SUB.getText());
        TEXT_DICT.setText(APP.getDict());
      }
    });
    SPIN_LEN.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        APP.setLen(Integer.class.cast(SPIN_LEN.getValue()));
        TEXT_DICT.setText(APP.getDict());
      }
    });
    BUTTON_PASS.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            TEXT_PASS.setText(APP.getPass());
        }
    });

    setLayout(LAYOUT);
    add(LABEL_LEN);
    add(SPIN_LEN);
    add(LABEL_CHARS);
    add(CHECK_CHARS);
    add(LABEL_NUMS);
    add(CHECK_NUMS);
    add(LABEL_SPECIAL);
    add(CHECK_SPECIALS);
    add(LABEL_ADD);
    add(SCROLL_ADD);
    add(LABEL_SUB);
    add(SCROLL_SUB);
    add(LABEL_DICT);
    add(SCROLL_DICT);
    add(BUTTON_PASS);
    add(SCROLL_PASS);
    setSize(400, 350);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  }
}
