package model;
import java.util.*;

public class CodeBook {
   private HashMap<String, Collection<String>> codes;

   public CodeBook() {
   }

   public CodeBook(HashMap<String, Collection<String>> codes) {
      this.codes = codes;
   }

   public HashMap<String, Collection<String>> getCodes() {
      return codes;
   }

   public void setCodes(HashMap<String, Collection<String>> codes) {
      this.codes = codes;
   }
}