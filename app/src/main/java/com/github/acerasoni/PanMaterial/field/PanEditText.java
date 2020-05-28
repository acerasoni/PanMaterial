package com.github.acerasoni.PanMaterial.field;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.textfield.TextInputEditText;

  /**
   * This class is my custom implementation of an EditText field purposely designed for credit card
   * numbers which, alongside the CardViewWatched class, inserts hyphens every four digits, accounting
   * for backspaces and edge cases.
   */
  public class PanEditText extends TextInputEditText {
    public PanEditText(Context context) {
      super(context);
    }

    public PanEditText(Context context, AttributeSet attrs) {
      super(context, attrs);
    }

    public PanEditText(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
    }

    {
      this.addTextChangedListener(
          new CardViewWatcher() {
            @Override
            protected void onTextChanged(String before, String old, String aNew, String after) {
              String completeOldText = before + old + after;
              String completeNewText = before + aNew + after;
              String updatedText;

              if (completeNewText.length() > completeOldText.length()) {
                // Adding a char
                updatedText = addHyphen(completeNewText);
              } else {
                // Deleting a char
                updatedText = deleteHyphen(completeNewText);
              }

              startUpdates(); // to prevent infinite loop.
              setText(updatedText);
              setSelection(updatedText.length());
              endUpdates();
            }
          });
    }
  }